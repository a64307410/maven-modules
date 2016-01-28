package com.lehman.commons.web;

/**
 * aop方式 sql操作保存到审计表
 */



import net.eoutech.webmin.commons.constant.EUConst;
import net.eoutech.webmin.commons.entity.TbAudit;
import net.eoutech.webmin.commons.utils.DateUtils;
import net.eoutech.webmin.syslog.service.AuditService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Aspect
@Component
public class ActionAuditAop extends HandlerInterceptorAdapter {
    @Autowired
    AuditService auditService;


    public static final String ACTION_INSERT = "I", ACTION_UPDATE = "U", ACTION_DELETE = "D";

    @Around( "execution(* org.springframework.jdbc.core.JdbcTemplate.update(..))" )
    public Object auditAspect( ProceedingJoinPoint pjp ) throws Throwable {
        Object[] args = pjp.getArgs();
        Object result = pjp.proceed();
        try {
            if ( args != null ) {
                String sql = ( String ) args[0];
                Object[] queryParams = null;
                if ( args.length > 1 ) {
                    try {
                        queryParams = ( Object[] ) args[1];
                    } catch ( Exception e ) {
                        e.printStackTrace();
                        return result;
                    }
                } else {
                    return result;
                }

                String action = getActionType( sql );
                String tableName = getTableName( sql, action );
                String filedsStr = getTableFileds( sql, queryParams, action );
                String sqlConditionStr = getSqlConditionStr( sql, queryParams, action );
                String[] pk = getTablePk( tableName, filedsStr, sqlConditionStr, action );

                TbAudit tbAudit = new TbAudit();
                tbAudit.setUserType( EUConst.AUDIT_UT_WEB_ADMIN );
                tbAudit.setIdxActionUser( UserUtils.getUserName() );
                tbAudit.setUserIP( ActionUtils.getRequest().getRemoteAddr() );
                tbAudit.setAction( action );
                tbAudit.setIdxTableName( tableName );
                tbAudit.setFields( filedsStr );
                tbAudit.setResult( ( int ) result );
                tbAudit.setTbKeyName( pk[0] );
                tbAudit.setIdxTbKeyValue( pk[1] );
                tbAudit.setCondition( sqlConditionStr );

                //
                tbAudit.setActionDate( new Date() );
                auditService.insertAudit( tbAudit );
            }
        } catch ( Exception e ) {
            e.printStackTrace();
        }

        return result;
    }


    private String getActionType( String sql ) {
        String lowerCaseSql = sql.toLowerCase();
        if ( lowerCaseSql.startsWith( "insert into" ) ) {
            return ACTION_INSERT;
        } else if ( lowerCaseSql.startsWith( "update" ) ) {
            return ACTION_UPDATE;
        } else if ( lowerCaseSql.startsWith( "delete from" ) ) {
            return ACTION_DELETE;
        } else {
            return "-";
        }
    }


    public String getTableName( String sql, String action ) {
        Matcher matcher = null;
        if ( ACTION_INSERT.equals( action ) ) {
            matcher = Pattern.compile( "(INSERT INTO|insert into) (.*?)\\(" ).matcher( sql );
        } else if ( ACTION_UPDATE.equals( action ) ) {
            matcher = Pattern.compile( "(UPDATE|update) (.*?) " ).matcher( sql );
        } else if ( ACTION_DELETE.equals( action ) ) {
            matcher = Pattern.compile( "(DELETE FROM|delete from) (.*?) " ).matcher( sql );
        }
        if ( matcher != null && matcher.find() ) {
            return matcher.group( 2 );
        }
        return null;
    }

    /**
     * 获得属性字符串,格式例:name=lehman|age=22
     */
    public String getTableFileds( String sql, Object[] params, String action ) {
        String[] fields = null;
        // 切出属性数组.
        if ( ACTION_INSERT.equals( action ) ) {
            Matcher matcher = Pattern.compile( "(INSERT INTO|insert into) .*?\\((.*?)\\)" ).matcher( sql );
            if ( matcher.find() ) {
                fields = matcher.group( 2 ).split( "\\s{0,},\\s{0,}" );
            }
        } else if ( ACTION_UPDATE.equals( action ) ) {
            Matcher matcher = Pattern.compile( "(UPDATE.*?SET|update.*?set) (.*?) WHERE" ).matcher( sql );
            if ( matcher.find() ) {
                fields = matcher.group( 2 ).replaceAll( "\\s{0,}=\\s{0,}\\?\\s{0,}", "" ).split( "," );
            }
        }
        if ( fields == null ) {
            return "";
        }

        StringBuilder fieldsStr = new StringBuilder();
        for ( int i = 0; i < fields.length; i++ ) {
            Object param = params[i];
            if ( param.getClass() == Date.class ) {
                param = DateUtils.formatDate( ( Date ) param );
            }
            fieldsStr.append( fields[i] ).append( "=" ).append( param ).append( "|" );
        }
        fieldsStr.deleteCharAt( fieldsStr.length() - 1 );
        return fieldsStr.toString();
    }

    /**
     * 获得条件字符串
     */
    public String getSqlConditionStr( String sql, Object[] params, String action ) {
        if ( ACTION_INSERT.equals( action ) ) {
            return "";
        }
        for ( int i = 0; i < params.length; i++ ) {
            try {
                params[i] = params[i].toString();
            } catch ( Exception e ) {
                params[i] = "";
            }
        }
        String nSql = String.format( sql.replaceAll( "\\?", "%s" ), params );
        return nSql.replaceAll( ".*?(WHERE|where)\\s{0,}", "" );
    }

    /**
     * 获得表主键名和值
     *
     * @return String[] [0]=主键名,[1]=主键值
     */
    public String[] getTablePk( String tableName, String filedsStr, String sqlConditionStr, String action ) {
        String[] pk = null;
        if ( ACTION_UPDATE.equals( action ) || ACTION_DELETE.equals( action ) ) {
            pk = sqlConditionStr.split( "\\s{0,}=\\s{0,}" );
        } else if ( ACTION_INSERT.equals( action ) ) {
            String pkName = EntityTbUtils.getPkName( tableName );
            Object pkValue = null;
            //  从数据字符串中截取主键值
            Matcher matcher = Pattern.compile( pkName + "=(.*?)(\\||$)" ).matcher( filedsStr );
            if ( matcher.find() ) {
                pkValue = matcher.group( 1 );
            }
            // 未设置主键值,从mysql 中获取
            if ( pkValue == null ) {
                pkValue = auditService.getLastInsertId();
            }
            pk = new String[]{pkName, pkValue + ""};
        }
        if ( pk == null || pk.length > 2 ) {
            pk = new String[]{"", ""};
        }
        return pk;
    }


}
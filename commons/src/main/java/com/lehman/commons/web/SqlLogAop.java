package com.lehman.commons.web;

/**
 * aop方式 sql操作 日志保存
 */


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import com.lehman.commons.utils.JsonUtils;
import com.lehman.commons.utils.LogUtils;


@Aspect
@Component
public class SqlLogAop extends HandlerInterceptorAdapter {

    @Pointcut( "execution(* org.springframework.jdbc.core.JdbcTemplate.query*(..)) || execution(* org.springframework.jdbc.core.JdbcTemplate.update(..))" )
    public void queryMethod() {
        //该方法没实际作用，只是切面声明对象，声明一个切面的表达式
        //下面使用时，只需要写入这个表达式名(方法名)即可   等同于
        //@Before("anyMethod()") == @Before("execution(* com.liyd.sample.service.impl.*.*(..))")
        //可以是private修饰符，但是会有never used的警告，所以这里用了public
    }

    @Around( "queryMethod()" )
    public Object sqlLogAspect( ProceedingJoinPoint pjp ) throws Throwable {
        Object[] args = pjp.getArgs();

        Object result = pjp.proceed();

        try {
            if ( args != null ) {
                String querySql = ( String ) args[0];
                Object queryParams = null;
                if ( args.length > 1 ) {
                    queryParams = args[1];
                }
                LogUtils.dbg( "Preparing Sql:{0}\n    Parameters:{1},Result: {2}", querySql, JsonUtils.toJSONString( queryParams ), JsonUtils.toJSONString( result ) );
            }
        } catch ( Exception e ) {
            e.printStackTrace();
            LogUtils.dbg( "Preparing SqlError!!! param:{0}\n Result: {1}", JsonUtils.toJSONString( args ), JsonUtils.toJSONString( result ) );
        }

        return result;
    }
}
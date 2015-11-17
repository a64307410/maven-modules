package com.spring.jdbc.assistants.persistence;

import com.spring.jdbc.assistants.interceptor.PageControl;
import com.spring.jdbc.assistants.pager.Pager;
import com.spring.jdbc.assistants.utils.ClassUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.util.CollectionUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * jdbc操作dao
 *
 * Created by liyd on 3/3/15.
 */
public class JdbcDao {

    /** spring jdbcTemplate 对象 */
    protected JdbcOperations jdbcTemplate;

    /** 名称处理器，为空按默认执行 */
    protected NameHandler    nameHandler;

    /** rowMapper，为空按默认执行 */
    protected String         rowMapperClass;

    /** 数据库方言 */
    protected String         dialect;

    /**
     * 插入数据
     *
     * @param entity the entity
     * @param criteria the criteria
     * @return long long
     */
    private int insert(Object entity, Criteria criteria) {
        criteria = criteria == null? Criteria.create( entity.getClass() ) : criteria;
        final BoundSql boundSql = SqlAssembleUtils.buildInsertSql(entity, criteria,this.getNameHandler());
        return jdbcTemplate.update( boundSql.getSql(),boundSql.getParams().toArray() );
    }


    public int insert(Object entity) {
        return this.insert(entity, Criteria.create( entity.getClass() ));
    }


    public int insert(Criteria criteria) {
        return this.insert(null, criteria);
    }



    public int save(Object entity) {
        final BoundSql boundSql = SqlAssembleUtils.buildInsertSql(entity, null,
                this.getNameHandler());
        return jdbcTemplate.update(boundSql.getSql(), boundSql.getParams().toArray());
    }


    public int save(Criteria criteria) {
        final BoundSql boundSql = SqlAssembleUtils.buildInsertSql( null, criteria,
                this.getNameHandler() );
        return jdbcTemplate.update(boundSql.getSql(), boundSql.getParams().toArray());
    }



    public int update(Criteria criteria) {
        BoundSql boundSql = SqlAssembleUtils.buildUpdateSql( null, criteria, this.getNameHandler() );
        return jdbcTemplate.update(boundSql.getSql(), boundSql.getParams().toArray());
    }


    public int update(Object entity) {
        BoundSql boundSql = SqlAssembleUtils.buildUpdateSql( entity, Criteria.create( entity.getClass() ), this.getNameHandler() );
        return jdbcTemplate.update(boundSql.getSql(), boundSql.getParams().toArray());
    }


    public int delete(Criteria criteria) {
        BoundSql boundSql = SqlAssembleUtils.buildDeleteSql( null, criteria, this.getNameHandler() );
        return jdbcTemplate.update(boundSql.getSql(), boundSql.getParams().toArray());
    }


    public int delete(Object entity) {
        BoundSql boundSql = SqlAssembleUtils.buildDeleteSql(entity, Criteria.create( entity.getClass() ), this.getNameHandler());
        return jdbcTemplate.update(boundSql.getSql(), boundSql.getParams().toArray());
    }


    public int delete(Class<?> clazz, Object id) {
        BoundSql boundSql = SqlAssembleUtils.buildDeleteSql(clazz, this.getNameHandler(), id );
        return jdbcTemplate.update(boundSql.getSql(), boundSql.getParams().toArray());
    }


    public void deleteAll(Class<?> clazz) {
        String tableName = this.getNameHandler().getTableName(clazz);
        String sql = "TRUNCATE TABLE " + tableName;
        jdbcTemplate.execute( sql );
    }


    public < T > List< T > queryList( Criteria criteria) {
        BoundSql boundSql = SqlAssembleUtils.buildListSql( null, criteria, this.getNameHandler() );
        List<?> list = jdbcTemplate.query(boundSql.getSql(), boundSql.getParams().toArray(),
                this.getRowMapper(criteria.getEntityClass()));
        return (List<T>) list;
    }


    public <T> List<T> queryList(T entity) {
        BoundSql boundSql = SqlAssembleUtils.buildListSql(entity, Criteria.create( entity.getClass() ), this.getNameHandler());
        List<?> list = jdbcTemplate.query(boundSql.getSql(), boundSql.getParams().toArray(),
                this.getRowMapper(entity.getClass()));
        return (List<T>) list;
    }


    public <T> List<T> queryList(T entity, Criteria criteria) {
        BoundSql boundSql = SqlAssembleUtils.buildListSql(entity, criteria, this.getNameHandler());
        List<?> list = jdbcTemplate.query(boundSql.getSql(), boundSql.getParams().toArray(),
                this.getRowMapper(entity.getClass()));
        return (List<T>) list;
    }

    // 查询使用 sql 字符串, 条件 和排序使用criteria 构建
    public List<Map< String, Object > > queryList(String sql,Criteria criteria) {
        BoundSql boundSql = SqlAssembleUtils.buildWhereOrderSql( null, criteria, this.getNameHandler() );
        boundSql.setSql(   sql + boundSql.getSql() );
        //采用list方式查询，当记录不存在时返回null而不会抛出异常
        List< Map< String, Object > > list = jdbcTemplate.queryForList( boundSql.getSql(), boundSql.getParams().toArray() );
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        return list;
    }

    public <E> Page<E> queryPage(Class<E> clazz, Criteria criteria,int pageNumber,int pageSize) {
        //直接传入页码和每页条数
        PageControl.performPage( pageNumber, pageSize );
        queryList( criteria );

        Pager pager =  PageControl.getPager();
        List<E> list =  pager.getList( clazz );
        if ( CollectionUtils.isEmpty( list ) ) {
            // 创建page对象
            PageRequest pageRequest = new PageRequest( 0, 1 );
            return new PageImpl< E >( new ArrayList< E >( 1 ), pageRequest, 0 );
        }
        // 创建page对象
        PageRequest pageRequest = new PageRequest( pager.getCurPage() -1, pager.getItemsPerPage() );
        return new PageImpl< E >( list, pageRequest, pager.getItemsTotal() );
    }


    public int queryCount(Object entity, Criteria criteria) {
        BoundSql boundSql = SqlAssembleUtils.buildCountSql(entity, criteria, this.getNameHandler());
        return jdbcTemplate.queryForObject( boundSql.getSql(), boundSql.getParams().toArray(), Integer.class );
    }


    public int queryCount(Object entity) {
        BoundSql boundSql = SqlAssembleUtils.buildCountSql(entity, Criteria.create( entity.getClass() ), this.getNameHandler());
        return jdbcTemplate.queryForObject( boundSql.getSql(), boundSql.getParams().toArray() ,Integer.class);
    }


    public int queryCount(Criteria criteria) {
        BoundSql boundSql = SqlAssembleUtils.buildCountSql(null, criteria, this.getNameHandler());
        return jdbcTemplate.queryForObject( boundSql.getSql(), boundSql.getParams().toArray() ,Integer.class);
    }


    public <T> T get(Class<T> clazz, Object id) {
        BoundSql boundSql = SqlAssembleUtils.buildByIdSql(clazz, id, null, this.getNameHandler());

        //采用list方式查询，当记录不存在时返回null而不会抛出异常
        List<T> list = jdbcTemplate.query( boundSql.getSql(), this.getRowMapper( clazz ), id );
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        return list.iterator().next();
    }


    public <T> T get(Criteria criteria, Object id) {
        BoundSql boundSql = SqlAssembleUtils
                .buildByIdSql( null, id, criteria, this.getNameHandler() );

        //采用list方式查询，当记录不存在时返回null而不会抛出异常
        List<T> list = (List<T>) jdbcTemplate.query(boundSql.getSql(),
                this.getRowMapper(criteria.getEntityClass()), id);
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        return list.iterator().next();
    }


    public <T> T querySingleResult(T entity) {
        BoundSql boundSql = SqlAssembleUtils.buildQuerySql( entity, Criteria.create( entity.getClass() ), this.getNameHandler() );

        //采用list方式查询，当记录不存在时返回null而不会抛出异常
        List<?> list = jdbcTemplate.query(boundSql.getSql(), boundSql.getParams().toArray(),
                this.getRowMapper(entity.getClass()));
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        return (T) list.iterator().next();
    }


    public <T> T querySingleResult(Criteria criteria) {
        BoundSql boundSql = SqlAssembleUtils.buildQuerySql(null, criteria, this.getNameHandler());
        //采用list方式查询，当记录不存在时返回null而不会抛出异常
        List<?> list = jdbcTemplate.query(boundSql.getSql(), boundSql.getParams().toArray(),
                this.getRowMapper(criteria.getEntityClass()));
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        return (T) list.iterator().next();
    }
    // 查询使用 sql 字符串, 条件 和排序使用criteria 构建
    public Map< String, Object > querySingleResult(String sql,Criteria criteria) {
        BoundSql boundSql = SqlAssembleUtils.buildWhereOrderSql( null, criteria, this.getNameHandler() );
        boundSql.setSql( sql + boundSql.getSql() );
        //采用list方式查询，当记录不存在时返回null而不会抛出异常
        List< Map< String, Object > > list = jdbcTemplate.queryForList( boundSql.getSql(), boundSql.getParams().toArray() );
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        return list.iterator().next();
    }


    public byte[] getBlobValue(Class<?> clazz, String fieldName, Long id) {
        String tableName = nameHandler.getTableName(clazz);
        String primaryName = nameHandler.getPKName(clazz);
        String columnName = nameHandler.getColumnName(fieldName);
        String tmp_sql = "select t.%s from %s t where t.%s = ?";
        String sql = String.format(tmp_sql, columnName, tableName, primaryName);
        return jdbcTemplate.query(sql, new Object[] { id }, new ResultSetExtractor<byte[]>() {

            public byte[] extractData(ResultSet rs) throws SQLException, DataAccessException {
                if (rs.next()) {
                    return rs.getBytes(1);
                }
                return null;
            }
        });
    }

    /**
     * 获取rowMapper对象
     *
     * @param clazz
     * @return
     */
    protected <T> RowMapper<T> getRowMapper(Class<T> clazz) {

        if (StringUtils.isBlank(rowMapperClass)) {
            return BeanPropertyRowMapper.newInstance(clazz);
        } else {
            return (RowMapper<T>) ClassUtils.newInstance( rowMapperClass );
        }
    }

    /**
     * 获取名称处理器
     *
     * @return
     */
    protected NameHandler getNameHandler() {

        if (this.nameHandler == null) {
            this.nameHandler = new DefaultNameHandler();
        }
        return this.nameHandler;
    }

    public void setJdbcTemplate(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public JdbcOperations getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setNameHandler(NameHandler nameHandler) {
        this.nameHandler = nameHandler;
    }

    public void setRowMapperClass(String rowMapperClass) {
        this.rowMapperClass = rowMapperClass;
    }

    public void setDialect(String dialect) {
        this.dialect = dialect;
    }
}

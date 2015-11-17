package com.spring.jdbc.assistants.persistence;


import com.spring.jdbc.assistants.utils.NameUtils;
import org.apache.commons.lang.StringUtils;

import java.lang.reflect.Field;


/**
 * 默认名称处理handler
 * 
 * User: liyd
 * Date: 2/12/14
 * Time: 4:51 PM
 */
public class DefaultNameHandler implements NameHandler {
    /**
     * 获得表名
     * 根据实体名获取表名,第一个字母小写策略
     */
    public String getTableName(Class<?> entityClass) {
        //Java属性的骆驼命名法转换回数据库下划线“_”分隔的格式
        //return NameUtils.getUnderlineName( entityClass.getSimpleName() );

        String clsName = entityClass.getSimpleName();
        return clsName.substring( 0, 1 ).toLowerCase() + clsName.substring( 1, clsName.length() );
    }

    /**
     * 获得主键名
     * 根据表名获取主键名, 取第一个字段名.作为主键依据
     */
    public String getPKName(Class<?> entityClass) {
        Field[] fields = entityClass.getDeclaredFields();
        fields[0].setAccessible( true );// 调用private方法的关键一句话  //设置跳过访问检查.使之访问private域
        return getColumnName( fields[0].getName() );
    }

    /**
     * 获得字段名
     * 根据属性名获取列名
     *
     * @param fieldName
     * @return
     */
    public String getColumnName(String fieldName) {
        // String underlineName = NameUtils.getUnderlineName(fieldName);
        // return underlineName;
        return fieldName;
    }

    /**
     * 根据实体名获取主键值 自增类主键数据库直接返回null即可
     *
     * @param entityClass the entity class
     * @param dialect the dialect
     * @return pK value
     */
    public String getPKValue(Class<?> entityClass, String dialect) {
        if (StringUtils.equalsIgnoreCase(dialect, "oracle")) {
            //获取序列就可以了，默认seq_加上表名为序列名
            String tableName = this.getTableName(entityClass);
            return String.format("SEQ_%s.NEXTVAL", tableName);
        }
        return null;
    }
}

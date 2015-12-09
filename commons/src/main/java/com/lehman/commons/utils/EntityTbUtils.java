package com.lehman.commons.utils;



import com.spring.jdbc.assistants.persistence.DefaultNameHandler;

import java.util.HashMap;
import java.util.Set;

public class EntityTbUtils {
    // 实体的路径配置
    private static final String[] ENTITY_PACKAGE_CFG = {"com.frame.commons.entity", "net.eoutech.webmin.commons.entity"};
    private static final DefaultNameHandler defaultNameHandler = new DefaultNameHandler();


    private static HashMap< String, Class< ? > > getTb2EntityMap() {
        return GetTb2EntityMap.tb2EntityMap;
    }

    private static class GetTb2EntityMap {
        public static HashMap< String, Class< ? > > tb2EntityMap = getTb2EntityMap();

        /**
         * 获得,表名对应实体class 的映射map
         */
        private static HashMap< String, Class< ? > > getTb2EntityMap() {
            Set< Class< ? > > entityCls = ReflectUtils.getClassesByPack( ENTITY_PACKAGE_CFG );
            HashMap< String, Class< ? > > tb2EntMap = new HashMap<>();
            for ( Class< ? > cls : entityCls ) {
                String tName = cls.getSimpleName();
                tName = tName.substring( 0, 1 ).toLowerCase() + tName.substring( 1 );
                tb2EntMap.put( tName, cls );
            }
            return tb2EntMap;
        }
    }


    public static String getPkName( Class< ? > entityClass ) {
        return defaultNameHandler.getPKName( entityClass );
    }

    public static String getPkName( String tableName ) {
        return getPkName( getEntityClsByTbName( tableName ) );
    }

    public static Class< ? > getEntityClsByTbName( String tbName ) {
        return getTb2EntityMap().get( tbName );
    }


    public static void main( String[] args ) {
        HashMap< String, Class< ? > > map = getTb2EntityMap();
        for ( Class cls : map.values() ) {
            System.out.println( getPkName( cls ) );
        }
    }

}

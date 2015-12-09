package com.lehman.commons.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.PropertyFilter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.apache.commons.collections.CollectionUtils;

import java.util.HashSet;
import java.util.Set;

/**
 * @Description: 阿里巴巴的Json工具包 (继承自 com.alibaba.fastjson.JSON)
 * @author:雷建军
 * @Company:dakewe (www.dakewe.com)
 * @create:2014年6月23日 上午11:44:26
 * @update:2014年6月23日 上午11:44:26
 */
public class JsonUtils extends JSON {
    /**
     * @author 雷建军
     * @time 2014年6月23日 上午11:49:11
     * @Description: 扩展toJSONString，只序列化数组中指定的属性(解决hibernate延时加载等问题)
     * @param:propertys 要序列化的属性名
     * @return PropertyFilter throws
     */
    public static String toJSONStringEx( Object obj, String[] propertys ) {
        PropertyFilter filter = new PropertyFilterA( propertys );
        // 默认如果重用对象的话，会使用引用的方式进行引用对象。SerializerFeature.DisableCircularReferenceDetect:指明不使用引用对象
        return toJSONString( obj, filter, SerializerFeature.DisableCircularReferenceDetect );
    }

    /**
     * @author 雷建军
     * @time 2014年6月23日 上午11:49:11
     * @Description: 过滤JSON序列化时属性，不列化 数组中指定的属性(解决hibernate延时加载等问题)
     * @param:propertys 不序列化的属性名
     * @return PropertyFilter throws
     */
    public static String toJSONStringExOut( Object obj, String[] propertys ) {
        PropertyFilter filter = new PropertyFilterB( propertys );
        // 默认如果重用对象的话，会使用引用的方式进行引用对象。SerializerFeature.DisableCircularReferenceDetect:指明不使用引用对象
        return toJSONString( obj, filter, SerializerFeature.DisableCircularReferenceDetect );
    }

    /**
     * @author 雷建军
     * @time 2014-9-4 13:53:38
     * @Description: 将对象转换为JSONObject(实现了map,此对象的toString方法是直接序列化对象,所以用起来方便)
     * @return JSONObject
     */
    public static JSONObject toJSONObject( Object obj ) {
        // 默认如果重用对象的话，会使用引用的方式进行引用对象。SerializerFeature.DisableCircularReferenceDetect:指明不使用引用对象
        String jsonValue = toJSONString( obj );
        return parseObject( jsonValue );
    }

    /**
     * @author 雷建军
     * @time 2014-9-4 13:53:38
     * @Description:
     *               将对象转换为JSONObject(实现了map,此对象的toString方法是直接序列化对象,所以用起来方便),只转换数组中指定的属性
     *               (解决hibernate延时加载等问题)
     * @param:propertys 序列化的属性名
     * @return JSONObject
     */
    public static JSONObject toJSONObject( Object obj, String[] propertys ) {
        PropertyFilter filter = new PropertyFilterA( propertys );
        // 默认如果重用对象的话，会使用引用的方式进行引用对象。SerializerFeature.DisableCircularReferenceDetect:指明不使用引用对象
        String jsonValue = toJSONString( obj, filter, SerializerFeature.DisableCircularReferenceDetect );
        return parseObject( jsonValue );
    }

    /**
     * @author 雷建军
     * @time 2014-9-4 13:53:38
     * @Description:
     *               将对象转换为JSONObject(实现了map,此对象的toString方法是直接序列化对象,所以用起来方便),不转换数组中指定的属性
     *               (解决hibernate延时加载等问题)
     * @param:propertys 序列化的属性名
     * @return JSONObject
     */
    public static JSONObject toJSONObjectOut( Object obj, String[] propertys ) {
        PropertyFilter filter = new PropertyFilterB( propertys );
        // 默认如果重用对象的话，会使用引用的方式进行引用对象。SerializerFeature.DisableCircularReferenceDetect:指明不使用引用对象
        String jsonValue = toJSONString( obj, filter, SerializerFeature.DisableCircularReferenceDetect );
        return parseObject( jsonValue );
    }

    /**
     * @author 雷建军
     * @time 2014年11月3日 下午4:24:52
     * @Description: 序列化对象,并且打印到控制台,(经常用于调试)
     * @return String json
     */
    public static String toStringPrint( Object object ) {
        String json = JSON.toJSONString( object );
        System.out.println( json );
        return json;
    }

}

/**
 * @Description: 过滤JSON序列化时属性，只序列化 set中指定的属性
 * @author:雷建军
 * @Company:dakewe (www.dakewe.com)
 * @create:2014年6月23日 上午11:44:26
 * @update:2014年6月23日 上午11:44:26
 */
class PropertyFilterA implements PropertyFilter {
    private Set<String> propertys = new HashSet<String>();

    public PropertyFilterA( String[] propertys ) {
        CollectionUtils.addAll( this.propertys, propertys );
    }

    @Override
    public boolean apply( Object source, String name, Object value ) {
        if ( propertys.contains( name ) ) {
            return true;
        }
        return false;
    }
}

/**
 * @Description: 过滤JSON序列化时属性，不列化 set中指定的属性
 * @author:雷建军
 * @Company:dakewe (www.dakewe.com)
 * @create:2014年6月23日 上午11:44:26
 * @update:2014年6月23日 上午11:44:26
 */
class PropertyFilterB implements PropertyFilter {
    private Set<String> propertys = null;

    public PropertyFilterB( String[] propertys ) {
        Set<String> setPropertys = new HashSet<String>();
        CollectionUtils.addAll( setPropertys, propertys );
        this.propertys = setPropertys;
    }

    @Override
    public boolean apply( Object source, String name, Object value ) {
        if ( !propertys.contains( name ) ) {
            return true;
        }
        return false;
    }
}

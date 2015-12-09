package com.lehman.commons.utils;


import javax.servlet.http.Cookie;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class CookieUtils {
    /**
     * 平年 一年的秒数
     */
    public static int YEAR = 31536000;
    /**
     * 大月的秒数
     */
    public static int MONTH = 2678400;
    /**
     * 一天的秒数
     */
    public static int DATE = 86400;
    /**
     * 一个小时的秒数
     */
    public static int HOURS = 3600;

    /**
     * 默认构造,
     * ActionUtils.getRequest() = ActionUtils.getRequest();
     * ActionUtils.getResponse() = ActionUtils.getResponse();
     */
    public CookieUtils() {

    }


    /**
     * 根据名字移除cookie对象
     *
     * @return
     * @param: key cookie名字
     */
    public static void remove( String key ) {
        Cookie[] cookies = ActionUtils.getRequest().getCookies();
        for ( Cookie cookie : cookies ) {
            String name = cookie.getName();
            if ( name.compareTo( key ) == 0 ) {
                cookie.setPath( "/" );
                cookie.setMaxAge( 0 );
                ActionUtils.getResponse().addCookie( cookie );
                break;
            }
        }
    }

    public static int size() {
        return ActionUtils.getRequest().getCookies().length;
    }

    /**
     * 设置cookie
     *
     * @param: key cookie名字
     * @param: value cookie值
     * @param: maxAge cookie生命周期 以秒为单位
     */
    public static void put( String key, String value, int maxAge ) {
        try {
            // 关键点
            value = URLEncoder.encode( value, "UTF-8" );
        } catch ( UnsupportedEncodingException e ) {
        }
        Cookie cookie = new Cookie( key, value );
        cookie.setPath( "/" );
        if ( maxAge > 0 )
            cookie.setMaxAge( maxAge );
        ActionUtils.getResponse().addCookie( cookie );
    }

    /**
     * 根据名字获取cookie对象
     *
     * @param key cookie名字
     * @return
     */
    public static String get( String key ) {
        Cookie[] cookies = ActionUtils.getRequest().getCookies();
        if ( null != cookies ) {
            for ( Cookie cookie : cookies ) {

                if ( key.equals( cookie.getName() ) ) {
                    try {
                        // 关键点
                        cookie.setValue( URLDecoder.decode( cookie.getValue(), "UTF-8" ) );
                    } catch ( UnsupportedEncodingException e ) {
                    }
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    /**
     * 将cookie封装到Map里面
     *
     * @return
     */
    public static Map< String, Cookie > readCookieMap() {
        Map< String, Cookie > cookieMap = new HashMap< String, Cookie >();
        Cookie[] cookies = ActionUtils.getRequest().getCookies();

        if ( null != cookies ) {
            for ( Cookie cookie : cookies ) {
                try {
                    // 关键点
                    cookie.setValue( URLDecoder.decode( cookie.getValue(), "UTF-8" ) );
                } catch ( UnsupportedEncodingException e ) {
                }
                cookieMap.put( cookie.getName(), cookie );
            }
        }
        return cookieMap;
    }
}

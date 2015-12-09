package com.lehman.commons.utils;

import com.frame.commons.constant.FrameConst;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by Administrator on 2015/8/19.
 */
public class CommonUtils {

    private static ResourceBundle appCfg = null;


    public static void sleep( long time ) {
        try {
            Thread.sleep( time );
        } catch ( InterruptedException e ) {
            e.printStackTrace();
        }
    }

    // app config
    public static ResourceBundle getAppCfg() {
        return appCfg == null ? ResourceBundle.getBundle( "application" ) : appCfg;
    }

    public static String getRsAppCfg( String key ) {
        String cfgStr = null;
        try {
            cfgStr = getAppCfg().getString( key );
        } catch ( Exception ignored ) {
        }
        return cfgStr;
    }

    public static boolean rsAppContainsKey( String key ) {
        return getAppCfg().containsKey( key );
    }


    //   语言国际化文件
    public static ResourceBundle getLangRes() {
        HttpServletRequest request = ActionUtils.getRequest();
        HttpSession session = request.getSession();
        ResourceBundle lang = ( ResourceBundle ) session.getAttribute( FrameConst.LANGUAGE_RESOURCE_SESSION_KEY );
        if ( lang == null ) {
            Locale locale = RequestContextUtils.getLocaleResolver( request ).resolveLocale( request );
            lang = ResourceBundle.getBundle( "messages", locale );
            session.setAttribute( FrameConst.LANGUAGE_RESOURCE_SESSION_KEY, lang );
        }
        return lang;
    }

    public static String langStr( String key ) {
        String lanStr = null;
        try {
            lanStr = getLangRes().getString( key );
        } catch ( Exception ignored ) {
        }
        return lanStr;
    }

    public static boolean langContainsKey( String key ) {
        return getLangRes().containsKey( key );
    }


}

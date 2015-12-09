package com.lehman.commons.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2015/8/5.
 */
public class DateUtils {
    /**
     * ת���ַ��� "yyyy-MM-dd HH:mm:ss"  �� Date
     */
    public static Date parseDate( String strDate ) {
        SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
        try {
            return sdf.parse( strDate );
        } catch ( ParseException e ) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * ת��Date  ���ַ��� "yyyy-MM-dd HH:mm:ss"
     */
    public static String formatDate( Date date ) {
        SimpleDateFormat formatter = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
        return formatter.format( date );
    }

    /**
     * ת����ǰʱ��  ���ַ��� "yyyy-MM-dd HH:mm:ss"
     */
    public static String formatDate() {
        return formatDate( new Date() );
    }
}

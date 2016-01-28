package com.lehman.commons.utils;


import org.apache.log4j.Logger;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.*;


/**
 * @Description: 关于action的一些工具
 * @author:雷建军
 * @Company:dakewe (www.dakewe.com)
 * @create:2014年7月25日 上午10:13:05
 * @update:2014年7月25日 上午10:13:05
 */
public class ActionUtils {


    /**
     * @param millis 毫秒数 throws
     * @author 雷建军
     * @time 2014年8月18日 下午3:48:40
     * @Description: 延时方法, 用来调试,模拟网络延时,
     */
    public static void sleep( long millis ) {
        try {
            Thread.sleep( millis );// 括号里面的5000代表5000毫秒，也就是5秒，可以该成你需要的时间
        } catch ( InterruptedException e ) {
            e.printStackTrace();
        }
    }

    /**
     * @param jsonString void throws
     * @author 雷建军
     * @time 2014年6月4日 下午3:16:53
     * @Description: 在action中调用response.getWriter().write(text);。可作为 ajax返回值
     */
    public static void printWriter( String jsonString ) {
        HttpServletResponse response = getResponse();
        response.setContentType( "text/html;charset=utf-8" );
        response.setHeader( "Cache-Control", "no-cache" ); // HTTP 1.1
        response.setHeader( "Pragma", "no-cache" ); // HTTP 1.0
        response.setDateHeader( "Expires", 0 ); // prevents caching at the proxy
        // server
        PrintWriter out = null;
        try {
            out = response.getWriter();
            out.write( jsonString );
        } catch ( IOException e ) {
            e.printStackTrace();
        } finally {
            if ( null != out ) {
                out.flush();
                out.close();
            }
        }
    }

    /**
     * ajax返回的返回值。value:返回的内容
     */
    public static < T > void ajaxReturn( T value ) {
        String resValue = value.toString();
        printWriter( resValue );
    }

    /**
     * ajax返回的返回值
     */
    public static void ajaxError(String message ) {
        ajaxRestObject( 300, message );
    }
    public static void ajaxError(String message, String dataJSON  ) {
        ajaxRestObject(300,message,dataJSON);
    }
    public static void ajaxRestObject(int state,String message ) {
        ajaxReturn( new StringBuilder( "{ \"state\" : " ).append( state ).append( ",\"message\":\"" ).append( message ).append( "\"}" ) );
    }
    public static void ajaxRestObject(int state,String message, String dataJSON ) {
        if ( dataJSON== null ){dataJSON = "";}
        ajaxReturn( new StringBuilder( "{ \"state\" : " ).append( state ).append( ",\"message\":\"" ).append( message ).append( "\",\"data\":\"" ).append( dataJSON ).append( "\"}" ) );
    }

    /**
     * ajax返回状态, 返回 new StringBuffer("{ \"state\" : \"").append( state ).append("\"}")
     */
    public static < T > void ajaxState( T state ) {
        ajaxReturn( new StringBuilder( "{ \"state\" : \"" ).append( state ).append( "\"}" ) );
    }

    /**
     * set 排序
     */
    public static Set sortBySet( Set source, Comparator comparator ) {
        Set< Object > treeSet = new TreeSet< Object >( comparator );
        treeSet.addAll( source );
        return treeSet;
    }

    /*********************************************** 文件上传下载 *******************************************************************/
    /**
     * @param request
     * @return List<MultipartFile> throws
     * @author 雷建军
     * @time 2014年8月13日 上午11:06:12
     * @Description: 文件上传方法
     * 使用spring-mvc的上传类从request中获得List<MultipartFile>上传的文件集.
     * (ps:速度比流方式快很多倍 , 一个4M的文件 流的方式的运行时间：14712ms multipartFile方法的运行时间：4ms)
     * 1.  拷贝文件:
     *     for (MultipartFile multipartFile : ActionUtils.getUploadFiles(request)) {
     *         multipartFile.transferTo(new File("D:/" + multipartFile.getOriginalFilename()));
     *     }
     * 2. getBytes得到 数据.等等...方法..
     *
     * 进度条:
     * 1. 使用html5做进度条,
     * 2. 重写CommonsMultipartResolver 获得进度信息,前端轮询取得信息...
     */
    public static List< MultipartFile > getUploadFiles( HttpServletRequest request ) {
        // 将当前上下文初始化给 CommonsMutipartResolver （多部分解析器）
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver( request.getSession().getServletContext() );
        // 检查form中是否有enctype="multipart/form-data"
        if ( multipartResolver.isMultipart( request ) ) {
            // 将request变成多部分request
            MultipartHttpServletRequest multiRequest = ( MultipartHttpServletRequest ) request;
            // 获取multiRequest 中所有的文件名
            Iterator< String > iter = multiRequest.getFileNames();

            List< MultipartFile > multipartFiles = new ArrayList< MultipartFile >();
            while ( iter.hasNext() ) {
                // 一次遍历所有文件
                MultipartFile file = multiRequest.getFile( iter.next().toString() );
                multipartFiles.add( file );
            }
            return multipartFiles;
        }
        return null;
    }

    /**
     * @param file
     * @time 2014年8月22日 下午5:34:05
     * @Description: 文件下载方法
     */
    public static void downloadFile( File file ) {
        String fileName = file.getName();
        try {
            fileName = URLEncoder.encode( fileName, "UTF-8" );
        } catch ( UnsupportedEncodingException e1 ) {
            e1.printStackTrace();
        }
        ActionUtils.getResponse().setContentType( "application/zip" );
        ActionUtils.getResponse().addHeader( "Content-Disposition", "attachment;filename=" + fileName );
        OutputStream outp = null;
        FileInputStream in = null;
        try {
            outp = ActionUtils.getResponse().getOutputStream();
            in = new FileInputStream( file );
            byte[] b = new byte[ 1024 ];
            int i = 0;
            while ( ( i = in.read( b ) ) > 0 ) {
                outp.write( b, 0, i );
            }
            outp.flush();
        } catch ( Exception e ) {
            e.printStackTrace();
        } finally {
            if ( in != null ) {
                try {
                    in.close();
                    in = null;
                } catch ( IOException e ) {
                    e.printStackTrace();
                }
            }
            if ( outp != null ) {
                try {
                    outp.close();
                    outp = null;
                } catch ( IOException e ) {
                    e.printStackTrace();
                }
            }
        }
    }

    /*********************************************** 几大作用域作用域 方法 *******************************************************************/
    /**
     * @param arg
     * @return String throws
     * @author 雷建军
     * @time 2014年6月13日 下午3:29:19
     * @Description: 从request中取指定值。等价=request.getParameter(arg);
     */
    public static String getParameter( String arg ) {
        return getRequest().getParameter( arg );
    }

    /**
     * @author 雷建军
     * @time 2014年6月13日 下午3:29:19
     * @Description: 等价于=request.getAttribute(arg); throws
     */
    public static Object getAttribute( String arg ) {
        return getRequest().getAttribute( arg );
    }

    /**
     * @author 雷建军
     * @time 2014年6月13日 下午3:29:19
     * @Description: 等价于=request.setAttribute(key,value); throws
     */
    public static void setAttribute( String key, Object value ) {
        getRequest().setAttribute( key, value );
    }

    /**
     * 获得几大作用域
     */
    public static HttpServletRequest getRequest() {
        return ( ( ServletRequestAttributes ) RequestContextHolder.getRequestAttributes() ).getRequest();
    }

    public static HttpSession getSession() {
        return getRequest().getSession();
    }

    public static ServletContext getApplication() {
        return RequestContextUtils.getWebApplicationContext( getRequest() ).getServletContext();
    }

    // 这个从request里取的(spring mvc只有response获取不到,所以自己手动保存)
    public static HttpServletResponse getResponse() {
        return ( HttpServletResponse ) getRequest().getAttribute( "[save]response" );
    }

    public static void setResponse( HttpServletResponse response ) {
        getRequest().setAttribute( "[save]response", response );
    }

    private static Logger LogUtils = Logger.getLogger( ActionUtils.class );

    /**
     * 获得log4j
     */
    public static Logger getLogger() {
        return LogUtils;
    }


    /**
     * 解析查询参数
     */

    /**
     * 取得带相同前缀的Request Parameters, 返回的结果的Parameter名已去除前缀.
     */
    public static Map<String, Object> parseParameters(ServletRequest request, String prefix) {
        if( request == null ) {
            return new HashMap<>();
        }
        Enumeration paramNames = request.getParameterNames();
        Map<String, Object> params = new TreeMap<String, Object>();
        if (prefix == null) {
            prefix = "";
        }
        while ((paramNames != null) && paramNames.hasMoreElements()) {
            String paramName = (String) paramNames.nextElement();
            if ("".equals(prefix) || paramName.startsWith(prefix)) {
                String unprefixed = paramName.substring(prefix.length());
                String[] values = request.getParameterValues(paramName);
                try {
                    if ((values == null) || (values.length == 0)) {
                        // Do nothing, no values found at all.
                        continue;
                    }

                    String val = values.length > 1 ? values.toString().trim() : values[0].trim();
                    val = URLDecoder.decode( val, "UTF-8" );
                    // 如果是时间格式转为 Date
                    if ( val.length() == 10 && val.charAt( 4 ) == '-' && val.charAt( 7 ) == '-' )
                        params.put( unprefixed, DateUtils.parseDate( val ) );
                    else
                        params.put( unprefixed, val );

                } catch (UnsupportedEncodingException e) {

                }
            }
        }
        return params;
    }
}

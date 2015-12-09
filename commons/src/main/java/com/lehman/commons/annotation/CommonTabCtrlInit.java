package com.lehman.commons.annotation;

/**
 * Created by Administrator on 2015/8/18.
 */

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 表 操作公共的控制器初始化 注解
 */
@Target({ ElementType.FIELD, ElementType.TYPE})
@Retention( RetentionPolicy.RUNTIME)
public @interface CommonTabCtrlInit {
    String viewName() default "/page/commons/frameCommonTpl"; // 视图名, 视图文件的查找
    String resource(); // 资源名,用于获取操作许可
}

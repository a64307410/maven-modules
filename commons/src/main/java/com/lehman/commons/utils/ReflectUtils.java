package com.lehman.commons.utils;


import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * 反射工具类
 * Created by Administrator on 2015/8/19.
 */
public class ReflectUtils {

    public static <T> T map2Entity(Map mapEntity, Class<T> entityClass) {
        T t = null;
        try {
            t = entityClass.newInstance();
            for (Object key : mapEntity.keySet()) {
                Field field = entityClass.getDeclaredField(key.toString());
                field.setAccessible(true);// 调用private方法的关键一句话  //设置跳过访问检查.使之访问private域
                field.set(t, mapEntity.get(key)); //为属性赋值
            }
        } catch (InstantiationException | IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        }
        return t;
    }

    /*************************************************************************************************************************************
     * 设置属性值,如果value 为null 或 "" 则不设置
     *
     * @param obj
     * @param prop  要设置的属性
     * @param value 值
     * @return 设置成功 true ,设置失败,或value 为null返回false
     */
    public static boolean setProp(Object obj, String prop, Object value) {
        if (value == null || value.toString().equals("")) {
            return false;
        }
        Class cl = obj.getClass();
        try {
            Field field = cl.getDeclaredField(prop);
            field.setAccessible(true);// 调用private方法的关键一句话  //设置跳过访问检查.使之访问private域
            field.set(obj, value); //为属性赋值
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /*************************************************************************************************************************************
     * 设置常规的未知类型的属性值,也就是说在不知道属性类型的情况下以字符串自动处理赋值.(不包括自定义类型)
     * 类型包括: String ,Integer,Double
     * 如果value 为null 或 "" 则不设置
     *
     * @param obj
     * @param prop  要设置的属性
     * @param value 值
     * @return 设置成功 true ,设置失败,或value 为null返回false
     */
    public static boolean setUnkTypeProp(Object obj, String prop, String value) {
        if (value == null || value.toString().equals("")) {
            return false;
        }
        Class cl = obj.getClass();
        try {
            Field field = cl.getDeclaredField(prop);
            field.setAccessible(true);// 调用private方法的关键一句话  //设置跳过访问检查.使之访问private域

            try {
                field.set(obj, value); //为属性赋值
            } catch (Exception e) {
                try {
                    field.set(obj, Integer.valueOf(value)); //Integer类型赋值
                } catch (Exception e1) {
                    try {
                        field.set(obj, Double.valueOf(value)); //Double类型赋值
                    } catch (Exception e2) {
                        e.printStackTrace();
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


    /**
     * 过滤 对象属性,只需要,过滤集中的属性其他都排除掉
     *
     * @param obj    要过滤的对象
     * @param filter 过滤集
     */
    public static void filterObjPro(Object obj, String[] filter) {
        //过滤集,放入set,加快比对
        Set<String> filterSet = new HashSet<String>(Arrays.asList(filter));
        filterObjPro(obj, filterSet);
    }

    /**
     * 过滤 对象属性,只需要,过滤集中的属性
     *
     * @param obj       要过滤的对象
     * @param filterSet 过滤集
     */
    private static void filterObjPro(Object obj, Set<String> filterSet) {
        Class cls = obj.getClass();

        List<Object> objs = isCollectionType(obj);

        // 如果是集合类型,遍历递归
        if (objs != null) {
            for (Object oTmp : objs) {
                filterObjPro(oTmp, filterSet);
            }
            return;
        }

        Field[] fields = cls.getDeclaredFields(); // 获取实体类的所有属性，返回Field数组
        try {
            for (int j = 0; j < fields.length; j++) { // 遍历所有属性
                Field field = fields[j]; // 获取属性的名字
                // 静态变量,跳过
                if (Modifier.isStatic(field.getModifiers())) continue;
                field.setAccessible(true);// 调用private方法的关键一句话  //设置跳过访问检查.使之访问private域
                String name = field.getName();
                // 如果在过滤集中的话
                if (filterSet.contains(name)) {
                    // 不是基础数据类型继续递归
                    if (!isBaseDataType(field.getType())) {
                        filterObjPro(field.get(obj), filterSet);
                    }
                } else {// 如果不在过滤集中的话,null
                    try {
                        field.set(obj, null);
                    } catch (IllegalAccessException e) {
                        continue;
                    } catch (IllegalArgumentException e) {
                        field.set(obj, 0);
                    }
                }


            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 判断是否是数组,或集合类型
     *
     * @param obj 要判断的类。
     * @return 类型的class  如果null 则不是集合类型
     */
    private static List<Object> isCollectionType(Object obj) {
        try {
            return new ArrayList((Collection) obj);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 判断一个类是否为基本数据类型。
     *
     * @param clazz 要判断的类。
     * @return true 表示为基本数据类型。
     */
    private static boolean isBaseDataType(Class clazz) {
        return (
                clazz.equals(String.class) ||
                        clazz.equals(Integer.class) ||
                        clazz.equals(Byte.class) ||
                        clazz.equals(Long.class) ||
                        clazz.equals(Double.class) ||
                        clazz.equals(Float.class) ||
                        clazz.equals(Character.class) ||
                        clazz.equals(Short.class) ||
                        clazz.equals(BigDecimal.class) ||
                        clazz.equals(BigInteger.class) ||
                        clazz.equals(Boolean.class) ||
                        clazz.equals(Date.class) ||
                        //         clazz.equals( DateTime.class ) ||
                        clazz.isPrimitive()
        );
    }


    /**
     * 从包package中获取所有的Class
     */
    public static Set<Class<?>> getClassesByPack(String[] packs) {
        Set<Class<?>> resCls = new HashSet<>();
        for (String pack : packs) {
            Set<Class<?>> cls = getClassesByPack(pack);
            resCls.addAll(cls);
        }
        return resCls;
    }

    /**
     * 从包package中获取所有的Class
     *
     * @param pack
     * @return
     */
    public static Set<Class<?>> getClassesByPack(String pack) {

        // 第一个class类的集合
        Set<Class<?>> classes = new LinkedHashSet<Class<?>>();
        // 是否循环迭代
        boolean recursive = true;
        // 获取包的名字 并进行替换
        String packageName = pack;
        String packageDirName = packageName.replace('.', '/');
        // 定义一个枚举的集合 并进行循环来处理这个目录下的things
        Enumeration<URL> dirs;
        try {
            dirs = Thread.currentThread().getContextClassLoader().getResources(
                    packageDirName);
            // 循环迭代下去
            while (dirs.hasMoreElements()) {
                // 获取下一个元素
                URL url = dirs.nextElement();
                // 得到协议的名称
                String protocol = url.getProtocol();
                // 如果是以文件的形式保存在服务器上
                if ("file".equals(protocol)) {
                    // System.out.println( "file类型的扫描" );
                    // 获取包的物理路径
                    String filePath = URLDecoder.decode(url.getFile(), "UTF-8");
                    // 以文件的方式扫描整个包下的文件 并添加到集合中
                    findAndAddClassesInPackageByFile(packageName, filePath,
                            recursive, classes);
                } else if ("jar".equals(protocol)) {
                    // 如果是jar包文件
                    // 定义一个JarFile
                    // System.out.println( "jar类型的扫描" );
                    JarFile jar;
                    try {
                        // 获取jar
                        jar = ((JarURLConnection) url.openConnection())
                                .getJarFile();
                        // 从此jar包 得到一个枚举类
                        Enumeration<JarEntry> entries = jar.entries();
                        // 同样的进行循环迭代
                        while (entries.hasMoreElements()) {
                            // 获取jar里的一个实体 可以是目录 和一些jar包里的其他文件 如META-INF等文件
                            JarEntry entry = entries.nextElement();
                            String name = entry.getName();
                            // 如果是以/开头的
                            if (name.charAt(0) == '/') {
                                // 获取后面的字符串
                                name = name.substring(1);
                            }
                            // 如果前半部分和定义的包名相同
                            if (name.startsWith(packageDirName)) {
                                int idx = name.lastIndexOf('/');
                                // 如果以"/"结尾 是一个包
                                if (idx != -1) {
                                    // 获取包名 把"/"替换成"."
                                    packageName = name.substring(0, idx)
                                            .replace('/', '.');
                                }
                                // 如果可以迭代下去 并且是一个包
                                if ((idx != -1) || recursive) {
                                    // 如果是一个.class文件 而且不是目录
                                    if (name.endsWith(".class")
                                            && !entry.isDirectory()) {
                                        // 去掉后面的".class" 获取真正的类名
                                        String className = name.substring(
                                                packageName.length() + 1, name
                                                        .length() - 6);
                                        try {
                                            // 添加到classes
                                            classes.add(Class
                                                    .forName(packageName + '.'
                                                            + className));
                                        } catch (ClassNotFoundException e) {
                                            // log
                                            // .error("添加用户自定义视图类错误 找不到此类的.class文件");
                                            e.printStackTrace();
                                        }
                                    }
                                }
                            }
                        }
                    } catch (IOException e) {
                        // log.error("在扫描用户定义视图时从jar包获取文件出错");
                        e.printStackTrace();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return classes;
    }

    /**
     * 以文件的形式来获取包下的所有Class
     *
     * @param packageName
     * @param packagePath
     * @param recursive
     * @param classes
     */
    private static void findAndAddClassesInPackageByFile(String packageName,
                                                         String packagePath, final boolean recursive, Set<Class<?>> classes) {
        // 获取此包的目录 建立一个File
        File dir = new File(packagePath);
        // 如果不存在或者 也不是目录就直接返回
        if (!dir.exists() || !dir.isDirectory()) {
            // log.warn("用户定义包名 " + packageName + " 下没有任何文件");
            return;
        }
        // 如果存在 就获取包下的所有文件 包括目录
        File[] dirfiles = dir.listFiles(new FileFilter() {
            // 自定义过滤规则 如果可以循环(包含子目录) 或则是以.class结尾的文件(编译好的java类文件)
            public boolean accept(File file) {
                return (recursive && file.isDirectory())
                        || (file.getName().endsWith(".class"));
            }
        });
        // 循环所有文件
        for (File file : dirfiles) {
            // 如果是目录 则继续扫描
            if (file.isDirectory()) {
                findAndAddClassesInPackageByFile(packageName + "."
                                + file.getName(), file.getAbsolutePath(), recursive,
                        classes);
            } else {
                // 如果是java类文件 去掉后面的.class 只留下类名
                String className = file.getName().substring(0,
                        file.getName().length() - 6);
                try {
                    // 添加到集合中去
                    //classes.add(Class.forName(packageName + '.' + className));
                    //经过回复同学的提醒，这里用forName有一些不好，会触发static方法，没有使用classLoader的load干净
                    classes.add(Thread.currentThread().getContextClassLoader().loadClass(packageName + '.' + className));
                } catch (ClassNotFoundException e) {
                    // log.error("添加用户自定义视图类错误 找不到此类的.class文件");
                    e.printStackTrace();
                }
            }
        }
    }


}

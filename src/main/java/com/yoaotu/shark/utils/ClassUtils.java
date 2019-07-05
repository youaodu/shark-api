package com.yoaotu.shark.utils;

import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * @author youao.du@gmail.com
 * @create 2019-06-07 11:21
 */
public class ClassUtils {
    /**
     * 获取此包名下所有类
     * @author youao.du@gmail.com
     * @time 11:22
     * @params [packageName]
     */
    public static List<Class<?>> queryClassesByPackage(String packageName) {
        List<Class<?>> classes = new ArrayList<Class<?>>();
        if (StringUtils.isBlank(packageName)) {
            return classes;
        }

        try {
            // 获取类名下所有类文件  >>> Url
            Enumeration<URL> dirs = Thread.currentThread().getContextClassLoader().getResources(packageName.replace(".", "/"));

            while (dirs.hasMoreElements()) {
                URL url = dirs.nextElement();
                // 查看URL协议
                String protocol = url.getProtocol();

                // 文件协议
                if ("file".equals(protocol)) {
                    String filePath = URLDecoder.decode(url.getFile(), "UTF-8");
//                    System.out.println(filePath);
                    queryClassesByDriPath(packageName, filePath, true, classes);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return classes;
    }

    /**
     * 根据文件夹路径获取Class
     *
     * packageName: 包路径
     * filePath: 包文件夹路径
     * flag: 是否递归
     * classes: 最后结果  >>>  递归需要。所以不能是返回值
     * @author youao.du@gmail.com
     * @time 14:55
     * @params [packageName, filePath, flag, classes]
     */
    private static void queryClassesByDriPath(String packageName, String filePath, final boolean flag, List<Class<?>> classes) {
        if (StringUtils.isBlank(packageName) || StringUtils.isBlank(filePath)) {
            return;
        }

        final File dir = new File(filePath);
        if (!dir.exists() || !dir.isDirectory()) {
            return;
        }

        File[] dirFile = dir.listFiles(new FileFilter() {
            // 文件过滤  >>> 只接受文件夹和.class文件
            public boolean accept(File pathname) {
                boolean acceptDir = flag && pathname.isDirectory();
                boolean acceptClass = pathname.getName().endsWith("class");
                return acceptDir || acceptClass;
            }
        });

        for (File file : dirFile) {
            // 文件夹
            if (file.isDirectory()) {
                queryClassesByDriPath(String.format(packageName + ".%s", file.getName()), file.getAbsolutePath(), flag, classes);
            } else {
                // 获取类名 >>> 去除后缀名
                String name =  file.getName().substring(0, file.getName().length() - 6);
                try {
                    classes.add(Thread.currentThread().getContextClassLoader().loadClass(String.format(packageName + ".%s", name)));
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 把传过来的第一个字符换成小写
     * @author youao.du@gmail.com
     * @time 15:40
     * @params [className]
     */
    public static String toLowerCaseTheFirst (String className) {
        return String.format("%s%s", className.substring(0, 1).toLowerCase(), className.substring(1));
    }

}

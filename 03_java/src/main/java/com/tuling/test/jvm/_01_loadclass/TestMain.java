package com.tuling.test.jvm._01_loadclass;

import com.sun.crypto.provider.DESKeyFactory;
import sun.misc.Launcher;

import java.net.URL;

public class TestMain {
    public static void main(String[] args) {
        // 获取内容
        ClassLoader stringCL = String.class.getClassLoader();
        System.out.println(stringCL);
        ClassLoader desCL = DESKeyFactory.class.getClassLoader();
        System.out.println(desCL);
        ClassLoader appCl = TestMain.class.getClassLoader();
        System.out.println(appCl);

        System.out.println("-------------------华丽分割线----------------------");
        System.out.println(desCL.getClass().getName());
        System.out.println(appCl.getClass().getName());

        ClassLoader appClassLoader = ClassLoader.getSystemClassLoader();
        ClassLoader extClassLoader = appClassLoader.getParent();
        ClassLoader bootClassLoader = extClassLoader.getParent();
        System.out.println("-------------------华丽分割线----------------------");
        System.out.println(appClassLoader);
        System.out.println(extClassLoader);
        System.out.println(bootClassLoader);


        System.out.println("-------------------boot 加载文件---------------------");
        for (URL url : Launcher.getBootstrapClassPath().getURLs()) {
            System.out.println(url);
        }

        System.out.println("-------------------ext 加载文件---------------------");
        String val = System.getProperty("java.ext.dirs");
        println(val);

        System.out.println("-------------------app 加载文件---------------------");
        println(System.getProperty("java.class.path"));

    }

    private static void println(String val) {
        for (String item : val.split(":")) {
            System.out.println(item);
        }
    }
}

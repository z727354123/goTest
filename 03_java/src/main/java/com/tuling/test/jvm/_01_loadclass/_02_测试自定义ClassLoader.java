package com.tuling.test.jvm._01_loadclass;

import com.sun.crypto.provider.DESKeyFactory;
import sun.misc.Launcher;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;

public class _02_测试自定义ClassLoader {


    public static void main(String[] args) throws Exception{
        MyClsLoader myClsLoader = new MyClsLoader("/Users/judy/workspace/TX_cloud/refStep/exa2/build/classes/java/main");

        Class<?> aClass = myClsLoader.loadClass("example.MyString");
        Object obj = aClass.newInstance();
        System.out.println(obj.getClass().getName());
        System.out.println(obj.getClass().getClassLoader());
    }

    public static class MyClsLoader extends ClassLoader {
        private String clsPath;
        public MyClsLoader(String clsPath) {
            this.clsPath = clsPath;
        }


        private byte[] loadByte(String fullName) throws Exception {
            fullName = fullName.replaceAll("\\.", "/");
            FileInputStream in = new FileInputStream(clsPath + "/" + fullName + ".class");
            int size = in.available();
            byte[] data = new byte[size];
            in.read(data);
            in.close();
            return data;
        }

        @Override
        protected Class<?> findClass(String name) throws ClassNotFoundException {
            try {
                byte[] bytes = loadByte(name);
                return defineClass(name, bytes, 0, bytes.length);
            } catch (Exception e) {
                throw new ClassNotFoundException(name, e);
            }
        }
    }
}

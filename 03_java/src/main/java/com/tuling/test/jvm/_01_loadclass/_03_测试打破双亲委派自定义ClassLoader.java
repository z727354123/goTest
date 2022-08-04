package com.tuling.test.jvm._01_loadclass;

import com.fizz.User;

import java.io.FileInputStream;
import java.lang.reflect.Method;

public class _03_测试打破双亲委派自定义ClassLoader {


    public static void main(String[] args) throws Exception {
        // \u000a System.out.println("hello");
        MyClsLoader myClsLoader = new MyClsLoader("/Users/judy/workspace/TX_cloud/refStep/exa2/build/classes/java/main");

        User user = new User();
        System.out.println(user);
        Class<?> aClass =  myClsLoader.loadClass("com.fizz.User");
        Object obj = aClass.newInstance();
        System.out.println(obj);
        System.out.println("-------------------另外で一条，华丽の分割线----------------------");
        Method getUser = aClass.getMethod("getUser");
        Object invoke = getUser.invoke(obj);
        System.out.println(invoke.getClass().getClassLoader());
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
                return null;
            }
        }

        @Override
        public Class<?> loadClass(String name) throws ClassNotFoundException {
            synchronized (getClassLoadingLock(name)) {
                Class<?> c = findLoadedClass(name);
                if (c != null) {
                    return c;
                }
                c = findClass(name);
                if (c == null) {
                    return super.loadClass(name);
                }
                // First, check if the class has already been loaded
                long t0 = System.nanoTime();
                // If still not found, then invoke findClass in order
                // to find the class.
                long t1 = System.nanoTime();

                // this is the defining class loader; record the stats
                sun.misc.PerfCounter.getParentDelegationTime().addTime(t1 - t0);
                sun.misc.PerfCounter.getFindClassTime().addElapsedTimeFrom(t1);
                sun.misc.PerfCounter.getFindClasses().increment();
                return c;
            }
        }
    }
}

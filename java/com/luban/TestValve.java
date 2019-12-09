package com.luban;

import org.apache.catalina.connector.Request;
import org.apache.catalina.connector.Response;
import org.apache.catalina.valves.RequestFilterValve;
import org.apache.juli.logging.Log;

import javax.servlet.ServletException;
import java.io.IOException;

public class TestValve extends RequestFilterValve {


    @Override
    public void invoke(Request request, Response response) throws IOException, ServletException {
        System.out.println("test value");
        getNext().invoke(request, response);
    }

    @Override
    protected Log getLog() {
        return null;
    }


    public static void main(String[] args) {
//          String str = new String(new char[]{'a'});   //一个普通的String对象，没有在常量池里
//          String str1 = str.intern();                 //在常量池中没有找到equal为true的String对象，这时会把str添加到池里去并返回对应的引用
//          System.out.println(str == str1);  // true

//          String str = new String("1");       // 这行代码会生成两个String对象，字面量"1"一个，new出来一个
//          String str1 = str.intern();        // 返回的是字面量"1"所对应的String对象的引用
//          System.out.println(str == str1);  // false

//            String str = "1";           // 一个String对象，这个对象在常量池里
//            String str1 = str.intern(); // 在常量池里找到了这个对象
//            System.out.println(str == str1);  // true



//        str.intern()
//        String t = "t".intern();
//        String s = "s".intern();
//
//        System.out.println(t == s);

//        Integer.valueOf()

    }
}

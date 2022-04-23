package com.tuling.test.jvm._01_loadclass;

import java.util.LinkedList;

public class _04_测试GC {
    static class Obj {
        private String name;
        private int age;
        private int time;
    }

    public static void main(String[] args) throws Exception{

        LinkedList<Obj> list = new LinkedList<>();
        while (true) {
            list.add(new Obj());
            list.add(new Obj());
            list.add(new Obj());
            list.add(new Obj());
            list.add(new Obj());
            list.add(new Obj());
            list.add(new Obj());
            list.add(new Obj());
            list.add(new Obj());
            list.add(new Obj());
            Thread.sleep(1);
        }
    }
}

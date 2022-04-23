package com.tuling.test.jvm._01_loadclass;


public class _06_测试UTF {
    public static void main(String[] args) {


        int zero = 0x0000;
        int mid = 0x8fff;
        int midSub = 0x8fff - 1;
        int midAdd = 0x8fff + 1;
        int max = 0xffff;
        int maxAdd = 0xffff + 1;
        int tmp = 0b11011000_00000000;

        print("zero", zero);
        print("mid", mid);
        print("midSub", midSub);
        print("midAdd", midAdd);
        print("max", max);
        print("maxAdd", maxAdd);
        print("tmp", tmp);
        char num = 0xffff;
        System.out.println("-------------------另外で一条，华丽の分割线----------------------");
        testOther();
    }

    private static void testOther() {
        //int end = 0xffff;
        //int start = 0b10000000_000000;
        //char[] chars = new char[end - start];
        //for (int i = 0; i < end - start; i++) {
        //    chars[i] = (char) (start + i);
        //}
        //String s = new String(chars);

        int start = 0xD800;
        int end = 0xDFFF;
        int mid = 0xDBFF;
        System.out.println(Integer.toBinaryString(start));
        System.out.println(Integer.toBinaryString(mid));
        System.out.println(Integer.toBinaryString(end));
        char[] chars = new char[end - start];
        for (int i = 0; i < end - start; i++) {
            chars[i] = (char) (start + i);
        }
        String s = new String(chars);

        String str = "⁋";
        System.out.println("-------------------华丽分割线----------------------");
    }

    private static void print(String name, int val) {
        System.out.println("-------------------华丽分割线----------------------");
        System.out.println(String.format("2: %s=%s, %s", name, Integer.toBinaryString(val),  Integer.toBinaryString(val).length()));
        System.out.println(String.format("10: %s=%s", name, val));
        System.out.println(String.format("16: %s=%s", name, Integer.toHexString(val)));
        System.out.println(String.format("s: %s=%s", name, Character.isSupplementaryCodePoint(val)));
        System.out.println(String.format("s: %s=%s", name, Character.isSurrogate((char) val)));
    }
}

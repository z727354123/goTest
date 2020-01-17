package com.luban;

import org.apache.tomcat.util.buf.CharChunk;

import java.io.IOException;

public class Test {

    public static void main(String[] args) throws IOException {
        CharChunk charChunk = new CharChunk();
        charChunk.append("123/");

        System.out.println(charChunk.startsWithIgnoreCase("3",2));
    }
}

package com.fizz.base64.zipfile;

import com.fizz.base64.MainBase64;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

public class ZipFile {
    static String src = "src.txt";
    static String target = "base64.txt";
    static String path;
    static {
        URL location = MainBase64.class.getProtectionDomain().getCodeSource().getLocation();
        String loc = location.getPath();
        System.out.println(loc);
        path = loc.substring(0, loc.lastIndexOf("03_java") + 8) + "src/main/java/com/fizz/base64/zipfile/file/";
    }

    public static void main(String[] args) throws Exception {
        srcToBase64();
        // base64ToSrc();
    }

    public static void srcToBase64() throws Exception{
        byte[] bytes = Files.readAllBytes(Paths.get(path + src));
        byte[] encode = Base64.getEncoder().encode(bytes);
        Files.write(Paths.get(path + target), encode);
    }


    public static void base64ToSrc() throws Exception{
        byte[] bytes = Files.readAllBytes(Paths.get(path + target));
        byte[] encode = Base64.getDecoder().decode(bytes);
        Files.write(Paths.get(path + src), encode);
    }

}

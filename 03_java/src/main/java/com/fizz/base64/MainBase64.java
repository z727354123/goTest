package com.fizz.base64;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.InflaterOutputStream;

public class MainBase64 {
    static String src = "src.txt";
    static String target = "base64.txt";

    static String path;
    static {
        URL location = MainBase64.class.getProtectionDomain().getCodeSource().getLocation();
        String loc = location.getPath();
        System.out.println(loc);
        path = loc.substring(0, loc.lastIndexOf("03_java") + 8) + "src/main/java/com/fizz/base64/file/";
    }
    public static void main(String[] args) throws Exception{
        // srcToBase64();
        base64ToSrc();
    }

    public static void srcToBase64() throws Exception{
        byte[] bytes = Files.readAllBytes(Paths.get(path + src));
        byte[] encode = Base64.getEncoder().encode(bytes);
        Files.write(Paths.get(path + target), encode);
    }
    public static void base64ToSrc() throws Exception{
        byte[] bytes = Files.readAllBytes(Paths.get(path + target));
        byte[] encode = unzipBase64(bytes);
        Files.write(Paths.get(path + src), encode);
    }



    public static String zipBase64(String text) {
        return zipBase64(text.getBytes(StandardCharsets.UTF_8));
    }
    public static String zipBase64(byte[] bytes) {
        try (ByteArrayOutputStream os = new ByteArrayOutputStream()) {
            try (DeflaterOutputStream out = new DeflaterOutputStream(os)) {
                out.write(bytes);
            }
            return new String(Base64.getEncoder().encode(os.toByteArray()));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return "";
    }

    public static byte[] unzipBase64(byte[] bytes) {
        try (ByteArrayOutputStream os = new ByteArrayOutputStream()) {
            try (InflaterOutputStream out = new InflaterOutputStream(os)) {
                out.write(Base64.getDecoder().decode(bytes));
            }
            return os.toByteArray();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static String unzipBase64(String text) {
        try (ByteArrayOutputStream os = new ByteArrayOutputStream()) {
            try (InflaterOutputStream out = new InflaterOutputStream(os)) {
                out.write(Base64.getDecoder().decode(text));
            }
            return new String(os.toByteArray(), StandardCharsets.UTF_8);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return "";
    }
}

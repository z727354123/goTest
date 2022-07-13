package mytest.socket;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringBufferInputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class SockeClientDemo {

    public static void main(String[] args) throws Exception{
        Socket socket = new Socket("localhost", 8101);
        OutputStream outputStream = socket.getOutputStream();
        int i = 1;
        while (true) {
            System.out.println(i++);
            outputStream.write("123\n".getBytes());
        }
    }
}

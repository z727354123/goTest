package mytest.socket;

import sun.java2d.pipe.BufferedRenderPipe;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class SockeServerDemo {

    public static void main(String[] args) throws Exception{
        ServerSocket serverSocket = new ServerSocket(8101);

        Socket socke = serverSocket.accept();

        InputStream inputStream = socke.getInputStream();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader reader = new BufferedReader(inputStreamReader);
        System.in.read();
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
        System.out.println("-------------------华丽分割线----------------------");
    }
}

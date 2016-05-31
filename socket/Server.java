package software.reuse.app.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by wangdechang on 2016/5/30.
 */
public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(8889);

            while (true) {
                Socket socket = serverSocket.accept();
                MyThread myThread = new MyThread(socket);
                Thread thread = new Thread(myThread);
                thread.start();
                System.out.println(thread.getId());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}



class MyThread implements Runnable {

    private ServerSocket serverSocket;
    private Socket socket;
    private Socket receiveSocket;
    private PrintWriter writer;

    public MyThread(Socket socket) {
        this.socket = socket;
        try {
            receiveSocket = new Socket("127.0.0.1", 8080);
            writer = new PrintWriter(receiveSocket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void run() {
        while (true) {
            //socket = serverSocket.accept();
            System.out.println();
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String str = null;
                str = reader.readLine();
                System.out.println("str -> " + str);
                writer.println(str);
                writer.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}


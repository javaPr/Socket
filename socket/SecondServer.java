package software.reuse.app.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;

/**
 * Created by wangdechang on 2016/5/30.
 */
public class SecondServer {
    private String realMessage;

    public static void main(String[] args) {
        BlockingQueue<String> blockingQueue = new LinkedBlockingQueue<String>();
        try {
            new AnotherThread(blockingQueue).start();

            ServerSocket serverSocket = new ServerSocket(8080);
            while (true) {
                Socket socket = serverSocket.accept();
                Receive myThread = new Receive(socket,blockingQueue);
                Thread thread = new Thread(myThread);
                thread.start();
                System.out.println(thread.getId());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class AnotherThread extends Thread {
    private  BlockingQueue<String> queue;

    public AnotherThread(BlockingQueue<String> blockingQueue) {
        this.queue = blockingQueue;
    }

    @Override
    public void run() {

        try {
            ServerSocket serverSocket2 = new ServerSocket(8887);
            while (true) {
                System.out.println("======");
                final Socket socket = serverSocket2.accept();
                new Thread(new Runnable() {

                    @Override
                    public void run() {
                        BufferedReader reader = null;
                       while(true){

                           try {
                               reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                               String str = null;
                               str = reader.readLine();
                               System.out.println("str -> " + str);
                               try {
                                   PrintWriter writer = new PrintWriter(socket.getOutputStream());
                                   String consumer = queue.take();
                                   writer.println(consumer + " for server");
                                   writer.flush();
                               } catch (InterruptedException e) {
                                   e.printStackTrace();
                               }
//                               writer.println(str + "for server");
//                               writer.flush();

                           } catch (IOException e) {
                               e.printStackTrace();
                           }
                       }
                    }
                }).start();

            }
        } catch (IOException e) {

        }

    }
}


class Receive implements Runnable {
    private Socket socket;
    private BlockingQueue<String> queue;

    public Receive(Socket socket,BlockingQueue<String> queue) {
        this.socket = socket;
        this.queue = queue;
    }

    @Override
    public void run() {
        BufferedReader reader = null;
        while (true) {
            try {
                reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String str = null;
                str = reader.readLine();
                try {
                    queue.put(str);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("receive -> " + str);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}

package software.reuse.app.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by wangdechang on 2016/5/30.
 */
public class Client2 {
    public static void main(String[] args){
       // ClientMSg clientMSg = new ClientMSg();
        new Thread(new ClientMSg()).start();
    }


}

class ClientMSg implements Runnable{
    private Socket socket;
    private BufferedReader reader;

    public ClientMSg() {
        try {
            this.socket = new Socket("127.0.0.1",8887);
            this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
       // Scanner in = new Scanner(System.in);
        String myMsg = "Hello Server";
        PrintWriter writer = null;

        while(true){
            try {
                writer = new PrintWriter(socket.getOutputStream());
                writer.println(" ");
                writer.flush();

                BufferedReader reader1 = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
                String msg;
                msg = reader1.readLine();
                System.out.println("clent -> "+msg);

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}

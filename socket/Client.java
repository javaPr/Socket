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
public class Client {
    public static void main(String[] args){
        try {
            Socket socket = new Socket("127.0.0.1",8889);
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream());
//            ClientMSg clientMSg = new ClientMSg();
//            new Thread(clientMSg).start();
            for(int i = 0;i<10;i++){
                Scanner in = new Scanner(System.in);
                String str = in.nextLine();
                writer.println(str);
                writer.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

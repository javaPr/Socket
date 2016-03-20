import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    /*这是version 2
   使用Socket需要主要的几点如下：

   */
   public static void main(String[] args) {
      try {
         ServerSocket ss = new ServerSocket(8888);
         System.out.println("启动服务器....");
         Socket s = ss.accept();
         System.out.println("客户端:"+s.getInetAddress().getLocalHost()+"已连接到服务器");
         
         BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
         //读取客户端发送来的消息
         String mess = br.readLine();
         System.out.println("客户端："+mess);
         BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
         bw.write(mess+" 服务端返回的数据"+"\n");
         bw.flush();
      } catch (IOException e) {
         e.printStackTrace();
      }
   }
   /* 当然这里添加的代码也是没有用处的
   仅仅是为了测试的！！！
   */
   public int add(int i,int y){
      return i+y;
   }
}
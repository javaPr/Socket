import java.io.*;
import java.net.*;
public class Client2{
	Socket socket;
	BufferedReader in;
	PrintWriter out;
	BufferedReader line;
	public Client2(){
		try{
			
			//while(true){
				socket = new Socket("127.0.0.1",10000);
				in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				out = new PrintWriter(socket.getOutputStream(),true);
				System.out.println(in.readLine());
				line = new BufferedReader(new InputStreamReader(System.in));
				out.println(line.readLine());
				//System.out.println(in.readLine());
				line.close();
				out.close();
				in.close();
			//}
			

			
			
		}catch (IOException e) {
			
		}finally{
			 try {
               socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
		}
	}

	public static void main(String[] args) {
		new Client2();
	}
}
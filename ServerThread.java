import java.io.*;
import java.net.*;

public class ServerThread extends ServerSocket{
	private static final int SERVER_PORT  = 10000;
	static int num = 0;
	public ServerThread() throws IOException{
		super(SERVER_PORT);
		try{
			while(true){
				Socket socket = accept();
				new CreateServerThread(socket);
			}
		}catch (IOException e) {
			
		}finally{
			close();
		}
	}

	class CreateServerThread extends Thread{
		private Socket client;
		private BufferedReader in;
		private PrintWriter out;
		public CreateServerThread(Socket s) throws IOException{
			client = s;
			in = new BufferedReader(new InputStreamReader(client.getInputStream(),"GB2312"));
			out = new PrintWriter(client.getOutputStream(),true);
			out.println("--Welcome--");
			start();
		}
		public void run(){
			try{
				String line = in.readLine();
				while(!line.equals("bye")){
					String msg = createMessage(line);
					System.out.println(msg);
					out.println(msg);
					line = in.readLine();
				}
				out.println("--see you,bye!--");
				client.close();
			}catch (IOException e) {
				
			}
		}
		private String createMessage(String line){
			
			return line+" "+(++num);
			//System.out.println(line);

		}
	}

	public static void main(String[] args)throws IOException {
		new ServerThread();
	}
	//下面测试tag
}
package chat;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ServerEx {

	public static void main(String[] args) {
		ServerSocket server = null;
		Socket socket = null;
		BufferedReader in = null;
		BufferedWriter out = null;
		Scanner sc = new Scanner(System.in);
		
		// 9999번 포트로 서버소켓 생성
		try {
			server = new ServerSocket(9999);
			System.out.println("연결 대기중.....");
			
			// accept 시 socket 생성.
			socket = server.accept();
			System.out.println("연결 되었습니다.");
			
			// in에 inputstream 할당 (socket)
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			
			while (true) {
				String inMsg = in.readLine();
				
				// 종료
				if (inMsg.equalsIgnoreCase("bye")) {
					System.out.println("클라이언트가 나갔습니다.");
					break ;
				}
				System.out.println("클라이언트: " + inMsg);
				
				System.out.print("보내기 >>");
				String outMsg = sc.nextLine();
				out.write(outMsg + "\n");
				out.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			
			try {
				sc.close();
				in.close();
				out.close();
				socket.close();
				server.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}

}
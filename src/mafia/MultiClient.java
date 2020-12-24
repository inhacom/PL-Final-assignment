package mafia;
/*콘솔 멀티채팅 클라이언트 프로그램*/
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
 
public class MultiClient {
    public static void main(String[] args) throws UnknownHostException, IOException {
       
        System.out.println("이름을 입력해 주세요.");
        Scanner s = new Scanner(System.in);    
        String s_name = s.nextLine();
       
        try{
            String ServerIP = "localhost";
            Socket socket = new Socket(ServerIP, 9999); //소켓 객체 생성         
            System.out.println("서버와 연결이 되었습니다......");
            //사용자로부터 얻은 문자열을 서버로 전송해주는 역할을 하는 쓰레드.
            Thread sender = new Sender(socket, s_name);            
            //서버에서 보내는 메시지를 사용자의 콘솔에 출력하는 쓰레드.
            Thread receiver = new Receiver(socket);        
            System.out.println("채팅방에 입장하였습니다.");
               
            sender.start(); //스레드 시동
            receiver.start(); //스레드 시동
           
        }catch(Exception e){
            System.out.println("예외[MultiClient class]:"+e);
        }
    }
}
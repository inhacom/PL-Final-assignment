package mafia;
/*�ܼ� ��Ƽä�� Ŭ���̾�Ʈ ���α׷�*/
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
 
public class MultiClient {
    public static void main(String[] args) throws UnknownHostException, IOException {
       
        System.out.println("�̸��� �Է��� �ּ���.");
        Scanner s = new Scanner(System.in);    
        String s_name = s.nextLine();
       
        try{
            String ServerIP = "localhost";
            Socket socket = new Socket(ServerIP, 9999); //���� ��ü ����         
            System.out.println("������ ������ �Ǿ����ϴ�......");
            //����ڷκ��� ���� ���ڿ��� ������ �������ִ� ������ �ϴ� ������.
            Thread sender = new Sender(socket, s_name);            
            //�������� ������ �޽����� ������� �ֿܼ� ����ϴ� ������.
            Thread receiver = new Receiver(socket);        
            System.out.println("ä�ù濡 �����Ͽ����ϴ�.");
               
            sender.start(); //������ �õ�
            receiver.start(); //������ �õ�
           
        }catch(Exception e){
            System.out.println("����[MultiClient class]:"+e);
        }
    }
}
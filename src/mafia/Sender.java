package mafia;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
//������ �޽����� �����ϴ� Ŭ���� 
public class Sender extends Thread {
    Socket socket;
    DataOutputStream out;
    String name;
   
    //������ ( �Ű������� ���ϰ� ����� �̸� �޽��ϴ�. )
    public Sender(Socket socket, String name){ //���ϰ� ����� �̸��� �޴´�.
        this.socket = socket;      
        try{
            out = new DataOutputStream(this.socket.getOutputStream());
            this.name = name; //�޾ƿ� ������̸��� ���������� ����, �ٸ� �޼����� run()���� ����ϱ�����.
        }catch(Exception e){
            System.out.println("����:"+e);
        }
    }
   
    @Override
    public void run(){ //run()�޼ҵ� ������
       
        Scanner s = new Scanner(System.in);
        //Ű����κ��� �Է��� �ޱ����� ��ĳ�� ��ü ����
       
        //������ �Է��� ������̸��� �����ش�.
        try {
            out.writeUTF(name);
        } catch (IOException e) {          
            System.out.println("����:"+e);
        }      
       
        while(out!=null){ //��½�Ʈ���� null�� �ƴϸ�..�ݺ�
            try { //while�� �ȿ� try-catch���� ����� ������ while�� ���ο��� ���ܰ� �߻��ϴ���
                  //��� �ݺ��Ҽ��ְ� �ϱ����ؼ��̴�.                   
                out.writeUTF(name+"=>"+s.nextLine()); //Ű����κ��� �Է¹��� ���ڿ��� ������ ������.
               
            } catch (IOException e) {
                System.out.println("����:"+e);
            }
        }//while------
    }//run()------
}//class Sender-------
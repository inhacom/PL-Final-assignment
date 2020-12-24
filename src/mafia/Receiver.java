package mafia;
import java.io.DataInputStream;
import java.net.Socket;
 
//�����κ��� �޽����� �д� Ŭ����
public class Receiver extends Thread{
   
    Socket socket;
    DataInputStream in;
   
    //Socket�� �Ű������� �޴� ������.
    public Receiver(Socket socket){
        this.socket = socket;
       
        try{
            in = new DataInputStream(this.socket.getInputStream());
        }catch(Exception e){
            System.out.println("����:"+e);
        }
    }//������ --------------------
   
    @Override
    public void run(){ //run()�޼ҵ� ������
       
        while(in!=null){ //�Է½�Ʈ���� null�� �ƴϸ�..�ݺ�
            try{
                System.out.println(in.readUTF());
                //�����κ��� �о�� �����͸� �ֿܼ� ���
               
            }catch(Exception e){
                System.out.println("����:"+e);
            }
        }//while----
    }//run()------
}//class Receiver -------

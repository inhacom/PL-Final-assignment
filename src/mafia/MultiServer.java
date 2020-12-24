package mafia;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

public class MultiServer {
    HashMap clientMap;
    ServerSocket serverSocket = null;
    Socket socket = null;
   
    public MultiServer(){
        clientMap = new HashMap();
        Collections.synchronizedMap(clientMap);
    }
   
    public void init(){
        try{
            serverSocket = new ServerSocket(9999);
            System.out.println("서버가 시작되었습니다.");
           
            while(true){ 
                socket = serverSocket.accept(); 
                System.out.println(socket.getInetAddress()+":"+socket.getPort()); 
               
                Thread msr = new MultiServerRec(socket); 
                msr.start(); 
            }      
           
        }catch(Exception e){
            e.printStackTrace();
        }
    }
   
    
    public void sendAllMsg(String msg){
       
        Iterator it = clientMap.keySet().iterator();
       
        while(it.hasNext()){
            try{
                DataOutputStream it_out = (DataOutputStream) clientMap.get(it.next());
                it_out.writeUTF(msg);
            }catch(Exception e){
                System.out.println("예외:"+e);
            }
        }
    }
   
    
    public static void main(String[] args) {
        MultiServer ms = new MultiServer();
        ms.init();
    }
   
   
    class MultiServerRec extends Thread {
       
        Socket socket;
        DataInputStream in;
        DataOutputStream out;
       
        
        public MultiServerRec(Socket socket){
            this.socket = socket;
            try{
                in = new DataInputStream(socket.getInputStream());
                out = new DataOutputStream(socket.getOutputStream());
            }catch(Exception e){
                System.out.println("예외:"+e);
            }
        }
       
        @Override
        public void run(){ 
           
            String name="";
            try{
                name = in.readUTF(); 
                sendAllMsg(name + "님이 입장하셨습니다.");
                clientMap.put(name, out); 
                System.out.println("현재 접속자 수는 "+clientMap.size()+"명 입니다.");
               
                while(in!=null){ 
                    sendAllMsg(in.readUTF()); 
                }
            }catch(Exception e){
                System.out.println(e + "----> ");
            }finally{
                clientMap.remove(name);
                sendAllMsg(name + "님이 퇴장하셨습니다.");              
                System.out.println("현재 접속자 수는 "+clientMap.size()+"명 입니다.");
            }
        }
    }
}

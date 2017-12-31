package ParOrImpar;

import java.net.ServerSocket;
import java.net.Socket;

public class Servidor{

    public static void main(String[] args) throws Exception{
        ServerSocket servidor = new ServerSocket(1234);
        Socket cliente = null;
        System.out.println("Aguardando...");
        
        while (true) {
            cliente = servidor.accept();
            System.out.println("Resposta da Stream: "+cliente.getInetAddress().getHostAddress());
            Thread thread = new Thread(new ParOrImpar(cliente));
            thread.start();
            System.out.println("Cliente finalizado!!\n\n\n\n\n");
            System.out.println("Aguardando...");
        }
    }
}
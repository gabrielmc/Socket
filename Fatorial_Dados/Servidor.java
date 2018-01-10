
package Fatorial_Dados;

import Config.Config;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class Servidor {
    
    public static void main(String[] args) throws IOException{
        Config config = new Config();
        ServerSocket servidor = new ServerSocket(config.IP);
        Socket socket = null;
        System.out.println("Aguardando conexão!!!!");
        
        while(true){
            socket = servidor.accept();
            System.out.println("Conectado ao server!!!!");
            
            Thread thr = new Thread( new DadosNumero(socket));
            thr.start();
            System.out.println("Aguardando próxima conexão!!!!");
        }
    }
    
}

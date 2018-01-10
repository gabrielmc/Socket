
package ContarLetras;

import Config.Config;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    
    public static void main(String[] args) throws IOException{
        
        Config config = new Config();
        ServerSocket servidor = new ServerSocket(config.IP);
        Socket socket = null;
        System.out.println("Aguardando Conexao!!");
        while(true){
            socket = servidor.accept();
            System.out.println("Conectado");
            
            Thread thr = new Thread( new Texto(socket));
            thr.start();
            System.out.println("Aguardando próximo cliente...");
        }
    }
}


package controle;

import Config.Config;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
        
public class Servidor {
    
    public static void main(String[] args) throws IOException{
        Config config = new Config();
        ServerSocket servidor = new ServerSocket(config.IP);
        System.out.println("Aguardando ...");
        Socket socket = null;
                
        while(true){
            socket = servidor.accept();
            System.out.println("Conexao Estabelecida ...");
            Thread thr = new Thread( new ProcessClass(socket));
            thr.start();
            System.out.println("Processo finalizado...\n\n\n\n");
            System.out.println("Aguardando proximo cliente...");
        }
    }
    
}

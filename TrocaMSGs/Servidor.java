
package TrocaMSGs;

import Config.Config;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor{
    
    public static void main(String[] args) throws IOException{
        Config config = new Config();
        ServerSocket servidor = new ServerSocket(config.IP);
        System.out.println("Aguardando ....");
        Socket cliente;
                
        while(true) {
            cliente = servidor.accept();
            System.out.println("Conectado ao server "+config.IP+"....");
            Thread thread = new Thread( new Mensagens(cliente));
            thread.start();
            System.out.println("Aguardando próxima requisição....");
        }
    }

}
package ParOrImpar;

import Config.Config;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor{

    public static void main(String[] args) throws Exception{
        Config config = new Config();
        ServerSocket servidor = new ServerSocket(config.IP);
        Socket cliente = null;
        System.out.println("Aguardando...");
        
        while (true) {
            cliente = servidor.accept();
            Thread thread = new Thread(new ParOrImpar(cliente));
            thread.start();
            System.out.println("Cliente finalizado!!\n\n\n\n\n");
            System.out.println("Aguardando...");
        }
    }
}
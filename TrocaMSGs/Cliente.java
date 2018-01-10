package TrocaMSGs;

import Config.Config;
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Cliente {
    
    
    public static void main(String[] args) throws Exception{
        Config config = new Config();
        Socket cliente = new Socket(config.LOCALHOST,config.IP);
        
        DataInputStream lerDoServer = new DataInputStream(cliente.getInputStream());
                
        System.out.println("Retorno do servidor: "+lerDoServer.readUTF());
    }
}
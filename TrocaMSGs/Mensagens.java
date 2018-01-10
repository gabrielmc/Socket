
package TrocaMSGs;

import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Mensagens implements Runnable{ // classe de processamento
    
    private String[] mensagens;
    private Socket socket;

    public Mensagens(Socket socket) { // Recebe o Socket do cliente
        this.socket = socket;
        this.insertMesgs();
    }
   
    public void insertMesgs(){
        this.mensagens = new String[9];
        this.mensagens[0] = " Boa sorte nos estudos ";
        this.mensagens[1] = " Sucesso na vida ";
        this.mensagens[2] = " Work Hard!! ";
        this.mensagens[3] = " Relaxar e preciso ";
        this.mensagens[4] = " Analista dorme pouco ";
        this.mensagens[5] = "I love you";
        this.mensagens[6] = "I hate you";
        this.mensagens[7] = "I cry for you";
        this.mensagens[8] = "We love";
    }
    
    public String getAleatoryMessanger(){
        Random random = new Random();
        int numero = random.nextInt(9);
        return this.mensagens[numero];
    }
    
    
    @Override
    public void run(){
        try {
            DataOutputStream mandaParaServer = new DataOutputStream(this.socket.getOutputStream());
            
            mandaParaServer.writeUTF(this.getAleatoryMessanger());
            mandaParaServer.flush();
            
            
        } catch (IOException ex) {
            Logger.getLogger(Mensagens.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
}

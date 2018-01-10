
package ContarLetras;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;


public class Texto implements Runnable {
    
    private Socket cliente;
    private String texto;    
    private int tamanho;

    
    public Texto(Socket socket){
        this.cliente = socket;
    }
    
    public void setTexto(String texto){
        this.texto = texto;
        this.tamanho = texto.length();
    }
    
    public int getQtdPalavras(){
        int cont = 1;
        char index;
        for (int i = 0; i < this.tamanho; i++) {
            index = this.texto.charAt(i);
            if(index == ' ') {
                cont++;
            }
        }
        return cont;
    }
    
    public boolean ehLetra(char ch){
        if((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z')){
            return true;
        }
        else{
            return false;
        }
    }
    
    public int getQtdLetras(){
        int cont = 1;
        char index;
        for (int i = 0; i < this.tamanho; i++) {
            index = this.texto.charAt(i);
            if(this.ehLetra(index)) {
                cont++;
            }
        }
        return cont;
    }
    
    
    
    @Override
    public void run(){
        try{
            DataOutputStream mandaParaCliente = new DataOutputStream(this.cliente.getOutputStream());
            DataInputStream lerDoCliente = new DataInputStream(this.cliente.getInputStream());
            
            this.setTexto(lerDoCliente.readUTF());
            
            mandaParaCliente.writeInt(this.getQtdPalavras());
            mandaParaCliente.flush();
            
            mandaParaCliente.writeInt(this.getQtdLetras());
            mandaParaCliente.flush();
            
        }catch(IOException erro){
            erro.printStackTrace();
        }
    }

}

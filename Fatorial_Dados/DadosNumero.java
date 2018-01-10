
package Fatorial_Dados;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;


public class DadosNumero implements Runnable{

    private Socket cliente;
    private int numero;
    private int fatorial;
    
    public DadosNumero(Socket socket){
        this.cliente = socket;
    }
    
    public void setNumero(int numero){
        this.numero = numero;
        this.fatorial = this.fatorial(this.numero);
    }
    
    public int fatorial(int numero){
        if (numero <= 1) {
            return 1;
        } else {
            return fatorial(numero - 1) * numero;
        }
    }
    
    public String parOrImpar(){
        return this.numero % 2 == 0 ? " par" : " impar";
    }
    
    
    @Override
    public void run() {
        try{
            
            DataOutputStream mandaParaServer = new DataOutputStream(this.cliente.getOutputStream());
            DataInputStream lerDoCliente = new DataInputStream(this.cliente.getInputStream());
            
            int numero = lerDoCliente.readInt();
            this.setNumero(numero);
            
            mandaParaServer.writeInt(this.fatorial);
            mandaParaServer.flush();
            
            mandaParaServer.writeUTF(this.parOrImpar());
            mandaParaServer.flush();
            
        }catch(IOException erro){
            erro.printStackTrace();
        }
                
    }
    
    
}

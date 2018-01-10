
package calculo_IMC;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;


public class IMC implements Runnable {
    
    private Socket socket;
    
    public IMC(Socket cliente){
        this.socket = cliente;
    }
    
    public double imc(double peso, double altura){
        return peso / Math.pow(altura,2);
    }
    
    @Override
    public void run(){
         try {
            DataOutputStream mandaParaCliente = new DataOutputStream(this.socket.getOutputStream());
            DataInputStream lerDoCliente = new DataInputStream(this.socket.getInputStream());
            
            
            double peso = lerDoCliente.readDouble();
            double altura = lerDoCliente.readDouble();
            
            mandaParaCliente.writeDouble(this.imc(peso, altura));
            mandaParaCliente.flush();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}

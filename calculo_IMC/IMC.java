
package calculo_IMC;

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
            Scanner lerDoCliente = new Scanner(this.socket.getInputStream());
            PrintWriter mandaParaCliente = new PrintWriter(this.socket.getOutputStream(), true);
            
            double peso = Double.parseDouble(lerDoCliente.nextLine());
            double altura = Double.parseDouble(lerDoCliente.nextLine());
            
            mandaParaCliente.println(this.imc(peso, altura));
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}

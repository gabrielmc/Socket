
package ParOrImpar;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ParOrImpar implements Runnable{
    Socket socket;

    public ParOrImpar(Socket socket) {
        this.socket = socket;
    }
    
    public String getStringNumero(int numero){
        return  numero % 2 == 0 ? "Numero par !" : "Numero impar !";
    }

    @Override
    public void run() {
        try {
            Scanner lerDoCliente = new Scanner(this.socket.getInputStream());
            PrintWriter mandaParaCliente = new PrintWriter(this.socket.getOutputStream(), true);
            
            mandaParaCliente.println( this.getStringNumero( Integer.parseInt(lerDoCliente.nextLine())));
            
        } catch (IOException ex) {
            Logger.getLogger(ParOrImpar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
}
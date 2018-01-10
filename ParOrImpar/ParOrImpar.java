
package ParOrImpar;

import java.io.DataInputStream;
import java.io.DataOutputStream;
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
            DataOutputStream mandaParaCliente = new DataOutputStream(this.socket.getOutputStream());
            DataInputStream lerDoCliente = new DataInputStream(this.socket.getInputStream());
            
            mandaParaCliente.writeUTF( this.getStringNumero(lerDoCliente.readInt()));
            mandaParaCliente.flush();
            
        } catch (IOException ex) {
            Logger.getLogger(ParOrImpar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
}
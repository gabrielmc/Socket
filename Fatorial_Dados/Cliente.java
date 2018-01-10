
package Fatorial_Dados;

import Config.Config;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.net.Socket;


public class Cliente {
    
    private int numero;
    
    public void menuNumero(){
        Scanner lerConsole = new Scanner(System.in);
        System.out.println("----------Dados do numero----------");
        System.out.println("Informe um numero: ");
        this.numero = Integer.parseInt(lerConsole.nextLine());
    }
    
    public void outDados(int resultFatorial , String parImpar ){
        System.out.println("\n\n\n");
        System.out.println("O fatorial do numero é: "+resultFatorial);
        System.out.println("E o numero é: "+parImpar);
    }
    
    
    public static void main(String[] args) throws IOException {
        Cliente cli = new Cliente();
        Config config = new Config();
        Socket cliente = new Socket(config.LOCALHOST, config.IP);
        
        DataInputStream lerDoCliente = new DataInputStream(cliente.getInputStream());
        DataOutputStream mandaParaServer = new DataOutputStream(cliente.getOutputStream());
                
        cli.menuNumero();
        mandaParaServer.writeInt(cli.numero);
        mandaParaServer.flush();
        
        int fatorial = lerDoCliente.readInt();
        String parOrImpar = lerDoCliente.readUTF();
        
        cli.outDados(fatorial, parOrImpar);
        
        
    }
}

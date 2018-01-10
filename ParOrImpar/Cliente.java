
package ParOrImpar;

import Config.Config;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;


public class Cliente{
    
    private int numero;
    
    public void menuCliente(){
        Scanner lerExterno = new Scanner(System.in);
        System.out.println("Informe um numero: ");
        this.numero = Integer.parseInt(lerExterno.nextLine());
    }
    
    public void outDados(String result){
        System.out.println("\nO numero informado é: "+result);
    }
    
    public static void main(String[] args) throws Exception{
        Cliente cli = new Cliente();
        Config config = new Config();
        Socket cliente = new Socket(config.LOCALHOST, config.IP);
        cli.menuCliente();
        
        DataInputStream lerDoServer = new DataInputStream(cliente.getInputStream());
        DataOutputStream mandaParaServer = new DataOutputStream(cliente.getOutputStream());
        
                
        mandaParaServer.writeInt(cli.numero);
        mandaParaServer.flush();
        
        String parOrImpar = lerDoServer.readUTF();
        cli.outDados(parOrImpar);
    }
}
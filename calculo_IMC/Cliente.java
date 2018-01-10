
package calculo_IMC;

import Config.Config;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {

    public double peso;
    public double altura;
            
    public void menuCliente(){
        Scanner ler = new Scanner(System.in);
        
        System.out.print("Informe seu peso: ");
        this.peso = Double.parseDouble(ler.nextLine());
        System.out.print("Informe sua altura: ");
        this.altura = Double.parseDouble(ler.nextLine());
    }
            
    public void outDados(double massaCorporea){
        System.out.print("Seu indice de massa corporea(IMC) é: "+massaCorporea);
    }
            
    public static void main(String[] args) throws IOException{
        Cliente cli = new Cliente();
        cli.menuCliente();
        
        Config config = new Config();
        Socket cliente = new Socket(config.LOCALHOST, config.IP);

        DataInputStream lerDoServer = new DataInputStream(cliente.getInputStream());
        DataOutputStream mandaParaServer = new DataOutputStream(cliente.getOutputStream());
        
        mandaParaServer.writeDouble(cli.peso);
        mandaParaServer.flush();
        
        mandaParaServer.writeDouble(cli.altura);
        mandaParaServer.flush();

        cli.outDados(lerDoServer.readDouble());
    }

}

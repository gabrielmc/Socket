
package calculo_IMC;

import Config.Config;
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
            
            
    public static void main(String[] args){
        Cliente cli = new Cliente();
        cli.menuCliente();
        
        try{
            Config config = new Config();
            Socket cliente = new Socket(config.LOCALHOST, config.IP);

            PrintWriter mandaServer = new PrintWriter(cliente.getOutputStream(), true);
            Scanner lerDoServer = new Scanner(cliente.getInputStream());
            
            mandaServer.println(cli.peso);
            mandaServer.println(cli.altura);

            System.out.print("Seu indice de massa corporea(IMC) é: "+lerDoServer.nextLine());
                        
        }catch(IOException erro){
            erro.getStackTrace();
        }
        
        
    }

}

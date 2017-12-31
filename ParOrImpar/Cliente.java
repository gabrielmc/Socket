
package ParOrImpar;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;


public class Cliente{
    
    private int numero;
    
    public void menuCliente(){
        //Faltou a leitura de teclado
        Scanner lerExterno = new Scanner(System.in);
        System.out.println("Informe um numero: ");
        this.numero = Integer.parseInt(lerExterno.nextLine());
    }
    
    public static void main(String[] args) throws Exception{
        Cliente cli = new Cliente();
        cli.menuCliente();
        
        Socket cliente = new Socket("127.0.0.1", 1234);
        
        Scanner lerDoServer = new Scanner(cliente.getInputStream());
        PrintWriter mandaParaServer = new PrintWriter(cliente.getOutputStream(), true);
        
        
        //Faltou você escrever o que captou do teclado para o servidor
        mandaParaServer.println(cli.numero);

        //printa o que vem do servidor
        System.out.println(lerDoServer.nextLine());
        
        System.out.println("Resultado: "+lerDoServer.nextLine());
    }
}
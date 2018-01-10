
package Pitagoras_Newton;

import Config.Config;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;


public class Cliente {
    
    private int catetoA;
    private int catetoB;
    private double massa;
    private double aceleracao;
    
    public void outDados(double hipotenusa, double forca ){
        System.out.println("A hipotenusa desse triangulo é: "+hipotenusa);
        System.out.println("A forca necessaria para locomover o objeto é: "+forca+" N");
    }
    
    public void menuHipotenusa(){
        Scanner lerConsole = new Scanner(System.in);
        System.out.println("----------Busca HIPOTENUSA----------");
        System.out.println("Informe o cateto Adjacente: ");
        this.catetoA = Integer.parseInt(lerConsole.nextLine());
        System.out.println("Informe o cateto Oposto: ");
        this.catetoB = Integer.parseInt(lerConsole.nextLine());
    }
    
    public void menuLeiDeNewton(){
        Scanner lerConsole = new Scanner(System.in);
        System.out.println("----------Encontre a Força----------");
        System.out.println("Informe a massa do objeto para o deslocamento: ");
        this.massa = Double.parseDouble(lerConsole.nextLine());
        System.out.println("Informe a aceleração: ");
        this.aceleracao = Double.parseDouble(lerConsole.nextLine());
    }
    
    public static void main(String[] args) throws IOException{
        Cliente cli = new Cliente();
        Config config = new Config();
        Socket socket = new Socket(config.LOCALHOST, config.IP);
        
        cli.menuHipotenusa();
        cli.menuLeiDeNewton();
        
        //Scanner lerDoServidor = new Scanner(socket.getInputStream());
        //PrintWriter mandaParaServidor = new PrintWriter(socket.getOutputStream(), true);
        /*
        // catetos
        mandaParaServidor.println(cli.catetoA);
        mandaParaServidor.println(cli.catetoB);
        
        // manda massa e aceleração 
        mandaParaServidor.println(cli.massa);
        mandaParaServidor.println(cli.aceleracao);
        
        //ler dados do servidor
        double hipotenusa = Double.parseDouble(lerDoServidor.nextLine());
        double forca = Double.parseDouble(lerDoServidor.nextLine());
        
        System.out.println("\n\n\n\n\n\n\n\n\n");
        cli.outDados(hipotenusa, forca);
        */
        
        DataInputStream lerDoServer = new DataInputStream(socket.getInputStream());
        DataOutputStream mandaParaServer = new DataOutputStream(socket.getOutputStream());
        
        mandaParaServer.writeInt(cli.catetoA);
        mandaParaServer.flush();
        mandaParaServer.writeInt(cli.catetoB);
        mandaParaServer.flush();
        
        mandaParaServer.writeDouble(cli.massa);
        mandaParaServer.flush();
        mandaParaServer.writeDouble(cli.aceleracao);
        mandaParaServer.flush();
        
        double hipotenusa = lerDoServer.readDouble();
        double forca = lerDoServer.readDouble();
        cli.outDados(hipotenusa, forca);
        
        
    }
    
}

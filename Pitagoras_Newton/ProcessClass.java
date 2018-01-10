
package Pitagoras_Newton;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;


public class ProcessClass implements Runnable{

    private Socket cliente;
    private int catetoA;
    private int catetoB;
    private double massa;
    private double aceleracao;
    
    
    public ProcessClass(Socket socket){
        this.cliente = socket;
    }
    
    public void setCatetos(int a, int b){
        this.catetoA = a;
        this.catetoB = b;
    }
    
    public void setMassaEAceleracao(double massa, double aceleracao){
        this.massa = massa;
        this.aceleracao = aceleracao;
    }
    
    public double getHipotenusa(){
        double somaCatetos = Math.pow(this.catetoA, 2) + Math.pow(this.catetoB, 2);
        return Math.pow(somaCatetos, 2);
    }
    
    public double getForca(){
        return this.massa * this.aceleracao;
    }
    
    @Override
    public void run() {
        try{
            /*PrintWriter mandaParaCliente = new PrintWriter(this.cliente.getOutputStream(), true);
            Scanner lerDoCliente = new Scanner(this.cliente.getInputStream());
            
            // fazer o teorema de pitagora
            int catetoA = Integer.parseInt(lerDoCliente.nextLine());
            int catetoB = Integer.parseInt(lerDoCliente.nextLine());
            this.setCatetos(catetoA, catetoB);
            
            // fazer o calculo da força de Newton
            double massa = Double.parseDouble(lerDoCliente.nextLine());
            double aceleracao = Double.parseDouble(lerDoCliente.nextLine());
            this.setMassaEAceleracao(massa, aceleracao);
            
            //manda os dados
            mandaParaCliente.println(this.getHipotenusa());
            mandaParaCliente.println(this.getForca());
            */
            
            DataOutputStream mandaParaServer = new DataOutputStream(this.cliente.getOutputStream());
            DataInputStream lerDoServer = new DataInputStream(this.cliente.getInputStream());
            
            int catetoA = lerDoServer.readInt();
            int catetoB = lerDoServer.readInt();
            this.setCatetos(catetoA, catetoB);
            
            
            double massa = lerDoServer.readDouble();
            double aceleracao = lerDoServer.readDouble();
            this.setMassaEAceleracao(massa, aceleracao);
            
            mandaParaServer.writeDouble(this.getHipotenusa());
            mandaParaServer.flush();
            mandaParaServer.writeDouble(this.getForca());
            mandaParaServer.flush();
        
            
        }catch(IOException erro){
            erro.printStackTrace();
        }
    }
    
}

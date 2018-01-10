
package ContarLetras;

import Config.Config;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;


public class Cliente {
    
    private String texto;

    public String getTexto(){
        return this.texto = "Texto para teste da questao proposta para atividade treino para a prova de PWeb, dessa forma execultar o que se deseja.";
    }    
    
    public void outByCliente(int qtd, int qtdLetras){
        System.out.println("\nA quantidade de palavras do texto em questão é : "+qtd);
        System.out.println("A quantidade de Letras do texto em questão é : "+qtdLetras);
    }
    
    
    public static void main(String[] args) throws IOException {
        Config config = new Config();
        Cliente cli = new Cliente();
        Socket cliente = new Socket(config.LOCALHOST, config.IP);
       
        DataInputStream lerDoServer = new DataInputStream(cliente.getInputStream());
        DataOutputStream mandaParaServer = new DataOutputStream(cliente.getOutputStream());
        
        mandaParaServer.writeUTF(cli.getTexto());
        mandaParaServer.flush();
        
        int qtdPalavras = lerDoServer.readInt();
        int qtdLetras = lerDoServer.readInt();
        
        cli.outByCliente(qtdPalavras, qtdLetras);
        
    }
    
}


package controle;

import Config.Config;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/*
A idéia é: uma cadastro pra usuário, ou seja ele informa se é pessoa física ou jurídica, 
sendo pessoa física informa : nome, CPF, RG, datadenascimento....
sendo pessoa jurídica informa: nome fantasia, CNPJ, protocolo ou número da empresa, 
.....valida todos os dados e se foram inseridos e 
retorno para o cliente o login e senha sendo login (primeiro + "•" + último nome) e senha randômica*/

public class Cliente {
    
    private int op;
    //Fisica
    private String nome;
    private String cpf;
    private String rg;
    private String dataNascimento;
    //Jurisica
    private String cnpj;
    private String nomeFantasia;
    private int protocolo;
    
    public void clearConsole(){
       for(int i = 0; i < 10; i++){
        System.out.println("");
       }
    }
    
    public boolean validaFisica(){
        return this.nome != " " && this.cpf != " " && this.rg != " " && this.dataNascimento != " ";
    }
    
    public boolean validaJuridica(){
        return this.cnpj != " " && this.nomeFantasia != " " && this.protocolo != 0 && this.protocolo > 0;
    }
    
    public void menuFisica(){
        Scanner lerSaida = new Scanner(System.in);
        System.out.println("INFORME O NOME: ");
        this.nome = lerSaida.nextLine();
        System.out.println("INFORME O CPF: ");
        this.cpf = lerSaida.nextLine();
        System.out.println("INFORME O RG: ");
        this.rg = lerSaida.nextLine();
        System.out.println("INFORME SUA DATA DE NASCIMENTO: ");
        this.dataNascimento = lerSaida.nextLine();
    }
    
    public void menuJuridica(){
        Scanner lerSaida = new Scanner(System.in);
        System.out.println("INFORME O CNPJ da empresa: ");
        this.cnpj = lerSaida.nextLine();
        System.out.println("INFORME O NOME DE FACHADA: ");
        this.nomeFantasia = lerSaida.nextLine();
        System.out.println("INFORME O PROTOCOLO OU NUMERAÇÃO DA EMPRESA: ");
        this.protocolo = Integer.parseInt(lerSaida.nextLine());
    }
    
    
    public void menu(){
        Scanner ler = new Scanner(System.in); // ler a variavel digitada
        System.out.println("---------CADASTRO DE CLIENTE ONLINE---------");
        System.out.println("INFORME O PERFIL PARA O CADASTRO DA PESSOA: \n");
        System.out.println("1 - FISICA\n2 - JURIDICA");
        System.out.println("--------------------------------------------");
        System.out.println("Digite a opção: ");
        this.op = Integer.parseInt(ler.nextLine());
        
        switch(op){
            case 1:
                this.clearConsole();
                this.menuFisica();
                break;
            case 2:
                this.clearConsole();
                this.menuJuridica();
                break;
            default: 
                this.clearConsole();
                System.out.println("Opção invalida!!");
        }
    }
    
    public void outDadosUser(String login, String senha){
        System.out.println("\n\nCADASTRO REALIZADO COM SUCESSO\n");
        System.out.println("Login do usuario:" +login);
        System.out.println("Senha :" +senha+"\n");
    }

    
    public static void main(String[] args) throws IOException {
        Cliente cli = new Cliente();
        cli.menu();        
        Config config = new Config();
        Socket cliente = new Socket(config.LOCALHOST, config.IP);
        
        DataOutputStream mandaParaServer = new DataOutputStream(cliente.getOutputStream());
        DataInputStream lerDoServer = new DataInputStream(cliente.getInputStream());
        
        mandaParaServer.writeInt(cli.op);
        mandaParaServer.flush();
        
        if(cli.op == 1){
            if(cli.validaFisica()){
                mandaParaServer.writeUTF(cli.nome);
                mandaParaServer.flush();
                mandaParaServer.writeUTF(cli.cpf);
                mandaParaServer.flush();
                mandaParaServer.writeUTF(cli.rg);
                mandaParaServer.flush();
                mandaParaServer.writeUTF(cli.dataNascimento);
                mandaParaServer.flush();
                
            }else{
                System.out.println("Dados incompletos!!");
            }
        }else{
            if(cli.validaJuridica()){
                mandaParaServer.writeUTF(cli.cnpj);
                mandaParaServer.flush();
                mandaParaServer.writeUTF(cli.nomeFantasia);
                mandaParaServer.flush();
                mandaParaServer.writeInt(cli.protocolo);
                mandaParaServer.flush();
            }else{
                System.out.println("Dados incompletos!!");
            }
        }
        
        cli.outDadosUser(lerDoServer.readUTF(), lerDoServer.readUTF());
       
    }
    
}

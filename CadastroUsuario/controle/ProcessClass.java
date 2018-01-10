
package controle;

import dominio.Fisica;
import dominio.Juridica;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ProcessClass implements Runnable{
    
    private Socket cliente;
    private String senha;
    
    public ProcessClass(Socket socket){
        this.cliente = socket;
        this.senha = "";
    }
    
    public String getCodUser() {
        return senha;
    }
    
    public void cadastroFisica(String nome, String cpf, String rg, String dataNascimento){
        Fisica fisica = new Fisica(nome, cpf, rg, dataNascimento);
        this.getRandomSenha();
    }
    
    public void cadastroJuridica(String cnpj, String nomeFantasia, int protocolo){
        Juridica juridica = new Juridica(cnpj, nomeFantasia, protocolo);
        this.getRandomSenha();
    }
    
    public String gerarLogin(String nome){ // tem que refazer pra pegar o inico do nome 
        int tam = nome.length();
        String primeiroNome = "";
        for (int i = 0; i <= tam; i++) {
            if( nome.charAt(i) == ' '){
                break;
            }else{
                primeiroNome += nome.charAt(i);
            }
        }
        return primeiroNome.toLowerCase()+".cadUnico";
        
    }
    
    public void getRandomSenha(){
        UUID uuid = UUID.randomUUID(); //gera Strings randomicas - Universally unique identifier (UUID)
        String myRandom = uuid.toString();
        this.senha = myRandom.substring(0,6);
    }
    
    @Override
    public void run(){
        try{
            DataInputStream lerDoCliente = new DataInputStream(this.cliente.getInputStream());
            DataOutputStream mandaParaCliente = new DataOutputStream(this.cliente.getOutputStream());
            
            int op = lerDoCliente.readInt();
            if(op == 1){
                String nome = lerDoCliente.readUTF();
                String cpf = lerDoCliente.readUTF();
                String rg = lerDoCliente.readUTF();
                String dataNascimento = lerDoCliente.readUTF();
                this.cadastroFisica(nome, cpf, rg, dataNascimento);
                
                mandaParaCliente.writeUTF(this.gerarLogin(nome));
                mandaParaCliente.flush();
                mandaParaCliente.writeUTF(this.senha);
                mandaParaCliente.flush();
                
            }else{
                String cnpj = lerDoCliente.readUTF();
                String nomeFantasia = lerDoCliente.readUTF();
                int protocolo = lerDoCliente.readInt();
                this.cadastroJuridica(cnpj, nomeFantasia, protocolo);
                
                mandaParaCliente.writeUTF(nomeFantasia.toLowerCase());
                mandaParaCliente.flush();
                mandaParaCliente.writeUTF(this.senha);
                mandaParaCliente.flush();
            }
            
        }catch(Exception erro){
            Logger.getLogger(ProcessClass.class.getName()).log(Level.SEVERE, null, erro);
        }
        
        
    }
            
}


package dominio;


public class Fisica{
    private String nome;
    private String cpf;
    private String rg;
    private String dataNascimento;
    
    public Fisica (String nome, String cpf, String rg, String dataNascimento){
        this.nome = nome;
        this.rg = rg;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
    
}

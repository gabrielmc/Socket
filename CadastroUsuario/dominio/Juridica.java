
package dominio;

public class Juridica{
    
    private String cnpj;
    private String nomeFantasia;
    private int protocolo;
    
    public Juridica(String cnpj, String nomeFantasia, int protocolo){
        this.cnpj = cnpj;
        this.nomeFantasia = nomeFantasia;
        this.protocolo = protocolo;
    }

    public int getProtocolo() {
        return this.protocolo;
    }

    public void setProtocolo(int protocolo) {
        this.protocolo = protocolo;
    }
    
    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

}

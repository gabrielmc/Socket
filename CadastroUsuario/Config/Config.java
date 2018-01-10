package Config;

/**
 *Classe somente para separa os canais de IP e HOST do package da aplicação.
 */
public class Config {
    
    public String LOCALHOST;
    public int IP;
    
    public Config(){
        this.LOCALHOST = "localhost";
        this.IP = 25251;
    }
    
}

package Socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor{

    private ServerSocket servidor;

    private void criarServidor(int porta) throws IOException{
        this.servidor = new ServerSocket(porta);
    }

    private Socket estabeleceConexao() throws IOException{
        Socket cliente = this.servidor.accept();
        return cliente;
    }

    private void efetuaConexao(Socket socket) throws IOException{ // define o protocolo para a aplicação.(regra de envio e recebimento)
        // chamada de classes caso seja necessario retorna um pesquisa elaborada

        try{
            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream input = new ObjectInputStream(socket.getInputStream());

            String msgCLiente = input.readUTF(); // recebe o que foi passado do cliente (INPUT), pode manipular para a classe

            output.writeUTF("Hello Word!!"); // retorna o pedido para o cliente na saida (OUTPUT)
            output.flush();


            //fecha a stream
            input.close();
            output.close();

        }catch(IOException e){
            System.out.println(e);
        }finally{
            socket.close();
        }
    }


    // o main deverá está numa classe teste que execute a aplicação
    public static void main(String[] args) throws InterruptedException{
        try{
            Servidor servidor = new Servidor();// instancia do servidor acima
            System.out.println("Aguardando conexao!!");
            servidor.criarServidor(32321);
            Socket cliente = servidor.estabeleceConexao();
            servidor.efetuaConexao(cliente);

        }catch(IOException e){
            System.out.println(e);
        }
    }


}
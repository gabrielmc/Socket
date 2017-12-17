package Socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Cliente{


    // o main deverá está numa classe teste que execute a aplicação
    public static void main(String[] args) throws IOException{

        try{
            Socket cliente = new Socket("localhost", 32321);
            ObjectOutputStream output = new ObjectOutputStream(cliente.getOutputStream());
            ObjectInputStream input = new ObjectInputStream(cliente.getInputStream());


            String msgSend = "Hello";
            output.writeUTF(msgSend); // mandando para o servidor
            output.flush();

            msgSend = input.readUTF(); // recebe a resposta do servidor
            System.out.println("Resposta do servidor ---> " + msgSend);

            input.close();
            output.close();
            cliente.close();

        }catch(IOException e){
            System.out.println(e);
        }
    }


}
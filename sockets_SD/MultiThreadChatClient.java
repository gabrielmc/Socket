import java.io.*;
import java.net.*;

public class MultiThreadChatClient implements Runnable{

    // Declaration section
    // clientClient: the client socket
    // os: the output stream
    // is: the input stream

    static Socket clientSocket = null;
    static PrintStream os = null;
    static DataInputStream is = null;
    static BufferedReader inputLine = null;
    static boolean closed = false;

    public static void main(String[] args) {
    	int porta   = 2222;
        String host = "localhost";

    	try {
            if (args.length < 2){
                System.out.println("Usage: java MultiThreadChatClient  \n"+ "Now using host="+host+", porta="+porta);
            } else {
                host=args[0];
                porta=Integer.valueOf(args[1]).intValue();
            }
            clientSocket = new Socket(host, porta);
            inputLine    = new BufferedReader(new InputStreamReader(System.in));
            os = new PrintStream(clientSocket.getOutputStream());
            is = new DataInputStream(clientSocket.getInputStream());

        } catch (UnknownHostException e) {
            System.err.println("Don't know about host "+host);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to the host "+host);
        }

        if (clientSocket != null && os != null && is != null) {
            try {
                new Thread( new MultiThreadChatClient()).start();
                while (!closed) {
                    os.println(inputLine.readLine());
                }
        		os.close();
        		is.close();
        		clientSocket.close();
            } catch (IOException e) {
                System.err.println("IOException:  " + e);
            }
        }
    }

    public void run() {
        try{
        String responseLine;
        while ((responseLine = is.readLine()) != null) {
            System.out.println(responseLine);
            if (responseLine.indexOf("*** Bye") != -1)
                break;
        }
        closed = true;

        } catch (IOException e) {
            System.err.println("IOException:  " + e);
        }
    }
}

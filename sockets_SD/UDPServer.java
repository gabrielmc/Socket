package sd;

import java.io.*;
import java.net.*;

class UDPServer
{
   public static void main(String args[]) throws Exception{
      DatagramSocket serverSocket = new DatagramSocket(9876);
      byte[] receiveData = new byte[1024];
      byte[] sendData = new byte[1024];

      while(true){
         DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
         serverSocket.receive(receivePacket);
         System.out.println("RECEIVED: " + String( receivePacket.getData()));

         InetAddress IPAddress = receivePacket.getAddress();
         int port = receivePacket.getPort();

         String capitalizedSentence = String( receivePacket.getData()).toUpperCase();
         sendData = capitalizedSentence.getBytes();

         DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
         serverSocket.send(sendPacket);
      }
   }
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LAB04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author AORUS
 */
public class serverSocket {

    public void initServerSoloUnMsg(int puerto) {

        ServerSocket server = null;
        Socket socket = null;
        PrintWriter flujoEnviar = null;     
        BufferedReader flujoRecibir = null; 
        String msgDelCliente = "";

        try {
            server = new ServerSocket(puerto);
            System.out.println("Esperando cliente");  
            socket = server.accept();

            flujoEnviar = new PrintWriter(socket.getOutputStream(), true);
            flujoEnviar.println("Hola chico...enviame un msg ");

            flujoRecibir = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            msgDelCliente = flujoRecibir.readLine();
            System.out.println("Servidor: he recibo este mensaje del cliente: " + msgDelCliente);

            flujoRecibir.close();
            flujoEnviar.close();
            socket.close();

        } catch (IOException ex) {
            System.out.println("Puerto " + puerto + " ocupado");
        }
    }

    public void initServerMsgContinuo(int puerto) {

        ServerSocket server = null;
        Socket socket = null;
        PrintWriter flujoEnviar = null;             
        BufferedReader flujoRecibir = null;
        Scanner teclado = new Scanner(System.in);
        String msgDelCliente = "";
        String msgDelServidor = "";

        try {
            server = new ServerSocket(puerto);
            System.out.println("Esperando cliente");
            socket = server.accept();

            flujoEnviar = new PrintWriter(socket.getOutputStream(), true);
            flujoEnviar.println("Servidor esta disponible ...(fin para terminar)");
            
            flujoRecibir = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            while (true) {
                
                flujoEnviar.println("Enviame un msg al servidor: ");
                msgDelCliente = flujoRecibir.readLine();
                
                if (!msgDelCliente.equals("fin")) {
                    System.out.println("Cliente dice: "+ msgDelCliente);
                    
                    System.out.print("Ingresa msg para el cliente: ");
                    msgDelServidor = teclado.nextLine();
                    
                    flujoEnviar.println("Servidor dice: "+ msgDelServidor);
                }else{
                    flujoEnviar.println("Hasta al vista baby...");
                    System.out.println("El cliente se desconecto");
                    
                    break;
                }
            }     
            flujoRecibir.close();
            flujoEnviar.close();
            socket.close();

        } catch (IOException ex) {
            System.out.println("Puerto " + puerto + " ocupado");
        }
    }
    public static void main(String[] args) {
        serverSocket s1 = new serverSocket();
        //s1.initServerSoloUnMsg(9090);
        s1.initServerMsgContinuo(9090);
    }
}

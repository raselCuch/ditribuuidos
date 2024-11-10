/*
 
    SI UN CLIENTE TELNET SE CONECTA, UN CMD, AHI MISMO PUEDE RECONECTARSE
    SI UN SEGUNDO SE CONECTA OTRO CMD, SE BLOQUE POR QUE EL PRIMERO ESTA SIENDO
    ATENDIDO--SOLUCION HILOS -SIGUIENTE LAB

 */
package LAB04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author AORUS
 */
public class multiServer {

    public void initServer(int puerto) {

        ServerSocket server = null;
        Socket socket = null;
        
        PrintWriter flujoEnviar = null;
        BufferedReader flujoRecibir = null;
        
        String msgCliente="";
        boolean stop = false;

        try {
            server = new ServerSocket(puerto);
            System.out.println("Esperando Clientes...");
            while (!stop) {
                 socket = server.accept();
                 flujoEnviar =new PrintWriter(socket.getOutputStream(), true);
                 
                 flujoEnviar.println("Hola cliente! ");
                 flujoEnviar.println("Envia un msg:  ");
                 
                 flujoRecibir = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                 
                 msgCliente = flujoRecibir.readLine();
                 System.out.println("Msg desde el cliente: "+msgCliente);
                 
                 flujoRecibir.close();
                 flujoEnviar.close();
                 socket.close();
            }
            server.close();

        } catch (IOException ex) {
            System.out.println(ex.toString());
        }
    }
    
    public static void main(String[] args) {
        multiServer ms = new multiServer();
        ms.initServer(6666);
    }
}

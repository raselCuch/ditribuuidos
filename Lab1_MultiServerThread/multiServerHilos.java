
package Lab1_MultiServerThread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;


public class multiServerHilos {
    
    public void initServer(int puerto){
        ServerSocket server = null;
        Socket socket = null;
              
        boolean detener = false;
        
        try {
            server = new ServerSocket(puerto);
            
            while(!detener){
                System.out.println("Esperando clientes....");
                socket = server.accept();
                System.out.println("Cliente conectado");
                ClienteThread cliente = new ClienteThread(socket);
                cliente.start();
            }
            server.close();                        
        } catch (IOException ex) {
            Logger.getLogger(multiServerHilos.class.getName()).log(
                    Level.SEVERE, null, ex);
        }                    
    }        
    
    public static void main(String[] args) {
        multiServerHilos m1 = new multiServerHilos();
        m1.initServer(9999);
    }
    
}

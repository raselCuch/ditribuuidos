
package Lab1_MultiServerThread;

import pruebas.hilos.contar5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


public class multiServerHilos { // servidor multihilos, OO
    private final List<PrintWriter> clientes = new ArrayList<>(); // Lista de clientes

    public void initServer(int puerto) {
        ServerSocket server = null;
        Socket socket = null;

        boolean detener = false;

        try {
            server = new ServerSocket(puerto);

            while (!detener) {
                System.out.println("Esperando clientes....");
                socket = server.accept();
                System.out.println("Cliente conectado");

                Thread hilo1 = new Thread(new ClienteThread(socket, clientes));
                hilo1.start();
            }
            if (server != null) {
                server.close();
            }
            server.close();
        } catch (IOException ex) {
            System.out.println("error: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        multiServerHilos m1 = new multiServerHilos();
        m1.initServer(9999);
    }

}

package Lab1_MultiServerThread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClienteThread implements Runnable {
    private final Socket socket;
    private final List<PrintWriter> clientesList;
    private PrintWriter out;
    private BufferedReader input;

    public ClienteThread(Socket socket, List<PrintWriter> clientesList) {
        this.socket = socket;
        this.clientesList = clientesList;
    }

    @Override
    public void run() {
        try (Scanner scanner = new Scanner(System.in); // forma corta
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            this.out = out;
            this.input = input;
            synchronized (clientesList) { // evita condiciones de carrera
                clientesList.add(out);
            }

            enviarMensajeACliente("Hola cliente! Envia 'fin' para terminar la conexión.");

            String msgCliente;
            do {
                msgCliente = recibirMensajeCliente();
                if (msgCliente != null) {
                    System.out.println("Msg del cliente: " + msgCliente); // servidor
                    enviarMensajeACliente(obtenerMensajeDelServidor(scanner));
                    enviarMensajeATodos("Cliente dice: " + msgCliente);
                }
            } while (msgCliente != null && !msgCliente.equalsIgnoreCase("fin"));

        } catch (IOException ex) {
            System.out.println("error: " + ex.getMessage());
        } finally {
            System.out.println("error: ");
            cerrarConexion();
        }
    }

    private String recibirMensajeCliente() throws IOException {
        return input.readLine();
    }

    private void enviarMensajeATodos(String mensaje) {
        synchronized (clientesList) {
            for (PrintWriter cliente : clientesList) {
                cliente.println(mensaje);
            }
        }
    }

    private void enviarMensajeACliente(String mensaje) {
        out.println(mensaje);
    }

    private String obtenerMensajeDelServidor(Scanner scanner) {
        System.out.print("Mensaje para el cliente: ");
        return scanner.nextLine();
    }

    private void cerrarConexion() {
        try {
            synchronized (clientesList) {
                clientesList.remove(out); // Remueve cliente de lista compartida
            }
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
        } catch (IOException ex) {
            System.out.println("error: ");
        }
    }
}

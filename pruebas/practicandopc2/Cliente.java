package pruebas.practicandopc2;

import java.io.*;
import java.net.Socket;

public class Cliente implements Runnable {
    private static int contadorClientes = 0;  // Contador estático para generar identificadores únicos
    private int id;
    private int mensajesRecibidos;
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    private Servidor servidor;

    public Cliente(Socket socket, Servidor servidor) {
        this.id = ++contadorClientes;  // Incrementar el contador para cada nuevo cliente
        this.mensajesRecibidos = 0;  // Inicializamos el contador de mensajes en 0
        this.socket = socket;
        this.servidor = servidor;   // Asignar el servidor

        try {
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            System.out.println("Error al crear los flujos de I/O para el cliente " + id);
        }
    }

    public int getId() {
        return id;
    }

    public void recibirMensaje() {
        mensajesRecibidos++;
    }

    public int getMensajesRecibidos() {
        return mensajesRecibidos;
    }

    public void resetearContadorMensajes() {
        mensajesRecibidos = 0;  // Resetea el contador de mensajes
    }

    @Override
    public void run() {
        try {
            String mensaje;
            while ((mensaje = in.readLine()) != null) {
                System.out.println("Cliente " + String.format("C%04d", id) + ": " + mensaje);
                mensajesRecibidos++;  // Contabilizamos cada mensaje recibido
                // Enviar respuesta al cliente (esto puede modificarse según los requerimientos)

                out.println("Mensaje recibido: " + mensaje);
            }
        } catch (IOException e) {
            System.out.println("Error al leer mensaje del cliente " + id);
        } finally {
            try {
                in.close();
                out.close();
                socket.close();
                // Notificar al servidor que el cliente se desconectó
                servidor.clienteDesconectado(this);
            } catch (IOException e) {
                System.out.println("Error al cerrar conexión con el cliente " + id);
            }
        }
    }

    @Override
    public String toString() {
        return "Cliente{id=" + String.format("C%04d", id) + ", mensajesRecibidos=" + mensajesRecibidos + "}";
    }
}

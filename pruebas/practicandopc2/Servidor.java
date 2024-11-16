package pruebas.practicandopc2;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;
import java.net.Socket;

public class Servidor {
    private ServerSocket serverSocket;
    private int puerto;
    private boolean activo;
    private List<Cliente> clientesConectados;
    private List<Cliente> clientesDesconectados;

    public Servidor() {
        this.puerto = 0; // Puerto sin asignar inicialmente
        this.activo = false;
        this.clientesConectados = new ArrayList<>();
        this.clientesDesconectados = new ArrayList<>();
    }

    public void asignarPuerto(int puerto) throws IllegalArgumentException {
        if (puerto < 1 || puerto > 65535) {
            throw new IllegalArgumentException("El puerto debe estar entre 1 y 65535.");
        }

        try (ServerSocket testSocket = new ServerSocket(puerto)) {
            // Si no se lanza una excepción, el puerto está disponible
            this.puerto = puerto;
        } catch (IOException e) {
            throw new IllegalArgumentException("El puerto " + puerto + " está en uso o no es válido.");
        }
    }

    public void activarServidor() throws IOException {
        if (puerto == 0) {
            throw new IllegalStateException("Debe asignar un puerto antes de activar el servidor.");
        }
        serverSocket = new ServerSocket(puerto);
        activo = true;
        System.out.println("Servidor activado en el puerto: " + puerto);

        // Aceptar conexiones de clientes en un hilo separado
        new Thread(() -> {
            try {
                while (activo) {
                    Socket socketCliente = serverSocket.accept();
                    Cliente cliente = new Cliente(socketCliente, this);
                    clientesConectados.add(cliente);

                    Thread hilo = new Thread(cliente);
                    hilo.start();
                }

            } catch (IOException ex) {
                System.out.println("error: " + ex.getMessage());
            }
        }).start();
    }

    public void desactivarServidor() throws IOException {
        if (serverSocket != null) {
            serverSocket.close();
        }
        activo = false;
        System.out.println("Servidor desactivado.");
    }

    public synchronized void clienteDesconectado(Cliente cliente) {
        clientesConectados.remove(cliente); // Quitar de la lista de conectados
        clientesDesconectados.add(cliente); // Agregar a la lista de desconectados
    }

    public synchronized String obtenerEstadoClientes() {
        return "Clientes conectados: " + clientesConectados.size() + ", Clientes desconectados: " + clientesDesconectados.size();
    }

    public synchronized String obtenerMensajesPorCliente() {
        StringBuilder resumen = new StringBuilder();
        resumen.append("Mensajes recibidos por cliente:\n");
        resumen.append("Clientes conectados: \n");
        for (Cliente cliente : clientesConectados) {
            resumen.append("Cliente ").append(String.format("C%04d", cliente.getId()))
                    .append(": ").append(cliente.getMensajesRecibidos())
                    .append(" mensajes\n");
        }

        resumen.append("\nClientes desConectados: \n");
        for (Cliente cliente : clientesDesconectados) {
            resumen.append("Cliente ").append(String.format("C%04d", cliente.getId()))
                    .append(": ").append(cliente.getMensajesRecibidos())
                    .append(" mensajes\n");
        }

        return resumen.toString();
    }

    public boolean estaActivo() {
        return activo;
    }

    public int getClientesConectados(){
        return clientesConectados.size();
    }
}

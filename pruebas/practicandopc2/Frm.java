package pruebas.practicandopc2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/*
Nota importante:
Este programa solo acepta múltiples conexiones de clientes, pero no procesa los mensajes que envían.
Esto se debe a que el ejercicio no lo solicita.

¿Cómo demostrar conexiones y desconexiones?
1. Al usar 'telnet localhost 9999' en un terminal, se establece una conexión con el servidor.
2. Al ingresar 'localhost:9999' en un navegador, se conecta y luego desconecta automáticamente.

De esta forma, se demuestra que el conteo de conexiones y desconexiones funciona correctamente,
sin necesidad de implementar la funcionalidad para escuchar los mensajes del cliente.
*/

public class Frm {

    public static void main(String[] args) {
        // Crea frame
        JFrame frame = new JFrame("Gestión del Servidor");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new GridLayout(6, 2)); // Disposición en una cuadrícula
        frame.setLocationRelativeTo(null);

        // Instancia de Servidor
        Servidor servidor = new Servidor();

        // crea componentes de frame
        JLabel lblEstado = new JLabel("Estado del Servidor:");
        JLabel lblEstadoValor = new JLabel("Desactivado");
        JLabel lblPuerto = new JLabel("Puerto:");
        JTextField txtPuerto = new JTextField();
        JLabel lblClientes = new JLabel("Clientes Conectados:");
        JLabel lblClientesValor = new JLabel("0");

        //
        JButton btnActivar = new JButton("Activar Servidor");
        JButton btnDesactivar = new JButton("Desactivar Servidor");
        JButton btnAsignarPuerto = new JButton("Asignar Puerto");
        JButton btnActualizarClientes = new JButton("Actualizar Estado");
        JButton btnMostrarMensajes = new JButton("Ver mensajes recibidos");

        // Agregar componentes al frame
        frame.add(lblEstado);
        frame.add(lblEstadoValor);
        frame.add(lblPuerto);
        frame.add(txtPuerto);
        txtPuerto.setText("9999");
        frame.add(lblClientes);
        frame.add(lblClientesValor);
        frame.add(btnActivar);
        frame.add(btnDesactivar);
        frame.add(btnAsignarPuerto);
        frame.add(btnActualizarClientes);
        frame.add(btnMostrarMensajes);

        btnActivar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Thread(() -> { // activación del servidor en hilo aparte, porque sino se congela el frm
                    try {
                        servidor.activarServidor();

                        SwingUtilities.invokeLater(() -> { // actualizar lbl aun estando en otro hilo
                            lblEstadoValor.setText("Activado");
                            JOptionPane.showMessageDialog(frame, "El servidor se ha activado en el puerto " + txtPuerto.getText(), "Estado del Servidor", JOptionPane.INFORMATION_MESSAGE);
                        });
                    } catch (IllegalStateException | IOException ex) {
                        SwingUtilities.invokeLater(() -> {
                            JOptionPane.showMessageDialog(frame, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        });
                    }
                }).start();
            }
        });

        btnDesactivar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    servidor.desactivarServidor();
                    lblEstadoValor.setText("Desactivado");
                    JOptionPane.showMessageDialog(frame, "El servidor se ha desactivado.", "Estado del Servidor", JOptionPane.INFORMATION_MESSAGE);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(frame, "Error al desactivar el servidor: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnAsignarPuerto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String puertoTexto = txtPuerto.getText();
                int puerto = 0;
                try {
                    puerto = Integer.parseInt(puertoTexto);
                    if (puerto > 0 && puerto <= 65535) {
                        servidor.asignarPuerto(puerto);
                        JOptionPane.showMessageDialog(frame, "Puerto asignado: " + puerto, "Asignación de Puerto", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(frame, "El puerto debe estar entre 1 y 65535.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(frame, "El puerto " + puerto + " está en uso o no es válido.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnActualizarClientes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String estadoClientes = servidor.obtenerEstadoClientes();
                lblClientesValor.setText(String.valueOf(servidor.getClientesConectados()));
                JOptionPane.showMessageDialog(frame, estadoClientes, "Estado de los Clientes", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        btnMostrarMensajes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String estadoMensajes = servidor.obtenerMensajesPorCliente();
                JOptionPane.showMessageDialog(frame, estadoMensajes, "Mensajes por Cliente", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        frame.setVisible(true);
    }

}

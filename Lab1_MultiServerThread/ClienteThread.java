    package Lab1_MultiServerThread;

    import java.io.BufferedReader;
    import java.io.IOException;
    import java.io.InputStreamReader;
    import java.io.PrintWriter;
    import java.net.Socket;
    import java.util.logging.Level;
    import java.util.logging.Logger;

    public class ClienteThread extends Thread {

        private Socket socket = null;

        public ClienteThread(Socket socket) {
            this.socket = socket;
        }

        public void run() {
            String msgCliente="";
            PrintWriter out  = null;

            try {
                out = new PrintWriter(socket.getOutputStream(), true);
                out.println("Hola cliente");
                BufferedReader input = new BufferedReader(new
                                            InputStreamReader(socket.getInputStream()));
                do{
                    msgCliente = input.readLine();
                    System.out.println("Msg del cliente: " + msgCliente);
                } while (!msgCliente.equalsIgnoreCase("fin"));
                input.close();
                out.close();
                socket.close();

            } catch (IOException ex) {
                Logger.getLogger(ClienteThread.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

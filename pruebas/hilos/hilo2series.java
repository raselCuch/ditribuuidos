package pruebas.hilos;
/*
Ejercicio 2: Crea dos hilos que impriman números de dos series distintas (1 al 5 y 6 al 10).

Usa dos clases diferentes que implementen Runnable.
Inicia los dos hilos y observa el comportamiento de concurrencia.
 */
public class hilo2series implements Runnable{

    int inicio;
    int fin;

    String nombreHilo;

    public hilo2series(int inicio, int fin, String nombreHilo) {
        this.inicio = inicio;
        this.fin = fin;
        this.nombreHilo = nombreHilo;
    }

    @Override
    public void run() {
        for (int i = inicio; i <= fin; i++) {
            System.out.println("hilo" + nombreHilo + ": " + i);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void main(String[] args) {
        Thread hilo1 = new Thread(new hilo2series(1, 5, "hilo1"));
        Thread hilo2 = new Thread(new hilo2series(6, 10, "hilo2"));

        hilo1.start();
        hilo2.start();
    }

}

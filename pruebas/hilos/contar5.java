package pruebas.hilos;


/*
* Ejercicio 1: Crear un hilo que imprima números del 1 al 5 con un retraso de 1 segundo entre cada número.

Extiende la clase Thread o implementa Runnable.
Usa Thread.sleep(1000) para el retraso.
* */
public class contar5 implements Runnable {

    String nombreHilo;

    public contar5(String nombreHilo) {
        this.nombreHilo = nombreHilo;
    }

    @Override
    public void run() {

        for (int i = 0; i < 5; i++) {
            System.out.println("" + nombreHilo + ": " + i);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }

    public static void main(String[] args) {
        Thread hilo1 = new Thread(new contar5("hilo1")); //
        Thread hilo2 = new Thread(new contar5("hilo2")); //

        hilo1.start();
        hilo2.start();
    }

}

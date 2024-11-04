
package file;

import java.util.logging.Level;
import java.util.logging.Logger;

public class hiloImplements implements Runnable {
    String id;

    public hiloImplements(String id) {
        this.id = id;
    }
    public void run(){
        for(int i = 0; i <= 10; i++){
            System.out.println("Proceso actual ID: "+this.id);
            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                Logger.getLogger(hiloImplements.class.getName()).log(Level.SEVERE, null, ex);
            }
        }        
    }
    
    public static void main(String[] args) {
        
        Thread h1 = new Thread(new hiloImplements("1"));        
        hiloImplements h2 = new hiloImplements("2");
        Thread hilo2 = new Thread(h2);
        
        h1.start();
        hilo2.start();
        
    }        
}

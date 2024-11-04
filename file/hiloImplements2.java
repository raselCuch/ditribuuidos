
package file;

import java.util.logging.Level;
import java.util.logging.Logger;

public class hiloImplements2 implements Runnable {
    String id;

    public hiloImplements2(String id) {
        this.id = id;
    }
    public void run(){
        for(int i = 0; i <= 10; i++){
            System.out.println("Proceso actual ID: "+this.id);
            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                Logger.getLogger(hiloImplements2.class.getName()).log(Level.SEVERE, null, ex);
            }
        }        
    }
    
    public void start(){
       Thread hilo = new Thread(this);
       hilo.start();
    }
    
    public static void main(String[] args) {
        
        hiloImplements2 h1 = new hiloImplements2("1");        
        hiloImplements2 h2 = new hiloImplements2("2");
                
        h1.start();
        h2.start();
        
    }        
}

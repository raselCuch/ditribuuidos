
package file;

import java.util.logging.Level;
import java.util.logging.Logger;

public class hiloExtends extends Thread {
    String id;

    public hiloExtends(String id) {
        this.id = id;
    }
    public void run(){
        for(int i = 0; i <= 10; i++){
            System.out.println("Proceso actual ID: "+this.id);
            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                Logger.getLogger(hiloExtends.class.getName()).log(Level.SEVERE, null, ex);
            }
        }        
    }
    
    public static void main(String[] args) {
        
        hiloExtends h1 = new hiloExtends("1");        
        hiloExtends h2 = new hiloExtends("2");
        
        h1.start();
        h2.start();
        
    }        
}

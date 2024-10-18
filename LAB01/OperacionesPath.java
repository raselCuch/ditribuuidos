
package LAB01;

import java.nio.file.Path;
import java.nio.file.Paths;

public class OperacionesPath {
    
    public static Path StringAPath(String cpath){
        Path path = Paths.get(cpath);
        return path;
    }

    public static void mostrarInformacion(Path path){ //
        System.out.println("Archivo: \t"+path.getFileName());
        System.out.println("Nombre(1): \t"+path.getName(1));
        System.out.println("Cadena: \t"+path.toString());
        System.out.println("Ruta Padre: \t"+path.getParent());
        System.out.println("Ruta Raiz: \t"+path.getRoot());                
    }
            
    public static void main(String[] args) {
        
        Path path = StringAPath("c:\\curso\\java\\operadores\\arit.java");
        mostrarInformacion(path);
        
    }    
}

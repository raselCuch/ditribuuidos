
package LAB01;

import java.nio.file.Path;
import java.nio.file.Paths;

public class OperacionesPath {
    public static Path stringToPath(String pathString){
        return Paths.get(pathString);
    }

    public static void mostrarPathInformacion(Path path){
        System.out.println("Archivo: \t"+path.getFileName());

        System.out.println("Nombre (índice 1): \t"+path.getName(0));
        System.out.println("Cadena: \t"+path.toString());
        System.out.println("Ruta Padre: \t"+path.getParent());
        System.out.println("Ruta Raiz: \t"+path.getRoot());                
    }
            
    public static void main(String[] args) {
        Path path = stringToPath("D:\\Universidad\\caraturla.pdf");
//        Path path = stringToPath("D:\\Universidad");
        mostrarPathInformacion(path);
    }

}

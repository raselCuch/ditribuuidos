
package LAB01;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;


public class Directorios {

     public static Path StringAPath(String cpath){ // convierte string a path
        Path path = Paths.get(cpath);
        return path;
    }
     
    public static void evaluaDirectorioArchivo(File file){ //
        
        if (file.exists())    {
            System.out.println("Ruta valida: "+ file.toString());
            if (file.isDirectory())
                System.out.println("Es una carpeta o directorio");
            if (file.isFile())
                System.out.println("Es una archivo");
        }else{
            System.out.println("Ruta no valida");
        }
    }
     
    public static void listaContenidoDirectorio(File file){
        
        String[] lista = file.list();
        if (lista == null || lista.length == 0)
            System.out.println("Sin elemantos");
        else{
            for (int i = 0;i < lista.length;i++)
                System.out.println(lista[i]);
        }
    }
    
    public static void main(String[] args) {
         // experimentar por posible examen
//        File file1 = new File("D:\\UNU");
        File file1 = new File("D:\\Universidad");
//        File file2 = new File("D:\\UNU\\firmas\\Dni Img\\anverso.jpeg");
        File file2 = new File("D:\\Universidad");
        evaluaDirectorioArchivo(file1);
        evaluaDirectorioArchivo(file2);
        System.out.println("====== CONTENIDO DE FILE1======");
        listaContenidoDirectorio(file1);
    }
}

//BasicFileAttributes, extension, info extra
//JFileChooser, para ver solo un tipo de archivo. e indicar un path (carpeta o file)
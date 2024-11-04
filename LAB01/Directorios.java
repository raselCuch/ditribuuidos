
package LAB01;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;


public class Directorios {

     public static Path convertirStringAPath(String ruta){ // convierte string a path
         return Paths.get(ruta);
    }

    public static void verificarArchivoODirectorio(File ruta) {
        if (!ruta.exists()) {
            System.out.println("Ruta no válida.");
            return;
        }

        System.out.println("Ruta válida: " + ruta);
        if (ruta.isDirectory()) {
            System.out.println("Es un directorio.");
        }
        if (ruta.isFile()) {
            System.out.println("Es un archivo.");
        }
    }


    public static void listaContenidoDirectorio(File directorio){
        if (!directorio.isDirectory()) {
            System.out.println("El archivo no es un directorio.");
            return;
        }

        String[] contenidoList = directorio.list();

        if (contenidoList == null || contenidoList.length == 0) {
            System.out.println("El directorio está vacío.");
            return;
        }

        System.out.println("Contenido del directorio:");
        for (String elemento : contenidoList) { // int i = 0;i < contenidoList.length;i++
            System.out.println(elemento);
        }
    }
    
    public static void main(String[] args) {
        File file1 = new File("D:\\Universidad");
        File file2 = new File("D:\\Universidad\\caraturla.pdf");
        verificarArchivoODirectorio(file1);
        verificarArchivoODirectorio(file2);

        System.out.println("====== CONTENIDO DE FILE1======");
        listaContenidoDirectorio(file1);

        System.out.println("====== CONTENIDO DE FILE2======");
        listaContenidoDirectorio(file2);
    }
}

//BasicFileAttributes, extension, info extra
//JFileChooser, para ver solo un tipo de archivo. e indicar un path (carpeta o file)
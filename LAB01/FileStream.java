
package LAB01;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileStream {
    // leer archivo
     public static void verificarArchivoODirectorio(File ruta){
         if (!ruta.exists()) {
             System.out.println("Ruta no válida.");
             return;
         }

         System.out.println("Ruta válida: " + ruta.toString());
         if (ruta.isDirectory()) {
             System.out.println("Es un directorio.");
         }
         if (ruta.isFile()) {
             System.out.println("Es un archivo.");
         }
    }
    
    public static void leerArchivo(File archivo){
        if (archivo == null) {
            System.out.println("El archivo no es válido.");
            return;
        }

        try (FileInputStream flujoEntrada = new FileInputStream(archivo)) { // abre y cierra automaticamente
            int byteLeido;
            while ((byteLeido = flujoEntrada.read()) != -1) { // recorre hasta que no halla
                System.out.print((char) byteLeido);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado.");
        } catch (IOException e) {
            System.out.println("Error al leer el archivo.");
        }
    }
    public static void main(String[] args) {
//        leerArchivo(new File("D:\\Universidad"));
        leerArchivo(new File("D:\\Universidad\\prueba.txt"));
    }

}

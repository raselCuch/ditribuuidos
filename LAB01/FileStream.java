
package LAB01;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


public class FileStream {
    // leer archivo
     public static void evaluaDirectorioArchivo(File file){
        
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
    
    public static void leerArchivo(File file){
        if (file != null){
            try {
                FileInputStream fis = new FileInputStream(file);
                int dato = 0;
                
                while(true){
                    dato = fis.read();
                    if (dato == -1)                        
                        break;
                    System.out.print(""+(char)dato);
                }
                fis.close();
            } catch (FileNotFoundException ex) {
                System.out.println("Archivo no encontrado");
            } catch (IOException ex) {
                System.out.println("Error en al lectura del contenido");
            }            
        }else
            System.out.println("El sistema no sabe nada del archivo");
    }
    public static void main(String[] args) {
        
        //evaluaDirectorioArchivo(new File("D:\\test\\prueba.txt"));
//        leerArchivo(new File("D:\\test\\prueba.txt"));
//        leerArchivo(new File("D:\\Universidad\\prueba.txt"));
        leerArchivo(new File("D:\\Universidad\\Base Word.docx"));
    }
}

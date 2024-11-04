package LAB02;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LecturaEscrituraBuffer {

     public void leerPorCaracter(File archivo) {
         try (FileInputStream fileInputStream  = new FileInputStream(archivo);
              BufferedReader bufferedReader =
                      new BufferedReader(new InputStreamReader(fileInputStream , "utf-8"))) { // permite leer linea completa

             int caracter;
             while ((caracter = bufferedReader.read()) != -1) {
                 System.out.print((char) caracter);
             }
         } catch (FileNotFoundException e) {
             System.out.println("Archivo no ubicado");
         } catch (IOException e) {
             System.out.println("Error de Entrada o Salida");
         }
    }

    public void leerPorLinea(File archivo) {
        try (FileInputStream fileInputStream  = new FileInputStream(archivo);
             BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream , "utf-8"))) {

            String linea;
            while ((linea = bufferedReader.readLine()) != null) {
                System.out.println(linea);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Archivo no ubicado");
        } catch (IOException e) {
            System.out.println("Error de Entrada o Salida");
        }
    }

    public void escribirDatos(File archivo, String contenido) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(archivo, false)) { // false: sobre escribe, true: agrega al final
            fileOutputStream.write(contenido.getBytes("UTF-8")); // Escribe directamente

//            int dato;
//            for (int c = 0; c < contenido.length(); c++) {
//                dato = contenido.charAt(c);
//                fileOutputStream.write(dato);
//            }
        } catch (FileNotFoundException e) {
            System.out.println("Archivo no Ubicado");
        } catch (IOException e) {
            System.out.println("Error de entrada o Salida");
        }
    }

    public static void main(String[] args) {
        LecturaEscrituraBuffer buffer = new LecturaEscrituraBuffer();
        File file = new File("D:\\Universidad\\archivo2.txt"); // Cambia la ruta según tu sistema

        // Escribir datos en el archivo
        buffer.escribirDatos(file, "Hola, este es un texto de prueba2.\n");

        // Leer el archivo carácter por carácter
        System.out.println("Lectura carácter por carácter:");
        buffer.leerPorCaracter(file);

        // Leer el archivo línea por línea
        System.out.println("\nLectura línea por línea:");
        buffer.leerPorLinea(file);
    }

}

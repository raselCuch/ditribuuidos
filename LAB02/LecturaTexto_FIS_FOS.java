package LAB02;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LecturaTexto_FIS_FOS {

    public void leerDatos(File file) {
        try (FileInputStream fis = new FileInputStream(file)) {
            int dato;

            while ((dato = fis.read()) != -1) {
                System.out.print((char) dato);
            }

        } catch (FileNotFoundException ex) {
            System.out.println("Archivo no ubicado");
        } catch (IOException ex) {
            System.out.println("Error de Entrada o Salida");
        }
    }

    public void escribirDatos(File file, String cadena) {
        try (FileOutputStream fos = new FileOutputStream(file, true)) { // true: agrega, false: sobre escribe
            for (char c : cadena.toCharArray()) { // int c = 0; c < cadena.length(); c++
                fos.write(c);  // dato = cadena.charAt(c);
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Archivo no Ubicado");
        } catch (IOException ex) {
            System.out.println("Error de entrada o Salida");
        }
    }

    public static void main(String[] args) {
        LecturaTexto_FIS_FOS lectorEscritor = new LecturaTexto_FIS_FOS();

        File file = new File("D:\\Universidad\\archivo.txt");

        lectorEscritor.escribirDatos(file, "Este es un texto de prueba3.\n"); // escribe en archivo
        lectorEscritor.leerDatos(file); // leyendo archivo
    }
}

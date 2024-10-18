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
        try {
            FileInputStream fis = new FileInputStream(file);
            int dato;

            while (true) {
                dato = fis.read();
                if (dato != -1) {
                    System.out.println("" + (char) dato);
                } else {
                    break;
                }
            }
            fis.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Archivo no ubicado");
        } catch (IOException ex) {
            System.out.println("Error de Entrada o Salida");
        }
    }

    public void escribirDatos(File file, String cadena) {

        int dato;
        try {
            FileOutputStream fos = new FileOutputStream(file, true);
            for (int c = 0; c < cadena.length(); c++) {
                dato = cadena.charAt(c);
                fos.write(dato);
            }
            fos.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Archivo no Ubicado");
        } catch (IOException ex) {
            System.out.println("Error de entrada o Salida");
        }
    }

    public static void main(String[] args) {

    }
}

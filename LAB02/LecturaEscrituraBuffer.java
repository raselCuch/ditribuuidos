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

     public void leerXCaracter(File file) {
        try {
            FileInputStream fis = new FileInputStream(file);
            BufferedReader bf = new BufferedReader(new InputStreamReader(fis,"utf-8"));
            
            int dato;

            while (true) {
                dato = bf.read();
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
    
    public void leerCadena(File file) {
        try {
            FileInputStream fis = new FileInputStream(file);
            BufferedReader bf = new BufferedReader(new InputStreamReader(fis,"utf-8"));
            
            String cadena="";

            while (true) {
                cadena = bf.readLine();                
                if (cadena != null) {
                    System.out.println("" + cadena);
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

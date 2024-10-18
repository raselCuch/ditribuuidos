package LAB02;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MoverArchivo {

    static FileInputStream fis = null;
    static FileOutputStream fos = null;
    static long[] bytes = null;

    public static int contadorBytes(File file) {

        int nBytes = 0;
        int elByte = 0;

        try {
            fis = new FileInputStream(file);
            while (true) {
                elByte = fis.read();
                if (elByte != -1) {
                    nBytes++;
                } else {
                    break;
                }
            }
            fis.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Archivo no encontrado");
        } catch (IOException ex) {
            System.out.println("Error I/O en contadorBytes()");
        }
        return nBytes;
    }

    public static void leerBytes(File file) {

        BasicFileAttributes attr = null;

        try {
            attr = Files.readAttributes(file.toPath(), BasicFileAttributes.class);

        } catch (IOException ex) {
            System.out.println("Error I/O leerBytes()");
        }

        int elByte = 0, idxBytes = 0;
        long cantBytes = attr.size();

        try {
            fis = new FileInputStream(file);
        } catch (FileNotFoundException ex) {
            System.out.println("Archivo no encontrado");
        }

        bytes = new long[(int) cantBytes];

        while (true) {
            try {
                elByte = fis.read();
                if (elByte == -1) {
                    break;
                } else {
                    bytes[idxBytes] = elByte;
                    idxBytes++;
                }
            } catch (IOException ex) {
                System.out.println("Error I/O leerBytes()");
            }
        }
    }

    public static void escribirArchivo(File file) {

        try {
            fos = new FileOutputStream(file);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MoverArchivo.class.getName()).log(Level.SEVERE, null, ex);
        }

        for (int idx = 0; idx < bytes.length; idx++) {
            try {
                fos.write((int) bytes[idx]);
            } catch (IOException ex) {
                Logger.getLogger(MoverArchivo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static void main(String[] args) {

    }
}

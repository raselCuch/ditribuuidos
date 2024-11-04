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
    static byte[] fileContent = null;

    public static int contadorBytes(File file) {
        int byteCount = 0;
        try (FileInputStream fis = new FileInputStream(file)) {
            while (fis.read() != -1) {
                byteCount++;
            }
        } catch (IOException ex) {
            System.out.println("Error al contar bytes: " + ex.getMessage());
        }
        return byteCount;
    }

    public static void leerBytes(File file) {
        try {
            long fileSize = Files.size(file.toPath());
            fileContent = new byte[(int) fileSize];

            try (FileInputStream fis = new FileInputStream(file)) {
                int bytesRead = fis.read(fileContent); // captura el conteniedo
                if (bytesRead == -1) {
                    System.out.println("No se pudo leer el archivo.");
                }
            }
        } catch (IOException ex) {
            System.out.println("Error al leer archivo: " + ex.getMessage());
        }
    }

    public static void escribirArchivo(File destino) {
        try (FileOutputStream fos = new FileOutputStream(destino, true)) { // true, false
            fos.write(fileContent);
        } catch (IOException ex) {
            Logger.getLogger(MoverArchivo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        File archivoOrigen = new File("D:\\Universidad\\archivoOrigen.txt");
        File archivoDestino = new File("D:\\Universidad\\archivoDestino.txt");

        leerBytes(archivoOrigen);
        escribirArchivo(archivoDestino);

        System.out.println("Operación completada.");
    }
}

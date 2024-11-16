package pruebas.I1;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.IOException;

public class ExploradorDirectorio {

    public static void main(String[] args) {
//        String rutaInicial = "D:\\Universidad\\AUDITORIA";
//        Directorio directorioRaiz = new Directorio(new File(rutaInicial));
//        directorioRaiz.mostrarContenido(0);

        JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        fileChooser.setDialogTitle("Selecciona el directorio de inicio");
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); // Solo seleccionar directorios

        int resultado = fileChooser.showOpenDialog(null);

        if (resultado != JFileChooser.APPROVE_OPTION) {
            System.out.println("No se seleccionó ningún directorio.");
            return;
        }

        // Obtener la ruta seleccionada
        String rutaInicial = fileChooser.getSelectedFile().getAbsolutePath();
        Directorio directorioRaiz = new Directorio(new File(rutaInicial));
        directorioRaiz.mostrarContenido(0);

        // Ejemplo de búsqueda de palabra
        String palabraABuscar = "ejemplo"; // Cambia por la palabra que deseas buscar
        int total = contarPalabraEnArchivos(directorioRaiz, palabraABuscar);
        System.out.println("La palabra '" + palabraABuscar + "' se encuentra " + total + " veces en los archivos.");
    }

    private static int contarPalabraEnArchivos(Contenido contenido, String palabra) {
        int conteoTotal = 0;

        if (contenido instanceof Archivo) {// si es arhivo
            try {
                conteoTotal += ((Archivo) contenido).contarPalabra(palabra);
            } catch (IOException e) {
                System.err.println("Error al leer el archivo: " + contenido.getNombre());
            }
        } else if (contenido instanceof Directorio) { // si es directorio
            File[] contenidoDirectorio = ((Directorio) contenido).getDirectorio().listFiles();
            if (contenidoDirectorio != null) {
                for (File file : contenidoDirectorio) {
                    Contenido item;
                    if (file.isDirectory()) {
                        item = new Directorio(file);
                    } else {
                        item = new Archivo(file);
                    }
                    conteoTotal += contarPalabraEnArchivos(item, palabra); // Llamada recursiva
                }
            }
        }

        return conteoTotal;
    }
}

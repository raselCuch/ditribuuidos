package pruebas.I1;

import java.io.File;
import java.io.IOException;

public class Directorio extends Contenido {
    private File directorio;

    public Directorio(File directorio) {
        super(directorio.getName());
        this.directorio = directorio;
    }

    @Override
    public void mostrarContenido(int nivel) {
        String indentacion = " ".repeat(nivel * 4); // 1er nivel
        System.out.println(indentacion + "Directorio: " + getNombre());

        File[] contenido = directorio.listFiles();
        if (contenido != null) {
            for (File archivo : contenido) {
                Contenido contenidoItem; // crea interface
                if (archivo.isDirectory()) { // instancia segun sea
                    contenidoItem = new Directorio(archivo);
                } else {
                    contenidoItem = new Archivo(archivo);
                }
                contenidoItem.mostrarContenido(nivel + 1); // aumenta un nivel
            }
        }
    }

    @Override
    public int contarPalabra(String palabra) {
        return 0; // No tiene sentido contar palabras en un directorio
    }

    public File getDirectorio() {
        return directorio;
    }
}

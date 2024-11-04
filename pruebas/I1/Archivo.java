package pruebas.I1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Archivo extends Contenido {
    private File file;

    public Archivo(File file) {
        super(file.getName());
        this.file = file;
    }

    @Override
    public void mostrarContenido(int nivel) {
        String indentacion = " ".repeat(nivel * 4);
        System.out.println(indentacion + "Archivo: " + getNombre());
    }

    @Override
    public int contarPalabra(String palabra) throws IOException {
        int conteo = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] palabras = linea.split("\\W+"); // Dividir por no caracteres de palabra
//                String[] palabras = linea.split(" "); // Dividir por no caracteres de palabra
                for (String p : palabras) {
                    if (p.equalsIgnoreCase(palabra)) {
                        conteo++;
                    }
                }
            }
        }
        return conteo;
    }
}

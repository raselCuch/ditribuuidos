package pruebas;

import java.io.File;

public class ExploradorDirectorio {

    public static void explorar(String ruta) {
        File directorio = new File(ruta);

        if (!directorio.exists()) {
            System.out.println("La ruta no es válida: " + ruta);
            return;
        }
        mostrarContenido(directorio, 0); // comienza en 0
    }

    private static void mostrarContenido(File directorio, int nivel) {
        String indentacion = " ".repeat(nivel * 4); // 4 espacios por nivel

        if (directorio.isDirectory()) {
            System.out.println(indentacion + "Directorio: " + directorio.getName());
            mostrarArchivosYDirectorios(directorio, nivel);
        } else {
            System.out.println(indentacion + "Archivo: " + directorio.getName());
        }
    }

    private static void mostrarArchivosYDirectorios(File directorio, int nivel) {
        File[] contenido = directorio.listFiles();
        if (contenido != null) {
            for (File archivo : contenido) {
                mostrarContenido(archivo, nivel + 1); // Llamar recursivamente
            }
        }
    }

    public static void main(String[] args) {
        String rutaInicial = "D:\\Universidad\\AUDITORIA"; // Cambia la ruta según tus necesidades
        explorar(rutaInicial);
    }

}

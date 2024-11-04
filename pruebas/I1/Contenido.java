package pruebas.I1;

import java.io.IOException;

public abstract class Contenido {
    protected String nombre;

    public Contenido(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public abstract void mostrarContenido(int nivel);
    public abstract int contarPalabra(String palabra) throws IOException;
}

package avion;

public class Asiento {
    int fila;
    char columna;
    boolean ocupado;

    //TODO: Crear un objeto Clase clase para manejar las categorias de asientos?? -A
    String clase;

    public Asiento(int fila, char columna, String clase) {
        this.fila = fila;
        this.columna = columna;
        this.clase = clase;
        this.ocupado = false;
    }

    public int getFila() {
        return fila;
    }

    public char getColumna() {
        return columna;
    }

    public boolean isOcupado() {
        return ocupado;
    }

    public String getClase() {
        return clase;
    }

    public String getCodigo(){
        return fila + Character.toString(columna); // 15A

    }
}

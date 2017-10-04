package avion;

public class Asiento {
    int fila;
    char columna;
    boolean ocupado;
    int DNI;

    ClaseDeAsiento clase;

    public Asiento(int fila, char columna, String clase) {
        this.fila = fila;
        this.columna = columna;
        this.clase = new ClaseDeAsiento(clase);
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

    public ClaseDeAsiento getClase() {
        return clase;
    }

    public String getFilaYColumna(){
        return fila + Character.toString(columna); // 15A

    }

    public void ocupar(int DNI){

        if(this.isOcupado()){
            //TODO: Custom exception "Asiento ya ocupado"
            throw new RuntimeException();
        }

        this.DNI = DNI;
        ocupado = true;
    }
}

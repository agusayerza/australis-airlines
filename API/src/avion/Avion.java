package avion;

import java.util.ArrayList;

public class Avion {
    private String patente;
    private ArrayList<ArrayList<Asiento>> mapaDeAsientos;
    private int cantidadDeAsientos;

    //  TODO: Pricing y catalogo de precios
    // Catalogo catalogoDePrecios;

    public Avion(String patente, int filasDeAsientos, int columnasDeAsientos) {
        this.patente = patente;

        cantidadDeAsientos = filasDeAsientos * columnasDeAsientos;

        for (int i = 0; i < filasDeAsientos; i++) {
            for (int j = 0; j < columnasDeAsientos; j++) {
                ArrayList<Asiento> fila = new ArrayList<>();

                //TODO: Clase de asientos, ver clase Avion.Asiento y modificar este constructor
                Asiento asiento = new Asiento(i + 1, columnaToChar(j), "Standard");
            }
        }
    }

    private char columnaToChar(int columna){
        return (char) (65 + columna);
    }
}

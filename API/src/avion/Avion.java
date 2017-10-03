package avion;

import java.util.HashMap;

public class Avion {
    private String patente;
    private HashMap<String, Asiento> mapaDeAsientos;

    private int cantidadDeFilasDeAsientos;
    private int cantidadDeColumnasDeAsientos;

    //  TODO: Pricing y catalogo de precios
    // Catalogo catalogoDePrecios;

    public Avion(String patente, int filasDeAsientos, int columnasDeAsientos) {
        this.patente = patente;
        this.cantidadDeColumnasDeAsientos = columnasDeAsientos;
        this.cantidadDeFilasDeAsientos = filasDeAsientos;

        for (int i = 0; i < cantidadDeFilasDeAsientos; i++) {
            for (int j = 0; j < cantidadDeColumnasDeAsientos; j++) {

                //TODO: Clase de asientos, ver clase Avion.Asiento y modificar este constructor
                Asiento asiento = new Asiento(i + 1, columnaToChar(j), "Standard");

                mapaDeAsientos.put(asiento.getCodigo(),asiento);

            }
        }
    }

    public String getPatente() {
        return patente;
    }

    public HashMap<String, Asiento> getMapaDeAsientos() {
        return mapaDeAsientos;
    }

    public int getCantidadDeFilasDeAsientos() {
        return cantidadDeFilasDeAsientos;
    }

    public int getCantidadDeColumnasDeAsientos() {
        return cantidadDeColumnasDeAsientos;
    }

    public String getAsientoLayout(){
        String output = "";

        for (int i = 0; i < cantidadDeFilasDeAsientos; i++) {
            for (int j = 0; j < cantidadDeColumnasDeAsientos; j++) {

                Asiento asiento;
                int fila = i + 1;
                String key = fila + Character.toString(columnaToChar(j));

                asiento = mapaDeAsientos.get(key);
                output += " - " + asiento.getCodigo() + "[";
                if (asiento.isOcupado()){
                    output += "X]";
                }else{
                    output += "O]";
                }

            }
            output += "\n";
        }

        return output;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Avion)) return false;

        Avion avion = (Avion) o;

        return patente.equals(avion.patente);
    }


    private char columnaToChar(int columna){
        return (char) (65 + columna);
    }


}

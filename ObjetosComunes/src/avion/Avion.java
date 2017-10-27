package avion;

import personas.Pasajero;

import java.util.HashMap;

public class Avion {
    private String patente;
    private Clase[] clases;
    private int numeroDeClases;

    public Avion(String patente, Clase[] clases) {
        this.patente = patente;
        this.clases = clases;
        this.numeroDeClases = clases.length;
    }

    public String getPatente() {
        return patente;
    }


    public HashMap<String, Asiento> getMapaDeAsientos() {
        HashMap<String, Asiento> mapaDeAsientos = new HashMap<>();
        for (Clase clase: clases) {
            mapaDeAsientos.putAll(clase.getMapaDeAsientos());
        }
        return mapaDeAsientos;
    }

    public Clase[] getClases() {
        return clases;
    }

    public int getNumeroDeClases() {
        return numeroDeClases;
    }

    public String getAsientoLayout(){
        String output = ""; //Spacing
        for (Clase clase: clases) {

            output += "\n \t " + clase.getNombreDeClase() + "\n";

            int cantidadDeColumnasDeAsientos = clase.getCantidadDeColumnasDeAsientos();
            int cantidadDeFilasDeAsientos = clase.getCantidadDeFilasDeAsientos();
            HashMap<String, Asiento> mapaDeAsientos = clase.getMapaDeAsientos();

            output += "\t ";
            for (int j = 0; j < cantidadDeColumnasDeAsientos; j++) {
                output += Character.toString(columnaToChar(j)) + "   ";
            }

            output += "\n";

            for (int i = 0; i < cantidadDeFilasDeAsientos; i++) {
                int fila = i + clase.getPrimeraFilaDeClase();
                output += fila + "  ";
                if(fila < 10){
                    output += " ";
                }
                for (int j = 0; j < cantidadDeColumnasDeAsientos; j++) {

                    Asiento asiento;

                    String key = fila + Character.toString(columnaToChar(j));
                    asiento = mapaDeAsientos.get(key);
                    if (asiento.isOcupado()){
                        output += "[X] ";
                    }else{
                        output += "[O] ";
                    }

                }
                output += "\n";
            }
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

    //TODO: Creo que columnaToChar no se usa mas en esta clase
    private char columnaToChar(int columna){
        return (char) (65 + columna);
    }

}

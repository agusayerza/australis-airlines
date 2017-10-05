package avion;

import java.util.HashMap;

public class Clase {
    private int cantidadDeFilasDeAsientos;
    private int cantidadDeColumnasDeAsientos;
    private String nombreDeClase;
    private HashMap<String, Asiento> mapaDeAsientos = new HashMap<>();

    private int ultimaFilaDeClase;
    private int primeraFilaDeClase;

    public Clase(int primerFilaDeClase, int cantidadDeAsientos, int cantidadDeFilasDeAsientos, String nombreDeClase) {
        if(primerFilaDeClase <= 0){
            //TODO: Custom exception "El numero de la primer fila de clase no puede ser negativo o 0"
            throw new RuntimeException();
        }

        this.primeraFilaDeClase = primerFilaDeClase;
        this.cantidadDeFilasDeAsientos = cantidadDeFilasDeAsientos;
        this.nombreDeClase = nombreDeClase;

        if(cantidadDeAsientos % cantidadDeFilasDeAsientos == 0){

            this.cantidadDeColumnasDeAsientos = cantidadDeAsientos / cantidadDeFilasDeAsientos;
            for (int i = 0; i < cantidadDeFilasDeAsientos; i++) {
                for (int j = 0; j < cantidadDeColumnasDeAsientos; j++) {

                    Asiento asiento = new Asiento(i + primerFilaDeClase, columnaToChar(j), nombreDeClase);
                    asiento.getFilaYColumna();

                    mapaDeAsientos.put(asiento.getFilaYColumna(),asiento);

                }
                ultimaFilaDeClase = primerFilaDeClase + i;
            }
        }else{
            //TODO: Custom exception "Cantidad de Asientos no distribuible en Cantidad de Filas"
            throw new RuntimeException();
        }

    }

    public void ocuparAsiento(String key){

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Clase)) return false;

        Clase clase = (Clase) o;

        return getNombreDeClase() != null ? getNombreDeClase().equals(clase.getNombreDeClase()) : clase.getNombreDeClase() == null;
    }


    public int getCantidadDeFilasDeAsientos() {
        return cantidadDeFilasDeAsientos;
    }

    public int getCantidadDeColumnasDeAsientos() {
        return cantidadDeColumnasDeAsientos;
    }

    public String getNombreDeClase() {
        return nombreDeClase;
    }

    public HashMap<String, Asiento> getMapaDeAsientos() {
        return mapaDeAsientos;
    }

    private char columnaToChar(int columna){
        return (char) (65 + columna);
    }

    public int getUltimaFilaDeClase() {
        return ultimaFilaDeClase;
    }

    public int getPrimeraFilaDeClase() {
        return primeraFilaDeClase;
    }
}

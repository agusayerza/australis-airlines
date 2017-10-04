package catalogo;


import vuelo.Vuelo;

import java.util.HashMap;

public class Catalogo {
    private HashMap<String,Vuelo> vuelos; // <codigoDeVuelo, vuelo>
    private HashMap<>
    public Catalogo() {
    }

    public void addVuelo(Vuelo vuelo){
        if(vuelos.containsKey(vuelo.getCodigoDeVuelo())){
            //TODO: Custom exception "Un vuelo con este codigo ya existe"
            throw new RuntimeException();
        }

        vuelos.put(vuelo.getCodigoDeVuelo(),vuelo);

    }

    public void removeVuelo(String codigoDeVuelo){
        if(vuelos.containsKey(codigoDeVuelo)){
            //TODO: Custom exception "No existe un vuelo con este codigo"
            throw new RuntimeException();
        }

        vuelos.remove(codigoDeVuelo);
    }
}

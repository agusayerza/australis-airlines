package catalogo;


import vuelo.Vuelo;

import CustomExceptions.FlightCodeAlreadyExistsException;
import CustomExceptions.FlightCodeNonexistentException;

import java.util.HashMap;

public class Catalogo {
    private HashMap<String,Vuelo> vuelos; // <codigoDeVuelo, vuelo>
    public Catalogo() {
    }

    public void addVuelo(Vuelo vuelo){
        if(vuelos.containsKey(vuelo.getCodigoDeVuelo())){
            throw new FlightCodeAlreadyExistsException("Un vuelo con este codigo ya existe");
        }

        vuelos.put(vuelo.getCodigoDeVuelo(),vuelo);

    }

    public void removeVuelo(String codigoDeVuelo){
        if(vuelos.containsKey(codigoDeVuelo)){
            throw new FlightCodeNonexistentException("No existe un vuelo con este codigo");
        }

        vuelos.remove(codigoDeVuelo);
    }
}

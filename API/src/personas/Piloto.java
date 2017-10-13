package personas;

import customExceptions.AdministratorNotAbleToSellException;
import vuelo.Vuelo;

import java.util.ArrayList;

public class Piloto extends Persona{

    private ArrayList<Vuelo> listaDeVuelos;

    public Piloto(Area area){
        listaDeVuelos = new ArrayList<>();
    }

    public void agregarVuelo(Vuelo unVuelo){
        for (Vuelo vuelo : listaDeVuelos) {
            if(vuelo.equals(unVuelo)){
                throw new RuntimeException("Ya posee el vuelo en su lista de vuelos.");
            }
            listaDeVuelos.add(unVuelo);
        }
    }

    public void quitarVuelo(Vuelo unVuelo){
        for (Vuelo vuelo : listaDeVuelos) {
            if(vuelo.equals(unVuelo)){
                listaDeVuelos.remove(unVuelo);
            } else {
                throw new RuntimeException("El vuelo no existe.");
            }
        }
    }

    //TODO: revisar si esto esta al pedo
    public void puedeVender(){
        throw new AdministratorNotAbleToSellException("Un piloto no puede vender pasajes");
    }

}

package personas;

import customExceptions.AdministratorNotAbleToSellException;
import customExceptions.FlightDoesntExistsException;
import vuelo.Vuelo;

import java.util.ArrayList;

public class Piloto extends Persona{

    private ArrayList<Vuelo> listaDeVuelos;

    public Piloto(int dni){
        super(dni);
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
                throw new FlightDoesntExistsException("El vuelo no existe.");
            }
        }
    }
    
    public ArrayList<Vuelo> getListaVuelos()
    {
        return listaDeVuelos;
    }

    //TODO: revisar si esto esta al pedo
    public void puedeVender(){
        throw new AdministratorNotAbleToSellException("Un piloto no puede vender pasajes");
    }

}

package personas;

import vuelo.Vuelo;

import java.util.ArrayList;

public class Piloto extends Empleado {

    private ArrayList<Vuelo> listaDeVuelos;

    public Piloto(){

        ArrayList<Vuelo> listaDeVuelos = new ArrayList<>();
    }

    public void agregarVuelo(Vuelo unVuelo){
        for (Vuelo vuelo : listaDeVuelos) {
            if(vuelo.equals(unVuelo)){
                throw new RuntimeException("El vuelo ya existe.");
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

    @Override
    public boolean puedeVender(){
        return false;
    }

}

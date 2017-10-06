package personas;

import vuelo.Vuelo;

import java.util.ArrayList;

public class Piloto extends Empleado {

    private ArrayList<Vuelo> listaDeVuelos;

    public Piloto(){
        ArrayList<Vuelo> listaDeVuelos = new ArrayList<>();
    }

    public void agregarVuelo(ArrayList<Vuelo> listaDeVuelos){

    }

    public void quitarVuelo(ArrayList<Vuelo> listaDeVuelos){

    }

    @Override
    public boolean puedeVender(){
        return false;
    }

}

package vuelo;

import java.util.ArrayList;

public class PaqueteDeVuelos  {
    ArrayList<Vuelo> listaDeVuelos = new ArrayList<>();

    public PaqueteDeVuelos() {
    }

    public void addVuelo(Vuelo vuelo){
        listaDeVuelos.add(vuelo);
    }

    public ArrayList<Vuelo> getListaDeVuelos() {
        return listaDeVuelos;
    }
}

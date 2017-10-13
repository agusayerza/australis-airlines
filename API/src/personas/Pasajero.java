package personas;

import vuelo.Pasaje;

import java.util.ArrayList;

public class Pasajero extends Persona{

    ArrayList<Pasaje> pasajesAdquiridos = new ArrayList<>();
    
    public Pasajero(ArrayList<Pasaje> pasajes) {
        this.pasajesAdquiridos = pasajes;
    }
    
    public int getCantidadPasajes()
    {
        return pasajesAdquiridos.size();
    }
    
    public ArrayList<Pasaje> getListaPasajes()
    {
        return pasajesAdquiridos;
    }

    //public ... comprarPasaje(Pasaje){

    //}

}

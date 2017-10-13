package personas;

import vuelo.Pasaje;

import java.util.ArrayList;

public class Pasajero extends Persona{

    ArrayList<Pasaje> pasajesAdquiridos = new ArrayList<>();
    
    public Pasajero() 
    {
        
    }
    
    public void agregarPasaje(Pasaje pasaje)
    {
        pasajesAdquiridos.add(pasaje);
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

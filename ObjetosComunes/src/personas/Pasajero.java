package personas;

import vuelo.Pasaje;

import java.util.ArrayList;

import customExceptions.PassageAlreadyExistsException;

public class Pasajero extends Persona{

    ArrayList<Pasaje> pasajesAdquiridos;
    public Pasajero(int dni)
    {
        super(dni);
        pasajesAdquiridos = new ArrayList<>();
    }
    
    public void agregarPasaje(Pasaje pasaje)
    {
        for(Pasaje pas : pasajesAdquiridos)
        {
            if(pas.equals(pasaje))
               {
                   throw new PassageAlreadyExistsException("Ya existe este pasaje");
               }
            pasajesAdquiridos.add(pasaje);
        }
    }
    
    public int getCantidadPasajes()
    {
        return pasajesAdquiridos.size();
    }
    
    public ArrayList<Pasaje> getListaPasajes()
    {
        return pasajesAdquiridos;
    }

    public void comprarPasaje(Pasaje pasaje)
    {

    }

}

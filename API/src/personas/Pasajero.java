package personas;

import avion.Asiento;

import java.util.ArrayList;

public class Pasajero extends Persona{

    ArrayList<Asiento> asientosAdquiridos = new ArrayList<>();
    
    public Pasajero(ArrayList<Asiento> asientos) {
        this.asientosAdquiridos = asientos;
    }
    
    public int getCantidadAsientos()
    {
        return asientosAdquiridos.size();
    }

    //public ... comprarPasaje(Pasaje){

    //}

}

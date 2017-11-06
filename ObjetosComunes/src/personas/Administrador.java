package personas;

import customExceptions.AdministratorNotAbleToSellException;

public class Administrador extends Persona {

    private Area area;

    public Administrador(int dni, Area area) {
        super(dni);
        this.area = area;
    }

    public void puedeVender() {
        if (!area.getCapacidadDeVender()) {
            throw new AdministratorNotAbleToSellException("Este administrador no puede vender");
        }
    }
    
    public Area getArea()
    {
        return this.area;
    }
}

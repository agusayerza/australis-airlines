package personas;

import customExceptions.AdministratorNotAbleToSellException;

public class Administrador extends Persona {

    private Area area;

    public Administrador(Area area) {
        this.area = area;
    }

    public void puedeVender() {
        if (!area.getCapacidadDeVender()) {
            throw new AdministratorNotAbleToSellException("Este administrador no puede vender");
        }
    }
}






package personas;

import customExceptions.AdministratorNotAbleToSellException;

public class Administrador extends Persona {

    private Area area;

    public Administrador(Area area) {
        this.area = area;
    }

    public boolean puedeVender() {
        if (area.getCapacidadDeVender()) {
            return true;
        }
        return false;
    }
}

    // otro camino: agregarlo directamente como validacion en el constructor y que tire la excepcion de not able to sell.








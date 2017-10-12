package personas;

import customExceptions.AdministratorNotAbleToSellException;

public class Administrador extends Persona implements AreaDeEmpleado{

    private boolean puedeVender;

    public Administrador(boolean puedeVender){
        this.puedeVender = puedeVender;
    }

    @Override
    public boolean puedeVender(){
        if(!puedeVender){
            throw new AdministratorNotAbleToSellException("El administrador no esta habilitado para vender.");
        }
        return true;
    }

}

package personas;

import CustomExceptions.AdministratorNotAbleToSellException;

public class Administrador extends Empleado{

    private boolean puedeVender;

    public Administrador(boolean puedeVender){
        this.puedeVender = puedeVender;
    }

    @Override
    public boolean puedeVender(){
        if(puedeVender == false){
            throw new AdministratorNotAbleToSellException("El administrador no esta habilitado para vender.");
        }
        return true;
    }

}

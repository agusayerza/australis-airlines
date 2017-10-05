package personas;

public class Administrador extends Persona implements AreaDeEmpleado{

    private boolean puedeVender;

    public Administrador(boolean puedeVender){
        this.puedeVender = puedeVender;
    }

    @Override
    public boolean puedeVender(){
        if(puedeVender == false){
            //TODO custom runtime exception.
            throw new RuntimeException("El administrador no esta habilitado para vender.");
        }
        return true;
    }

}

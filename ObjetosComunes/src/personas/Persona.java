package personas;

public abstract class Persona {

    protected int dni;
    public Persona(int dni){
        this.dni = dni;
    }
    public int getDni(){
        return dni;
    }
}

package personas;

import vuelo.*;

public class Administrador extends Empleado {
    boolean puedeVender;
    public Administrador(int DNI, boolean puedeVender) {
        super(DNI);
        //TODO: puedeVender se supone que esta definido por sector, no se si deberiamos hacerlo desde el constructor. -A
        this.puedeVender = puedeVender;
    }

    public void agregarVuelo(Vuelo vuelo){

    }

}

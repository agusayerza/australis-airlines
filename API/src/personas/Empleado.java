package personas;

public class Empleado implements Persona {
    int DNI;
    boolean esEmpleado;

    public Empleado(int DNI) {
        this.DNI = DNI;
        esEmpleado = true;
    }

    @Override
    public int getDNI() {
        return DNI;
    }

    public boolean esEmpleado() {
        return esEmpleado;
    }
}

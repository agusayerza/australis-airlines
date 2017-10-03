package personas;

public class Cliente implements Persona {
    private int DNI;
    boolean esEmpleado;

    public Cliente(int DNI) {
        this.DNI = DNI;
        esEmpleado = false;
    }

    @Override
    public int getDNI() {
        return DNI;
    }

    public boolean esEmpleado() {
        return esEmpleado;
    }
}

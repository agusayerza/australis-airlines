package customExceptions;

public class MenuInvalidOptionSelectedException extends Exception{
    public MenuInvalidOptionSelectedException(String mensaje) {
        super(mensaje);
    }
}

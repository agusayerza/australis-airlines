package customExceptions;

public class MenuInvalidOptionSelectedException extends RuntimeException{
    public MenuInvalidOptionSelectedException(String mensaje) {
        super(mensaje);
    }
}

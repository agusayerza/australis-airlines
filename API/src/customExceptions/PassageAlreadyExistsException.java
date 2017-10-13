package customExceptions;

public class PassageAlreadyExistsException extends RuntimeException {
    public PassageAlreadyExistsException(String mensaje) {
        super(mensaje);
    }
}

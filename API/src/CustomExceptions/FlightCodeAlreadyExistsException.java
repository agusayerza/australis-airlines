package CustomExceptions;

public class FlightCodeAlreadyExistsException extends RuntimeException {
    public FlightCodeAlreadyExistsException(String mensaje) {
        super(mensaje);
    }
}

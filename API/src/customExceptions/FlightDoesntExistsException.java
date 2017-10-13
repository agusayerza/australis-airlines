package customExceptions;

public class FlightDoesntExistsException extends RuntimeException {
    public FlightDoesntExistsException(String mensaje) {
        super(mensaje);
    }
}

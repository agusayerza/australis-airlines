package CustomExceptions;

public class FlightCodeNonexistentException extends RuntimeException{
    public FlightCodeNonexistentException(String mensaje) {
        super(mensaje);
    }
}

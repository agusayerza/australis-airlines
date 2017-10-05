package CustomExceptions;

public class SeatNonexistentException extends RuntimeException {
    public SeatNonexistentException(String mensaje) {
        super(mensaje);
    }
}

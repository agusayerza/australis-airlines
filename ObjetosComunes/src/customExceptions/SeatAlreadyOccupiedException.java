package customExceptions;

public class SeatAlreadyOccupiedException extends RuntimeException{
    public SeatAlreadyOccupiedException(String mensaje) {
        super(mensaje);
    }
}

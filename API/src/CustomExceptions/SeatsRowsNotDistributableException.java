package CustomExceptions;

public class SeatsRowsNotDistributableException extends RuntimeException {
    public SeatsRowsNotDistributableException(String mensaje) {
        super(mensaje);
    }
}

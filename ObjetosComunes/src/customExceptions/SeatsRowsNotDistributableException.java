package customExceptions;

public class SeatsRowsNotDistributableException extends RuntimeException {
    public SeatsRowsNotDistributableException(String mensaje) {
        super(mensaje);
    }
}

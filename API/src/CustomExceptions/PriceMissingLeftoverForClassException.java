package CustomExceptions;

public class PriceMissingLeftoverForClassException extends RuntimeException {
    public PriceMissingLeftoverForClassException(String mensaje) {
        super(mensaje);
    }
}

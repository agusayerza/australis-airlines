package customExceptions;

public class PriceListNotContainClassException extends RuntimeException {
    public PriceListNotContainClassException(String mensaje) {
        super(mensaje);
    }
}

package customExceptions;

public class PriceNegativeException extends RuntimeException{
    public PriceNegativeException(String mensaje) {
        super(mensaje);
    }
}

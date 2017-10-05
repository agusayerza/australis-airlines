package CustomExceptions;

public class PriceNegativeException extends RuntimeException{
    public PriceNegativeException(String mensaje) {
        super(mensaje);
    }
}

package customExceptions;

public class AdministratorNotAbleToSellException extends RuntimeException {
    public AdministratorNotAbleToSellException(String mensaje) {
        super(mensaje);
    }
}

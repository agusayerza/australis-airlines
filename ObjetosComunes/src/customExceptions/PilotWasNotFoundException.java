
package customExceptions;

public class PilotWasNotFoundException extends RuntimeException {
    public PilotWasNotFoundException(String mensaje) {
        super(mensaje);
    }
}

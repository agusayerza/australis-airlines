import UI.*;
import customExceptions.MenuInvalidOptionSelectedException;
import vuelo.*;

public class Consola {
    Menu menuPrincipal;
    static Protocol protocol;
    static int DNI;
    static boolean programRun = true;
    public static void main(String[] args){

        Scanner mainScanner = new Scanner();

        System.out.println("Bienvenido a la aplicacion de Clientes de Australis Airlines");

        //TODO: Sistema de Registro de usuarios? Contraseñas?
        System.out.println("Para empezar, usted debe conectarse");

        boolean validDNI = false;
        while(!validDNI){
            DNI = mainScanner.getInt("Ingrese su DNI: ");
            if((DNI > 999999) && (DNI < 100000000)){
                validDNI = true;
            }else {
                System.out.println("DNI Invalido, vuelva a ingresarlo.");
            }
        }

        protocol = new Protocol(DNI);

        //TODO: Preguntar si el concepto de progRun esta bien.
        while(programRun){
            try{
                loopMenuPrincipal();
            } catch (MenuInvalidOptionSelectedException e){
                // Esto NUNCA deberia pasar, ya que se chequea en UI.Menu
                System.out.println(e.getMessage());
                System.exit(1); //End with error
            }
        }

        System.out.println("Gracias por volar con nosotros.");

    }

    public static void loopMenuPrincipal() throws MenuInvalidOptionSelectedException{
        String[] opcionesMenuPrincipal = new String[3];

        opcionesMenuPrincipal[0] = "Ver Pasajes";
        opcionesMenuPrincipal[1] = "Reservar Pasajes";
        opcionesMenuPrincipal[2] = "Salir";

        Menu menuPrincipal = new Menu(opcionesMenuPrincipal, "Menú Principal");

        System.out.println(menuPrincipal.strPrintMenu());
        int option = menuPrincipal.pedirOpcionAlUsuario();

        switch(option){
            case 1:
                protocol.getTicketsForThisUser();
                break;
            case 2:
                break;
            case 3:
                //Prog run se controla desde el main loop.
                programRun = false;
                break;
            default:
                throw new MenuInvalidOptionSelectedException("Se selecciono una opcion invalida");
        }
    }
}
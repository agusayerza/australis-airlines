
import customExceptions.InvalidPassengersQuantity;
import customExceptions.MenuInvalidOptionSelectedException;
import customExceptions.SeatAlreadyOccupiedException;
import customExceptions.SeatNonexistentException;
import personas.Pasajero;
import vuelo.Vuelo;

import java.time.LocalDate;
import java.util.ArrayList;

public class Consola {
    Menu menuPrincipal;
    static Protocol protocol;
    static Scanner mainScanner;
    static int DNI;
    static boolean programRun = true;

    public static void main(String[] args){

        mainScanner = new Scanner();

        System.out.println("Bienvenido a la aplicacion de Pilotos de Australis Airlines");

        //TODO: Sistema de Registro de usuarios? Contraseñas?
        System.out.println("Para empezar, usted debe conectarse");

        protocol = new Protocol();

        //TODO: Preguntar si el concepto de progRun esta bien.
        while(programRun){
            try{
                loopMenuPrincipal();
            } catch (MenuInvalidOptionSelectedException e){
                //TODO: Consultar si este tipo de redundancias se ponen o no
                // Esto NUNCA deberia pasar, ya que se chequea en UI.Menu
                System.out.println(e.getMessage());
                System.exit(1); //End with error
            }
        }

        System.out.println("Gracias por trabajar con nosotros.");

    }

    public static void loopMenuPrincipal() throws MenuInvalidOptionSelectedException{
        String[] opcionesMenuPrincipal = new String[2];

        opcionesMenuPrincipal[0] = "Ver Vuelos";
        opcionesMenuPrincipal[1] = "Salir";

        Menu menuPrincipal = new Menu(opcionesMenuPrincipal, "Menú Principal");

        System.out.println(menuPrincipal.strPrintMenu());
        int option = menuPrincipal.pedirOpcionAlUsuario();

        switch(option){
            case 1:
                System.out.println("Buscando sus vuelos ...");
                String todosLosVuelos = "";
                for (Vuelo vuelo: protocol.getFlightsForPilot()) {
                    todosLosVuelos += vuelo.getInfoVueloPiloto() + "\n";
                }
                System.out.println(todosLosVuelos);
                break;
            case 2:
                //Prog run se controla desde el main loop.
                programRun = false;
                break;
            default:
                throw new MenuInvalidOptionSelectedException("Se selecciono una opcion invalida");
        }
    }

}
import customExceptions.InvalidPassengersQuantity;
import customExceptions.MenuInvalidOptionSelectedException;
import vuelo.Vuelo;

import java.time.LocalDate;
import java.util.ArrayList;

public class Consola {
    Menu menuPrincipal;
    static Protocol protocol;
    static Scanner mainScanner;
    static int DNI;
    static boolean programRun = true;

    public static void main(String[] args) {
        mainScanner = new Scanner();

        System.out.println("Aplicacion de Empleados de Australis Airlines");

        boolean validDNI = false;
        while (!validDNI) {
            DNI = mainScanner.getInt("Ingrese su DNI:");
            if ( (DNI > 999999) && (DNI < 100000000) ) {
                validDNI = true;
            } else {
                System.out.println("DNI invalido, vuelva a ingresarlo");
            }
        }

        protocol = new Protocol(DNI);

        //Lo puse porque agus lo puso
        while (programRun) {
            try {
                loopMenuPrincipal();
            } catch (MenuInvalidOptionSelectedException e) {
                System.out.println(e.getMessage());
                System.exit(1); //End with error
            }
        }

        System.out.println("Gracias por trabajar con nosotros");
    }

    public static void loopMenuPrincipal() throws MenuInvalidOptionSelectedException {
        String[] opcionesMenuPrincipal = new String[3];

        opcionesMenuPrincipal[0] = "Vender Pasajes";
        opcionesMenuPrincipal[1] = "Ver Reservas del Cliente";
        opcionesMenuPrincipal[2] = "Salir";

        Menu menuPrincipal = new Menu(opcionesMenuPrincipal, "Menu Principal");

        System.out.println(menuPrincipal.strPrintMenu());
        int option = menuPrincipal.pedirOpcionAlUsuario();

        switch (option) {
            case 1:
                searchFlightForReservation();
                break;
            case 2:
                protocol.getTicketsForThisUser();
                break;
            case 3:
                programRun = false; //Preguntar a Agus como funciona esto que no entendi ;-;
                break;
            default:
                throw new MenuInvalidOptionSelectedException("Se selecciono una opcion invalida");
        }
    }

    //Igual

    private static void searchFlightForReservation() {
        String aeropuertoSalida;
        String aeropuertoLlegada;

        LocalDate fechaDeSalida;
        String dateToParse;

        int cantidadDePasajeros;

        aeropuertoSalida = mainScanner.getString("Ingrese aeropuerto de origen: ");
        aeropuertoLlegada = mainScanner.getString("Ingrese aeropuerto de destino: ");

        fechaDeSalida = mainScanner.getLocalDate("Ingrese fecha de salida (formato dd/MM/yy):");

        cantidadDePasajeros = getPassengerQuantity();

        String[] opcionesEscalas = new String[4];

        opcionesEscalas[0] = "Sin escalas";
        opcionesEscalas[1] = "Una escala";
        opcionesEscalas[2] = "Dos escalas";
        opcionesEscalas[3] = "Tres escalas";

        Menu menuEscalas = new Menu(opcionesEscalas,"Seleccione cuantas escalas seran:"); //TODO: Estaria bueno poder buscar vuelos sin esfecificar cuantas escalas quieres/quiere el cliente
        System.out.println(menuEscalas.strPrintMenu());

        int escalas = menuEscalas.pedirOpcionAlUsuario();

        //ArrayList que contiene ArrayLists con Vuelos. Cada ArrayList es un "set" de escalas. Si solo tiene un vuelo, es un vuelo directo.

        ArrayList<ArrayList<Vuelo>> posiblesVuelos = new ArrayList<>();
        posiblesVuelos = protocol.getPossibleFlights(aeropuertoSalida,aeropuertoLlegada,fechaDeSalida,cantidadDePasajeros,escalas); //Que pasaria si no se encuentra un vuelo que coincida? Se buscaria de nuevo? Hay como hacer que el programa busque de nuevo pero aumentandole 1 escala?

        //TODO: Esto es despues de que se seleccione un posible vuelo
        String[] opcionesCategoria = new String[3];
        opcionesCategoria[0] = "Cualquiera";
    }

    private static int getPassengerQuantity(){
        int cantidadDePasajeros;
        try {
            cantidadDePasajeros = mainScanner.getInt("Ingrese cantidad de pasajeros: ");
            if (cantidadDePasajeros < 1) {
                throw new InvalidPassengersQuantity("Cantidad de pasajeros invalida");
            }
        }catch(InvalidPassengersQuantity e){
            return getPassengerQuantity();
        }
        return cantidadDePasajeros;
    }
}


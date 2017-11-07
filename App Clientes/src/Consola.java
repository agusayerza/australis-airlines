
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

        System.out.println("Bienvenido a la aplicacion de Clientes de Australis Airlines");

        //TODO: Sistema de Registro de usuarios? Contraseñas?
        System.out.println("Para empezar, usted debe conectarse");

        DNI = getDNI(true);

        protocol = new Protocol(DNI);

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
                System.out.println("Buscando sus vuelos ...");
                System.out.println(protocol.getTicketsForThisUser());
                break;
            case 2:
                searchFlightForReservation();
                break;
            case 3:
                //Prog run se controla desde el main loop.
                programRun = false;
                break;
            default:
                throw new MenuInvalidOptionSelectedException("Se selecciono una opcion invalida");
        }
    }
    private static int getDNI(boolean userDNI){
        boolean validDNI = false;
        int givenDNI = 0;
        while(!validDNI){


            if(userDNI){
                givenDNI = mainScanner.getInt("Ingrese su DNI: ");
            }else{
                givenDNI = mainScanner.getInt("Ingrese el DNI del pasajero: ");
            }

            if((givenDNI > 999999) && (givenDNI < 100000000)){
                validDNI = true;
            }else {
                System.out.println("DNI Invalido, vuelva a ingresarlo.");
            }
        }
        return givenDNI;
    }
    private static void searchFlightForReservation() {
        String aeropuertoSalida;
        String aeropuertoLlegada;

        LocalDate fechaDeSalida;

        aeropuertoSalida = mainScanner.getString("Ingrese aeropuerto de origen: ");
        aeropuertoLlegada = mainScanner.getString("Ingrese aeropuerto de destino: ");
        fechaDeSalida = mainScanner.getLocalDate("Ingrese fecha de salida (formato dd/MM/yy):");

//        String[] opcionesEscalas = new String[4];
//
//        opcionesEscalas[0] = "Sin escalas";
//        opcionesEscalas[1] = "Una escala";
//        opcionesEscalas[2] = "Dos escalas";
//        opcionesEscalas[3] = "Tres escalas";
//
//        Menu menuEscalas = new Menu(opcionesEscalas,"Seleccione cuantas escalas desea:");
//        System.out.println(menuEscalas.strPrintMenu());
//
//        int escalas = menuEscalas.pedirOpcionAlUsuario();


        ArrayList<Vuelo> posiblesVuelos = new ArrayList<>();
        posiblesVuelos = protocol.getPossibleFlights(aeropuertoSalida,aeropuertoLlegada,fechaDeSalida);

        if(posiblesVuelos.size() == 0){
            System.out.println("No se encontraron vuelos con los parametros indicados.");
            return;
        }

        String vuelos[] = new String[posiblesVuelos.size()];
        int i = 0;
        for (Vuelo vuelo: posiblesVuelos) {
            vuelos[i] = fechaDeSalida + "  ||  " + vuelo.getAeropuertoDePartida() + " --> " + vuelo.getAeropuertoDeArribo();
            i++;
        }

        Menu menuVuelos = new Menu(vuelos,"Vuelos encontrados");

        System.out.println(menuVuelos.strPrintMenu());
        int opcionvueloseleccionado = menuVuelos.pedirOpcionAlUsuario();

        int cantidadDePasajeros = 1;

        i = 1;
        Vuelo vueloSeleccionado = new Vuelo();
        for (Vuelo vuelo: posiblesVuelos) {
            if(i == opcionvueloseleccionado){
                vueloSeleccionado = vuelo;
                System.out.println("Selecciono el vuelo con codigo " + vueloSeleccionado.getCodigoDeVuelo());


                cantidadDePasajeros = getPassengerQuantity();

                break;
            }
            i++;
        }
        for (int j = 0; j < cantidadDePasajeros; j++) {
            Pasajero pasajero = new Pasajero(getDNI(false));

            //TODO: Esto es despues de que se seleccione un posible vuelo
            String[] opcionesCategoria = new String[3];
            opcionesCategoria[0] = "Cualquiera";

            boolean seleccionarAsiento = true;
            String asiento = "";
            while (seleccionarAsiento){
                System.out.println(vueloSeleccionado.getAsientoLayout(fechaDeSalida));
                asiento = mainScanner.getString("Seleccione el asiento deseado: ").toUpperCase();

                if(vueloSeleccionado.validarAsiento(asiento)){
                    try {
                        protocol.sellTicket(vueloSeleccionado.getCodigoDeVuelo(),asiento,fechaDeSalida, pasajero);
                        seleccionarAsiento = false;
                    }catch (SeatNonexistentException | SeatAlreadyOccupiedException e){
                        System.out.println(e.getMessage());
                    }
                }
            }

            System.out.println("Asiento " + asiento + " vendido a " + DNI);
            System.out.println();
        }

        System.out.println("Reservas actuales: ");
        System.out.println(protocol.getTicketsForThisUser());


    }

    private static int getPassengerQuantity(){
        int cantidadDePasajeros;
        try {
            cantidadDePasajeros = mainScanner.getInt("Ingrese cantidad de pasajeros: ");
            if (cantidadDePasajeros < 1) {
                throw new InvalidPassengersQuantity("Cantidad de pasajeros invalida.");
            }
        }catch(InvalidPassengersQuantity e){
            return getPassengerQuantity();
        }
        return cantidadDePasajeros;
    }
}
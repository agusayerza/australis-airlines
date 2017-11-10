import avion.Avion;
import avion.Clase;
import catalogo.Pricing;
import customExceptions.*;
import personas.Pasajero;
import personas.Piloto;
import vuelo.Vuelo;

import java.lang.reflect.Array;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

public class Consola {
    static Protocol protocol;
    static Scanner mainScanner;
    static int DNI;
    static boolean programRun = true;
    static int pasajeroDNI;


    public static void main(String[] args) {
        mainScanner = new Scanner();

        System.out.println("Aplicacion de Empleados de Australis Airlines");
        protocol = new Protocol();

        boolean validDNI = false;
        while (!validDNI) {
            DNI = mainScanner.getInt("Ingrese su DNI:");
            if ( (DNI > 999999) && (DNI < 100000000) ) {
                if(protocol.esAdmin(DNI)){
                    validDNI = true;
                } else {
                    System.out.println("Ese DNI no pertenece a un administrador");
                }
            } else {
                System.out.println("DNI invalido, vuelva a ingresarlo");
            }
        }



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
        String[] opcionesMenuPrincipal = new String[5];

        opcionesMenuPrincipal[0] = "Crear Avion";
        opcionesMenuPrincipal[1] = "Agregar nuevo vuelo";
        opcionesMenuPrincipal[2] = "Vender Pasajes";
        opcionesMenuPrincipal[3] = "Ver Reservas del Cliente";
        opcionesMenuPrincipal[4] = "Salir";

        Menu menuPrincipal = new Menu(opcionesMenuPrincipal, "Menu Principal");

        System.out.println(menuPrincipal.strPrintMenu());
        int option = menuPrincipal.pedirOpcionAlUsuario();

        switch (option) {
            case 1:
                crearAvion();
                break;
            case 2:
                crearVuelo();
                break;
            case 3:
                if(protocol.getAdministradorPuedeVender(DNI)) {
                    searchFlightForReservation();
                } else {
                    System.out.println("Usted no esta habiltiado para vender pasajes.");
                }
                break;

            case 4:
                int DNICliente = mainScanner.getInt("Ingrese el DNI para el cual quiere ver los pasajes: ");
                System.out.println( protocol.getTicketsForThisUser(DNICliente) );
                break;

            case 5:
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
        aeropuertoSalida = mainScanner.getString("Ingrese aeropuerto de origen: ");
        aeropuertoLlegada = mainScanner.getString("Ingrese aeropuerto de destino: ");
        int passangerQuantity = getPassengerQuantity();
        LocalDate fechaDeSalida;
        fechaDeSalida = mainScanner.getLocalDate("Ingrese fecha de salida (formato dd/MM/yy):");

        boolean roundTrip = false;
        roundTrip = mainScanner.getYesNo("Desea reservar ida y vuelta? (Y/N)");
        if(roundTrip){

            LocalDate fechaDeVuelta;
            fechaDeVuelta = mainScanner.getLocalDate("Ingrese fecha de vuelta(formato dd/MM/yy):");

            for (int i = 0; i < passangerQuantity; i++) {
                int ii = i+1;
                System.out.println("----- RESERVA PASAJERO "+ ii +" -----");
                int dniPasajero = getDNI(false);
                reserveFlight(aeropuertoSalida, aeropuertoLlegada, fechaDeSalida,dniPasajero);
                System.out.println(" ---- Reservar vuelta pasajero "+ ii +" -----");
                reserveFlight(aeropuertoLlegada, aeropuertoSalida, fechaDeVuelta,dniPasajero);
            }
        }else{
            for (int i = 0; i < passangerQuantity; i++) {
                int ii = i+1;
                System.out.println("----- RESERVA PASAJERO "+ ii +" -----");
                int dniPasajero = getDNI(false);
                reserveFlight(aeropuertoSalida, aeropuertoLlegada, fechaDeSalida,dniPasajero);
            }
        }

    }

    private static void reserveFlight(String aeropuertoSalida, String aeropuertoLlegada, LocalDate fechaDeSalida, int dniPasajero){

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

        i = 1;
        Vuelo vueloSeleccionado = new Vuelo();
        for (Vuelo vuelo: posiblesVuelos) {
            if(i == opcionvueloseleccionado){
                vueloSeleccionado = vuelo;
                System.out.println("Selecciono el vuelo con codigo " + vueloSeleccionado.getCodigoDeVuelo());
                break;
            }
            i++;
        }
        Pasajero pasajero = new Pasajero(dniPasajero);

        //TODO: Esto es despues de que se seleccione un posible vuelo
        String[] opcionesCategoria = new String[3];
        opcionesCategoria[0] = "Cualquiera";

        boolean seleccionarAsiento = true;
        String asiento = "";
        while (seleccionarAsiento){
            System.out.println(vueloSeleccionado.getAsientoLayout(fechaDeSalida));
            Pricing pricing = vueloSeleccionado.getPricing();
            Avion avion = vueloSeleccionado.getAvion();

            for (Clase clase: avion.getClases()) {
                System.out.println("Precio clase " + clase.getNombreDeClase() + ": " + pricing.getPrecioDeClase(clase.getNombreDeClase()));
            }
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

        System.out.println("Asiento " + asiento + " vendido a " + dniPasajero);
        System.out.println();

        System.out.println("Reservas actuales: ");
        System.out.println(protocol.getTicketsForThisUser(dniPasajero));
        System.out.println("\n\n");

    }


    private static int getPassengerQuantity(){
        int cantidadDePasajeros;
        try {
            cantidadDePasajeros = mainScanner.getInt("Ingrese cantidad de pasajeros (max 5): ");
            if (cantidadDePasajeros < 1) {
                throw new InvalidPassengersQuantity("Cantidad de pasajeros invalida.");
            }

            if(cantidadDePasajeros > 5){
                throw new InvalidPassengersQuantity("Cantidad de pasajeros invalida.");
            }
        }catch(InvalidPassengersQuantity e){
            return getPassengerQuantity();
        }
        return cantidadDePasajeros;
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

    private static void crearAvion() {
        String patente;
        int numeroDeClases;

        patente = mainScanner.getString("Introduzca la patente del avion: ");
        numeroDeClases = mainScanner.getInt("Introduzca la cantidad de clases: ");
        Clase[] clases = new Clase[numeroDeClases];


        int ultimaFilaClaseAnterior = 1;
        for (int i = 0; i < clases.length; i++) {
            String nombreDeClase = mainScanner.getString("Introduzca el nombre de la clase "+ (i + 1) +": ");

            int primerFilaDeClase = ultimaFilaClaseAnterior;

            //cantidadDeAsiento multiplo de cantidadDeFila
            int cantidadDeAsientos = mainScanner.getInt("Introduzca la cantidad de asientos de la clase " + nombreDeClase + ": ");
            int cantidadDeFilasDeAsientos = mainScanner.getInt("Introduzca la cantidad de filas de asientos de la clase " + nombreDeClase + ": ");

            if(cantidadDeAsientos % cantidadDeFilasDeAsientos != 0){
                System.out.println("Error, la cantidad de asientos debe ser divisible en las filas de asientos.");
                return;
            }

            Clase clase = new Clase(primerFilaDeClase, cantidadDeAsientos, cantidadDeFilasDeAsientos, nombreDeClase);

            ultimaFilaClaseAnterior += cantidadDeFilasDeAsientos;

            clases[i] = clase;
        }

        Avion avion = new Avion(patente, clases);

        protocol.crearAvion(avion);

        System.out.println("Avion " + patente + " creado con exito.");
    }

    public static void crearVuelo(){
        int contador = 0;
        String nombreMenuAviones = "Menu aviones:";
        //LocalDateTime startDayTime = LocalDateTime.now();

        int dur = mainScanner.getInt("Ingrese la duracion del vuelo EN HORAS:");
        if (dur <= 0){
            throw new RuntimeException("No puede ingresar una duracion de vuelo menor o igual a 0");
        }
        LocalDate startDate = mainScanner.getLocalDate("Ingrese la fecha de inicio del vuelo en formato (dd/mm/yy):");
        LocalTime hora = LocalTime.parse(mainScanner.getString("Ingrese el horario de despegue del vuelo (format HH:MM): "));
        LocalDateTime dateInicio = LocalDateTime.of(startDate,hora);

        Duration duration = Duration.ofHours(dur);
        LocalDate endDate = mainScanner.getLocalDate("Ingrese la fecha de finalizacion del vuelo en formato (dd/mm/yy):");
        String aeropuertoDePartida = mainScanner.getString("Ingrese el aeropuerto de partida:").toUpperCase();
        String aeropuertoDeArribo = mainScanner.getString("Ingrese el aeropuerto de arribo:").toUpperCase();
        String codigoDeVuelo = mainScanner.getString("Ingrese el codigo de vuelo:");
        ArrayList<Avion> aviones = protocol.getListaDeAviones();
        String[] patentesDeAviones = new String[aviones.size()];
        for (Avion avion : aviones ) {
            patentesDeAviones[contador] = avion.getPatente();
            contador++;
        }
        Avion avion = askForPlane(patentesDeAviones,aviones);

        double[] pricings = new double[avion.getClases().length];

        int x = 0;

        for (Clase clase : avion.getClases()) {
            pricings[x] = mainScanner.getDouble("Ingrese el precio de la clase " + clase.getNombreDeClase() + ": ");
            x++;
        }
        Pricing pricing = new Pricing(avion, pricings);

        int dniPiloto = mainScanner.getInt("Ingrese el DNI del piloto:");
        Piloto piloto = new Piloto(dniPiloto);

        Vuelo unVuelo = new Vuelo(dateInicio, duration, endDate, aeropuertoDePartida, aeropuertoDeArribo, codigoDeVuelo, avion, pricing, piloto);
        protocol.agregarVuelo(unVuelo);

    }

    public static Avion askForPlane(String[] patentesDeAviones, ArrayList<Avion> aviones){
        Menu menuAviones = new Menu(patentesDeAviones, "Menu aviones:");
        System.out.println(menuAviones.strPrintMenu());
        int opcionPatenteSeleccionada = menuAviones.pedirOpcionAlUsuario();
        Avion avion;
        for (Avion unAvion: aviones) {
            if(unAvion.getPatente() == patentesDeAviones[opcionPatenteSeleccionada-1]){
                return unAvion;
            }
        }
        throw new RuntimeException("404 Avion not found.");
    }
}


import avion.Avion;
import avion.Clase;
import catalogo.Pricing;
import customExceptions.FlightCodeNonexistentException;
import personas.Pasajero;
import vuelo.Vuelo;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class MockServer implements Servicios{

    LocalDateTime tiempo;
    Vuelo vuelo;
    Duration duracion;
    Pricing pricing;
    Pasajero pasajero;
    Avion avion;
    Clase[] clases;
    ArrayList<Vuelo> lista;
    ArrayList<String> codigosDeVuelo;

    public MockServer() {
        /*
        * Set up de nuestro server ficticio, crea varios vuelos y aviones y los llena un poco.
        * */

        duracion = Duration.ofHours(8);
        tiempo = LocalDateTime.now();

        Clase clase = new Clase(1,21,3,"Primera");
        clases = new Clase[1];
        clases[0] = clase;

        avion = new Avion("LV-501",clases);

        double precio[] = new double[1];
        precio[0] = 199.3;
        pricing = new Pricing(avion,precio);

        vuelo = new Vuelo(tiempo,duracion,tiempo.plusYears(1).toLocalDate(),"Ezeiza","Paris","BARBAR",avion, pricing);

        clase = new Clase(1,4,2,"Primera");
        Clase economica = new Clase(2,21,3,"Economica");

        clases = new Clase[2];
        clases[0] = clase;
        clases[1] = economica;

        avion = new Avion("LV-600",clases);
        tiempo = LocalDateTime.now();

        duracion = Duration.ofHours(8);

        precio = new double[2];
        precio[0] = 199.3;
        precio[1] = 149.99;
        pricing = new Pricing(avion,precio);

        Vuelo copiaVuelo = new Vuelo(tiempo.plusDays(1),duracion,tiempo.plusYears(1).toLocalDate(),"Madrid","Paris","BARBAR-COPIA",avion, pricing);
        Vuelo otroVuelo = new Vuelo(tiempo.plusDays(2),duracion,tiempo.plusYears(1).toLocalDate(),"Paris","Ezeiza","BARBOR",avion, pricing);

//        vuelo.ocuparAsiento("1B",pasajero,tiempo.toLocalDate());
//        copiaVuelo.ocuparAsiento("2B",pasajero,tiempo.toLocalDate());
//        otroVuelo.ocuparAsiento("5C",pasajero,tiempo.toLocalDate());
//
//        pasajero = new Pasajero(40719052);
//        vuelo.ocuparAsiento("3B",pasajero,tiempo.toLocalDate());
//        copiaVuelo.ocuparAsiento("2A",pasajero,tiempo.toLocalDate());
//        otroVuelo.ocuparAsiento("1A",pasajero,tiempo.toLocalDate());
//
//        pasajero = new Pasajero(40719050);
//        vuelo.ocuparAsiento("1A",pasajero,tiempo.toLocalDate());
//        copiaVuelo.ocuparAsiento("3B",pasajero,tiempo.toLocalDate());
//        otroVuelo.ocuparAsiento("2C",pasajero,tiempo.toLocalDate());

        lista.add(vuelo);
        lista.add(copiaVuelo);
        lista.add(otroVuelo);


    }

    @Override
    public ArrayList<Vuelo> BuscarVuelos(LocalDateTime fecha) {
        return null;
    }

    @Override
    public ArrayList<Vuelo> BuscarVuelos(String aeropuerto) {
        return null;
    }

    @Override
    public ArrayList<Vuelo> getFlightsOnDateFromToDestination(LocalDate date, String aeropuertoDePartida, String aeropuertoDeArribo) {
        ArrayList<Vuelo> result = new ArrayList<>();

        for (Vuelo vuelo: lista) {
            if(vuelo.getDiaDeVuelo().equals(date.getDayOfWeek())){
                if(vuelo.getEndDate().isAfter(date)) {
                    if(vuelo.getStartDate().toLocalDate().isBefore(date) || vuelo.getStartDate().toLocalDate().isEqual(date)) {
                        if(vuelo.hasFreeSeats(date)){
                            result.add(vuelo);
                        }
                    }
                }
            }
        }

        return result;
    }

    @Override
    public void venderAsiento(LocalDate date, String codigoDeVuelo, String codigoDeAsiento, Pasajero pasajero) {
        getFlightByCode(codigoDeVuelo).ocuparAsiento(codigoDeAsiento,pasajero,date);
    }


    private Vuelo getFlightByCode(String FlightCode){
        for (Vuelo vuelo: lista) {
            if(vuelo.getCodigoDeVuelo().equals(FlightCode)){
                return vuelo;
            }
        }
        throw new FlightCodeNonexistentException("No existe un vuelo con este codigo");
    }

    @Override
    public Vuelo BuscarVueloCodigo(String codigo) {
        return null;
    }

    @Override
    public void OcuparAsiento(LocalDate fecha, String codigoVuelo, String codigoAsiento, Pasajero pasajero) {

    }
}

import avion.Avion;
import avion.Clase;
import catalogo.Pricing;
import customExceptions.FlightCodeNonexistentException;
import personas.Pasajero;
import personas.Piloto;
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
    ArrayList<Vuelo> listaDeVuelos = new ArrayList<>();
    ArrayList<String> codigosDeVuelo = new ArrayList<>();
    ArrayList<Piloto> listaPilotos = new ArrayList<>();
    ArrayList<Avion> aviones = new ArrayList<>();

    public MockServer() {

        tiempo = LocalDateTime.now();
        duracion = Duration.ofHours(8);

        Piloto piloto = new Piloto(11111000);
        Piloto otroPiloto = new Piloto(20000000);
        Piloto unPiloto = new Piloto(19999998);

        listaPilotos.add(piloto);
        listaPilotos.add(otroPiloto);
        listaPilotos.add(unPiloto);

        Clase clase = new Clase(1,21,3,"Primera");
        clases = new Clase[1];
        clases[0] = clase;

        avion = new Avion("LV-501",clases);

        aviones.add(avion);

        double precio[] = new double[1];
        precio[0] = 199.3;
        pricing = new Pricing(avion,precio);

        vuelo = new Vuelo(tiempo,duracion,tiempo.plusYears(1).toLocalDate(),"Ezeiza","Paris","BARBAR",avion, pricing,piloto);
        piloto.agregarVuelo(vuelo);

        clase = new Clase(1,4,2,"Primera");
        Clase economica = new Clase(2,21,3,"Economica");

        clases = new Clase[2];
        clases[0] = clase;
        clases[1] = economica;

        avion = new Avion("LV-600",clases);
        aviones.add(avion);

        precio = new double[2];
        precio[0] = 199.3;
        precio[1] = 149.99;
        pricing = new Pricing(avion,precio);

        Vuelo copiaVuelo = new Vuelo(tiempo.plusDays(1),duracion,tiempo.plusYears(1).toLocalDate(),"Madrid","Paris","BARBAR-COPIA",avion, pricing,otroPiloto);
        Vuelo otroVuelo = new Vuelo(tiempo.plusDays(2),duracion,tiempo.plusYears(1).toLocalDate(),"Paris","Ezeiza","BARBOR",avion, pricing,unPiloto);
        otroPiloto.agregarVuelo(copiaVuelo);
        unPiloto.agregarVuelo(otroVuelo);

        pasajero = new Pasajero(40999222);
        vuelo.ocuparAsiento("2B",pasajero,tiempo.toLocalDate());
        copiaVuelo.ocuparAsiento("2B",pasajero,tiempo.toLocalDate().plusDays(1));
        otroVuelo.ocuparAsiento("1A",pasajero,tiempo.toLocalDate().plusDays(2));

        pasajero = new Pasajero(40719052);
        vuelo.ocuparAsiento("3B",pasajero,tiempo.toLocalDate());
        copiaVuelo.ocuparAsiento("2A",pasajero,tiempo.toLocalDate().plusDays(1));
        otroVuelo.ocuparAsiento("2B",pasajero,tiempo.toLocalDate().plusDays(2));

        pasajero = new Pasajero(40719050);
        vuelo.ocuparAsiento("1A",pasajero,tiempo.toLocalDate());
        copiaVuelo.ocuparAsiento("3B",pasajero,tiempo.toLocalDate().plusDays(1));
        otroVuelo.ocuparAsiento("3B",pasajero,tiempo.toLocalDate().plusDays(2));

        listaDeVuelos.add(vuelo);
        listaDeVuelos.add(copiaVuelo);
        listaDeVuelos.add(otroVuelo);


    }

    @Override
    public ArrayList<Vuelo> getFlightsOnDateFromToDestination(LocalDate date, String aeropuertoDePartida, String aeropuertoDeArribo) {
        ArrayList<Vuelo> result = new ArrayList<>();

        for (Vuelo vuelo: listaDeVuelos) {
            if(vuelo.getAeropuertoDeArribo().toUpperCase().equals(aeropuertoDeArribo.toUpperCase())){
                if(vuelo.getAeropuertoDePartida().toUpperCase().equals(aeropuertoDePartida.toUpperCase())){
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
            }
        }

        return result;
    }

    @Override
    public void venderAsiento(LocalDate date, String codigoDeVuelo, String codigoDeAsiento, Pasajero pasajero) {
        getFlightByCode(codigoDeVuelo).ocuparAsiento(codigoDeAsiento,pasajero,date);
    }


    private Vuelo getFlightByCode(String FlightCode){
        for (Vuelo vuelo: listaDeVuelos) {
            if(vuelo.getCodigoDeVuelo().equals(FlightCode)){
                return vuelo;
            }
        }
        throw new FlightCodeNonexistentException("No existe un vuelo con este codigo");
    }

    @Override
    public ArrayList<Vuelo> BuscarVuelosPiloto(int dni) {
        for(Piloto piloto : listaPilotos){
            if(piloto.getDni() == dni){
                return piloto.getListaVuelos();
            }
        }
        throw new RuntimeException("Piloto no encontrado");
    }

    @Override
    public void agregarVuelo(Avion avion, Piloto piloto, LocalDate tiempo,String codigoDeVuelo, String aeropuertoSalida, String aeropuertoArribo, LocalDate ultimaFechaDeVuelo) {

    }
}

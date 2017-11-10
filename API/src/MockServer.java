import avion.Avion;
import avion.Clase;
import catalogo.Pricing;
import customExceptions.FlightCodeNonexistentException;
import personas.Administrador;
import personas.Area;
import personas.Pasajero;
import personas.Piloto;
import vuelo.Vuelo;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class MockServer implements Servicios{

    ArrayList<Vuelo> listaDeVuelos = new ArrayList<>();
    ArrayList<String> codigosDeVuelo = new ArrayList<>();
    ArrayList<Piloto> listaPilotos = new ArrayList<>();
    ArrayList<Avion> aviones = new ArrayList<>();
    ArrayList<Administrador> administradores = new ArrayList<>();

    public MockServer() {
        LocalDateTime tiempo;
        Duration duracion;
        Pricing pricing;
        Pasajero pasajero;
        Avion avion;
        Clase[] clases;

        tiempo = LocalDateTime.now();
        duracion = Duration.ofHours(8);

        Area areaDeVentas = new Area(true);
        Area algunArea = new Area(false);
        Administrador administradorDeVentas = new Administrador(40543537, areaDeVentas);
        Administrador algunAdministrador = new Administrador(1001001, algunArea);
        administradores.add(administradorDeVentas);
        administradores.add(algunAdministrador);

        Piloto piloto = new Piloto(11111000);
        Piloto otroPiloto = new Piloto(20000000);
        Piloto unPiloto = new Piloto(19999998);

        Clase clase = new Clase(1,21,7,"Primera");
        Clase claseBuss = new Clase(7,32,4,"Business");
        Clase claseEco = new Clase(11,72,8, "Economica");

        clases = new Clase[3];
        clases[0] = clase;
        clases[1] = claseBuss;
        clases[2] = claseEco;

        avion = new Avion("LV-501",clases);

        aviones.add(avion);

        double precio[] = new double[3];
        precio[0] = 199.3;
        precio[1] = 150.22;
        precio[2] = 80.9;

        pricing = new Pricing(avion,precio);

        agregarVuelo(tiempo,duracion,tiempo.plusYears(1).toLocalDate(),"Ezeiza","Paris","BARBAR",avion, pricing,piloto);
        agregarVuelo(tiempo.plusDays(7),duracion,tiempo.plusYears(1).toLocalDate(),"Paris","Ezeiza","LOLBAR",avion, pricing,piloto);

        clase = new Clase(1,4,2,"Primera");
        Clase economica = new Clase(2,21,3,"Economica");

        clases = new Clase[3];
        clases[0] = clase;
        clases[1] = economica;
        clases[2] = claseEco;

        avion = new Avion("LV-600",clases);
        aviones.add(avion);

        precio = new double[3];
        precio[0] = 199.3;
        precio[1] = 149.99;
        precio[2] = 90.33;

        pricing = new Pricing(avion,precio);

        agregarVuelo(tiempo.plusDays(1),duracion,tiempo.plusYears(1).toLocalDate(),"Madrid","Paris","BARBAR-COPIA",avion, pricing,otroPiloto);
        agregarVuelo(tiempo.plusDays(2),duracion,tiempo.plusYears(1).toLocalDate(),"Paris","Ezeiza","BARBOR",avion, pricing,unPiloto);

        pasajero = new Pasajero(40999222);
        venderAsiento("BARBAR","2B",pasajero,tiempo.toLocalDate());
        venderAsiento("LOLBAR","2B",pasajero,tiempo.toLocalDate().plusDays(7));
        venderAsiento("BARBAR-COPIA","2B",pasajero,tiempo.toLocalDate().plusDays(1));
        venderAsiento("BARBOR","1A",pasajero,tiempo.toLocalDate().plusDays(2));

        pasajero = new Pasajero(40719052);
        venderAsiento("BARBAR","3B",pasajero,tiempo.toLocalDate());
        venderAsiento("LOLBAR","3B",pasajero,tiempo.toLocalDate().plusDays(7));
        venderAsiento("BARBAR-COPIA","2A",pasajero,tiempo.toLocalDate().plusDays(1));
        venderAsiento("BARBOR","2B",pasajero,tiempo.toLocalDate().plusDays(2));

        pasajero = new Pasajero(40719050);
        venderAsiento("BARBAR","1A",pasajero,tiempo.toLocalDate());
        venderAsiento("LOLBAR","1A",pasajero,tiempo.toLocalDate().plusDays(7));
        venderAsiento("BARBAR-COPIA","3B",pasajero,tiempo.toLocalDate().plusDays(1));
        venderAsiento("BARBOR","3B",pasajero,tiempo.toLocalDate().plusDays(2));


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
    public void venderAsiento(String codigoDeVuelo, String codigoDeAsiento, Pasajero pasajero, LocalDate date) {
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
    public void agregarVuelo(LocalDateTime tiempo, Duration duracion, LocalDate ultimaFechaDeVuelo, String aeropuertoSalida, String aeropuertoArribo,String codigoDeVuelo, Avion avion,Pricing pricing, Piloto piloto) {

        Vuelo vuelo = new Vuelo(tiempo, duracion,  ultimaFechaDeVuelo,aeropuertoSalida,aeropuertoArribo,codigoDeVuelo,avion,pricing,piloto);

        listaDeVuelos.add(vuelo);
        codigosDeVuelo.add(codigoDeVuelo);

        boolean pilotoEncontrado = false;
        for (Piloto unPiloto: listaPilotos) {
            if(unPiloto.getDni() == piloto.getDni()){
                unPiloto.agregarVuelo(vuelo);
                pilotoEncontrado = true;
                break;
            }
        }

        if(!pilotoEncontrado){
            piloto.agregarVuelo(vuelo);
            listaPilotos.add(piloto);
        }

    }

    @Override
    public String getReservasCliente(Pasajero pasajero){
        String result = "";
        for (Vuelo vuelo: listaDeVuelos) {
            result += vuelo.getReservasCliente(pasajero);
        }
        if(result.equals("")){
            return "No se encontraron vuelos para el DNI: " + pasajero.getDni();
        }

        return result;
    }

    @Override
    public boolean esAdmin(int dni){
        for (Administrador administrador: administradores) {
            if(administrador.getDni() == dni) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean administradorPuedeVender(int dni){
        for (Administrador administrador: administradores) {
            if(administrador.getDni() == dni) {
                if (administrador.getArea().getCapacidadDeVender()) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void crearAvion(Avion avion) {
        aviones.add(avion);
    }

    @Override
    public ArrayList<Avion> getListaDeAviones() {
        return aviones;
    }

    @Override
    public void agregarVuelo(Vuelo vuelo){
        listaDeVuelos.add(vuelo);
    }

    @Override
    public ArrayList<Vuelo> getVuelosPiloto(Piloto piloto){
        //Ver buscarVuelosPiloto, esta mejor
        validarDniPiloto(piloto.getDni());

        for(Piloto unPiloto : listaPilotos){
            if(unPiloto.getDni() == piloto.getDni()){
                return unPiloto.getListaVuelos();
            }
        }
        return piloto.getListaVuelos();
    }

    @Override
    public ArrayList<Piloto> getListaDePilotos() {
        return listaPilotos;
    }

    public boolean validarDniPiloto(int dni){
        boolean dniExistente = false;
        for(Piloto piloto : listaPilotos){
            if(piloto.getDni() == dni){
                return true;
            }
        }
        throw new RuntimeException("Piloto no encontrado.");
    }
}



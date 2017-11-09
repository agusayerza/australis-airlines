import avion.Avion;
import personas.Administrador;
import personas.Pasajero;
import vuelo.Vuelo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Protocol {
    int userDNI;
    Servicios server;
    Administrador user;

    public Protocol() {
        server = new MockServer();
    }

    public String getTicketsForThisUser(int dni) {
        Pasajero pasajero = new Pasajero(dni);
        return server.getReservasCliente(pasajero);
    }

    public ArrayList<Vuelo> getPossibleFlights(String from, String to, LocalDate fechaSalida) {

        ArrayList<Vuelo> result = new ArrayList<>();
        result = server.getFlightsOnDateFromToDestination(fechaSalida, from, to);


        return result;
    }

    public void sellTicket(String codigoDeVuelo, String asiento, LocalDate date, Pasajero pasajero) {
        server.venderAsiento(codigoDeVuelo, asiento, pasajero, date);
    }

    public boolean getAdministradorPuedeVender(int dni) {
        return server.administradorPuedeVender(dni);
    }

    public boolean esAdmin(int dni){
        return server.esAdmin(dni);
    }

    public void crearAvion(Avion avion){
        server.crearAvion(avion);
    }

    public void agregarVuelo(Vuelo vuelo){
        server.agregarVuelo(vuelo.getStartDate(),vuelo.getDuracionDeVuelo(),vuelo.getEndDate(),vuelo.getAeropuertoDePartida(),vuelo.getAeropuertoDeArribo(),vuelo.getCodigoDeVuelo(),vuelo.getAvion(),vuelo.getPricing(),vuelo.getPiloto());
    }

    public ArrayList<Avion> getListaDeAviones(){
        return server.getListaDeAviones();
    }
}

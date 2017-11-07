import personas.Pasajero;
import vuelo.Vuelo;

import java.time.LocalDate;
import java.util.ArrayList;

public class Protocol {
    int userDNI;
    Servicios server;
    Pasajero user;

    public Protocol(int userDNI) {

        this.userDNI = userDNI;
        server = new MockServer();
        user = new Pasajero(userDNI);
    }

    public String getTicketsForThisUser(){
        return server.getReservasCliente(user);
    }

    public ArrayList<Vuelo> getPossibleFlights(String from, String to, LocalDate fechaSalida){

        ArrayList<Vuelo> result = new ArrayList<>();
        result = server.getFlightsOnDateFromToDestination(fechaSalida, from, to);


        return result;
    }
}

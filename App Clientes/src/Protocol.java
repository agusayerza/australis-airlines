import vuelo.Vuelo;

import java.time.LocalDate;
import java.util.ArrayList;

public class Protocol {
    int userDNI;
    Servicios server;


    public Protocol(int userDNI) {

        this.userDNI = userDNI;
        server = new MockServer();
    }

    public void getTicketsForThisUser(){
        //Envia al servidor el pedido de la listaDeVuelos de pasajes
    }

    public ArrayList<Vuelo> getPossibleFlights(String from, String to, LocalDate fechaSalida){

        ArrayList<Vuelo> result = new ArrayList<>();
        result = server.getFlightsOnDateFromToDestination(fechaSalida, from, to);


        return result;
    }
}

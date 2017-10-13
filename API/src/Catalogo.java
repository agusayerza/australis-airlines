import vuelo.Vuelo;

import java.time.LocalDate;
import java.util.ArrayList;

public class Catalogo {

    private ListaDeVuelos vuelos;

    public Catalogo() {
        vuelos = new ListaDeVuelos();
    }

    public void addVuelo(Vuelo vuelo){
        vuelos.addVuelo(vuelo);
    }

    public void removeVuelo(String codigoDeVuelo){
        vuelos.removeVuelo(codigoDeVuelo);
    }

    public ArrayList<Vuelo> getFlightsOnDate(LocalDate date){
        return vuelos.getFlightsOnDate(date);
    }

    public ArrayList<Vuelo> getFlightsOnDateFromToDestination(LocalDate date, String aeropuertoDePartida, String aeropuertoDeArribo){
        return vuelos.getFlightsOnDateFromToDestination(date,aeropuertoDePartida,aeropuertoDeArribo);
    }

}
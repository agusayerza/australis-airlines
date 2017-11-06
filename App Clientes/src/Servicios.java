import avion.Asiento;
import personas.Pasajero;
import personas.Piloto;
import vuelo.Vuelo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public interface Servicios {

    ArrayList<Vuelo> getFlightsOnDateFromToDestination(LocalDate date, String aeropuertoDePartida, String aeropuertoDeArribo);

    void venderAsiento(LocalDate date, String codigoDeVuelo, String codigoDeAsiento, Pasajero pasajero);

    ArrayList<Vuelo> BuscarVuelosPiloto(int dni);
}

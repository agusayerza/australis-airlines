import avion.Asiento;
import avion.Avion;
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

    void agregarVuelo(Avion avion, Piloto piloto, LocalDate tiempo, String codigoDeVuelo, String aeropuertoSalida, String aeropuertoArribo, LocalDate ultimaFechaDeVuelo);
}

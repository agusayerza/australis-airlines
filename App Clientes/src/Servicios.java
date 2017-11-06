import avion.Asiento;
import personas.Pasajero;
import personas.Piloto;
import vuelo.Vuelo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public interface Servicios {

    ArrayList<Vuelo> BuscarVuelos(LocalDateTime fecha);

    ArrayList<Vuelo> BuscarVuelos(String aeropuerto);

    ArrayList<Vuelo> getFlightsOnDateFromToDestination(LocalDate date, String aeropuertoDePartida, String aeropuertoDeArribo);

    void venderAsiento(LocalDate date, String codigoDeVuelo, String codigoDeAsiento, Pasajero pasajero);

    Vuelo BuscarVueloCodigo(String codigo);

    void OcuparAsiento(LocalDate fecha, String codigoVuelo, String codigoAsiento, Pasajero pasajero);

    ArrayList<Vuelo> BuscarVuelosPiloto(int dni);
}

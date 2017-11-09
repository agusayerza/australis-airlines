import avion.Avion;
import catalogo.Pricing;
import personas.Pasajero;
import personas.Piloto;
import vuelo.Vuelo;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public interface Servicios {

    ArrayList<Vuelo> getFlightsOnDateFromToDestination(LocalDate date, String aeropuertoDePartida, String aeropuertoDeArribo);

    void venderAsiento(String codigoDeVuelo, String codigoDeAsiento, Pasajero pasajero, LocalDate date);

    ArrayList<Vuelo> buscarVuelosPiloto(int dni);

    void agregarVuelo(LocalDateTime tiempo, Duration duracion, LocalDate ultimaFechaDeVuelo, String aeropuertoSalida, String aeropuertoArribo, String codigoDeVuelo, Avion avion, Pricing pricing, Piloto piloto);

    String getReservasCliente(Pasajero pasajero);

    ArrayList<Vuelo> getVuelosPiloto(Piloto piloto);

    public ArrayList<Piloto> getListaDePilotos();

}
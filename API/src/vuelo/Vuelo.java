package vuelo;

import java.time.Duration;
import java.time.Instant;
import avion.*;

public class Vuelo {

    /**
     * @param startDate Primer fecha de vuelo
     * @param aeropuertoDePartida Aeropuerto de partida.
     * @param aeropuertoDeArribo Aeropuerto de arribo.
     * @param codigoDeVuelo Codigo Identificador del Vuelo.
     * @param duracionDeOperaciones Durante cuanto tiempo desde startDate estara dispoible ese vuelo.
     * @param avion El avion utilizado para el vuelo.
     */

    private Instant startDate;
    private String aeropuertoDePartida;
    private String aeropuertoDeArribo;
    private String codigoDeVuelo;
    private Duration duracionDeOperaciones;
    private Avion avion;

    public Vuelo(Instant startDate, String aeropuertoDePartida, String aeropuertoDeArribo, String codigoDeVuelo, Duration duracionDeOperaciones, Avion avion) {
        this.startDate = startDate;
        this.aeropuertoDePartida = aeropuertoDePartida;
        this.aeropuertoDeArribo = aeropuertoDeArribo;
        this.codigoDeVuelo = codigoDeVuelo;
        this.duracionDeOperaciones = duracionDeOperaciones;
        this.avion = avion;
    }

    public Instant getStartDate() {
        return startDate;
    }

    public String getAeropuertoDePartida() {
        return aeropuertoDePartida;
    }

    public String getAeropuertoDeArribo() {
        return aeropuertoDeArribo;
    }

    public String getCodigoDeVuelo() {
        return codigoDeVuelo;
    }

    public Duration getDuracionDeOperaciones() {
        return duracionDeOperaciones;
    }

    public Avion getAvion() {
        return avion;
    }

}

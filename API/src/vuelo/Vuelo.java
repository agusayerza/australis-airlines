package vuelo;

import java.time.*;

import avion.*;
import catalogo.Pricing;

public class Vuelo {

    /**
     * @param endDate Ultima fecha de vuelo
     * @param aeropuertoDePartida Aeropuerto de partida.
     * @param aeropuertoDeArribo Aeropuerto de arribo.
     * @param codigoDeVuelo Codigo Identificador del Vuelo.
     * @param avion El avion utilizado para el vuelo.
     * @param pricing El precio de cada categoria de asiento.
     */

    private LocalDateTime startDate;
    private Duration duracionDeVuelo;
    private LocalDate endDate;
    private String aeropuertoDePartida;
    private String aeropuertoDeArribo;
    private String codigoDeVuelo;
    private Avion avion;
    private Pricing pricing;

    private DayOfWeek diaDeVuelo;
    private LocalTime horarioDeVuelo;

    public Vuelo(LocalDateTime startDate, Duration duracionDeVuelo, LocalDate endDate, String aeropuertoDePartida, String aeropuertoDeArribo, String codigoDeVuelo, Avion avion, Pricing pricing) {
        this.startDate = startDate;
        this.duracionDeVuelo = duracionDeVuelo;
        this.endDate = endDate;
        this.aeropuertoDePartida = aeropuertoDePartida;
        this.aeropuertoDeArribo = aeropuertoDeArribo;
        this.codigoDeVuelo = codigoDeVuelo;
        this.avion = avion;
        this.pricing = pricing;

        diaDeVuelo = startDate.getDayOfWeek();
        horarioDeVuelo = startDate.toLocalTime();
    }

    public void ocuparAsiento(){

    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public Duration getDuracionDeVuelo() {
        return duracionDeVuelo;
    }

    public DayOfWeek getDiaDeVuelo() {
        return diaDeVuelo;
    }

    public LocalTime getHorarioDeVuelo() {
        return horarioDeVuelo;
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

    public LocalDate getEndDate() {
        return endDate;
    }

    public Avion getAvion() {
        return avion;
    }

    public Pricing getPricing() {
        return pricing;
    }
}

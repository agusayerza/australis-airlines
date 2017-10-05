package vuelo;

import java.util.Date;

import avion.*;
import catalogo.Pricing;

public class Vuelo {

    /**
     * @param startDate Primer fecha de vuelo
     * @param endDate Ultima fecha de vuelo
     * @param aeropuertoDePartida Aeropuerto de partida.
     * @param aeropuertoDeArribo Aeropuerto de arribo.
     * @param codigoDeVuelo Codigo Identificador del Vuelo.
     * @param avion El avion utilizado para el vuelo.
     * @param pricing El precio de cada categoria de asiento.
     */

    private Date startDate;
    private Date endDate;
    private String aeropuertoDePartida;
    private String aeropuertoDeArribo;
    private String codigoDeVuelo;
    private Avion avion;
    private Pricing pricing;

    public Vuelo(Date startDate, Date endDate, String aeropuertoDePartida, String aeropuertoDeArribo, String codigoDeVuelo, Avion avion, Pricing pricing) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.aeropuertoDePartida = aeropuertoDePartida;
        this.aeropuertoDeArribo = aeropuertoDeArribo;
        this.codigoDeVuelo = codigoDeVuelo;
        this.avion = avion;
        this.pricing = pricing;

    }

    public Date getStartDate() {
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

    public Date getEndDate() { return endDate; }

    public Avion getAvion() {
        return avion;
    }

    public Pricing getPricing() {
        return pricing;
    }
}

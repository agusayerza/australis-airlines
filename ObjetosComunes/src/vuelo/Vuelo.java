package vuelo;

import java.time.*;

import avion.*;
import catalogo.Pricing;
import personas.Pasajero;

public class Vuelo {

    /**
     * @param endDate Ultima fecha de vuelo
     * @param aeropuertoDePartida Aeropuerto de partida.
     * @param aeropuertoDeArribo Aeropuerto de arribo.
     * @param codigoDeVuelo Codigo Identificador del Vuelo.
     * @param avion El avion utilizado para el vuelo.
     * @param pricing El precio de cada categoria de asiento.
     */

    //TODO: El avion no tiene piloto.

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

    public Vuelo(LocalDateTime startDateAndTime, Duration duracionDeVuelo, LocalDate endDate, String aeropuertoDePartida, String aeropuertoDeArribo, String codigoDeVuelo, Avion avion, Pricing pricing) {
        this.startDate = startDateAndTime;
        this.duracionDeVuelo = duracionDeVuelo;
        this.endDate = endDate;
        this.aeropuertoDePartida = aeropuertoDePartida;
        this.aeropuertoDeArribo = aeropuertoDeArribo;
        this.codigoDeVuelo = codigoDeVuelo;

        this.avion = ;   //Creamos una copia del avion, asi no se pisan los asientos reservados en memoria

        this.pricing = pricing;

        diaDeVuelo = startDate.getDayOfWeek();
        horarioDeVuelo = startDate.toLocalTime();
    }

    public void ocuparAsiento(String codigoDeAsiento, Pasajero pasajero){
        codigoDeAsiento.toUpperCase();
        //El codigo debe terminar con una letra
        if(Character.isLetter(codigoDeAsiento.charAt(codigoDeAsiento.length() - 1))){
            //El codigo debe tener al menos 2 caracteres
            if(codigoDeAsiento.length() >= 2){

                String codigoDeAsientosFila = codigoDeAsiento.substring(0,codigoDeAsiento.length() - 1);
                String regex = "[0-9]+";

                //La fila solo puede ser numerica
                if(codigoDeAsientosFila.matches(regex)){
                    avion.ocuparAsiento(codigoDeAsiento, pasajero);
                    return;
                }
            }
        }

        //TODO: Custom Exception
        throw new RuntimeException("Codigo de asiento invalido");


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

    public boolean hasFreeSeats(){
        for(Clase clase : avion.getClases()){
            if(clase.hasFreeSeats()){
                return true;
            }
        }
        return false;
    }
}

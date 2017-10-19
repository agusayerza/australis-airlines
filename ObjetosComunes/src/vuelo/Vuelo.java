package vuelo;

import java.time.*;
import java.util.HashMap;
import java.util.Map;

import avion.*;
import catalogo.Pricing;
import customExceptions.SeatAlreadyOccupiedException;
import customExceptions.SeatNonexistentException;
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

    private HashMap<String, Asiento> mapaDeAsientosGeneral;
    private HashMap<LocalDate, HashMap<String, Asiento>> mapaDeAsientosPorFecha;

    private DayOfWeek diaDeVuelo;
    private LocalTime horarioDeVuelo;

    public Vuelo(LocalDateTime startDateAndTime, Duration duracionDeVuelo, LocalDate endDate, String aeropuertoDePartida, String aeropuertoDeArribo, String codigoDeVuelo, Avion avion, Pricing pricing) {
        this.startDate = startDateAndTime;
        this.duracionDeVuelo = duracionDeVuelo;
        this.endDate = endDate;
        this.aeropuertoDePartida = aeropuertoDePartida;
        this.aeropuertoDeArribo = aeropuertoDeArribo;
        this.codigoDeVuelo = codigoDeVuelo;

        mapaDeAsientosGeneral = new HashMap<>();
        mapaDeAsientosPorFecha = new HashMap<>();

        for(Map.Entry<String, Asiento> entrada : avion.getMapaDeAsientos().entrySet()){
            //Creamos una copia limpia de los asientos para este vuelo
            Asiento asientoTemp = new Asiento(entrada.getValue().getFila(),entrada.getValue().getColumna(),entrada.getValue().getClase());
            mapaDeAsientosGeneral.put(asientoTemp.getFilaYColumna(),asientoTemp);
        }
        this.pricing = pricing;

        diaDeVuelo = startDate.getDayOfWeek();
        horarioDeVuelo = startDate.toLocalTime();
    }

    public void ocuparAsiento(String codigoDeAsiento, Pasajero pasajero, LocalDate date){

        if(!isValidFlightDate(date)){
            //TODO: Custom exception??? No estoy seguro si es necesario, por ahora no la hagamos
            throw new RuntimeException("Este vuelo no existe en la fecha solicitada");
        }

        if(!mapaDeAsientosPorFecha.containsKey(date)){
            //Si el vuelo en esta fecha no esta inicializado, lo hacemos.
            HashMap<String, Asiento> copiaMapaGeneral = new HashMap<>(mapaDeAsientosGeneral);
            mapaDeAsientosPorFecha.put(date,copiaMapaGeneral);
        }

        HashMap<String, Asiento> mapaDeAsientosParticular = mapaDeAsientosPorFecha.get(date);

        codigoDeAsiento.toUpperCase();
        //El codigo debe terminar con una letra
        if(Character.isLetter(codigoDeAsiento.charAt(codigoDeAsiento.length() - 1))){
            //El codigo debe tener al menos 2 caracteres
            if(codigoDeAsiento.length() >= 2){

                String codigoDeAsientosFila = codigoDeAsiento.substring(0,codigoDeAsiento.length() - 1);
                String regex = "[0-9]+";

                //La fila solo puede ser numerica
                if(codigoDeAsientosFila.matches(regex)){
                    if(mapaDeAsientosParticular.containsKey(codigoDeAsiento)){
                        Asiento asiento;
                        asiento = mapaDeAsientosParticular.get(codigoDeAsiento);
                        if (asiento.isOcupado()) {
                            throw new SeatAlreadyOccupiedException("El asiento deseado ya esta ocupado.");
                        }
                        int DNI = pasajero.getDni();
                        asiento.ocupar(DNI);
                        return;
                    }
                        //TODO hacer una custom exception para esto.
                        throw new RuntimeException("El asiento deseado no pertenece a esta clase.");
                }
            }
        }

        throw new SeatNonexistentException("No existe el asiento buscado.");


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

    public boolean hasFreeSeats(LocalDate date){
        if(!isValidFlightDate(date)){
            //TODO: Custom exception??? No estoy seguro si es necesario, por ahora no la hagamos
            throw new RuntimeException("Este vuelo no existe en la fecha solicitada");
        }

        if(!mapaDeAsientosPorFecha.containsKey(date)){
            //Si el vuelo en esta fecha no esta inicializado, por lo que esta vacio, tiene lugar.
            return true;
        }

        HashMap<String, Asiento> mapaDeAsientosParticular = mapaDeAsientosPorFecha.get(date);

        // TODO: Toto: cambiar esto a que devuelva un int
        for(Map.Entry<String, Asiento> entrada : mapaDeAsientosParticular.entrySet()){
            Asiento asiento = entrada.getValue();
            if(!asiento.isOcupado()){
                return true;
            }
        }
        return false;
    }

    public boolean isValidFlightDate(LocalDate date){
        // This flight actually flies on this date?
        LocalDate test = endDate.plusDays(1);
        if(date.isAfter(test) || !startDate.getDayOfWeek().equals(date.getDayOfWeek())){
            return false;
        }
        LocalDate bufferDate = startDate.toLocalDate();
        boolean stillLooping = true;

        while(stillLooping){
            if(bufferDate.isEqual(date)){
                return true;
            }
            if(bufferDate.isAfter(endDate)){
                stillLooping = false;
            }

            bufferDate = bufferDate.plusDays(7);
        }

        return false;
    }
}

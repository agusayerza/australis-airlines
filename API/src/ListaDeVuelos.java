

import customExceptions.FlightCodeAlreadyExistsException;
import customExceptions.FlightCodeNonexistentException;
import personas.Pasajero;
import personas.Persona;
import vuelo.Vuelo;

import java.time.LocalDate;
import java.util.ArrayList;

public class ListaDeVuelos {
    ArrayList<Vuelo> lista;
    ArrayList<String> codigosDeVuelo;

    public ListaDeVuelos() {
        lista = new ArrayList<>();
        codigosDeVuelo = new ArrayList<>();
    }

    public ArrayList<Vuelo> getLista() {
        return lista;
    }

    public void addVuelo(Vuelo vuelo){
        if(codigosDeVuelo.contains(vuelo.getCodigoDeVuelo())) {
            throw new FlightCodeAlreadyExistsException("Un vuelo con este codigo ya existe");
        }

        lista.add(vuelo);
        codigosDeVuelo.add(vuelo.getCodigoDeVuelo());
    }

    public void removeVuelo(String codigoDeVuelo){
        if(codigosDeVuelo.contains(codigoDeVuelo)){
            throw new FlightCodeNonexistentException("No existe un vuelo con este codigo");
        }

        for(int i = 0; i <= lista.size() - 1; i++)
        {
            if(lista.get(i).getCodigoDeVuelo().equals(codigoDeVuelo))
            {
                lista.remove(i);
                codigosDeVuelo.remove(codigoDeVuelo);
            }
        }
    }

    public ArrayList<Vuelo> getFlightsOnDate(LocalDate date){
        ArrayList<Vuelo> result = new ArrayList<>();

        for (Vuelo vuelo: lista) {
            if(vuelo.getDiaDeVuelo().equals(date.getDayOfWeek())){
                if(vuelo.getEndDate().isAfter(date)) {
                    if(vuelo.getStartDate().toLocalDate().isBefore(date) || vuelo.getStartDate().toLocalDate().isEqual(date)) {
                        if(vuelo.hasFreeSeats(date)){
                            result.add(vuelo);
                        }
                    }
                }
            }
        }
        return result;
    }

    public ArrayList<Vuelo> getFlightsOnDateFromToDestination(LocalDate date, String aeropuertoDePartida, String aeropuertoDeArribo){
        ArrayList<Vuelo> result = new ArrayList<>();
        ArrayList<Vuelo> previousResult = getFlightsOnDate(date);

        for (Vuelo vuelo: previousResult) {
            if(vuelo.getAeropuertoDeArribo().equals(aeropuertoDeArribo) && vuelo.getAeropuertoDePartida().equals(aeropuertoDePartida)){
                result.add(vuelo);
            }
        }
        return result;
    }
    private Vuelo getFlightByCode(String FlightCode){
        for (Vuelo vuelo: lista) {
            if(vuelo.getCodigoDeVuelo().equals(FlightCode)){
                return vuelo;
            }
        }
        throw new FlightCodeNonexistentException("No existe un vuelo con este codigo");
    }
    public void venderAsiento(LocalDate date, String codigoDeVuelo, String codigoDeAsiento, Pasajero pasajero){
        //TODO: Esto hasta ahora solo ocupa el asiento, no hace chequeos ni se lo carga al cliente como que el pasaje ya es suyo
        if(codigosDeVuelo.contains(codigoDeVuelo)){
            getFlightByCode(codigoDeVuelo).ocuparAsiento(codigoDeAsiento,pasajero,date);
        }else{
            throw new FlightCodeNonexistentException("No existe un vuelo con este codigo");
        }
    }


}
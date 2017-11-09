import customExceptions.FlightCodeAlreadyExistsException;
import customExceptions.FlightCodeNonexistentException;
import personas.Pasajero;
import vuelo.Vuelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ListaDeVuelos {
    ArrayList<Vuelo> lista;
    ArrayList<String> codigosDeVuelo;
    Map<String, ArrayList<String>> aeropuertos;

    public ListaDeVuelos() {
        lista = new ArrayList<>();
        codigosDeVuelo = new ArrayList<>();
        aeropuertos = new HashMap<>();
    }

    public ArrayList<Vuelo> getLista() {
        return lista;
    }

    public void addVuelo(Vuelo vuelo){
        if(codigosDeVuelo.contains(vuelo.getCodigoDeVuelo())) {
            throw new FlightCodeAlreadyExistsException("Un vuelo con este codigo ya existe");
        }

        lista.add(vuelo);
        verificarAddVuelo(vuelo);
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
                verificarRemoveVuelo(lista.get(i));
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


    /* por lo que agus me paso
    Cuando agregas un vuelo, debe pasar por aqui, falta implementarlo en agregar vuelo cuando lo termine
    Le pasas un vuelo que va de A -> B, y verifica si existe un arrayList de A (si no existe, lo crear y le agraga B) y
    verifica si contiene a B, si no, lo agrega, si si, no hace nada (?)
     */
    public void verificarAddVuelo(Vuelo vuelo) {
        String aeropuertoA = vuelo.getAeropuertoDePartida(); //aeropuerto A
        String aeropuertoB = vuelo.getAeropuertoDeArribo(); //aeropuerto B

        if ( !( aeropuertos.containsKey(aeropuertoA) ) ) {                      //verifica si el hashMap NO contiene el AL del aeropuerto A
            ArrayList<String> aero = new ArrayList<String>();                        //si no, lo crea
            aero.add(aeropuertoB);
            aeropuertos.put(aeropuertoA, aero);                                 //y lo agrega
                                                                                 //y le agrega el aeropuerto B
        } else if ( !( aeropuertos.get(aeropuertoA).contains(aeropuertoB) ) ){  //si exite el AL del aeropuerto A, verifica si NO contiene B
            ArrayList<String> aero = aeropuertos.get(aeropuertoA);                                        //si no cintiene B, lo agrega
            aero.add(aeropuertoB);
            aeropuertos.remove(aeropuertoA);
            aeropuertos.put(aeropuertoA,aero);
        } //lo pense asi, si queres cambia lo que quieras
    }

    public Map getAeropuertos() {
        return aeropuertos;
    }

    public void verificarRemoveVuelo(Vuelo vuelo) {
        String aeropuertoA = vuelo.getAeropuertoDePartida();
        String aeropuertoB = vuelo.getAeropuertoDeArribo();

        ArrayList<String> aero = aeropuertos.get(aeropuertoA);                                        //si no cintiene B, lo agrega
        aero.remove(aeropuertoB);
        aeropuertos.remove(aeropuertoA);
        aeropuertos.put(aeropuertoA,aero);
    }
}
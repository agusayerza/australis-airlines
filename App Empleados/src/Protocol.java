import vuelo.Vuelo;

import java.time.LocalDate;
import java.util.ArrayList;

public class Protocol {
    int userDNI;

    public Protocol(int userDNI) {
        this.userDNI = userDNI;
    }

    public void getTicketsForThisUser() {
        //Envia al servidor el pedido de la lista de pasajes del cliente
    }

    //Igual

    public ArrayList<ArrayList<Vuelo>> getPossibleFlights(String from, String to, LocalDate fechaSalida, int cantidadDePasajeros, int escalas){

        ArrayList<ArrayList<Vuelo>> result = new ArrayList<>();

        //Envia al servidor el pedido de una lista de posibles vuelos cumpliendo las condiciones
        for (int i = 0; i < result.size(); i++) {
            if(result.get(i).size() > escalas){
                // El set tiene mas escalas que las deseadas, hay que eliminarlo.
                result.remove(i);
            }
        }
        return result;
    }


}

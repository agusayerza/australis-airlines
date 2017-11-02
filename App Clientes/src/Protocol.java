import vuelo.Vuelo;

import java.time.LocalDate;
import java.util.ArrayList;

public class Protocol {
    int userDNI;

    public Protocol(int userDNI) {
        this.userDNI = userDNI;
    }

    public void getTicketsForThisUser(){
        //Envia al servidor el pedido de la lista de pasajes
    }

    public ArrayList<Vuelo> getPossibleFlights(String from, String to, LocalDate fechaSalida){

        ArrayList<Vuelo> result = new ArrayList<>();


        return result;
    }
}

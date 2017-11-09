import personas.Pasajero;
import personas.Persona;
import personas.Piloto;
import vuelo.Vuelo;

import java.time.LocalDate;
import java.util.ArrayList;

public class Protocol {
    int userDNI;
    Servicios server;
    Piloto user;

    public Protocol(int userDNI) {
        this.userDNI = userDNI;
        server = new MockServer();
        user = new Piloto(userDNI);
    }

    public ArrayList<Vuelo> getFlightsForPilot(){
        return server.getVuelosPiloto(user);
    }

}

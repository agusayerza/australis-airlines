package vuelo;

import avion.Asiento;
import personas.Pasajero;

public class Pasaje {
    private Asiento asiento;
    private Vuelo vuelo;
    private Pasajero pasajero;

    public Pasaje(Asiento asiento, Vuelo vuelo, Pasajero pasajero) {
        this.asiento = asiento;
        this.vuelo = vuelo;
        this.pasajero = pasajero;
    }

    public Asiento getAsiento() {
        return asiento;
    }

    public Vuelo getVuelo() {
        return vuelo;
    }
}

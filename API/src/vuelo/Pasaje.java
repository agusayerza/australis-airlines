package vuelo;

import avion.Asiento;

public class Pasaje {
    Asiento asiento;
    Vuelo vuelo;

    public Pasaje(Asiento asiento, Vuelo vuelo) {
        this.asiento = asiento;
        this.vuelo = vuelo;
    }

    public Asiento getAsiento() {
        return asiento;
    }

    public Vuelo getVuelo() {
        return vuelo;
    }
}

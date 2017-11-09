package vuelo;

import java.io.Serializable;
import java.time.LocalDate;

public class Pasaje implements Serializable {
    private String asiento;
    private String vuelo;

    private LocalDate date;
    private int pasajero;

    public Pasaje(String asiento, String vuelo, LocalDate date, int pasajero) {
        this.asiento = asiento;
        this.vuelo = vuelo;
        this.pasajero = pasajero;
        this.date = date;
    }

    public String getAsiento() {
        return asiento;
    }

    public String getVuelo() {
        return vuelo;
    }

    public LocalDate getDate() {
        return date;
    }

    public int getPasajero() {
        return pasajero;
    }
}

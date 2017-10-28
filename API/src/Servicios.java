import avion.Asiento;
import personas.Pasajero;
import vuelo.Vuelo;

import java.time.LocalDate;

public interface Servicios {

    void BuscarVuelo(Vuelo vuelo);

    void OcuparAsiento(LocalDate fecha, String codigoVuelo, String codigoAsiento, Pasajero pasajero);
}

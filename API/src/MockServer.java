import avion.Asiento;
import personas.Pasajero;
import vuelo.Vuelo;

import java.time.LocalDate;

public class MockServer implements Servicios {

    ListaDeVuelos ldv = new ListaDeVuelos();

    //TODO: Terminar esto
    public void BuscarVuelo(Vuelo vuelo)
    {

    }
    public void OcuparAsiento(LocalDate fecha, String codigoVuelo, String codigoAsiento, Pasajero pasajero)
    {
        ldv.venderAsiento(fecha, codigoVuelo, codigoAsiento, pasajero);
    }

}

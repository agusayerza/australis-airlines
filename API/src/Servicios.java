import avion.Asiento;
import vuelo.Vuelo;

public interface Servicios {

    void BuscarVuelo(Vuelo vuelo);

    void OcuparAsiento(Asiento asiento);
}

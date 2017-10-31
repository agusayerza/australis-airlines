import avion.Asiento;
        import personas.Pasajero;
        import vuelo.Vuelo;

        import java.time.LocalDate;
        import java.time.LocalDateTime;
        import java.util.ArrayList;

public class MockServer implements Servicios {

    ListaDeVuelos ldv = new ListaDeVuelos();


    //TODO: Agregar HasFreeSeats antes de devolver la lista ?
    public ArrayList<Vuelo> BuscarVuelos(LocalDateTime fecha)
    {
        ArrayList<Vuelo> vuelos = new ArrayList<>();
        for(Vuelo v : ldv.getLista())
        {
            if(v.getStartDate() == fecha)
            {
                if(!vuelos.contains(v))
                {
                    vuelos.add(v);
                }
            }
        }
        return vuelos;
    }

    public ArrayList<Vuelo> BuscarVuelos(String aeropuerto)
    {
        ArrayList<Vuelo> vuelos = new ArrayList<>();
        for(Vuelo v : ldv.getLista())
        {
            if(v.getAeropuertoDePartida() == aeropuerto)
            {
                if(!vuelos.contains(v))
                {
                    vuelos.add(v);
                }
            }
        }
        return vuelos;
    }

    public Vuelo BuscarVueloCodigo(String codigo)
    {
        for(Vuelo v : ldv.getLista())
        {
            if(v.getCodigoDeVuelo() == codigo)
            {
                return v;
            }
        }
        return null;
    }

    public void OcuparAsiento(LocalDate fecha, String codigoVuelo, String codigoAsiento, Pasajero pasajero)
    {
        ldv.venderAsiento(fecha, codigoVuelo, codigoAsiento, pasajero);
    }
}

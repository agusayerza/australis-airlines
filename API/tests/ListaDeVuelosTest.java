import avion.Avion;
import avion.Clase;
import catalogo.Pricing;
import org.junit.Assert;
import org.junit.Test;
import personas.Pasajero;
import vuelo.Vuelo;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class ListaDeVuelosTest {

    @Test
    public void getFlightsOnDate() throws Exception {
        Catalogo catalogo = new Catalogo();
        Clase clase = new Clase(1,21,3,"Primera");
        Clase clases[] = new Clase[1];
        clases[0] = clase;

        Avion avion = new Avion("funcional",clases);

        LocalDateTime tiempo = LocalDateTime.now();

        Duration duracion = Duration.ofHours(8);
        double precio[] = new double[1];
        precio[0] = 199.3;
        Pricing pricing = new Pricing(avion,precio);
        Vuelo vuelo = new Vuelo(tiempo,duracion,tiempo.plusYears(1).toLocalDate(),"Ezeiza","Paris","BARBAR",avion, pricing);

        catalogo.addVuelo(vuelo);

        ArrayList<Vuelo> expected = new ArrayList<>();
        expected.add(vuelo);

        assertTrue(catalogo.getFlightsOnDate(tiempo.toLocalDate()).equals(expected));
        assertTrue(catalogo.getFlightsOnDate(tiempo.plusDays(7).toLocalDate()).equals(expected));
        assertFalse(catalogo.getFlightsOnDate(tiempo.plusDays(1).toLocalDate()).equals(expected));
        assertFalse(catalogo.getFlightsOnDate(tiempo.plusDays(400).toLocalDate()).equals(expected));
        assertFalse(catalogo.getFlightsOnDate(tiempo.minusDays(7).toLocalDate()).equals(expected));

    }

    @Test
    public void getFlightsOnDateFromToDestination() throws Exception {
        Catalogo catalogo = new Catalogo();
        Clase clase = new Clase(1,21,3,"Primera");
        Clase clases[] = new Clase[1];
        clases[0] = clase;

        Avion avion = new Avion("funcional",clases);

        LocalDateTime tiempo = LocalDateTime.now();

        Duration duracion = Duration.ofHours(8);
        double precio[] = new double[1];
        precio[0] = 199.3;
        Pricing pricing = new Pricing(avion,precio);
        Vuelo vuelo = new Vuelo(tiempo,duracion,tiempo.plusYears(1).toLocalDate(),"Ezeiza","Paris","BARBAR",avion, pricing);
        Vuelo copiaVuelo = new Vuelo(tiempo,duracion,tiempo.plusYears(1).toLocalDate(),"Ezeiza","Paris","BARBAR-COPIA",avion, pricing);

        Vuelo otroVuelo = new Vuelo(tiempo.plusDays(1),duracion,tiempo.plusYears(1).toLocalDate(),"Paris","Ezeiza","BARBOR",avion, pricing);

        catalogo.addVuelo(vuelo);
        catalogo.addVuelo(otroVuelo);
        ArrayList<Vuelo> expected = new ArrayList<>();

        expected.add(vuelo);
        assertTrue(catalogo.getFlightsOnDateFromToDestination(tiempo.toLocalDate(),"Ezeiza","Paris").equals(expected));

        expected.remove(vuelo);
        assertTrue(catalogo.getFlightsOnDateFromToDestination(tiempo.toLocalDate().plusDays(1),"Paris","Madrid").equals(expected));

        expected.add(otroVuelo);
        assertTrue(catalogo.getFlightsOnDateFromToDestination(tiempo.toLocalDate().plusDays(1),"Paris","Ezeiza").equals(expected));

        catalogo.addVuelo(copiaVuelo);
        expected.add(vuelo);
        expected.remove(otroVuelo);
        expected.add(copiaVuelo);
        assertTrue(catalogo.getFlightsOnDateFromToDestination(tiempo.toLocalDate(),"Ezeiza","Paris").equals(expected));

    }

    @Test
    public void hasFlightFreeSeatsTest() throws Exception{
        Catalogo catalogo = new Catalogo();

        Clase clase = new Clase(1,1,1,"Primera");
        Clase clases[] = new Clase[1];
        clases[0] = clase;

        Avion avion = new Avion("funcional",clases);
        Pasajero pasajero = new Pasajero(40719053);

        LocalDateTime tiempo = LocalDateTime.now();

        Duration duracion = Duration.ofHours(8);
        double precio[] = new double[1];
        precio[0] = 199.3;
        Pricing pricing = new Pricing(avion,precio);

        Vuelo vuelo = new Vuelo(tiempo,duracion,tiempo.plusYears(1).toLocalDate(),"Ezeiza","Paris","BARBAR",avion, pricing);
        Vuelo otroVuelo = new Vuelo(tiempo,duracion,tiempo.plusYears(1).toLocalDate(),"Ezeiza","Paris","BARBAR-COPIA",avion, pricing);

        vuelo.ocuparAsiento("1A", pasajero);
        catalogo.addVuelo(vuelo);

        ArrayList<Vuelo> expected = new ArrayList<>();

        assertTrue(catalogo.getFlightsOnDateFromToDestination(tiempo.toLocalDate(),"Ezeiza","Paris").equals(expected));

        catalogo.addVuelo(otroVuelo);
        expected.add(otroVuelo);

        assertTrue(catalogo.getFlightsOnDateFromToDestination(tiempo.toLocalDate(),"Ezeiza","Paris").equals(expected));


    }

}
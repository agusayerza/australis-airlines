import avion.Avion;
import avion.Clase;
import catalogo.Pricing;
import customExceptions.SeatAlreadyOccupiedException;
import org.junit.*;
import org.junit.rules.ExpectedException;
import personas.Pasajero;
import personas.Piloto;
import vuelo.Vuelo;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class ListaDeVuelosTest {
    Catalogo catalogo;
    LocalDateTime tiempo;
    Vuelo vuelo;
    Duration duracion;
    Pricing pricing;
    Pasajero pasajero;
    Avion avion;
    Clase[] clases;

    @Before
    public void SetUp(){
        catalogo = new Catalogo();
        Clase clase = new Clase(1,1,1,"Primera");
        clases = new Clase[1];
        clases[0] = clase;
        Piloto piloto = new Piloto(17888888);

        avion = new Avion("funcional",clases);
        pasajero = new Pasajero(40719053);
        tiempo = LocalDateTime.now();

        duracion = Duration.ofHours(8);
        double precio[] = new double[1];
        precio[0] = 199.3;
        pricing = new Pricing(avion,precio);
        vuelo = new Vuelo(tiempo,duracion,tiempo.plusYears(1).toLocalDate(),"Ezeiza","Paris","BARBAR",avion, pricing,piloto);

        catalogo.addVuelo(vuelo);

    }

    @Test
    public void getFlightsOnDate() throws Exception {

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
        Piloto otroPiloto = new Piloto(20000000);
        Piloto unPiloto = new Piloto(19999998);
        Vuelo copiaVuelo = new Vuelo(tiempo,duracion,tiempo.plusYears(1).toLocalDate(),"Ezeiza","Paris","BARBAR-COPIA",avion, pricing,otroPiloto);
        Vuelo otroVuelo = new Vuelo(tiempo.plusDays(1),duracion,tiempo.plusYears(1).toLocalDate(),"Paris","Ezeiza","BARBOR",avion, pricing,unPiloto);

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

    @After
    public void tearDown(){
        catalogo = new Catalogo();
    }

    @Test
    public void hasFlightFreeSeatsTest() throws Exception{
        Piloto otroPiloto = new Piloto(20000000);
        Vuelo otroVuelo = new Vuelo(tiempo.plusDays(1),duracion,tiempo.plusYears(1).toLocalDate(),"Ezeiza","Paris","BARBAR-COPIA",avion, pricing,otroPiloto);

        vuelo.ocuparAsiento("1A", pasajero,tiempo.toLocalDate());

        ArrayList<Vuelo> expected = new ArrayList<>();

        assertTrue(catalogo.getFlightsOnDateFromToDestination(tiempo.toLocalDate(),"Ezeiza","Paris").equals(expected));

        catalogo.addVuelo(otroVuelo);
        expected.add(otroVuelo);

        assertTrue(catalogo.getFlightsOnDateFromToDestination(tiempo.toLocalDate().plusDays(1),"Ezeiza","Paris").equals(expected));


    }
    @Test(expected = SeatAlreadyOccupiedException.class)
    public void SeatAlreadyOccupiedTest(){

        Piloto otroPiloto = new Piloto(20000000);
        Piloto unPiloto = new Piloto(19999998);
        Vuelo vuelo = new Vuelo(tiempo,duracion,tiempo.plusYears(1).toLocalDate(),"Ezeiza","Paris","BARBAR",avion, pricing,otroPiloto);
        Vuelo otroVuelo = new Vuelo(tiempo,duracion,tiempo.plusYears(1).toLocalDate(),"Ezeiza","Paris","BARBAR-COPIA",avion, pricing,unPiloto);

        vuelo.ocuparAsiento("1A", pasajero,tiempo.toLocalDate());
        vuelo.ocuparAsiento("1A", pasajero,tiempo.toLocalDate());
    }

    @Test
    public void SellSeatsOfSameFlightsOnDifferentDatesTest(){
        catalogo.venderAsiento(tiempo.toLocalDate(),"BARBAR","1A",pasajero);
        catalogo.venderAsiento(tiempo.toLocalDate().plusDays(7),"BARBAR","1A",pasajero);

    }

    @Test(expected = SeatAlreadyOccupiedException.class)
    public void SeatAlreadyOccupiedFromFlightListTest(){
        catalogo.venderAsiento(tiempo.toLocalDate(),"BARBAR","1A",pasajero);
        catalogo.venderAsiento(tiempo.toLocalDate(),"BARBAR","1A",pasajero);
    }

}
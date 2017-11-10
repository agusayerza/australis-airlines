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


    }

    @Test
    public void getFlightsOnDate() throws Exception {

        ArrayList<Vuelo> expected = new ArrayList<>();
        expected.add(vuelo);

        assertTrue(flightListEquals(catalogo.getFlightsOnDate(tiempo.toLocalDate()),expected));
        assertTrue(flightListEquals(catalogo.getFlightsOnDate(tiempo.plusDays(7).toLocalDate()),expected));
        assertFalse(flightListEquals(catalogo.getFlightsOnDate(tiempo.plusDays(1).toLocalDate()),expected));
        assertFalse(flightListEquals(catalogo.getFlightsOnDate(tiempo.plusDays(403).toLocalDate()),expected));
        assertFalse(flightListEquals(catalogo.getFlightsOnDate(tiempo.minusDays(7).toLocalDate()),expected));

    }

    public boolean flightListEquals(ArrayList<Vuelo> unLista, ArrayList<Vuelo> otraLista){
        if(unLista.size() != otraLista.size()){
            return false;
        }
        for(Vuelo vuelo : unLista){
            boolean encontreIguales = false;
            for(Vuelo segundoVuelo : otraLista){
                if(vuelo.getCodigoDeVuelo().equals(segundoVuelo.getCodigoDeVuelo())){
                    encontreIguales = true;
                    continue;
                }
            }
            if(encontreIguales == false){
                return false;
            }
        }
        return true;
    }

    @Test
    public void getFlightsOnDateFromToDestination() throws Exception {

        //catalogo.addVuelo(otroVuelo);
        Piloto otroPiloto = new Piloto(20000000);
        Piloto unPiloto = new Piloto(19999998);
        Vuelo otroVuelo = new Vuelo(tiempo.plusDays(1),duracion,tiempo.plusYears(1).toLocalDate(),"Ezeiza","Paris","BARBAR-COPIA",avion, pricing,otroPiloto);
        Vuelo copiaVuelo = new Vuelo(tiempo.plusDays(1),duracion,tiempo.plusYears(1).toLocalDate(),"Madrid","Paris","BARBAR-COPIA",avion, pricing,unPiloto);

        ArrayList<Vuelo> expected = new ArrayList<>();

        expected.add(vuelo);
        assertTrue(flightListEquals(catalogo.getFlightsOnDateFromToDestination(tiempo.toLocalDate(),"Ezeiza","Paris"),expected));

        expected.remove(vuelo);
        assertTrue(flightListEquals(catalogo.getFlightsOnDateFromToDestination(tiempo.toLocalDate().plusDays(1),"Paris","Madrid"),expected));

        expected.add(copiaVuelo);
        assertTrue(flightListEquals(catalogo.getFlightsOnDateFromToDestination(tiempo.toLocalDate().plusDays(1),"Madrid","Paris"),expected));

    }

    @After
    public void tearDown(){

    }


    @Test(expected = SeatAlreadyOccupiedException.class)
    public void SeatAlreadyOccupiedTest(){

        Piloto otroPiloto = new Piloto(20000000);
        Piloto unPiloto = new Piloto(19999998);
        Vuelo vuelo = new Vuelo(tiempo,duracion,tiempo.plusYears(1).toLocalDate(),"Ezeiza","Paris","BARBAR",avion, pricing,otroPiloto);

        vuelo.ocuparAsiento("1A", pasajero,tiempo.toLocalDate());
        vuelo.ocuparAsiento("1A", pasajero,tiempo.toLocalDate());
    }

    @Test
    public void SellSeatsOfSameFlightsOnDifferentDatesTest(){
        catalogo.ocuparAsiento(tiempo.toLocalDate(),"BARBAR","1A",pasajero);
        catalogo.ocuparAsiento(tiempo.toLocalDate().plusDays(7),"BARBAR","1A",pasajero);

    }

    @Test(expected = SeatAlreadyOccupiedException.class)
    public void SeatAlreadyOccupiedFromFlightListTest(){
        catalogo.ocuparAsiento(tiempo.toLocalDate(),"BARBAR","1A",pasajero);
        catalogo.ocuparAsiento(tiempo.toLocalDate(),"BARBAR","1A",pasajero);
    }

}
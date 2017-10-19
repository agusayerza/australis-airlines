package vuelo;

import avion.Avion;
import avion.Clase;
import catalogo.Pricing;
import org.junit.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class VueloTest {
    @Test
    public void ocuparAsiento() throws Exception {
    }

    @Test
    public void hasFreeSeats() throws Exception {
    }

    @Test
    public void isValidFlightDate() throws Exception {
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

        LocalDate date = tiempo.toLocalDate();

        assertTrue(vuelo.isValidFlightDate(date));
        assertTrue(vuelo.isValidFlightDate(date.plusDays(7)));
        assertFalse(vuelo.isValidFlightDate(date.plusDays(1)));
        assertTrue(vuelo.isValidFlightDate(date.plusDays(364)));
        assertFalse(vuelo.isValidFlightDate(date.plusDays(728)));
    }

}
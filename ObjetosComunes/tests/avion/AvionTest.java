package avion;

import org.junit.Test;

import static org.junit.Assert.*;

public class AvionTest {
    @Test
    public void getPatente() throws Exception {

        Clase primeraClase = new Clase(1,10,5,"Primera");
        Clase claseBussines = new Clase(primeraClase.getUltimaFilaDeClase() + 1,24,6,"Bussines");
        Clase claseEconomica = new Clase(claseBussines.getUltimaFilaDeClase() + 1,114,19,"Economica");

        Clase[] clases = new Clase[3];

        clases[0] = primeraClase;
        clases[1] = claseBussines;
        clases[2] = claseEconomica;

        Avion avion = new Avion("test",clases);
        assertEquals("test", avion.getPatente());
    }

    @Test
    public void getMapaDeAsientos() throws Exception {

        //TODO: No es un test real, lo estoy usando para debugging -A
        Clase primeraClase = new Clase(1,10,5,"Primera");
        Clase claseBussines = new Clase(primeraClase.getUltimaFilaDeClase() + 1,24,6,"Bussines");
        Clase claseEconomica = new Clase(claseBussines.getUltimaFilaDeClase()+ 1,114,19,"Economica");

        Clase[] clases = new Clase[3];

        clases[0] = primeraClase;
        clases[1] = claseBussines;
        clases[2] = claseEconomica;

        Avion avion = new Avion("test",clases);

        assertEquals("test", avion.getPatente());
        System.out.println(avion.getAsientoLayout());

        //avion.ocuparAsiento
        System.out.println(avion.getAsientoLayout());

    }

}
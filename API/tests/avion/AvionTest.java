package avion;

import org.junit.Test;

import static org.junit.Assert.*;

public class AvionTest {
    @Test
    public void getPatente() throws Exception {
        Avion avion = new Avion("test",1,1);
        assertEquals("test", avion.getPatente());
    }

    @Test
    public void getMapaDeAsientos() throws Exception {
        //TODO: No es un test real, lo estoy usando para debugging -A
        Avion avion = new Avion("test",28,3);
        assertEquals("test", avion.getPatente());
        System.out.println(avion.getAsientoLayout());
    }

}
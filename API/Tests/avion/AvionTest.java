package avion;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Agus on 3/10/17.
 */
public class AvionTest {
    @Test
    public void getAsientoLayout() throws Exception {
        Avion avion = new Avion("TEST",15,3);

        System.out.println(avion.getAsientoLayout());
    }

}
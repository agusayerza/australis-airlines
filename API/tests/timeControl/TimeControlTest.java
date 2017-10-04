package timeControl;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

public class TimeControlTest {
    @Test
    public void hasPassed() throws Exception {
        TimeControl timer = new TimeControl();

        String oldDate  = "03/10/2017";
        assertEquals(true, timer.hasPassed(oldDate));

        String futureDate  = "20/12/2099"; //Esto puede fallar dentro de varios años....
        assertEquals(false, timer.hasPassed(futureDate));

        Date now = new Date();
        String myFormatString = "dd/MM/yy";
        SimpleDateFormat df = new SimpleDateFormat(myFormatString);

        String actualDate = df.format(now).toString();
        assertEquals(true, timer.hasPassed(actualDate));
    }

}
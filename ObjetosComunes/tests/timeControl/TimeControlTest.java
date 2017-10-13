package timeControl;

import org.junit.Test;

import java.time.LocalDate;
import java.time.ZoneId;

import static org.junit.Assert.*;

public class TimeControlTest {
    @Test
    public void hasPassed() throws Exception {
        TimeControl timer = new TimeControl();

        String oldDate  = "2017-10-03";
        assertEquals(true, timer.hasPassed(oldDate));

        String futureDate  = "2099-12-30"; //Esto puede fallar dentro de varios años....
        assertEquals(false, timer.hasPassed(futureDate));

        ZoneId zone = ZoneId.systemDefault();
        LocalDate now = LocalDate.now(zone);

        //String myFormatString = "dd/MM/yy";
        //SimpleDateFormat df = new SimpleDateFormat(myFormatString);
        //String actualDate = df.format(now).toString();

        assertEquals(true, timer.hasPassed(now.toString()));
    }

}
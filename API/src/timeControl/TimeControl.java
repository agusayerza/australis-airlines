package timeControl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class TimeControl {
    private LocalDate now;

    public TimeControl() {
    }

    public boolean hasPassed(String dateToParse) throws ParseException {

        ZoneId z = ZoneId.systemDefault();
        LocalDate now = LocalDate.now( z );


        DateTimeFormatter f = DateTimeFormatter.ofPattern( "d/MM/yyyy" );
        LocalDate ld = LocalDate.parse( dateToParse , f );


        //Comparamos las fechas
        if(ld.isAfter(now) || (ld.equals(now))){
            return false;
        } else {
            return true;
        }
    }
}

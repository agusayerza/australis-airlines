package timeControl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeControl {
    private Date now;

    public TimeControl() {
    }

    public boolean hasPassed(String dateToParse) throws ParseException {

        Date now = new Date();

        String myFormatString = "dd/MM/yy";
        SimpleDateFormat df = new SimpleDateFormat(myFormatString);
        Date givenDate = df.parse(dateToParse);
        Long l = givenDate.getTime();

        //Comparamos las fechas
        if(givenDate.after(now) || (givenDate.equals(now))){
            return false;
        } else {
            return true;
        }
    }
}

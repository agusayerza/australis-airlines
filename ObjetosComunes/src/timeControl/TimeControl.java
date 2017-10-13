package timeControl;

import java.text.ParseException;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;


public class TimeControl {
    private LocalDate now;

    public TimeControl() {
    }

    public boolean hasPassed(String dateToParse) throws ParseException {
        /*
        *  La funcion has passed compara dia contra dia, no tiene en cuenta horarios y por ende
        *  si las fechas son iguales, considera que esa fecha ya paso.
        *  Consultar con Agus si creen que hay que modificar este comportamiento.
        *   -A
        * */
        
        ZoneId zone = ZoneId.systemDefault();
        now = LocalDate.now(zone);

        //DateTimeFormatter formatter = DateTimeFormatter.ofPattern( "dd/MM/uu" );
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
        LocalDate localDate = LocalDate.parse(dateToParse, formatter);


        //Comparamos las fechas
        if(localDate.isBefore(now) || (localDate.equals(now))){
            return true;
        } else {
            return false;
        }
    }
}

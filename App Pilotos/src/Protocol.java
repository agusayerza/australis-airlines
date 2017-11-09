import personas.Pasajero;
import personas.Persona;
import personas.Piloto;
import vuelo.Vuelo;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;

public class Protocol {
    int userDNI;
    Servicios server;
    Piloto user;

    public Protocol() {
        server = new MockServer();
        this.userDNI = getDNI();
        user = new Piloto(userDNI);
    }

    public ArrayList<Vuelo> getFlightsForPilot(){
        return server.getVuelosPiloto(user);
    }

    public boolean validarDniPiloto(int dni){
        boolean dniExistente = false;
        ArrayList<Piloto> listaPilotos = server.getListaDePilotos();
        for(Piloto piloto : listaPilotos){
            if(piloto.getDni() == dni){
                dniExistente = true;
            } else {
                throw new RuntimeException("Piloto no encontrado.");
            }
        }
        return dniExistente;
    }

    private int getDNI(){
        Scanner mainScanner = new Scanner();
        boolean validDNI = false;
        int givenDNI = 0;

        while(!validDNI){
                givenDNI = mainScanner.getInt("Ingrese su DNI: ");

            if((givenDNI > 999999) && (givenDNI < 100000000)){
              try {
                  if (validarDniPiloto(givenDNI)) {
                      validDNI = true;
                  }
              } catch (Exception e) {
                  System.out.println(e.getMessage());
              }
            }else {
                System.out.println("DNI Invalido, vuelva a ingresarlo.");
            }
        }
        return givenDNI;
    }
}

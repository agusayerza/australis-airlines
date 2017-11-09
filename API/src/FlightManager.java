import personas.Administrador;
import personas.Area;
import vuelo.*;
import catalogo.Pricing;
import avion.*;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class FlightManager {
    static ArrayList<Administrador> administradores = new ArrayList<>();

    public static void main(String[] args){
        Catalogo catalogo = new Catalogo();

        Area areaDeVentas = new Area(true);
        Area algunArea = new Area(false);
        Administrador administradorDeVentas = new Administrador(40543537, areaDeVentas);
        Administrador algunAdministrador = new Administrador(1001001, algunArea);
        administradores.add(administradorDeVentas);
        administradores.add(algunAdministrador);

    }
}

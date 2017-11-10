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

    public static void main(String[] args) throws InterruptedException {
        Catalogo catalogo = new Catalogo();

        System.out.println("Servidor de Australis Airlines.");
        System.out.println("Cargando...");

        Area areaDeVentas = new Area(true);
        Area algunArea = new Area(false);
        Administrador administradorDeVentas = new Administrador(40543537, areaDeVentas);
        Administrador algunAdministrador = new Administrador(1001001, algunArea);
        administradores.add(administradorDeVentas);
        administradores.add(algunAdministrador);

        System.out.println("Servidor de Australis Airlines.");

        while (true){
            System.out.print("\nEscuchando");
            for (int i = 0; i < 6; i++) {
                System.out.print(".");
                //Pause for 2 seconds
                Thread.sleep(2000);
            }

        }

    }
}

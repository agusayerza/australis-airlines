import avion.Avion;
import avion.Clase;
import catalogo.Pricing;
import personas.Administrador;
import personas.Area;
import personas.Pasajero;
import personas.Piloto;
import sun.security.krb5.internal.PAData;
import vuelo.PaqueteDeVuelos;
import vuelo.Pasaje;
import vuelo.Vuelo;

import java.io.*;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

public class Catalogo implements Servicios{

    private ListaDeVuelos vuelos;
    ArrayList<Piloto> listaPilotos;
    ArrayList<String> codigosDeVuelo;
    ArrayList<Pasaje> listaPasajes = new ArrayList<>();
    ArrayList<Administrador> administradores = new ArrayList<>();
    ArrayList<Avion> aviones = new ArrayList<>();

    Area areaDeVentas = new Area(true);
    Area algunArea = new Area(false);
    Administrador administradorDeVentas = new Administrador(40543537, areaDeVentas);
    Administrador algunAdministrador = new Administrador(1001001, algunArea);


    public Catalogo() {

        readFile(listaPasajes);

        administradores.add(administradorDeVentas);
        administradores.add(algunAdministrador);

        vuelos = new ListaDeVuelos();

        LocalDateTime tiempo;
        Vuelo vuelo;
        Duration duracion;
        Pricing pricing;
        Pasajero pasajero;
        Avion avion;
        Clase[] clases;

        Piloto piloto = new Piloto(11111000);
        Piloto otroPiloto = new Piloto(20000000);
        Piloto unPiloto = new Piloto(19999998);

        listaPilotos = new ArrayList<>();
        listaPilotos.add(piloto);
        listaPilotos.add(otroPiloto);
        listaPilotos.add(unPiloto);

        duracion = Duration.ofHours(8);
        pasajero = new Pasajero(40719053);
        tiempo = LocalDateTime.now();

        Clase clase = new Clase(1,21,3,"Primera");
        clases = new Clase[1];
        clases[0] = clase;

        avion = new Avion("LV-501",clases);

        double precio[] = new double[1];
        precio[0] = 199.3;
        pricing = new Pricing(avion,precio);

        vuelo = new Vuelo(tiempo,duracion,tiempo.plusYears(1).toLocalDate(),"Ezeiza","Paris","BARBAR",avion, pricing,piloto);
        piloto.agregarVuelo(vuelo);

        clase = new Clase(1,4,2,"Primera");
        Clase economica = new Clase(2,21,3,"Economica");

        clases = new Clase[2];
        clases[0] = clase;
        clases[1] = economica;

        avion = new Avion("LV-600",clases);

        precio = new double[2];
        precio[0] = 199.3;
        precio[1] = 149.99;
        pricing = new Pricing(avion,precio);

        Vuelo copiaVuelo = new Vuelo(tiempo.plusDays(1),duracion,tiempo.plusYears(1).toLocalDate(),"Madrid","Paris","BARBAR-COPIA",avion, pricing,otroPiloto);
        Vuelo otroVuelo = new Vuelo(tiempo.plusDays(2),duracion,tiempo.plusYears(1).toLocalDate(),"Paris","Ezeiza","BARBOR",avion, pricing,unPiloto);
        otroPiloto.agregarVuelo(copiaVuelo);
        unPiloto.agregarVuelo(otroVuelo);

        vuelos.addVuelo(vuelo);
        vuelos.addVuelo(copiaVuelo);
        vuelos.addVuelo(otroVuelo);


        //venderAsiento(tiempo.toLocalDate(),"BARBAR","1A",pasajero);
    }

    public void ocuparPasajesDelArchivo(){
        for (Pasaje pasaje : listaPasajes) {
            Pasajero pasajero = new Pasajero(pasaje.getPasajero());
            vuelos.venderAsiento(pasaje.getDate(),pasaje.getVuelo(),pasaje.getAsiento(),pasajero);
        }
    }

    public void addVuelo(Vuelo vuelo){
        vuelos.addVuelo(vuelo);
    }

    public void removeVuelo(String codigoDeVuelo){
        vuelos.removeVuelo(codigoDeVuelo);
    }

    public ArrayList<Vuelo> getFlightsOnDate(LocalDate date){
        return vuelos.getFlightsOnDate(date);
    }

    public ArrayList<Vuelo> getFlightsOnDateFromToDestination(LocalDate date, String aeropuertoDePartida, String aeropuertoDeArribo){
        return vuelos.getFlightsOnDateFromToDestination(date,aeropuertoDePartida,aeropuertoDeArribo);
    }

    public ArrayList<PaqueteDeVuelos> buscarVueloConEscalas(String aeropuertoDeSalida, String aeropuertoDeLlegada, int escalasMaximas, LocalDate fechaSalida){
        ArrayList<PaqueteDeVuelos> paquetes = new ArrayList<>();

        for (Vuelo vuelo: getFlightsOnDateFromToDestination(fechaSalida,aeropuertoDeSalida,aeropuertoDeLlegada)) {
            PaqueteDeVuelos paquete = new PaqueteDeVuelos();
            paquete.addVuelo(vuelo);
        }

        if(escalasMaximas == 0){
            return paquetes;
        }

        HashMap<String, ArrayList<String>> aeropuertos = vuelos.getAeropuertos();
        for (String aeropuertoB : aeropuertos.get(aeropuertoDeSalida)) {
            if(getFlightsOnDateFromToDestination(fechaSalida.plusDays(1),aeropuertoB,aeropuertoDeLlegada).size() != 0 && getFlightsOnDateFromToDestination(fechaSalida,aeropuertoDeSalida,aeropuertoB).size() != 0){
                for (Vuelo vuelo: getFlightsOnDateFromToDestination(fechaSalida,aeropuertoDeSalida,aeropuertoB)) {
                    for (Vuelo vueloB : getFlightsOnDateFromToDestination(fechaSalida,aeropuertoB,aeropuertoDeLlegada)) {
                        PaqueteDeVuelos paquete = new PaqueteDeVuelos();
                        paquete.addVuelo(vuelo);
                        paquete.addVuelo(vueloB);
                        paquetes.add(paquete);
                    }
                }
            }
        }

        return paquetes;

    }



    public void ocuparAsiento(LocalDate date, String codigoDeVuelo, String codigoDeAsiento, Pasajero pasajero){
        vuelos.venderAsiento(date,codigoDeVuelo,codigoDeAsiento,pasajero);
        Pasaje pasaje = new Pasaje(codigoDeAsiento,codigoDeVuelo, date, pasajero.getDni());

        listaPasajes.add(pasaje);
        writeFile(listaPasajes);
    }

    public void writeFile(ArrayList<Pasaje> pasajes) {
        try {
            FileOutputStream fos = new FileOutputStream("pasajes.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            for (Pasaje pasaje : pasajes) {
                oos.writeObject(pasaje);
                oos.reset();
            }
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readFile(ArrayList<Pasaje> pasajes) {
        try {
            FileInputStream fis = new FileInputStream("pasajes.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);
            Object obj = null;
            while ((obj = ois.readObject()) != null) {
                if (obj instanceof Pasaje) {
                    pasajes.add(((Pasaje) obj));
                }
            }
            ois.close();
        } catch (EOFException ignored) {
        } catch (FileNotFoundException ignored) {
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }

    public String getReservasCliente(Pasajero pasajero){
        String result = "";
        for (Vuelo vuelo: vuelos.getLista()) {
            result += vuelo.getReservasCliente(pasajero);
        }
        if(result.equals("")){
            return "No se encontraron vuelos para el DNI: " + pasajero.getDni();
        }

        return result;
    }

    @Override
    public void venderAsiento(String codigoDeVuelo, String codigoDeAsiento, Pasajero pasajero, LocalDate date) {
        this.ocuparAsiento(date,codigoDeVuelo,codigoDeAsiento,pasajero);
    }

    @Override
    public ArrayList<Vuelo> BuscarVuelosPiloto(int dni) {
        return vuelos.BuscarVuelosPiloto(dni);
    }

    @Override
    public void agregarVuelo(LocalDateTime tiempo, Duration duracion, LocalDate ultimaFechaDeVuelo, String aeropuertoSalida, String aeropuertoArribo, String codigoDeVuelo, Avion avion, Pricing pricing, Piloto piloto) {
        Vuelo vuelo = new Vuelo(tiempo, duracion,ultimaFechaDeVuelo,aeropuertoSalida,aeropuertoArribo,codigoDeVuelo,avion,pricing,piloto);
        this.addVuelo(vuelo);
    }

    @Override
    public boolean administradorPuedeVender(int dni) {
        for (Administrador administrador: administradores) {
            if(administrador.getDni() == dni) {
                if (administrador.getArea().getCapacidadDeVender()) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean esAdmin(int dni) {
        for (Administrador administrador: administradores) {
            if(administrador.getDni() == dni) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void crearAvion(Avion avion) {
        aviones.add(avion);
    }

    @Override
    public ArrayList<Avion> getListaDeAviones() {
        return aviones;
    }

    @Override
    public void agregarVuelo(Vuelo vuelo){
        this.addVuelo(vuelo);
    }

    @Override
    public ArrayList<Vuelo> getVuelosPiloto(Piloto piloto) {
        //Ver buscarVuelosPiloto, esta mejor
        validarDniPiloto(piloto.getDni());

        for(Piloto unPiloto : listaPilotos){
            if(unPiloto.getDni() == piloto.getDni()){
                return unPiloto.getListaVuelos();
            }
        }
        return piloto.getListaVuelos();
    }

    @Override
    public ArrayList<Piloto> getListaDePilotos() {
        return this.listaPilotos;
    }

    @Override
    public boolean validarDniPiloto(int dni) {
        for(Piloto piloto : listaPilotos){
            if(piloto.getDni() == dni){
                return true;
            }
        }
        return false;
    }
}

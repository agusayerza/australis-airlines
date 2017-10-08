package catalogo;


import vuelo.Vuelo;

public class Catalogo {
    private ListaDeVuelos posiblesVuelos;
    private ListaDeVuelos vuelos;

    public Catalogo() {

        vuelos = new ListaDeVuelos();
        posiblesVuelos = new ListaDeVuelos();

    }

    public void addVuelo(Vuelo vuelo){
        posiblesVuelos.addVuelo(vuelo);
    }

    public void removeVuelo(String codigoDeVuelo){
        posiblesVuelos.removeVuelo(codigoDeVuelo);
        vuelos.removeVuelo(codigoDeVuelo);
    }
}

package catalogo;


import avion.Clase;

import java.util.HashMap;

public class Pricing {
    HashMap<String, Double> precios;

    public Pricing(Clase[] clases, double[] precios) {
        if(clases.length != precios.length) {
            //TODO: Custom exception "Falta o sobra un precio de alguna clase"
            throw new RuntimeException();
        }
        int i = 0;

        for (Clase clase: clases) {

            if(precios[i] < 0){
                //TODO: Custom exception "Precio negativo"
                throw new RuntimeException();
            }

            this.precios.put(clase.getNombreDeClase(),precios[i]);
            i++;
        }
    }

    public HashMap<String, Double> getPrecios() {
        return precios;
    }

    public double getPrecioDeClase(String nombreDeClase){
        if(!precios.containsKey(nombreDeClase)){
            //TODO: Custom exception "La lista de precios no contiene la clase"
            throw new RuntimeException();
        }

        return precios.get(nombreDeClase);
    }
}

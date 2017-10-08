package catalogo;


import avion.Avion;
import avion.Clase;

import customExceptions.PriceMissingLeftoverForClassException;
import customExceptions.PriceNegativeException;
import customExceptions.PriceListNotContainClassException;

import java.util.HashMap;

public class Pricing {
    HashMap<String, Double> precios;
    Avion avion; //TODO: Cambiar todo esto para que el Pricing conozca el avion y de ahi extraiga las clases.


    public Pricing(Clase[] clases, double[] precios) {
        if(clases.length != precios.length) {
            throw new PriceMissingLeftoverForClassException("Falta o sobra un precio de alguna clase");
        }
        int i = 0;

        for (Clase clase: clases) {

            if(precios[i] < 0){
                throw new PriceNegativeException("Precio negativo");
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
            throw new PriceListNotContainClassException("La lista de precios no contiene la clase");
        }

        return precios.get(nombreDeClase);
    }
}

package UI;

import customExceptions.MenuInvalidOptionSelectedException;

public class Menu {
    private String[] opcionesMenu;
    private String nombreMenu;

    private UI.Scanner scanner =  new Scanner();

    public Menu(String[] opcionesMenu, String nombreMenu) {
        this.opcionesMenu = opcionesMenu;
        this.nombreMenu = nombreMenu;
    }

    public String[] getOpcionesMenu() {
        return opcionesMenu;
    }

    public String getNombreMenu() {
        return nombreMenu;
    }

    public String strPrintMenu(){
        String output = nombreMenu + "\n";

        for (int i = 0; i < opcionesMenu.length ; i++) {
            output += (i + 1) + " - " + opcionesMenu[i] + "\n";
        }

        return output + "\n";
    }

    private boolean esOpcionInvalida (int i) throws MenuInvalidOptionSelectedException{
        if(i < (opcionesMenu.length + 1) && (i >= 0)){
            throw new MenuInvalidOptionSelectedException("Se selecciono una opcion invalida");
        } else {
            return true;
        }
    }

    public int pedirOpcionAlUsuario(){
        boolean esperarOpcion = true;
        int i = 0;
        while(esperarOpcion) {
            i = scanner.getInt("Seleccione una opcion: ");
            try{
                this.esOpcionInvalida(i);
                esperarOpcion = false;
            }
            catch (MenuInvalidOptionSelectedException e){
                System.out.println(e.getMessage());
                System.out.println(this.strPrintMenu());
            }
        }

        return i;
    }
}

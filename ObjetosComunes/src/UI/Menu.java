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

    private void esOpcionInvalida (int i) throws MenuInvalidOptionSelectedException{
        if(i > opcionesMenu.length || i <= 0){
            throw new MenuInvalidOptionSelectedException("Se selecciono una opcion invalida");
        }
    }

    public int pedirOpcionAlUsuario(){
        int i = 0;
            i = scanner.getInt("Seleccione una opcion: ");
            try{
                this.esOpcionInvalida(i);
            }
            catch (MenuInvalidOptionSelectedException e){
                System.out.println(e.getMessage());
                System.out.println(this.strPrintMenu());
                return this.pedirOpcionAlUsuario();    //Viva la recursividad
            }

        return i;
    }
}

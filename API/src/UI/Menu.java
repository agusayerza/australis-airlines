package UI;

public class Menu {
    private String[] opcionesMenu;
    private String nombreMenu;

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
            output += i + " - " + opcionesMenu[i] + "\n";
        }

        return output;
    }

    public int validateOption(int i){
        if(i < opcionesMenu.length && i >= 0){
            return i;
        } else {
            //TODO: Custom Exception "Menu: Invalid option selected"
            throw new RuntimeException();
        }
    }
}

package UI;

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
            output += i + " - " + opcionesMenu[i] + "\n";
        }

        return output;
    }

    private boolean esOpcionInvalida(int i){
        if(i < opcionesMenu.length && i >= 0){
            return false;
        } else {
            return true;
            //"Menu: Invalid option selected" by agus; "Menu: Opcion invalida seleccionada" by yo;
            //throw new MenuInvalidOptionSelectedException("Menu: Opcion invalida seleccionada");
        }
    }

    public int pedirOpcionAlUsuario(){
        boolean esperarOpcion = true;
        int i = 0;
        while(esperarOpcion) {
            i = scanner.getInt("Seleccione una opcion:");
            esperarOpcion = esOpcionInvalida(i);
        }

        return i;
    }
}

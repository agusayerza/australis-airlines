import vuelo.*;
import customExceptions.MenuInvalidOptionSelectedException;

public class Consola {

    Menu menuPrincipal;
    static Scanner mainScanner;
    static Protocol protocol;
    static int DNI;
    static boolean programRun = true;
    static String password; //TODO: algo sobre guardar combinacion de dni y password

    public static void main(String[] args) {
        mainScanner = new Scanner();

        System.out.println("Bienvenido a la aplicacion de Empleados de Australis Airlines");
        System.out.println("Para empezar, usted debe conectarse");

        boolean validDNI = false;
        while (!validDNI) {
            DNI = mainScanner.getInt("Ingrese su DNI: ");
            if ((DNI > 999999) && (DNI < 100000000)) {
                validDNI = true;
            } else {
                System.out.println("DNI Invalido, vuelva a ingresarlo.");
            }
        }

        password = mainScanner.getString("Ingrese su password");

        protocol = new Protocol(DNI, password);

            //algo para validar el dni y su password, solo empleados com puedeVender = true deverian pasar
        }

        public static void loopMenuPrincipal() throws MenuInvalidOptionSelectedException {
            String[] opcionesMenuPrincipal = new String[3];

            opcionesMenuPrincipal[0] = "Ver Vuelos";
            opcionesMenuPrincipal[1] = "Reservar del Cliente";
            opcionesMenuPrincipal[2] = "Salir";

            Menu menuPrincipal = new Menu(opcionesMenuPrincipal, "Menú Principal");

            System.out.println(menuPrincipal.strPrintMenu());
            int option = menuPrincipal.pedirOpcionAlUsuario();

            switch (option) {
                case 1:

                default:
                    throw new MenuInvalidOptionSelectedException("Se selecciono una opcion invalida");
            }
    }

}


/*    public static void main(String[] args) {
        mainScanner = new Scanner();

        System.out.println("Bienvenido a la aplicacion de Empleados de Australis Airlines");

        System.out.println("Para empezar, usted debe conectarse");

        iniciarSecion();


    }

    public static void iniciarSecion() {
        boolean validDNI = false;
        while(!validDNI){
            DNI = mainScanner.getInt("Ingrese su DNI: ");
            if((DNI > 999999) && (DNI < 100000000)){
                validDNI = true;
            }else {
                System.out.println("DNI Invalido, vuelva a ingresarlo.");
            }
        }

        password = mainScanner.getString("Ingrese su password: ");

        protocol = new Protocol(DNI, password);
        protocol.checkValidUser();


    }
*/


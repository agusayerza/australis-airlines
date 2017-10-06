package UI;

public class Scanner {

    private static final java.util.Scanner scanner = new java.util.Scanner(System.in);

    public Scanner() { }

    /** Displays the given message and waits for user to enter some text.
     * @param message to be displayed.
     * @return text entered by the user.
     */
    public String getString(String message) {
        System.out.print(message);
        final String result = scanner.nextLine().trim();
        if(result.isEmpty()) {
            System.out.println("Por favor, ingrese solo texto.");
            return getString(message);
        }
        return result;
    }

    /** Displays the given message and waits for user to enter a character.
     * @param message to be displayed.
     * @return char entered by the user.
     */
    public char getChar(String message) {
        return getString(message).charAt(0);
    }

    /** Displays the given message and waits for user to enter an int.
     * @param message to be displayed.
     * @return integer entered by the user.
     */
    public int getInt(String message) {
        System.out.print(message);
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Por favor, ingrese solo numeros.");
            return getInt(message);
        }
    }

    /** Displays the given message and waits for user to enter a long.
     * @param message to be displayed.
     * @return long entered by the user.
     */
    public long getLong(String message) {
        System.out.print(message);
        try {
            return Long.parseLong(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Por favor, ingrese solo numeros.");
            return getLong(message);
        }
    }

    /** Displays the given message and waits for user to enter a float.
     * @param message to be displayed.
     * @return float entered by the user.
     */
    public float getFloat(String message) {
        System.out.print(message);
        try {
            return Float.parseFloat(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Por favor, ingrese solo numeros.");
            return getFloat(message);
        }
    }

    /** Displays the given message and waits for user to enter a double.
     * @param message to be displayed.
     * @return double entered by the user.
     */
    public double getDouble(String message) {
        System.out.print(message);
        try {
            return Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Por favor, ingrese solo numeros.");
            return getDouble(message);
        }
    }

}

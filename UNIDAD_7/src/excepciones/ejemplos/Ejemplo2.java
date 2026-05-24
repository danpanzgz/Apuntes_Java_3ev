package excepciones.ejemplos;

import java.util.Scanner;

public class Ejemplo2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        boolean correcto;
        int intentos = 5, cociente = 0;
        do {
            System.out.print("DIVIDENDO: ");
            String dividendo = sc.nextLine();
            System.out.print("DIVISOR: ");
            String divisor = sc.nextLine();
            try {
                int dividendoInt = Integer.parseInt(dividendo);
                int divisorInt = Integer.parseInt(divisor);
                cociente = dividendoInt / divisorInt;
                correcto = true;
            } catch (NumberFormatException nfe) {
                System.out.println("Error: El número no es válido");
                correcto = false;
                intentos--;
            } catch (ArithmeticException ae) {
                System.out.println("Error: No se puede dividir por cero");
                correcto = false;
                intentos--;
            }
        } while (!correcto && intentos != 0);

        if (intentos == 0) {
            System.out.println("Has consumido tus 5 intentos");
        }
        System.out.println("El cociente es: " + cociente);
    }
}

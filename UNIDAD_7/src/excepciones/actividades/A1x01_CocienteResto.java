package excepciones.actividades;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class A1x01_CocienteResto {
    public static String solicitarCadena(String mensaje) throws IOException {
        BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
        System.out.print(mensaje);
        return teclado.readLine();
    }

    public static short solicitarEnteroCorto(String mensaje, int numMaxErrores) throws IOException {
        boolean error;
        short corto = 0;
        int numIntentos = 0;
        do {
            error = false;
            try {
                corto = Short.parseShort(solicitarCadena(mensaje));
            } catch (NumberFormatException nfe) {
                numIntentos++;
                System.out.println("El dato introducido no tiene formato de número entero corto.");
                System.out.println(nfe.getMessage());
                System.out.println("Error número " + numIntentos);
                error = true;
            }
        } while (error && numIntentos < numMaxErrores);
        if (error) {
            System.out.println("Se ha alcanzado el número máximo de errores, que es " + numMaxErrores);
        }
        return corto;
    }

    public static void main(String[] args) {
        final int MAX_ERRORES = 3;
        try {
            short dividendo = solicitarEnteroCorto("Dividendo: ", MAX_ERRORES);
            short divisor = solicitarEnteroCorto("Divisor: ", MAX_ERRORES);
            if (dividendo % divisor == 0) {
                System.out.println(dividendo + " es divisible entre " + divisor);
            } else {
                System.out.println(dividendo + " no es divisible entre " + divisor);
            }
            System.out.println("Cociente: " + dividendo / divisor);
            System.out.println("Resto: " + dividendo % divisor);
        } catch (IOException ioe) {
            System.out.println("Se ha producido un error de entrada/salida.");
            System.out.println(ioe.getMessage());
        } catch (ArithmeticException ae) {
            System.out.println("Se ha producido un error aritmético");
            System.out.println(ae.getMessage());
        }
    }
}

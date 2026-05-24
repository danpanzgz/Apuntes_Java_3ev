package excepciones.ejemplos;

public class Ejemplo1 {
    public static void main(String[] args) {
        int dividendo = 10, divisor = 0;
        try {
            int cociente = division(dividendo, divisor);
            System.out.println("El cociente es: " + cociente);
        } catch (ArithmeticException ae) {
            System.out.println("Excepción aritmética: " + ae.getMessage());
        }
    }

    public static int division(int dividendo, int divisor) throws ArithmeticException {
        int cociente;
        cociente = dividendo / divisor;
        return cociente;
    }
}

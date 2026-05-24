package ejemplos;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import entrada.Teclado;

public class escribirFichero {

    public static void main(String[] args) {

        String fichero = Teclado.leerCadena("Diga el nombre del fichero");
        boolean append = Teclado.leerBooleano("Desea continuar por el final escribiendo?");
        escrituraFichero(fichero, append);

    }

    /**
     *
     * @param nombre
     * @param append
     */
    private static void escrituraFichero(String nombre, boolean append) {
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(nombre, append));
            bw.write("Hola amigo");
            bw.newLine();
            bw.write("Qué tal");
            bw.newLine();
            bw.write("Muy bien");
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Error al escribir el fichero"+ e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (bw != null) {
                    bw.close();
                }
            } catch (IOException e) {
                System.out.println("Error al cerrar el fichero: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
}
package ejemplos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import entrada.Teclado;

public class leerFichero {

    /**
     * Mostrar por pantalla las líneas de un fichero de texto
     * @param args
     */
    public static void main(String[] args) {

        String nombreFichero = Teclado.leerCadena("Diga el nombre del fichero");
        lecturaFichero(nombreFichero);

    }

    /**
     *
     * @param nombreFichero
     */
    private static void lecturaFichero(String nombreFichero) {
        BufferedReader br = null;
        try {
            File archivo = new File(nombreFichero);
            FileReader fr = new FileReader(archivo);
            br = new BufferedReader(fr);

            // Lectura del fichero línea por línea
            String linea = br.readLine();
            while (linea != null) {
                System.out.println(linea);
                linea = br.readLine();
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("Error al abrir el fichero"+ e.getMessage());
            e.printStackTrace();
        }
        catch (IOException e) {
            System.out.println("Error al leer del fichero"+ e.getMessage());
            e.printStackTrace();
        }
        finally {
            // Cierra el fichero
            try {
                if (br != null) {
                    br.close();
                }
            }
            catch (IOException e) {
                System.out.println("Error al cerrar el fichero: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
}
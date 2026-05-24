package ejemplos;

import java.io.*;
import entrada.Teclado;

public class EliminarLineasConPalabra {

    public static void main(String[] args) {
        String nombreFichero = Teclado.leerCadena("Diga el nombre del fichero: ");
        String palabraBorrar = Teclado.leerCadena("Palabra a borrar: ");
        int lineasBorradas = borrarLineaPalabra(nombreFichero, palabraBorrar);
        System.out.println("lineas borradas: " + lineasBorradas);
    }

    private static int borrarLineaPalabra(String nombreFichero, String palabra) {
        BufferedReader br = null;
        BufferedWriter bw = null;

        int contadorLineasBorradas = 0;

        File archivoOriginal = new File(nombreFichero);
        File archivoTemporal = new File(nombreFichero + ".tmp");

        try {
            br = new BufferedReader(new FileReader(archivoOriginal));
            bw = new BufferedWriter(new FileWriter(archivoTemporal));

            String linea;
            while ((linea = br.readLine()) != null) {
                linea = linea.trim();
                if (!linea.contains(palabra)) {
                    bw.write(linea);
                    bw.newLine();
                } else {
                    contadorLineasBorradas++;
                }
            }

            bw.close();
            br.close();

            archivoOriginal.delete();
            archivoTemporal.renameTo(archivoOriginal);
        } catch (FileNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }

                if (bw != null) {
                    bw.close();
                }
            } catch (IOException e) {
                System.out.println("Error al cerrar el fichero: " + e.getMessage());
                e.printStackTrace();
            }
        }
        return contadorLineasBorradas;
    }
}

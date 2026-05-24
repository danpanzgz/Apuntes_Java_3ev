package actividades;

import entrada.Teclado;

import java.io.*;
import java.util.LinkedList;

public class A1x01_Reverso {
    public static void main(String[] args) {
        String nombreFichero1 = Teclado.leerCadena("Diga el nombre del fichero1: ");
        System.out.println("FICHERO 1");
        darleLaVueltaFichero(nombreFichero1);
        lecturaFichero(nombreFichero1);
    }

    private static void darleLaVueltaFichero(String nombreFichero) {
        LinkedList<String> lineas = invertirFichero(nombreFichero);

        BufferedWriter bw = null;

        File archivoOriginal = new File(nombreFichero);
        File archivoTemporal = new File(nombreFichero + ".tmp");

        try {
            bw = new BufferedWriter(new FileWriter(archivoTemporal));

            for (String l : lineas) {
                bw.write(l);
                bw.newLine();
            }

            bw.close();

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
                if (bw != null) {
                    bw.close();
                }
            } catch (IOException e) {
                System.out.println("Error al cerrar el fichero: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    private static LinkedList<String> invertirFichero(String nombreFichero) {
        BufferedReader br = null;
        LinkedList<String> lineas = new LinkedList<>();
        try {
            File archivo = new File(nombreFichero);
            FileReader fr = new FileReader(archivo);
            br = new BufferedReader(fr);

            // Lectura del fichero línea por línea
            String linea = br.readLine();
            while (linea != null) {
                lineas.add(linea);
                linea = br.readLine();
            }

            lineas = lineas.reversed();
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

        return lineas;
    }

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

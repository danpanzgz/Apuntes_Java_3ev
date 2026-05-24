package actividades.combinacion;

import entrada.Teclado;

import java.io.*;

public class Combinar {
    public static void main(String[] args) {
        String nombreFichero1 = Teclado.leerCadena("Diga el nombre del fichero1: ");
        String nombreFichero2 = Teclado.leerCadena("Diga el nombre del fichero2: ");
        String nombreResultado = Teclado.leerCadena("Diga el nombre del fichero resultado: ");
        combinarFicheros(nombreFichero1, nombreFichero2, nombreResultado);
        System.out.println("FICHERO 1");
        lecturaFichero(nombreFichero1);
        System.out.println("-------------");
        System.out.println("FICHERO 2");
        lecturaFichero(nombreFichero2);
        System.out.println("-------------");
        System.out.println("FICHERO RESULTADO");
        lecturaFichero(nombreResultado);
    }

    private static void combinarFicheros(String nombreFichero1, String nombreFichero2, String nombreResultado) {
        BufferedReader br1 = null;
        BufferedReader br2 = null;
        BufferedWriter bw = null;

        File archivoOriginal1 = new File(nombreFichero1);
        File archivoOriginal2 = new File(nombreFichero2);

        File archivoResultado = new File(nombreResultado);

        try {
            br1 = new BufferedReader(new FileReader(archivoOriginal1));
            br2 = new BufferedReader(new FileReader(archivoOriginal2));

            bw = new BufferedWriter(new FileWriter(archivoResultado));

            String linea = br1.readLine();
            String linea2 = br2.readLine();
            while (linea != null && linea2 != null) {
                bw.write(linea);
                bw.newLine();
                bw.write(linea2);
                bw.newLine();
                linea = br1.readLine();
                linea2 = br2.readLine();
            }

            while (linea != null) {
                bw.write(linea);
                bw.newLine();
                linea = br1.readLine();
            }

            while (linea2 != null) {
                bw.write(linea2);
                bw.newLine();
                linea2 = br2.readLine();
            }

            bw.close();
            br1.close();
            br2.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (br1 != null) {
                    br1.close();
                }

                if (br2 != null) {
                    br2.close();
                }

                if (bw != null) {
                    bw.close();
                }
            } catch (IOException e) {
                System.out.println("Error al cerrar el fichero: " + e.getMessage());
                e.printStackTrace();
            }
        }
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

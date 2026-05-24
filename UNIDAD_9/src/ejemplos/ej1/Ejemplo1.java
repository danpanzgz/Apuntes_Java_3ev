package ejemplos.ej1;

import java.io.File;
import entrada.Teclado;

public class Ejemplo1 {

    /**
     * Lista los nombres de los ficheros contenidos en rutaNombreFichero
     * Fichero: Nombre
     * Directorio : Nombre
     * @param rutaNombreFichero
     */
    public static void listaNombresFicheros(String rutaNombreFichero) {

        // Acedo al fichero
        File f = new File(rutaNombreFichero);

        // Si el fichero
        if (f.exists() && f.isDirectory()) {
            File[] listadoFicheros = f.listFiles();

            for (int i = 0; i < listadoFicheros.length; i++) {
                File file = listadoFicheros[i];
                if (file.isDirectory()) {
                    System.out.print("directorio ");
                } else {
                    System.out.print("fichero ");
                }
                System.out.println(file.getName());
            }

        } else {
            System.out.println("El fichero no existe o no es un directorio");
        }
    }

    /**
     * Muestra información sobre el fichero pasado como parámetro
     *
     * @param rutaNombreFichero
     */
    public static void muestraInformacion(String rutaNombreFichero) {
        // Accedo al fichero
        File f = new File(rutaNombreFichero);

        // Si existe y es un fichero
        if (f.exists() && f.isFile()) {
            System.out.println("Nombre del fichero:" + f.getName());
            System.out.println("Ruta del directorio del fichero: "
                    + f.getParent());
            System.out.println("Nombre del fichero (incluyendo ruta): "
                    + f.getPath());
            System.out.println("Tamaño del fichero (en bytes): " + f.length());
            System.out.println("Puede ser leído: " + f.canRead());
            System.out.println("Puede ser escrito: " + f.canWrite());
            System.out.println("Puede ser ejecutado: " + f.canExecute());
        } else {
            System.out.println("No existe el fichero o no es directorio");
        }
    }

    /**
     * Modifica los nombres de los ficheros de un directorio
     * Convirtiendo a mayúsculas los ficheros (no los directorios)
     * @param directorio
     */
    private static void cambiarNombresFicheros(String directorio) {
        // TODO Auto-generated method stub
        // Acedo al fichero
        File f = new File(directorio);

        // Si el fichero
        if (f.exists() && f.isDirectory()) {
            File[] listadoFicheros = f.listFiles();

            for (File file : listadoFicheros) {
                if (file.isFile()) {
                    String fileName = file.getName();
                    String sinExtension = fileName;
                    String extension = "";
                    if (fileName.contains(".")) {
                        sinExtension = fileName.substring(0, file.getName().lastIndexOf("."));
                        extension = fileName.substring(file.getName().lastIndexOf("."));
                    }

                    File rename = new File(directorio, sinExtension.toUpperCase() + extension);
                    file.renameTo(rename);
                }
            }
        } else {
            System.out.println("El fichero no existe o no es un directorio");
        }
    }

    private static void eliminarExtension(String directorio) {
        // TODO Auto-generated method stub
        // Acedo al fichero
        File f = new File(directorio);

        // Si el fichero
        if (f.exists() && f.isDirectory()) {
            File[] listadoFicheros = f.listFiles();

            for (File file : listadoFicheros) {
                if (file.isFile()) {
                    String fileName = file.getName();
                    String sinExtension = fileName;
                    if (fileName.contains(".")) {
                        sinExtension = fileName.substring(0, file.getName().lastIndexOf("."));
                    }

                    File rename = new File(directorio, sinExtension);
                    file.renameTo(rename);
                }
            }
        } else {
            System.out.println("El fichero no existe o no es un directorio");
        }
    }

    private static void cambiarTxtDat(String directorio) {
        // TODO Auto-generated method stub
        // Acedo al fichero
        File f = new File(directorio);

        // Si el fichero
        if (f.exists() && f.isDirectory()) {
            File[] listadoFicheros = f.listFiles();

            for (File file : listadoFicheros) {
                if (file.isFile()) {
                    String fileName = file.getName();
                    String sinExtension = fileName;
                    String extension = "";
                    if (fileName.contains(".")) {
                        extension = fileName.substring(file.getName().lastIndexOf("."));
                    }

                    if (extension.equalsIgnoreCase(".txt")) {
                        extension = ".dat";
                    }

                    File rename = new File(directorio, sinExtension + extension);
                    file.renameTo(rename);
                }
            }
        } else {
            System.out.println("El fichero no existe o no es un directorio");
        }
    }

    /**
     * Borra los ficheros del directorio que tengan extensión .txt
     * @param directorio
     */
    private static void borrarFicherosTxt(String directorio) {
        File f = new File(directorio);

        // Si el fichero
        if (f.exists() && f.isDirectory()) {
            File[] listadoFicheros = f.listFiles();

            for (File file : listadoFicheros) {
                if (file.isFile()) {
                    String fileName = file.getName();
                    String extension = "";
                    if (fileName.contains(".")) {
                        extension = fileName.substring(file.getName().lastIndexOf("."));
                    }

                    if (extension.equalsIgnoreCase(".txt")) {
                        file.delete();
                    }
                }
            }
        } else {
            System.out.println("El fichero no existe o no es un directorio");
        }

    }

    /**
     * Prueba métodos para trabajar con ficheros de alto nivel (File)
     *
     * @param args
     */
    public static void main(String[] args) {

        String fichero = Teclado.leerCadena("Escriba el nombre de un fichero para ver sus propiedades");
        // Muestra información del fichero
        muestraInformacion(fichero);

        String directorio = Teclado.leerCadena("Escriba el nombre de un directorio para ver su contenido");
        //Lista los nombres de los ficheros contenidos en un directorio
        listaNombresFicheros(directorio);

        directorio = Teclado.leerCadena("Escriba el nombre de un directorio para modificar los"
                + "nombres de los ficheros y convertirlos a mayúsculas");
        cambiarNombresFicheros(directorio);

        directorio = Teclado.leerCadena("Escriba el nombre de un directorio para eliminar las extensiones"
                + "de los ficheros y convertirlos a mayúsculas");
        eliminarExtension(directorio);

        directorio = Teclado.leerCadena("txt a dat");
        cambiarTxtDat(directorio);

        //Borrar los ficheros que terminen en ".txt" de un directorio dado
        directorio = Teclado.leerCadena("Escriba el nombre de un directorio para borrar los ficheros cuya extensión sea .txt");
        borrarFicherosTxt(directorio);

    }
}
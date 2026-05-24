package colecciones.actividades.A2x03_Libros;

import entrada.Teclado;

import java.util.ArrayList;

public class Principal {
    public static int menu(String mensaje, int numOpciones) {
        int opcion;
        do {
            System.out.println(mensaje);

            opcion = Teclado.leerEntero("Opción: ");
            if (opcion < 0 || opcion > numOpciones) {
                System.out.println("la opcion debe estar entre 0 y " + numOpciones);
            }
        } while (opcion < 0 || opcion > numOpciones);
        return opcion;
    }

    public static void main(String[] args) {
        Libreria libreria = new Libreria("Libreria");
        int opcion;
        do {
            opcion = menu("\n(0) Salir del programa.\n" +
                    "(1) Insertar un libro en la lista.\n" +
                    "(2) Eliminar libro por ISBN.\n" +
                    "(3) Consultar libro por ISBN.\n" +
                    "(4) Consultar todos los libros de la lista.\n" +
                    "(5) Consultar todos los libros por orden descendente.\n" +
                    "(6) Consultar todos los libros de un escritor.\n", 6);
            switch (opcion) {
                case 1:
                    int isbnAdd = Teclado.leerEntero("ISBN: ");
                    String titulo = Teclado.leerCadena("Título: ");
                    String escritor = Teclado.leerCadena("Escritor: ");
                    int agnoPublicacion = Teclado.leerEntero("Año de publicación: ");
                    int stock = Teclado.leerEntero("Stock: ");
                    double euros = Teclado.leerEntero("Precio: ");
                    Libro libro = new Libro(isbnAdd, titulo, escritor, agnoPublicacion, stock, euros);
                    if (libreria.insertarLibro(libro)) {
                        System.out.println("Libro insertado con éxito.");
                    } else {
                        System.out.println("Ya existe otro libro con ese ISBN en la lista.");
                    }
                    break;
                case 2:
                    int isbnRemove = Teclado.leerEntero("ISBN: ");
                    if (libreria.eliminarLibroPorISBN(isbnRemove)) {
                        System.out.println("Libro eliminado con éxito.");
                    } else {
                        System.out.println("No existe ningún libro con ese ISBN en la lista.");
                    }
                    break;
                case 3:
                    int isbnConsultar = Teclado.leerEntero("ISBN: ");
                    Libro libroConsultar = libreria.consultarLibroPorISBN(isbnConsultar);
                    if (libroConsultar != null) {
                        System.out.println(libroConsultar);
                    } else {
                        System.out.println("No existe ningún libro con ese ISBN en la lista.");
                    }
                    break;
                case 4:
                    if (!libreria.getLibros().isEmpty()) {
                        System.out.println(libreria);
                    } else {
                        System.out.println("La libreria está vacía.");
                    }
                    break;
                case 5:
                    Libreria libreriaConsultarDescendente = libreria.consultarLibrosPrecioDescendente();
                    if (!libreriaConsultarDescendente.getLibros().isEmpty()) {
                        System.out.println(libreriaConsultarDescendente);
                    } else {
                        System.out.println("La libreria está vacía.");
                    }
                    break;
                case 6:
                    String escritorConsultar = Teclado.leerCadena("Escritor: ");
                    ArrayList<Libro> listaConsultarEscritor = libreria.consultarLibrosPorEscritor(escritorConsultar);
                    if (!listaConsultarEscritor.isEmpty()) {
                        for (Libro l : listaConsultarEscritor) {
                            System.out.println(l);
                        }
                    } else {
                        System.out.println("No existe ningún libro de ese escritor en la lista.");
                    }
            }
        } while (opcion != 0);
    }
}

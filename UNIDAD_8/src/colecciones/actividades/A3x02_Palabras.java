package colecciones.actividades;

import entrada.Teclado;

import java.util.*;

public class A3x02_Palabras {
    private static final String[] palabras = {"secuencial", "aleatorio", "letra", "numero", "frio", "calor", "alto", "bajo", "dibujo", "redaccion", "grande", "pequeño", "montaña", "rio"};
    private static final Random r = new Random();

    public static void generarPalabras(int cantidadPalabras, HashSet<String> h, LinkedHashSet<String> l, TreeSet<String> t) {
        while (h.size() < cantidadPalabras) {
            int random = r.nextInt(palabras.length);
            String p = palabras[random];
            h.add(p);
            l.add(p);
            t.add(p);
        }
    }

    public static void main(String[] args) {
        HashSet<String> palabrasConHash = new HashSet<>();
        LinkedHashSet<String> palabrasConEnlazado = new LinkedHashSet<>();
        TreeSet<String> palabrasConArbol = new TreeSet<>();

        int cantidadCadenas;
        do {
            cantidadCadenas = Teclado.leerEntero("¿Cuantas cadenas? ");
            if (cantidadCadenas > palabras.length) {
                System.out.println("No existen más de " + palabras.length + " palabras");
            }
        } while (cantidadCadenas > palabras.length);

        System.out.println("Generando " + cantidadCadenas + " cadenas aleatorias de palabras");
        generarPalabras(cantidadCadenas, palabrasConHash, palabrasConEnlazado, palabrasConArbol);

        System.out.println("\nSIN ORDEN");
        System.out.println("Elementos del Conjunto Hash de Cadenas (con Iterador):");
        Iterator<String> iteradorHash = palabrasConHash.iterator();
        while (iteradorHash.hasNext()) {
            System.out.print(iteradorHash.next() + " ");
        }
        System.out.println("\nElementos del Conjunto Hash de Cadenas (Método toString):");
        System.out.println(palabrasConHash);

        System.out.println("\nPOR ORDEN DE INSERCIÓN");
        System.out.println("Elementos del Conjunto Hash Enlazado de Cadenas (con Iterador):");
        Iterator<String> iteradorHashEnlazado = palabrasConEnlazado.iterator();
        while (iteradorHashEnlazado.hasNext()) {
            System.out.print(iteradorHashEnlazado.next() + " ");
        }
        System.out.println("\nElementos del Conjunto Hash Enlazado de Cadenas (Método toString):");
        System.out.println(palabrasConEnlazado);

        System.out.println("\nPOR ORDEN NATURAL");
        System.out.println("Elementos del Conjunto Árbol de Cadenas (con Iterador):");
        Iterator<String> iteradorArbol = palabrasConArbol.iterator();
        while (iteradorArbol.hasNext()) {
            System.out.print(iteradorArbol.next() + " ");
        }
        System.out.println("\nElementos del Conjunto Árbol de Cadenas (Método toString):");
        System.out.println(palabrasConArbol);
    }
}

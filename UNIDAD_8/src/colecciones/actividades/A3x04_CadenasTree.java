package colecciones.actividades;

import java.util.*;
import entrada.Teclado;

public class A3x04_CadenasTree {
    private static void generarCadenas(Set<String> set) {
        String cadena;
        do {
            cadena = Teclado.leerCadena("¿Cadena? (*** para terminar) ");
            if (!cadena.equals("***")) {
                set.add(cadena);
            }
        } while (!cadena.equals("***"));
    }

    public static void main(String[] args) {
        Set<String> set = new TreeSet<>();
        generarCadenas(set);

        System.out.println("Elementos for each");
        for (String i : set) {
            System.out.print(i + " ");
        }
        System.out.println();

        int cantidad;
        do {
            cantidad = Teclado.leerEntero("¿Cuantos elementos desea imprimir? ");
            if (cantidad < 1 ||  cantidad > set.size()) {
                System.out.println("¡El número de elementos debe ser entre 1 y " + set.size() + "!");
            }
        } while (cantidad < 1 || cantidad > set.size());

        System.out.println(cantidad + " primeros elementos:");
        Iterator<String> iterator = set.iterator();
        for (int i = 0; i < cantidad; i++) {
            System.out.print(iterator.next() + " ");
        }
    }
}

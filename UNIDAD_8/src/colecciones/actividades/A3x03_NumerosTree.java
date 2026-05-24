package colecciones.actividades;

import entrada.Teclado;
import java.util.*;

public class A3x03_NumerosTree {
    private static void generarNumeros(Set<Integer> set) {
        int num;
        do {
            num = Teclado.leerEntero("¿Número entero? (0 para terminar) ");
            if (num != 0) {
                set.add(num);
            }
        } while (num != 0);
    }

    public static void main(String[] args) {
        Set<Integer> set = new TreeSet<>();
        generarNumeros(set);

        System.out.println("Elementos for each");
        for (Integer i : set) {
            System.out.print(i + " ");
        }
        System.out.println();

        int cantidad;
        do {
            cantidad = Teclado.leerEntero("¿Cuantos elementos desea imprimir? ");
            if (cantidad < 1 || cantidad > set.size()) {
                System.out.println("¡El número de elementos debe ser entre 1 y " + set.size() + "!");
            }
        } while (cantidad < 1 || cantidad > set.size());

        System.out.println(cantidad + " primeros elementos:");
        Iterator<Integer> iterator = set.iterator();
        for (int i = 0; i < cantidad; i++) {
            System.out.print(iterator.next() + " ");
        }
    }
}

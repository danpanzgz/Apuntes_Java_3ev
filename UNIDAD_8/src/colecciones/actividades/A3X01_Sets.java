package colecciones.actividades;

import entrada.Teclado;

import java.util.*;

public class A3X01_Sets {
    public static void generarEnteros(int cantidadEnteros, HashSet<Integer> h, LinkedHashSet<Integer> l, TreeSet<Integer> t) {
        for (int i = 0; i < cantidadEnteros; i++) {
            while (h.size() < cantidadEnteros && l.size() < cantidadEnteros && t.size() < cantidadEnteros) {
                int random = new Random().nextInt(20 - 10 + 1) + 10;
                h.add(random);
                l.add(random);
                t.add(random);
            }
        }
    }

    public static void main(String[] args) {
        int cantidadEnteros = Teclado.leerEntero("¿Cantidad de enteros? ");

        HashSet<Integer> hashSet = new HashSet<>();
        LinkedHashSet<Integer> linkedHashSet = new LinkedHashSet<>();
        TreeSet<Integer> treeSet = new TreeSet<>();

        generarEnteros(cantidadEnteros, hashSet, linkedHashSet, treeSet);

        System.out.println("Elementos del consjunto hash con iterator: ");
        Iterator<Integer> iterator = hashSet.iterator();
        while (iterator.hasNext()) {
            System.out.print(" " + iterator.next());
        }

        System.out.println("\nElementos del conjunto hash con toString");
        System.out.println(hashSet);

        System.out.println("Elementos del consjunto hash enlazado con iterator: ");
        Iterator<Integer> iterator2 = linkedHashSet.iterator();
        while (iterator2.hasNext()) {
            System.out.print(" " + iterator2.next());
        }

        System.out.println("\nElementos del conjunto hash enlazado con toString");
        System.out.println(linkedHashSet);

        System.out.println("Elementos del arbol con iterator: ");
        Iterator<Integer> iterator3 = treeSet.iterator();
        while (iterator3.hasNext()) {
            System.out.print(" " + iterator3.next());
        }

        System.out.println("\nElementos del arbol con toString");
        System.out.println(treeSet);
    }
}

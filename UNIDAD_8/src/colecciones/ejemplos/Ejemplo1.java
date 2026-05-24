package colecciones.ejemplos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class Ejemplo1 {
    public static void main(String[] args) {
        // creo un array list de strings con una capacidad inicial de 2 elementos
        ArrayList<String> vector = new ArrayList<String>(2);

        // añado 3 elementos, por lo que el array list cambia de capacidad
        vector.add("Mundo");
        vector.add("Hola");
        // el tercer elemento lo añado en la segunda posicion, en el medio de los anteriores
        vector.add(1, "!");

        // imprimo el vector normal
        System.out.println(vector);

        // recorro imprimo cada elemento individualmente
        System.out.println("---------------------");
        for (int i = 0; i < vector.size(); i++) {
            System.out.println(vector.get(i) + " " + i);
        }

        // elimino el segundo elemento
        vector.remove(1);
        System.out.println("---------------------");
        System.out.println(vector);

        // cambio el segundo valor
        vector.set(1, "HOLA");
        System.out.println("---------------------");
        System.out.println(vector);

        // recorro el vector con un bucle for each
        System.out.println("---------------------");
        for (String i : vector) {
            System.out.println(i);
        }

        // recorro con iterator
        System.out.println("---------------------");
        Iterator<String> it = vector.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }

        // ordeno el vector con collections.sort
        Collections.sort(vector);
        System.out.println("---------------------");
        System.out.println(vector);

        // ordeno en orden inverso
        Collections.sort(vector, Collections.reverseOrder());
        System.out.println("---------------------");
        System.out.println(vector);

        // compruebo si está vacío con size
        System.out.println("---------------------");
        System.out.println(vector.size() == 0);

        // borro el vector con clear
        vector.clear();

        // compruebo si está vacío con isEmpty
        System.out.println("---------------------");
        System.out.println(vector.isEmpty());
    }
}

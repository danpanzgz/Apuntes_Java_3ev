package colecciones.actividades;

import entrada.Teclado;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;

public class A4x01_Mapas {
    public static void main(String[] args) {
        Map<String, Integer> mapa = new TreeMap<>();

        String input;
        int nota;
        do {
            boolean correcto;
            do {
                correcto = true;
                input = Teclado.leerCadena("¿Nombre (*** para terminar)? ");

                if (!input.equals("***")) {
                    if (mapa.containsKey(input)) {
                        System.out.println("Ya existe un alumno con ese nombre");
                        correcto = false;
                    } else {
                        nota = Teclado.leerEntero("¿Nota? ");

                        if (nota < 1 || nota > 10) {
                            System.out.println("La nota debe estar comprendida entre 1 y 10");
                            correcto = false;
                        } else {
                            mapa.put(input, nota);
                        }
                    }
                }
            } while (!correcto);
        } while (!input.equals("***"));

        System.out.println("Iterador de claves");
        Iterator<String> iterator = mapa.keySet().iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next() + " " + mapa.get(iterator.next()));
        }

        System.out.println("Foreach de claves");
        for (String clave : mapa.keySet()) {
            System.out.println(clave + " " + mapa.get(clave));
        }

        System.out.println("Iterador de entradas");
        Iterator<Entry<String, Integer>> iteradorEntradas = mapa.entrySet().iterator();
        while (iteradorEntradas.hasNext()) {
            Entry<String, Integer> entrada = iteradorEntradas.next();
            System.out.println(entrada.getKey() + " " + entrada.getValue());
        }

        System.out.println("Foreach de entradas");
        for (Entry<String, Integer> entrada : mapa.entrySet()) {
            System.out.println(entrada.getKey() + " " + entrada.getValue());
        }
    }
}

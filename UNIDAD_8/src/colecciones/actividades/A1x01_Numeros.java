package colecciones.actividades;

import java.util.ArrayList;
import java.util.Random;

public class A1x01_Numeros {
    public static void main(String[] args) {
        ArrayList<Integer> numeros = new ArrayList<Integer>();
        Random r = new Random();
        for (int i = 0; i < 15; i++) {
            numeros.add(r.nextInt(200 - 100 + 1) + 100);
        }

        System.out.println(numeros);
        int suma = 0;
        for (Integer i : numeros) {
            suma += i;
        }
        System.out.println("Se han consultado " + numeros.size() + " nodos en la lista.");

        System.out.println("Suma: " + suma);
        System.out.println("Media: " + (double) suma/numeros.size());
    }
}

package colecciones.actividades.BingoOposicion;

import java.util.Set;
import java.util.TreeSet;

public class Principal {
    public static void main(String[] args) {
        final int numTemas = 74;
        final int numBolas = 5;

        Set<Tema> bolasOposicion = new TreeSet<>();

        while (bolasOposicion.size() < numBolas) {
            int aleatorio = (int) (Math.random()*numTemas + 1);
            Tema t = new Tema(aleatorio);
            bolasOposicion.add(t);
        }

        System.out.println(bolasOposicion);
    }
}

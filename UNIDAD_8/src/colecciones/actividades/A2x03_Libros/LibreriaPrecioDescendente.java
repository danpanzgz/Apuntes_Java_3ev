package colecciones.actividades.A2x03_Libros;

import java.util.Comparator;

public class LibreriaPrecioDescendente implements Comparator<Libro> {
    @Override
    public int compare(Libro o1, Libro o2) {
        return Double.compare(o2.getEuros(), o1.getEuros());
    }
}

package colecciones.actividades.RepasoExamen.orden;

import java.util.Comparator;

public class OrdenClaveDescendente implements Comparator<String> {
    @Override
    public int compare(String k1, String k2) {
        return k2.compareTo(k1);
    }
}

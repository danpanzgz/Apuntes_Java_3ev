package colecciones.actividades.RepasoExamen.orden;

import colecciones.actividades.RepasoExamen.clases.Producto;

import java.util.Comparator;

public class OrdenCodigoProducto implements Comparator<Producto> {

    @Override
    public int compare(Producto o1, Producto o2) {
        return Integer.compare(o1.getCodProducto(), o2.getCodProducto());
    }
}

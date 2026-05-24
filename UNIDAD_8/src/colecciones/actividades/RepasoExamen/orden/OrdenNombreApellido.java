package colecciones.actividades.RepasoExamen.orden;

import colecciones.actividades.RepasoExamen.clases.Usuario;

import java.util.Comparator;

public class OrdenNombreApellido implements Comparator<Usuario> {

    @Override
    public int compare(Usuario o1, Usuario o2) {
        if (o1.getNombre().equalsIgnoreCase(o2.getNombre())) {
            if (o1.getApellido1().equalsIgnoreCase(o2.getApellido1())) {
                return o1.getApellido2().compareTo(o2.getApellido2());
            }

            return o1.getApellido1().compareTo(o2.getApellido1());
        }

        return o1.getNombre().compareTo(o2.getNombre());
    }
}

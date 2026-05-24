package colecciones.actividades.RepasoExamen.clases;

import java.util.HashSet;

public class Comprador extends Usuario {
    private String tarjetaPago;
    private String direccionEnvio;
    private HashSet<Producto> productosFavs;

    public Comprador(String correo, String nombre, String apellido1, String apellido2, String contraseña, String tarjetaPago, String direccionEnvio) {
        super(correo, nombre, apellido1, apellido2, contraseña);
        this.tarjetaPago = tarjetaPago;
        this.direccionEnvio = direccionEnvio;
        this.productosFavs = new HashSet<>();
    }

    @Override
    public String toString() {
        return "Comprador{\n" +
                "\ttarjetaPago='" + tarjetaPago + '\'' + "\n" +
                "\tdireccionEnvio='" + direccionEnvio + '\'' + "\n" +
                "\tproductosFavs=" + productosFavs + "\n" +
                super.toString();
    }

    public boolean añadirProductoFavorito(Producto p) {
        return this.productosFavs.add(p);
    }

    public boolean borrarProductoFavorito(int prod) {
        return this.productosFavs.remove(new Producto(prod));
    }

    public boolean tieneFavorito(int prod) {
        return this.productosFavs.contains(new Producto(prod));
    }

    public String getTarjetaPago() {
        return tarjetaPago;
    }

    public void setTarjetaPago(String tarjetaPago) {
        this.tarjetaPago = tarjetaPago;
    }

    public String getDireccionEnvio() {
        return direccionEnvio;
    }

    public void setDireccionEnvio(String direccionEnvio) {
        this.direccionEnvio = direccionEnvio;
    }

    public HashSet<Producto> getProductosFavs() {
        return productosFavs;
    }

    public void setProductosFavs(HashSet<Producto> productosFavs) {
        this.productosFavs = productosFavs;
    }
}

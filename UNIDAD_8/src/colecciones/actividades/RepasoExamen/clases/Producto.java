package colecciones.actividades.RepasoExamen.clases;

import java.util.Objects;

public class Producto {
    private int codProducto;
    private String nombre;
    private double precio;

    public Producto(int codProducto, String nombre, double precio) {
        this.codProducto = codProducto;
        this.nombre = nombre;
        this.precio = precio;
    }

    public Producto(int codProducto) {
        this.codProducto = codProducto;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "codProducto=" + codProducto +
                ", nombre='" + nombre + '\'' +
                ", precio=" + precio +
                '}';
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCodProducto() {
        return codProducto;
    }

    public void setCodProducto(int codProducto) {
        this.codProducto = codProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Producto producto = (Producto) o;
        return codProducto == producto.codProducto;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(codProducto);
    }
}

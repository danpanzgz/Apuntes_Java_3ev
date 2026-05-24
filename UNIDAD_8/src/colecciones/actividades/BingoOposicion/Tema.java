package colecciones.actividades.BingoOposicion;

import java.util.Objects;

public class Tema implements Comparable<Tema> {
    private int numTema;
    private String nombre;

    public Tema(int numTema) {
        this.numTema = numTema;
        this.nombre = "TEMA " + numTema;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tema tema = (Tema) o;
        return numTema == tema.numTema && Objects.equals(nombre, tema.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numTema, nombre);
    }

    @Override
    public int compareTo(Tema t) {
        return this.numTema - t.numTema;
    }

    @Override
    public String toString() {
        return "Tema{" +
                "numTema=" + numTema +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}

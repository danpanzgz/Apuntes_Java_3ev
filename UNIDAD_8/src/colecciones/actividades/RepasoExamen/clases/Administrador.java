package colecciones.actividades.RepasoExamen.clases;

public class Administrador extends Usuario {
    private String categoria;
    private boolean revisaEscritos;

    public Administrador(String correo, String nombre, String apellido1, String apellido2, String contraseña, String categoria, boolean revisaEscritos) {
        super(correo, nombre, apellido1, apellido2, contraseña);
        this.categoria = categoria;
        this.revisaEscritos = revisaEscritos;
    }

    @Override
    public String toString() {
        return "Administrador{\n" +
                "\tcategoria='" + categoria + '\'' + "\n" +
                "\trevisaEscritos=" + revisaEscritos + "\n" +
                super.toString();
    }

    public String getCategoria() {
        return categoria;
    }

    public boolean coincideCategoria(String cat) {
        return this.categoria.equalsIgnoreCase(cat);
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public boolean isRevisaEscritos() {
        return revisaEscritos;
    }

    public void setRevisaEscritos(boolean revisaEscritos) {
        this.revisaEscritos = revisaEscritos;
    }
}

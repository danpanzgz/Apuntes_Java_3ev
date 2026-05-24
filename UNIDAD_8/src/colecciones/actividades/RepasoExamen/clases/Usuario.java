package colecciones.actividades.RepasoExamen.clases;

public abstract class Usuario {
    protected String correo;
    protected String nombre;
    protected String apellido1;
    protected String apellido2;
    protected String contraseña;

    public Usuario(String correo, String nombre, String apellido1, String apellido2, String contraseña) {
        this.correo = correo;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.contraseña = contraseña;
    }

    @Override
    public String toString() {
        return "\tcorreo='" + correo + '\'' + "\n" +
                "\tnombre='" + nombre + '\'' + "\n" +
                "\tapellido1='" + apellido1 + '\'' + "\n" +
                "\tapellido2='" + apellido2 + '\'' + "\n" +
                "\tcontraseña='" + contraseña + '\'' + "\n" +
                '}';
    }

    // TODO: implement
    private String encriptarContraseña(String contraseña) {
        for (int i = 0; i < contraseña.length(); i++) {
            char c = contraseña.charAt(i);
            return contraseña;
        }
        return contraseña;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
}

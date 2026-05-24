package excepciones.actividades.A2x01_ExcepcionesPersonalizadas;

import excepciones.actividades.A2x01_ExcepcionesPersonalizadas.excepciones.ExcepcionUsuario;

public class Usuario {
    private String nombre;
    private String contraseña;
    private int numPuntos;

    public Usuario(String nombre, String contraseña, int numPuntos) {
        if (contraseña.length() < 6 || contraseña.length() > 20) {
            throw new ExcepcionUsuario(ExcepcionUsuario.EXCEPCION_CARACTERES);
        }

        if (numPuntos < 0) {
            throw new ExcepcionUsuario(ExcepcionUsuario.EXCEPCION_PUNTOS);
        }

        this.nombre = nombre;
        this.contraseña = contraseña;
        this.numPuntos = numPuntos;
    }

    @Override
    public String toString() {
        return  "nombre='" + nombre + '\'' +
                ", contraseña='" + contraseña + '\'' +
                ", numPuntos=" + numPuntos;
    }

    public String getNombre() {
        return this.nombre;
    }
}

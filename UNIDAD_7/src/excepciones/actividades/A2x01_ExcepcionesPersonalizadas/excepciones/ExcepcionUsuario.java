package excepciones.actividades.A2x01_ExcepcionesPersonalizadas.excepciones;

public class ExcepcionUsuario extends RuntimeException {
    public static final String EXCEPCION_CARACTERES = "contraseña no tiene entre 6 y 20 caracteres.";
    public static final String EXCEPCION_PUNTOS = "número de puntos negativo.";

    public ExcepcionUsuario(String mensaje) {
        super("Error al crear usuario: " + mensaje);
    }
}

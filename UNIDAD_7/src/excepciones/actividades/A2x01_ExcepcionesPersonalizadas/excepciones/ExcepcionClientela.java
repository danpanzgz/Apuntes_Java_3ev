package excepciones.actividades.A2x01_ExcepcionesPersonalizadas.excepciones;

public class ExcepcionClientela extends RuntimeException {
    public static final String EXCEPCION_INSERTAR_LLENO = "Error al insertar usuario: vector de usuarios lleno";
    public static final String EXCEPCION_INSERTAR_REPETIDO = "Error al insertar usuario: nombre de usuario repetido";
    public static final String EXCEPCION_ELIMINAR_VACIO = "Error al eliminar usuario: vector de usuarios vacío.";
    public static final String EXCEPCION_ELIMINAR_NO_ENCONTRADO = "Error al eliminar usuario: posición no indica usuario en la clientela.";


    public ExcepcionClientela(String mensaje) {
        super(mensaje);
    }
}

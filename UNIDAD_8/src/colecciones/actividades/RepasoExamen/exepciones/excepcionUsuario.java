package colecciones.actividades.RepasoExamen.exepciones;

public class excepcionUsuario extends RuntimeException {
    public static String usuarioNoExiste = "no existe el usuario";
    public static String noEsComprador = "no es un comprador";
    public excepcionUsuario(String message) {
        super("Error: " + message);
    }
}

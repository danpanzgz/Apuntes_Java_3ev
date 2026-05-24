package colecciones.actividades.RepasoExamen.exepciones;

public class excepcionProductoFavorito extends RuntimeException {
    public static String esFavorito = "el producto ya es favorito";
    public static String noEsFavorito = "el producto no es favorito";

    public excepcionProductoFavorito(String message) {
        super("Error: " + message);
    }
}

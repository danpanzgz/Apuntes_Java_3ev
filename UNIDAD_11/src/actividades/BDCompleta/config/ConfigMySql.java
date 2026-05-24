package actividades.BDCompleta.config;

import actividades.BDCompleta.excepciones.BDException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConfigMySql {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URLBD = "jdbc:mysql://localhost:3306/empresa";

    private static final String usuario = "root";
    private static String contrasena = "Juanelvapers22";

    public static Connection abrirConexion() throws BDException {
        Connection conexion = null;

        try {
            Class.forName(DRIVER);
            conexion = DriverManager.getConnection(URLBD, usuario, contrasena);
        } catch (ClassNotFoundException e) {
            throw new BDException(BDException.ERROR_CARGAR_DRIVER + e.getMessage());
        } catch (SQLException e) {
            throw new BDException(BDException.ERROR_ABRIR_CONEXION + e.getMessage());
        }

        return conexion;
    }

    public static void cerrarConexion(Connection conexion) throws BDException {
        try {
            conexion.close();
        } catch (SQLException e) {
            throw new BDException(BDException.ERROR_CERRAR_CONEXION + e.getMessage());
        }
    }
}

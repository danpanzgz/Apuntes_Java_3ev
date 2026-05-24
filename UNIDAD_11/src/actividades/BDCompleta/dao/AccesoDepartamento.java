package actividades.BDCompleta.dao;

import actividades.BDCompleta.config.ConfigMySql;
import actividades.BDCompleta.config.ConfigSQLite;
import actividades.BDCompleta.excepciones.BDException;
import actividades.BDCompleta.modelo.Departamento;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AccesoDepartamento {
    // Consultar el departamento por codigo
    public static Departamento consultarDepartamentoCodigo(int codigo, String opcion) throws BDException {
        Departamento departamentoReturn = null;
        Connection conexion = null;
        try {
            conexion = opcion.equalsIgnoreCase("mysql")
                    ? ConfigMySql.abrirConexion()
                    : ConfigSQLite.abrirConexion();

            String query = "SELECT * FROM departamento WHERE codigo = " + codigo;

            Statement sentencia = conexion.createStatement();
            ResultSet resultado = sentencia.executeQuery(query);

            String nombre = "", ubicacion = "";
            if (resultado.next()) {
                nombre = resultado.getString("nombre");
                ubicacion = resultado.getString("ubicacion");

                departamentoReturn = new Departamento(codigo, nombre, ubicacion);
            } else {
                throw new BDException(BDException.ERROR_NO_EXISTE);
            }
        } catch (SQLException e) {
            throw new BDException(BDException.ERROR_QUERY + e.getMessage());
        } finally {
            if (conexion != null) {
                if (opcion.equalsIgnoreCase("mysql")) {
                    ConfigMySql.cerrarConexion(conexion);
                } else {
                    ConfigSQLite.cerrarConexion(conexion);
                }
            }
        }

        return departamentoReturn;
    }

    // Modificar la ubicacion de un departamento
    public static boolean modificarUbicacionDepartamentoCodigo(int codigo, String ubicacion, String opcion) throws BDException {
        int filasAfectadas = 0;
        Connection conexion = null;
        try {
            conexion = opcion.equalsIgnoreCase("mysql")
                    ? ConfigMySql.abrirConexion()
                    : ConfigSQLite.abrirConexion();
            Statement sentencia = conexion.createStatement();

            String queryUpdate = "UPDATE departamento SET ubicacion = '" + ubicacion +
                    "' WHERE codigo = " + codigo;
            filasAfectadas = sentencia.executeUpdate(queryUpdate);
        } catch (SQLException e) {
            throw new BDException(BDException.ERROR_QUERY + e.getMessage());
        } finally {
            if (conexion != null) {
                if (opcion.equalsIgnoreCase("mysql")) {
                    ConfigMySql.cerrarConexion(conexion);
                } else {
                    ConfigSQLite.cerrarConexion(conexion);
                }
            }
        }

        return filasAfectadas == 1;
    }

    // Borrar un departamento por codigo
    public static boolean borrarDepartamentoCodigo(int codigo, String opcion) throws BDException {
        int filas = 0;
        Connection conexion = null;

        try {
            conexion = opcion.equalsIgnoreCase("mysql")
                    ? ConfigMySql.abrirConexion()
                    : ConfigSQLite.abrirConexion();
            Statement sentencia = conexion.createStatement();

            String queryDelete = "DELETE FROM departamento WHERE codigo = " + codigo;
            filas = sentencia.executeUpdate(queryDelete);
        } catch (SQLException e) {
            throw new BDException(BDException.ERROR_QUERY + e.getMessage());
        } finally {
            if (conexion != null) {
                if (opcion.equalsIgnoreCase("mysql")) {
                    ConfigMySql.cerrarConexion(conexion);
                } else {
                    ConfigSQLite.cerrarConexion(conexion);
                }
            }
        }

        return filas == 1;
    }

    // Consultar todos los departamentos ordenados por nombre
    public static List<Departamento> consultarDepartamentosOrdenNombre(String opcion) throws BDException {
        List<Departamento> listaDepartamentos = new ArrayList<Departamento>();
        Connection conexion = null;
        try {
            conexion = opcion.equalsIgnoreCase("mysql")
                    ? ConfigMySql.abrirConexion()
                    : ConfigSQLite.abrirConexion();

            String query = "SELECT * FROM departamento ORDER BY nombre";
            Statement sentencia = conexion.createStatement();
            ResultSet resultado = sentencia.executeQuery(query);

            while (resultado.next()) {
                Departamento departamento = new Departamento(resultado.getInt("codigo"),
                        resultado.getString("nombre"), resultado.getString("ubicacion"));
                listaDepartamentos.add(departamento);
            }
        } catch (SQLException e) {
            throw new BDException(BDException.ERROR_QUERY + e.getMessage());
        } finally {
            if (conexion != null) {
                if (opcion.equalsIgnoreCase("mysql")) {
                    ConfigMySql.cerrarConexion(conexion);
                } else {
                    ConfigSQLite.cerrarConexion(conexion);
                }
            }
        }

        return listaDepartamentos;
    }

    //añadir un departamento
    public static boolean anadirDepartamento(String nombre, String ubicacion, String opcion) throws BDException {
        Connection conexion = null;
        int filas = 0;
        try {
            conexion = opcion.equalsIgnoreCase("mysql")
                    ? ConfigMySql.abrirConexion()
                    : ConfigSQLite.abrirConexion();

            String query = "insert into departamento (nombre, ubicacion) values ('" +
                    nombre + "', '" +
                    ubicacion + "')";
            Statement sentencia = conexion.createStatement();
            filas = sentencia.executeUpdate(query);
        } catch (SQLException e) {
            throw new BDException(BDException.ERROR_QUERY + e.getMessage());
        } finally {
            if (conexion != null) {
                if (opcion.equalsIgnoreCase("mysql")) {
                    ConfigMySql.cerrarConexion(conexion);
                } else {
                    ConfigSQLite.cerrarConexion(conexion);
                }
            }
        }

        return filas == 1;
    }

    //añadir un departamento
    public static boolean anadirDepartamentoCodigo(int codigo, String nombre, String ubicacion, String opcion) throws BDException {
        Connection conexion = null;
        int filas = 0;
        try {
            conexion = opcion.equalsIgnoreCase("mysql")
                    ? ConfigMySql.abrirConexion()
                    : ConfigSQLite.abrirConexion();

            String query = "insert into departamento (codigo, nombre, ubicacion) values (" +
                    codigo + ", '" +
                    nombre + "', '" +
                    ubicacion + "')";
            Statement sentencia = conexion.createStatement();
            filas = sentencia.executeUpdate(query);
        } catch (SQLException e) {
            throw new BDException(BDException.ERROR_QUERY + e.getMessage());
        } finally {
            if (conexion != null) {
                if (opcion.equalsIgnoreCase("mysql")) {
                    ConfigMySql.cerrarConexion(conexion);
                } else {
                    ConfigSQLite.cerrarConexion(conexion);
                }
            }
        }

        return filas == 1;
    }
}

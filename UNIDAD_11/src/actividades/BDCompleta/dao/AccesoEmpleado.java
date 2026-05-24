package actividades.BDCompleta.dao;

import actividades.BDCompleta.Principal;
import actividades.BDCompleta.config.ConfigMySql;
import actividades.BDCompleta.config.ConfigSQLite;
import actividades.BDCompleta.excepciones.BDException;
import actividades.BDCompleta.excepciones.CSVException;
import actividades.BDCompleta.modelo.Departamento;
import actividades.BDCompleta.modelo.Empleado;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccesoEmpleado {
    public static Empleado consultarEmpleadoCodigo(int codigo, String opcion) throws BDException {
        Empleado empleadoReturn = null;
        Connection conexion = null;
        try {
            conexion = opcion.equalsIgnoreCase("mysql")
                    ? ConfigMySql.abrirConexion()
                    : ConfigSQLite.abrirConexion();

            String query = "SELECT * FROM empleado WHERE codigo = ?";

            PreparedStatement ps = conexion.prepareStatement(query);
            ps.setInt(1, codigo);
            ResultSet resultado = ps.executeQuery();

            if (resultado.next()) {
                String nombre = resultado.getString("nombre");
                String fecha_alta = resultado.getString("fecha_alta");
                float salario = resultado.getFloat("salario");
                Departamento departamento = AccesoDepartamento.consultarDepartamentoCodigo(resultado.getInt("codigo_departamento"), opcion);

                empleadoReturn = new Empleado(codigo, nombre, fecha_alta, salario, departamento);
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

        return empleadoReturn;
    }

    public static boolean anadirEmpleado(String nombre, String fecha_alta, float salario, int codigoDepartamento, String opcion) throws BDException {
        Connection conexion = null;
        int filas = 0;
        try {
            conexion = opcion.equalsIgnoreCase("mysql")
                    ? ConfigMySql.abrirConexion()
                    : ConfigSQLite.abrirConexion();

            String query = "insert into empleado (nombre, fecha_alta, salario, codigo_departamento) values (?, ?, ?, ?)";
            PreparedStatement ps = conexion.prepareStatement(query);
            ps.setString(1, nombre);
            ps.setString(2, fecha_alta);
            ps.setFloat(3, salario);
            ps.setInt(4, codigoDepartamento);
            filas = ps.executeUpdate();
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

    public static void anadirEmpleadoEntero(Empleado empleado, String opcion) throws BDException {
        Connection conexion = null;
        try {
            conexion = opcion.equalsIgnoreCase("mysql")
                    ? ConfigMySql.abrirConexion()
                    : ConfigSQLite.abrirConexion();

            try {
                AccesoDepartamento.consultarDepartamentoCodigo(empleado.getCodigo(), Principal.opcionBD);
            } catch (BDException e) {
                throw new CSVException(CSVException.ERROR_NO_EXISTE_DEP);
            }

            String query = "insert into empleado (codigo, nombre, fecha_alta, salario, codigo_departamento) values (?, ?, ?, ?, ?)";
            PreparedStatement ps = conexion.prepareStatement(query);
            ps.setInt(1, empleado.getCodigo());
            ps.setString(2, empleado.getNombre());
            ps.setString(3, empleado.getFechaAlta());
            ps.setFloat(4, empleado.getSalario());
            ps.setInt(5, empleado.getDepartamento().getCodigo());
            ps.executeUpdate();
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
    }

    public static boolean borrarEmpleadoCodigo(int codigo, String opcion) throws BDException {
        int filas = 0;
        Connection conexion = null;

        try {
            conexion = opcion.equalsIgnoreCase("mysql")
                    ? ConfigMySql.abrirConexion()
                    : ConfigSQLite.abrirConexion();

            String queryDelete = "DELETE FROM empleado WHERE codigo = ?"; // ?????
            PreparedStatement ps = conexion.prepareStatement(queryDelete);
            ps.setInt(1, codigo);
            filas = ps.executeUpdate();
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

    public static List<Empleado> consultarEmpleados(String opcion) throws BDException {
        List<Empleado> listaEmpleados = new ArrayList<>();
        Connection conexion = null;
        try {
            conexion = opcion.equalsIgnoreCase("mysql")
                    ? ConfigMySql.abrirConexion()
                    : ConfigSQLite.abrirConexion();
            String query = "SELECT e.codigo as \"codigo_empleado\", e.nombre as \"nombre_empleado\", e.fecha_alta, e.salario, " +
                    "d.codigo as \"codigo_departamento\", d.nombre as \"nombre_departamento\", d.ubicacion FROM empleado e " +
                    "join departamento d " +
                    "on e.codigo_departamento = d.codigo " +
                    "order by e.nombre";
            PreparedStatement ps = conexion.prepareStatement(query);
            ResultSet resultado = ps.executeQuery();
            while (resultado.next()) {
                int codigoEmpleado = resultado.getInt("codigo_empleado");
                String nombreEmpleado = resultado.getString("nombre_empleado");
                String fechaAlta = resultado.getString("fecha_alta");
                float salarioEmpleado = resultado.getFloat("salario");
                int codigoDepartamento = resultado.getInt("codigo_departamento");
                String nombreDepartamento = resultado.getString("nombre_departamento");
                String ubicacion = resultado.getString("ubicacion");
                Departamento d = new Departamento(codigoDepartamento, nombreDepartamento, ubicacion);
                Empleado e = new Empleado(codigoEmpleado, nombreEmpleado, fechaAlta, salarioEmpleado, d);
                listaEmpleados.add(e);
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
        return listaEmpleados;
    }

    public static List<Empleado> consultarEmpleadosConMasSalario(float salario, String opcion) throws BDException {
        List<Empleado> listaEmpleados = new ArrayList<>();
        Connection conexion = null;
        try {
            conexion = opcion.equalsIgnoreCase("mysql")
                    ? ConfigMySql.abrirConexion()
                    : ConfigSQLite.abrirConexion();

            String query = "SELECT e.codigo as \"codigo_empleado\", e.nombre as \"nombre_empleado\", e.fecha_alta, e.salario, " +
                    "d.codigo as \"codigo_departamento\", d.nombre as \"nombre_departamento\", d.ubicacion FROM empleado e " +
                    "join departamento d " +
                    "on e.codigo_departamento = d.codigo " +
                    "where e.salario > ? " +
                    "order by e.nombre";
            PreparedStatement ps = conexion.prepareStatement(query);
            ps.setFloat(1, salario);
            ResultSet resultado = ps.executeQuery();

            while (resultado.next()) {
                int codigoEmpleado = resultado.getInt("codigo_empleado");
                String nombreEmpelado = resultado.getString("nombre_empleado");
                String fechaAlta = resultado.getString("fecha_alta");
                float salarioEmpleado = resultado.getFloat("salario");
                int codigoDepartamento = resultado.getInt("codigo_departamento");
                String nombreDepartamento = resultado.getString("nombre_departamento");
                String ubicacion = resultado.getString("ubicacion");

                Departamento d = new Departamento(codigoDepartamento, nombreDepartamento, ubicacion);
                Empleado e = new Empleado(codigoEmpleado, nombreEmpelado, fechaAlta, salarioEmpleado, d);
                listaEmpleados.add(e);
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

        return listaEmpleados;
    }

    public static void modificarEmpleadoEntero(Empleado empleado, String opcion) throws BDException {
        Connection conexion = null;
        try {
            conexion = opcion.equalsIgnoreCase("mysql")
                    ? ConfigMySql.abrirConexion()
                    : ConfigSQLite.abrirConexion();
            String queryUpdate = "update empleado set nombre = ?, fecha_alta = ?, salario = ?, codigo_departamento = ? where codigo = ?";
            PreparedStatement ps = conexion.prepareStatement(queryUpdate);
            ps.setString(1, empleado.getNombre());
            ps.setString(2, empleado.getFechaAlta());
            ps.setFloat(3, empleado.getSalario());
            ps.setInt(4, empleado.getDepartamento().getCodigo());
            ps.setInt(5, empleado.getCodigo());

            try {
                AccesoDepartamento.consultarDepartamentoCodigo(empleado.getCodigo(), Principal.opcionBD);
            } catch (BDException e) {
                throw new CSVException("no existe ese dep");
            }

            ps.executeUpdate();
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
    }

    public static boolean modificarDepartamentoCodigo(int codigo, int codigoDepartamento, String opcion) throws BDException {
        int filasAfectadas = 0;
        Connection conexion = null;
        try {
            conexion = opcion.equalsIgnoreCase("mysql")
                    ? ConfigMySql.abrirConexion()
                    : ConfigSQLite.abrirConexion();
            String queryUpdate = "update empleado set codigo_departamento = ? where codigo = ?";
            PreparedStatement ps = conexion.prepareStatement(queryUpdate);
            ps.setInt(1, codigoDepartamento);
            ps.setInt(2, codigo);

            filasAfectadas = ps.executeUpdate();

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

    public static List<Empleado> consultarEmpleadosInformatica(String opcion) throws BDException {
        List<Empleado> listaEmpleados = new ArrayList<>();
        Connection conexion = null;
        try {
            conexion = opcion.equalsIgnoreCase("mysql")
                    ? ConfigMySql.abrirConexion()
                    : ConfigSQLite.abrirConexion();

            String query = "SELECT e.codigo as \"codigo_empleado\", e.nombre as \"nombre_empleado\" FROM empleado e " +
                    "join departamento d " +
                    "on e.codigo_departamento = d.codigo " +
                    "where lower(d.nombre) = 'informatica' or lower(d.nombre) = 'informática' " +
                    "order by e.nombre";
            PreparedStatement ps = conexion.prepareStatement(query);
            ResultSet resultado = ps.executeQuery();

            while (resultado.next()) {
                int codigoEmpleado = resultado.getInt("codigo_empleado");
                String nombreEmpelado = resultado.getString("nombre_empleado");

                Empleado e = new Empleado(codigoEmpleado, nombreEmpelado);
                listaEmpleados.add(e);
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

        return listaEmpleados;
    }

    public static void exportarEmpleadosCSV(List<Empleado> empleados) {
        BufferedWriter bw = null;
        String ruta = "csv/empleados.csv";
        try {
            bw = new BufferedWriter(new FileWriter(ruta, false));

            for (Empleado empleado : empleados) {
                bw.write(empleado.toStringWithSeparators());
                bw.newLine();
            }

            bw.close();
        } catch (IOException ioe) {
            throw new CSVException(CSVException.ERROR_ESCRITURA);
        } finally {
            try {
                if (bw != null) {
                    bw.close();
                }
            } catch (IOException ioe) {
                throw new CSVException(CSVException.ERROR_CIERRE);
            }
        }
    }

    public static boolean importarEmpleadosCSV(String ruta) throws BDException {
        boolean fallaronAlgunas = false;

        BufferedReader br = null;
        File fichero = new File(ruta);
        if (!fichero.exists()) {
            throw new CSVException(CSVException.ERROR_NO_EXISTE);
        }

        try {
            br = new BufferedReader(new FileReader(fichero));
            String linea = br.readLine();

            while (linea != null) {
                Empleado empleado = new Empleado(linea);
                try {
                    AccesoEmpleado.anadirEmpleadoEntero(empleado, Principal.opcionBD);
                } catch (BDException bde) {
                    AccesoEmpleado.modificarEmpleadoEntero(empleado, Principal.opcionBD);
                } catch (CSVException csve) {
                    fallaronAlgunas = true;
                }
                linea = br.readLine();
            }

            br.close();
        } catch (FileNotFoundException fnfe) {
            throw new CSVException(CSVException.ERROR_APERTURA);
        } catch (IOException ioe) {
            throw new CSVException(CSVException.ERROR_ESCRITURA);
        } catch (NumberFormatException nfe) {
            throw new CSVException(CSVException.ERROR_PARSE);
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException ioe) {
                throw new CSVException(CSVException.ERROR_CIERRE);
            }
        }

        return fallaronAlgunas;
    }
}

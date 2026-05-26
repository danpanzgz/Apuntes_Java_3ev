package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import config.ConfigMySql;
import excepciones.BDException;
import modelo.Estacion;

public class AccesoEstacion {

    // TODO

    /**
     *
     * @return
     * @throws BDException
     */
    public static ArrayList<Estacion> consultarEstaciones() throws BDException {
        List<Estacion> lista = new ArrayList<>();
        String sql = "SELECT codigo, nombre, ubicacion, agno_inauguracion, vias FROM estacion";

        try (Connection con = ConfigMySql.abrirConexion();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                lista.add(new Estacion(
                        rs.getInt("codigo"),
                        rs.getString("nombre"),
                        rs.getString("ubicacion"),
                        rs.getInt("agno_inauguracion"),
                        rs.getInt("vias")
                ));
            }

        } catch (SQLException e) {
            throw new BDException("Error al obtener estaciones: " + e.getMessage());
        }
        return (ArrayList<Estacion>) lista;
    }

    /**
     *
     * @param codigo
     * @return
     * @throws BDException
     */
    public Estacion obtenerEstacion(int codigo) throws BDException {
        String sql = "SELECT codigo, nombre, ubicacion, agno_inauguracion, vias FROM estacion WHERE codigo = ?";

        try (Connection con = ConfigMySql.abrirConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, codigo);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Estacion(
                        rs.getInt("codigo"),
                        rs.getString("nombre"),
                        rs.getString("ubicacion"),
                        rs.getInt("agno_inauguracion"),
                        rs.getInt("vias")
                );
            }

        } catch (SQLException e) {
            throw new BDException("Error al obtener estacion: " + e.getMessage());
        }
        return null;
    }
}

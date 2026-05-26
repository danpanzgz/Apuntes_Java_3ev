package dao;

import config.ConfigMySql;
import excepciones.BDException;
import modelo.Clase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccesoClase {

    /**
     *
     * @return
     * @throws BDException
     */
    public List<Clase> obtenerTodasClases() throws BDException {
        List<Clase> lista = new ArrayList<>();
        String sql = "SELECT codigo, nombre, consumo_minimo_anual, porcentaje_adicional FROM clase";

        try (Connection con = ConfigMySql.abrirConexion();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Clase c = new Clase(
                        rs.getInt("codigo"),
                        rs.getString("nombre"),
                        rs.getDouble("consumo_minimo_anual"),
                        rs.getInt("porcentaje_adicional")
                );
                lista.add(c);
            }

        } catch (SQLException e) {
            throw new BDException("Error al obtener clases: " + e.getMessage());
        }
        return lista;
    }

    /**
     *
     * @param codigo
     * @return
     * @throws BDException
     */
    public Clase obtenerClasePorCodigo(int codigo) throws BDException {
        String sql = "SELECT codigo, nombre, consumo_minimo_anual, porcentaje_adicional FROM clase WHERE codigo = ?";

        try (Connection con = ConfigMySql.abrirConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, codigo);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Clase(
                        rs.getInt("codigo"),
                        rs.getString("nombre"),
                        rs.getDouble("consumo_minimo_anual"),
                        rs.getInt("porcentaje_adicional")
                );
            }

        } catch (SQLException e) {
            throw new BDException("Error al obtener clase: " + e.getMessage());
        }
        return null;
    }

}

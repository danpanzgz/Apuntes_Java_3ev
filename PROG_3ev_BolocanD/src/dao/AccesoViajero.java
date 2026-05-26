package dao;

import config.ConfigMySql;
import excepciones.BDException;
import modelo.Viajero;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccesoViajero {

	// TODO
	public static Viajero consultarViajero(int codigo) throws BDException {
		String sql = "SELECT codigo, codigo_clase, nombre, fecha_nacimiento, " +
				"lugar_residencia, correo, puntos FROM viajero WHERE codigo = ?";

		try (Connection con = ConfigMySql.abrirConexion();
			 PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setInt(1, codigo);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				return new Viajero(
						rs.getInt("codigo"),
						rs.getInt("codigo_clase"),
						rs.getString("nombre"),
						rs.getString("fecha_nacimiento"),
						rs.getString("lugar_residencia"),
						rs.getString("correo"),
						rs.getInt("puntos")
				);
			}

		} catch (SQLException e) {
			throw new BDException("Error al obtener elviajero: " + e.getMessage());
		}
		return null;
	}

	/**
	 *
	 * @param codigoEstacion
	 * @param fecha
	 * @return
	 * @throws BDException
	 */
	public List<Viajero> obtenerViajerosPorEstacionYFecha(int codigoEstacion, String fecha) throws BDException {
		List<Viajero> lista = new ArrayList<>();

		String sql = "SELECT DISTINCT v.codigo, v.codigo_clase, v.nombre, v.fecha_nacimiento, " +
				"v.lugar_residencia, v.correo, v.puntos " +
				"FROM viajero v " +
				"JOIN billete b ON v.codigo = b.codigo_viajero " +
				"WHERE (b.codigo_estacion_origen = ? OR b.codigo_estacion_destino = ?) " +
				"AND b.fecha = ? " +
				"ORDER BY v.nombre ASC";

		try (Connection con = ConfigMySql.abrirConexion();
			 PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setInt(1, codigoEstacion);
			ps.setInt(2, codigoEstacion);
			ps.setString(3, fecha);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				lista.add(new Viajero(
						rs.getInt("codigo"),
						rs.getInt("codigo_clase"),
						rs.getString("nombre"),
						rs.getString("fecha_nacimiento"),
						rs.getString("lugar_residencia"),
						rs.getString("correo"),
						rs.getInt("puntos")
				));
			}

		} catch (SQLException e) {
			throw new BDException("Error al obtener viajeros: " + e.getMessage());
		}

		return lista;
	}

}
package dao;

import config.ConfigMySql;
import excepciones.BDException;
import modelo.Billete;

import java.sql.*;

public class AccesoBillete {

    //TODO

    /**
     *
     * @param codigoViajero
     * @param codigoEstacionOrigen
     * @param codigoEstacionDestino
     * @param fecha
     * @param horaSalida
     * @param horaLlegada
     * @param importe
     * @return
     * @throws BDException
     */
    public static int sacarBillete(int codigoViajero, int codigoEstacionOrigen, int codigoEstacionDestino, String fecha,
                                   String horaSalida, String horaLlegada, double importe) throws BDException {

        String sql = "INSERT INTO billete (codigo_viajero, codigo_estacion_origen, " +
                "codigo_estacion_destino, fecha, hora_salida, hora_llegada, importe) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";


        try (Connection con = ConfigMySql.abrirConexion();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, codigoViajero);
            ps.setInt(2, codigoEstacionOrigen);
            ps.setInt(3, codigoEstacionDestino);
            ps.setString(4, fecha);
            ps.setString(5, horaSalida);
            ps.setString(6, horaLlegada);
            ps.setDouble(7, importe);

            int filas = ps.executeUpdate();
            if (filas == 0) {
                throw new BDException("No se pudo insertar el billete");
            }

            ResultSet keys = ps.getGeneratedKeys();
            if (keys.next()) {
                return keys.getInt(1);
            }

        } catch (
                SQLException e) {
            throw new BDException("Error al insertar billete: " + e.getMessage());
        }
        throw new

                BDException("No se consigio el codigo del billete");
    }
}


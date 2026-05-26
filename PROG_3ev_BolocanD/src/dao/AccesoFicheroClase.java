package dao;

import modelo.Clase;
import excepciones.BDException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AccesoFicheroClase {

	private String rutaFichero;

	public AccesoFicheroClase(String rutaFichero) {
		this.rutaFichero = rutaFichero;
	}

	/**
	 *
	 * @return
	 * @throws BDException
	 */
	public List<Clase> leerClases() throws BDException {
		List<Clase> lista = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(new FileReader(rutaFichero))) {
			String linea;
			while ((linea = br.readLine()) != null) {
				linea = linea.trim();
				if (linea.isEmpty()) continue;
				String[] partes = linea.split(";");
				if (partes.length < 4) continue;
				int codigo = Integer.parseInt(partes[0].trim());
				String nombre = partes[1].trim();
				double consumo = Double.parseDouble(partes[2].trim());
				int porcentaje = (int) Double.parseDouble(partes[3].trim());
				lista.add(new Clase(codigo, nombre, consumo, porcentaje));
			}
		} catch (IOException e) {
			throw new BDException("Error al leer el fichero: " + e.getMessage());
		}

		return lista;
	}

	/**
	 *
	 * @param clases
	 * @throws BDException
	 */
	public void escribirClases(List<Clase> clases) throws BDException {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(rutaFichero))) {
			for (Clase c : clases) {
				bw.write(c.getCodigo() + ";" + c.getNombre() + ";" +
						c.getConsumoMinimoAnual() + ";" + c.getPorcentajeAdicional());
				bw.newLine();
			}
		} catch (IOException e) {
			throw new BDException("Error al escribir el fichero: " + e.getMessage());
		}
	}

	/**
	 *
	 * @param rutaFichero
	 * @throws BDException
	 */
	public void actualizarFicheroDesdeDB(String rutaFichero) throws BDException {
		AccesoFicheroClase accesoFichero = new AccesoFicheroClase(rutaFichero);
		AccesoClase accesoClase = new AccesoClase();

		List<Clase> clasesFichero = accesoFichero.leerClases();
		List<Clase> clasesDB = accesoClase.obtenerTodasClases();
		List<Clase> clasesActualizadas = new ArrayList<>();

		for (Clase cf : clasesFichero) {
			Clase claseBD = null;
			for (Clase cdb : clasesDB) {
				if (cdb.getCodigo() == cf.getCodigo()) {
					claseBD = cdb;
					break;
				}
			}
			if (claseBD != null) {
				cf.setConsumoMinimoAnual(claseBD.getConsumoMinimoAnual());
				cf.setPorcentajeAdicional(claseBD.getPorcentajeAdicional());
				clasesActualizadas.add(cf);
			}
		}

		accesoFichero.escribirClases(clasesActualizadas);
		System.out.println("Fichero clases.csv actualizado.   ");
	}
}

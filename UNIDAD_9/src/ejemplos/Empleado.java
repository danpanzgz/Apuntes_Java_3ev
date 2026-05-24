package ejemplos;

import java.io.Serializable;

public class Empleado implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	// atributos de objeto
	private int codigo;              // identificador
	private int codigoDepartamento;
	private String nombre;
	private String fechaAlta;
	private double salario;
	
	// Crea un empleado a partir de varios par�metros.	
	public Empleado(int codigo, int codigoDepartamento, 
	                String nombre, String fechaAlta, double salario) {
		this.codigo = codigo;
		this.codigoDepartamento = codigoDepartamento;
		this.nombre = nombre;
		this.fechaAlta = fechaAlta;
		this.salario = salario;
	}

	// Devuelve una cadena de caracteres con el estado del empleado.
	@Override
	public String toString() {
		return 
			"Empleado [C�digo = " + this.codigo + 
			", C�digoDepartamento = " + this.codigoDepartamento + 
			", Nombre = " + this.nombre +
			", FechaAlta = " + this.fechaAlta + 
			", Salario = " + String.format("%.2f", this.salario) + 
			"]";
	}

	// Devuelve el c�digo del empleado.
	public int getCodigo() {
		return this.codigo;
	}
	
	// Devuelve el c�digo de departamento del empleado.
	public int getCodigoDepartamento() {
		return this.codigoDepartamento;
	}

	// Modifica el c�digo de departamento del empleado.
	public void setCodigoDepartamento(int codigoDepartamento) {
		this.codigoDepartamento = codigoDepartamento;
	}

	// Devuelve el nombre del empleado.
	public String getNombre() {
		return this.nombre;
	}

	// Modifica el nombre del empleado.
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	// Devuelve la fecha de alta del empleado.
	public String getFechaAlta() {
		return this.fechaAlta;
	}

	// Modifica la fecha de alta del empleado.
	public void setFechaAlta(String fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	// Devuelve el salario del empleado.
	public double getSalario() {
		return this.salario;
	}

	// Modifica el salario del empleado.
	public void setSalario(double salario) {
		this.salario = salario;
	}

}

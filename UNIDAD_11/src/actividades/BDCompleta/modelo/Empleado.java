package actividades.BDCompleta.modelo;

public class Empleado {
    private static final String SEPARADOR = ";";

    private int codigo;
    private String nombre;
    private String fechaAlta;
    private float salario;
    private Departamento departamento;

    public Empleado(int codigo, String nombre, String fechaAlta, float salario, Departamento departamento) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.fechaAlta = fechaAlta;
        this.salario = salario;
        this.departamento = departamento;
    }

    public Empleado(String linea) {
        String[] datos = linea.split(SEPARADOR);
        this.codigo = Integer.parseInt(datos[0]);
        this.nombre = datos[1];
        this.fechaAlta = datos[2];
        this.salario = Float.parseFloat(datos[3]);
        this.departamento = new Departamento(Integer.parseInt(datos[4]), datos[5], datos[6]);
    }

    public Empleado(int codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }

    public String toStringWithSeparators() {
        return
                this.codigo + SEPARADOR +
                        this.nombre + SEPARADOR +
                        this.fechaAlta + SEPARADOR +
                        String.format("%.2f", this.salario).replace(",", ".") + SEPARADOR +
                        this.departamento.getCodigo() + SEPARADOR +
                        this.departamento.getNombre() + SEPARADOR +
                        this.departamento.getUbicacion();
    }

    @Override
    public String toString() {
        return "Empleado{" +
                "codigo=" + codigo +
                ", nombre='" + nombre + '\'' +
                ", fechaAlta='" + fechaAlta + '\'' +
                ", salario=" + salario +
                ", Departamento=" + departamento +
                '}';
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(String fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public float getSalario() {
        return salario;
    }

    public void setSalario(float salario) {
        this.salario = salario;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }
}

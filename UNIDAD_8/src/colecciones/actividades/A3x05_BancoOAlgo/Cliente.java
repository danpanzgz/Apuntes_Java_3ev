package colecciones.actividades.A3x05_BancoOAlgo;

import java.util.Objects;

public class Cliente {
    private String dni;
    private String nombre;
    private Fecha fechaNacimiento;
    private String domicilio;
    private String eMail;
    private double saldo;

    public Cliente(String dni, String nombre, Fecha fechaNacimiento, String eMail, String domicilio, double saldo) {
        this.dni = dni;
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.eMail = eMail;
        this.domicilio = domicilio;
        this.saldo = saldo;
    }

    public Cliente(String dni) {
        this.dni = dni;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "dni='" + dni + '\'' +
                ", nombre='" + nombre + '\'' +
                ", fechaNacimiento=" + fechaNacimiento.toString() +
                ", domicilio='" + domicilio + '\'' +
                ", eMail='" + eMail + '\'' +
                ", saldo=" + String.format("%.2f", saldo) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return Objects.equals(dni, cliente.dni);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(dni);
    }

    public String getDni() {
        return dni;
    }

    public String getNombre() {
        return nombre;
    }

    public Fecha getFechaNacimiento() {
        return fechaNacimiento;
    }

    public String getEmail() {
        return this.eMail;
    }

    public void setEmail(String email) {
        this.eMail = email;
    }
}

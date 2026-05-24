package colecciones.actividades.A3x05_BancoOAlgo;

import colecciones.actividades.A3x05_BancoOAlgo.orden.OrdenDNIAscendente;
import colecciones.actividades.A3x05_BancoOAlgo.orden.OrdenNacimientoAscendente;
import colecciones.actividades.A3x05_BancoOAlgo.orden.OrdenNombreAscendente;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class Banco {
    private Set<Cliente> clientes;

    public Banco() {
        this.clientes = new HashSet<>();
    }

    public Banco(TreeSet<Cliente> t) {
        this.clientes = t;
    }

    @Override
    public String toString() {
        String strClientes = "Banco {\n";
        for (Cliente c : this.clientes) {
            strClientes = strClientes.concat("\t" + c.toString() + "\n");
        }
        strClientes = strClientes.concat("}");
        return strClientes;
    }

    public void insertarCliente(Cliente c) {
        this.clientes.add(c);
    }

    public boolean eliminarPorDNI(String dni) {
        return this.clientes.remove(new Cliente(dni));
    }

    public boolean estaDNI(String dni) {
        return this.clientes.contains(new Cliente(dni));
    }

    public Cliente consultarPorDNI(String dni) {
        for (Cliente c : this.clientes) {
            if (c.getDni().equals(dni)) {
                return c;
            }
        }

        return null;
    }

    public boolean estaVacio() {
        return this.clientes.isEmpty();
    }

    public Banco consultarTodosDNIAscendente() {
        TreeSet<Cliente> t = new TreeSet<>(new OrdenDNIAscendente());
        t.addAll(this.clientes);
        return new Banco(t);
    }

    public Banco consultarTodosNombreAscendente() {
        TreeSet<Cliente> t = new TreeSet<>(new OrdenNombreAscendente());
        t.addAll(this.clientes);
        return new Banco(t);
    }

    public Banco consultarTodosNacimientoAscendente() {
        TreeSet<Cliente> t = new TreeSet<>(new OrdenNacimientoAscendente());
        t.addAll(this.clientes);
        return new Banco(t);
    }

    public int cambiarClientesYahooGmail() {
        int contadorAfectados = 0;
        for (Cliente c : this.clientes) {
            String email = c.getEmail();
            if (email.endsWith("@yahoo.es")) {
                int indexArroba = email.indexOf("@");
                String prefijoEmail = email.substring(0, indexArroba);
                String emailNuevo = prefijoEmail.concat("@gmail.com");
                c.setEmail(emailNuevo);
                contadorAfectados++;
            }
        }

        return contadorAfectados;
    }
}

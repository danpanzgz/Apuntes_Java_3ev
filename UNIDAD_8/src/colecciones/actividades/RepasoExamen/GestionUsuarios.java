package colecciones.actividades.RepasoExamen;

import colecciones.actividades.RepasoExamen.clases.Administrador;
import colecciones.actividades.RepasoExamen.clases.Comprador;
import colecciones.actividades.RepasoExamen.clases.Producto;
import colecciones.actividades.RepasoExamen.clases.Usuario;
import colecciones.actividades.RepasoExamen.exepciones.excepcionProductoFavorito;
import colecciones.actividades.RepasoExamen.exepciones.excepcionUsuario;
import colecciones.actividades.RepasoExamen.orden.OrdenClaveDescendente;
import colecciones.actividades.RepasoExamen.orden.OrdenCodigoProducto;
import colecciones.actividades.RepasoExamen.orden.OrdenNombreApellido;

import java.util.*;

/**
 * GestionUsuarios es una clase que como atributo tiene un mapa de usuarios, por lo
 * que su función principal es controlar ese conjunto de usuarios
 */
public class GestionUsuarios {
    private Map<String, Usuario> mapaUsuarios;

    public GestionUsuarios() {
        this.mapaUsuarios = new HashMap<String, Usuario>();
    }

    public GestionUsuarios(Map<String, Usuario> mapaUsuarios) {
        this.mapaUsuarios = mapaUsuarios;
    }

    /**
     * inserta un usuario pasado por parametros en el conjunto, y devuelve si
     * se ha insertado con un booleano
     * @param usuario usuario a insertar
     * @return booleano de si se ha insertado
     */
    public boolean insertarUsuario(Usuario usuario) {
        return this.mapaUsuarios.putIfAbsent(usuario.getCorreo(), usuario) == null;
    }

    @Override
    public String toString() {
        String str = "";
        for (String key : mapaUsuarios.keySet()) {
            str = str.concat(mapaUsuarios.get(key).toString()) + "\n";
        }

        return str;
    }

    /**
     * devuelve una gestión de usuarios nueva con los mismos usuarios, pero ordenados
     * por su clave.
     * @return gestion nueva ordenada por clave
     */
    public GestionUsuarios ordenarPorClave() {
        TreeMap<String, Usuario> treeMap = new TreeMap<String, Usuario>(this.mapaUsuarios);
        return new GestionUsuarios(treeMap);
    }

    /**
     * devuelve una gestión de usuarios nueva con los mismos usuarios, pero
     * ordenados por su clave de manera descendente.
     * @return gestión ordenada por clave descendente
     */
    public GestionUsuarios ordenPorClaveDescendente() {
        TreeMap<String, Usuario> treeMap = new TreeMap<>(new OrdenClaveDescendente());
        treeMap.putAll(this.mapaUsuarios);
        return new GestionUsuarios(treeMap);
    }

    /**
     * devuelve un usuario iden
     * @param correo
     * @return
     */
    public Usuario consultarPorCorreo(String correo) {
        return this.mapaUsuarios.get(correo);
    }

    public void actualizarPorCorreo(Usuario u, String nombre, String apellido1, String apellido2, String contraseña) {
        u.setNombre(nombre);
        u.setApellido1(apellido1);
        u.setApellido2(apellido2);
        u.setContraseña(contraseña);
    }

    public boolean borrarPorCorreo(String c) {
        return this.mapaUsuarios.remove(c) != null;
    }

    /**
     * devuelve true si es un comprador
     * @param c
     * @return
     */
    public boolean consultarTipoUsuario(String c) {
        return this.mapaUsuarios.get(c) instanceof Comprador;
    }

    public boolean estaUsuario(String c) {
        return this.mapaUsuarios.containsKey(c);
    }

    public void añadirProductoFavorito(String correo, Producto p) throws excepcionUsuario, excepcionProductoFavorito {
        if (!estaUsuario(correo)) {
            throw new excepcionUsuario(excepcionUsuario.usuarioNoExiste);
        }

        if (!consultarTipoUsuario(correo)) {
            throw new excepcionUsuario(excepcionUsuario.noEsComprador);
        }

        Comprador c = (Comprador) this.mapaUsuarios.get(correo);
        if (!c.añadirProductoFavorito(p)) {
            throw new excepcionProductoFavorito(excepcionProductoFavorito.esFavorito);
        }
    }

    public void borrarProductoFavorito(String correo, int prod) throws excepcionUsuario, excepcionProductoFavorito {
        if (!estaUsuario(correo)) {
            throw new excepcionUsuario(excepcionUsuario.usuarioNoExiste);
        }

        if (!consultarTipoUsuario(correo)) {
            throw new excepcionUsuario(excepcionUsuario.noEsComprador);
        }

        Comprador c = (Comprador) this.mapaUsuarios.get(correo);
        if (!c.borrarProductoFavorito(prod)) {
            throw new excepcionProductoFavorito(excepcionProductoFavorito.noEsFavorito);
        }
    }

    public LinkedList<String> obtenerCorreosFavoritos(int codProd) {
        LinkedList<String> list = new LinkedList<>();
        for (String correo : this.mapaUsuarios.keySet()) {
            if (consultarTipoUsuario(correo)) {
                Comprador c = (Comprador) this.mapaUsuarios.get(correo);
                if (c.tieneFavorito(codProd)) list.add(c.getCorreo());
            }
        }

        return list;
    }

    public LinkedList<Administrador> obtenerAdministradoresCategoria(String cat) {
        LinkedList<Administrador> list = new LinkedList<>();
        for (String correo : this.mapaUsuarios.keySet()) {
            if (!consultarTipoUsuario(correo)) {
                Administrador a = (Administrador) this.mapaUsuarios.get(correo);
                if (a.coincideCategoria(cat) && a.isRevisaEscritos())
                    list.add(a);
            }
        }

        return list;
    }

    public ArrayList<Usuario> ordenarPorNombreYApellidos() {
        ArrayList<Usuario> arrayList = new ArrayList<>(this.mapaUsuarios.values());
        arrayList.sort(new OrdenNombreApellido());
        return arrayList;
    }

    public ArrayList<Producto> obtenerListaFavoritosComprador(String correo) throws  excepcionUsuario {
        if (!estaUsuario(correo)) {
            throw new excepcionUsuario(excepcionUsuario.usuarioNoExiste);
        }

        if (!consultarTipoUsuario(correo)) {
            throw new excepcionUsuario(excepcionUsuario.noEsComprador);
        }

        Comprador c = (Comprador) this.mapaUsuarios.get(correo);
        HashSet<Producto> hash = c.getProductosFavs();
        ArrayList<Producto> list = new ArrayList<>(hash);
        list.sort(new OrdenCodigoProducto());
        return list;
    }

    public HashSet<Producto> obtenerProductoMasFavoritos() {
        TreeMap<Producto, Integer> mapaValores = new TreeMap<>();
        for (Usuario u : this.mapaUsuarios.values()) {
            if (u instanceof Comprador c) {
                for (Producto p : c.getProductosFavs()) {
                    mapaValores.put(p, mapaValores.getOrDefault(p, 0) + 1);
                }
            }
        }

        TreeSet<Integer> treeMaximo = new TreeSet<>(mapaValores.values());
        int maxFavoritos = treeMaximo.getLast();
        HashSet<Producto> productosMasFavoritos = new HashSet<>();

        for (Producto p : mapaValores.keySet()) {
            if (mapaValores.get(p) == maxFavoritos) {
                productosMasFavoritos.add(p);
            }
        }

        return productosMasFavoritos;
    }
}

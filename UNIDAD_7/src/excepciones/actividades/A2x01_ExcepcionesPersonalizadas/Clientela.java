package excepciones.actividades.A2x01_ExcepcionesPersonalizadas;

import excepciones.actividades.A2x01_ExcepcionesPersonalizadas.excepciones.ExcepcionClientela;

import java.util.Arrays;

public class Clientela {
    private Usuario[] usuarios;
    private int numElementos;

    public Clientela(int capacidad) {
        usuarios = new Usuario[capacidad];
    }

    private String stringUsuarios() {
        String usuariosStr = "";
        for (int i = 0; i < numElementos; i++) {
            usuariosStr += "\t" + i + " " + usuarios[i].toString() + "\n";
        }
        return usuariosStr;
    }

    @Override
    public String toString() {
        if (numElementos == 0) {
            return "La clientela está vacía";
        }

        return "usuarios{\n" + stringUsuarios() +
                '}';
    }

    public void agregar(Usuario usuario) {
        if (numElementos == usuarios.length) {
            throw new ExcepcionClientela(ExcepcionClientela.EXCEPCION_INSERTAR_LLENO);
        }

        for (int i = 0; i < numElementos; i++) {
            if (usuarios[i].getNombre().equalsIgnoreCase(usuario.getNombre())) {
                throw new ExcepcionClientela(ExcepcionClientela.EXCEPCION_INSERTAR_REPETIDO);
            }
        }

        usuarios[numElementos] = usuario;
        numElementos++;
    }

    public void eliminarPorPosciion(int pos) {
        if (this.numElementos == 0) {
            throw new ExcepcionClientela(ExcepcionClientela.EXCEPCION_ELIMINAR_VACIO);
        }

        if (pos < 0 || pos >= this.numElementos) {
            throw new ExcepcionClientela(ExcepcionClientela.EXCEPCION_ELIMINAR_NO_ENCONTRADO);
        }

        boolean borrado = false;
        for (int i = 0; i < this.numElementos && !borrado; i++) {
            Usuario u = this.usuarios[i];

            if (u != null && i == pos) {
                u = null;
                borrado = true;
            }
        }

        if (!borrado) {
            throw new ExcepcionClientela(ExcepcionClientela.EXCEPCION_ELIMINAR_NO_ENCONTRADO);
        }

        for (int i = 0; i < this.numElementos; i++) {
            Usuario u = this.usuarios[i];

            boolean movido = false;
            if (u == null) {
                for (int j = i + 1; j < this.numElementos && !movido; j++) {
                    Usuario u2 = this.usuarios[j];
                    if (u2 != null) {
                        u = u2;
                        u2 = null;
                        movido = true;
                    }
                }
            }
        }

        this.numElementos--;
    }
}

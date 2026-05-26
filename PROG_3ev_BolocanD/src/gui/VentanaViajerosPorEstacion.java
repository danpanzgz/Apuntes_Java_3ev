package gui;

import dao.AccesoViajero;
import excepciones.BDException;
import modelo.Viajero;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class VentanaViajerosPorEstacion extends JFrame {

    private JTextField txtCodigoEstacion;
    private JTextField txtFecha;
    private JButton btnListar;
    private JButton btnCancelar;


    public VentanaViajerosPorEstacion() {
        setTitle("Introducir datos");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        setResizable(false);

        JPanel panelSuperior = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));

        panelSuperior.add(new JLabel("Codigo de la estacion:"));
        txtCodigoEstacion = new JTextField(10);
        panelSuperior.add(txtCodigoEstacion);

        panelSuperior.add(new JLabel("Fecha:"));
        txtFecha = new JTextField(12);
        panelSuperior.add(txtFecha);

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER));
        btnListar = new JButton("Listar Viajeros");
        btnCancelar = new JButton("cancelar");
        panelBotones.add(btnListar);
        panelBotones.add(btnCancelar);

        JPanel panelNorte = new JPanel(new BorderLayout());
        panelNorte.add(panelSuperior, BorderLayout.CENTER);
        panelNorte.add(panelBotones, BorderLayout.SOUTH);

        add(panelNorte, BorderLayout.NORTH);

        btnCancelar.addActionListener(e -> dispose());
        btnListar.addActionListener(e -> listarViajeros());

        pack();
        setSize(600, 130);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void listarViajeros() {
        String codigoStr = txtCodigoEstacion.getText().trim();
        String fecha = txtFecha.getText().trim();

        int codigoEstacion;
        try {
            codigoEstacion = Integer.parseInt(codigoStr);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "El codigo debe ser entero.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            AccesoViajero accesoViajero = new AccesoViajero();
            List<Viajero> viajeros = accesoViajero.obtenerViajerosPorEstacionYFecha(codigoEstacion, fecha);

            if (viajeros.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "No existe ningun viajero que paso por la estacion este dia",
                        "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                mostrarTablaViajeros(viajeros);
            }

        } catch (BDException ex) {
            JOptionPane.showMessageDialog(this, "Error al consultar la bd: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void mostrarTablaViajeros(List<Viajero> viajeros) {
        JDialog dialogo = new JDialog(this, "Tabla viajeros", true);
        dialogo.setLayout(new BorderLayout(10, 10));

        String[] columnas = {"Codigo", "Codigo clase", "Nombre", "Fecha nacimiento",
                "Lugar de Residencia", "Correo", "Puntos"};

        DefaultTableModel modelo = new DefaultTableModel(columnas, 0);

        for (Viajero v : viajeros) {
            modelo.addRow(new Object[]{
                    v.getCodigo(),
                    v.getCodigoClase(),
                    v.getNombre(),
                    v.getFechaNacimiento(),
                    v.getLugarResidencia(),
                    v.getCorreo(),
                    v.getPuntos()
            });
        }

        JTable tabla = new JTable(modelo);
        tabla.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        JScrollPane scroll = new JScrollPane(tabla);

        dialogo.add(scroll, BorderLayout.CENTER);

        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.addActionListener(e -> dialogo.dispose());

        JPanel panelCerrar = new JPanel();
        panelCerrar.add(btnCerrar);
        dialogo.add(panelCerrar, BorderLayout.SOUTH);

        dialogo.setSize(700, 300);
        dialogo.setLocationRelativeTo(this);
        dialogo.setVisible(true);
    }
}

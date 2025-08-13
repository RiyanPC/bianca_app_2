package main;

import Config.DatabaseConfig;
import java.awt.*;
import java.sql.*;
import javax.swing.*;

// ---------------- Ventana Principal ----------------
public class EmpresasUI extends JFrame {
    private PanelDatos panelDatos;
    private PanelLogos panelLogos;
    private JComboBox<String> comboEmpresas;
    private JCheckBox chkVerLogos;
    private String empresaSeleccionada;

    public EmpresasUI() {
        setTitle("Empresas");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel izquierdo con datos y logos
        JPanel panelIzquierdo = new JPanel(new BorderLayout());
        panelIzquierdo.setPreferredSize(new Dimension(300, 500));
        add(panelIzquierdo, BorderLayout.WEST);

        panelDatos = new PanelDatos();
        panelLogos = new PanelLogos();

        panelIzquierdo.add(panelDatos, BorderLayout.NORTH);
        panelIzquierdo.add(panelLogos, BorderLayout.CENTER);

        // Panel superior con combo y checkbox
        JPanel panelSuperior = new JPanel(new FlowLayout(FlowLayout.LEFT));
        comboEmpresas = new JComboBox<>();
        chkVerLogos = new JCheckBox("Ver logos", true);

        cargarEmpresas();

        comboEmpresas.addActionListener(e -> {
            empresaSeleccionada = (String) comboEmpresas.getSelectedItem();
            mostrarDatosEmpresa();
        });

        chkVerLogos.addActionListener(e -> mostrarDatosEmpresa());

        panelSuperior.add(new JLabel("Seleccione empresa:"));
        panelSuperior.add(comboEmpresas);
        panelSuperior.add(chkVerLogos);
        add(panelSuperior, BorderLayout.NORTH);

        // Panel derecho vacío
        add(new JPanel(), BorderLayout.CENTER);
    }

    private void cargarEmpresas() {
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT CM_Nombre_Compania FROM compania");
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                comboEmpresas.addItem(rs.getString("CM_Nombre_Compania"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void mostrarDatosEmpresa() {
        if (empresaSeleccionada == null) return;

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(
                     "SELECT CM_IdCompania, CM_Ruc, CM_Nombre_Compania, CM_Nombre_Comercial, " +
                             "CM_Logo1, CM_Logo2, CM_Logo3 " +
                             "FROM compania WHERE CM_Nombre_Compania = ?")) {

            ps.setString(1, empresaSeleccionada);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                // Actualizar datos
                panelDatos.setDatos(
                        rs.getInt("CM_IdCompania"),
                        rs.getString("CM_Ruc"),
                        rs.getString("CM_Nombre_Compania"),
                        rs.getString("CM_Nombre_Comercial")
                );

                // Actualizar logos
                if (chkVerLogos.isSelected()) {
                    panelLogos.mostrarLogos(
                            rs.getBytes("CM_Logo1"),
                            rs.getBytes("CM_Logo2"),
                            rs.getBytes("CM_Logo3")
                    );
                } else {
                    panelLogos.limpiar();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        DatabaseConfig.init();
        SwingUtilities.invokeLater(() -> new EmpresasUI().setVisible(true));
    }
}

// ---------------- PanelDatos ----------------
class PanelDatos extends JPanel {
    private JLabel lblId, lblRuc, lblNombreCompania, lblNombreComercial;

    public PanelDatos() {
        setLayout(new GridLayout(4, 1));
        lblId = new JLabel("ID: ");
        lblRuc = new JLabel("RUC: ");
        lblNombreCompania = new JLabel("Compañía: ");
        lblNombreComercial = new JLabel("Comercial: ");
        add(lblId);
        add(lblRuc);
        add(lblNombreCompania);
        add(lblNombreComercial);
    }

    public void setDatos(int id, String ruc, String compania, String comercial) {
        lblId.setText("ID: " + id);
        lblRuc.setText("RUC: " + ruc);
        lblNombreCompania.setText("Compañía: " + compania);
        lblNombreComercial.setText("Comercial: " + comercial);
    }
}

// ---------------- PanelLogos ----------------
class PanelLogos extends JPanel {
    public PanelLogos() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }

    public void mostrarLogos(byte[]... imagenes) {
        removeAll();
        for (byte[] img : imagenes) {
            if (img != null && img.length > 0) {
                JLabel label = new JLabel();
                ImageIcon icon = new ImageIcon(img);
                Image scaled = icon.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
                label.setIcon(new ImageIcon(scaled));
                label.setAlignmentX(Component.CENTER_ALIGNMENT);
                add(label);
            }
        }
        revalidate();
        repaint();
    }

    public void limpiar() {
        removeAll();
        revalidate();
        repaint();
    }
}

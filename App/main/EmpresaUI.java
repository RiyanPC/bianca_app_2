package main;

import Config.DatabaseConfig;
import java.awt.*;
import java.sql.*;
import javax.swing.*;

public class EmpresaUI extends JFrame {
    private JComboBox<String> comboEmpresas;
    private JLabel lblRuc, lblNombre, lblComercial;
    private JLabel lblLogo1, lblLogo2, lblLogo3;
    private JPanel panelDetalles;

    public EmpresaUI() {
        setTitle("Gestión de Empresas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        setSize(600, 400);
        setLocationRelativeTo(null);

        // --- Panel de selección ---
        JPanel panelSeleccion = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelSeleccion.add(new JLabel("Seleccionar Empresa:"));

        comboEmpresas = new JComboBox<>();
        cargarEmpresas();
        panelSeleccion.add(comboEmpresas);

        add(panelSeleccion, BorderLayout.NORTH);

        // --- Panel de detalles ---
        panelDetalles = new JPanel();
        panelDetalles.setLayout(new BoxLayout(panelDetalles, BoxLayout.Y_AXIS));
        panelDetalles.setBorder(BorderFactory.createTitledBorder("Detalles de la Empresa"));

        lblRuc = new JLabel("RUC: ");
        lblNombre = new JLabel("Razón Social: ");
        lblComercial = new JLabel("Nombre Comercial: ");

        // Logos
        JPanel panelLogos = new JPanel(new FlowLayout());
        lblLogo1 = new JLabel();
        lblLogo2 = new JLabel();
        lblLogo3 = new JLabel();
        panelLogos.add(lblLogo1);
        panelLogos.add(lblLogo2);
        panelLogos.add(lblLogo3);

        panelDetalles.add(lblRuc);
        panelDetalles.add(lblNombre);
        panelDetalles.add(lblComercial);
        panelDetalles.add(panelLogos);

        add(panelDetalles, BorderLayout.CENTER);

        // Acción del ComboBox
        comboEmpresas.addActionListener(e -> mostrarDetallesEmpresa((String) comboEmpresas.getSelectedItem()));
    }

    private void cargarEmpresas() {
        try (Connection conn = DatabaseConfig.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT CM_Nombre_Compania FROM compania")) {

            while (rs.next()) {
                comboEmpresas.addItem(rs.getString("CM_Nombre_Compania"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void mostrarDetallesEmpresa(String nombreEmpresa) {
        if (nombreEmpresa == null) return;

        try (Connection conn = DatabaseConfig.getConnection()) {
            String sql = "SELECT CM_Ruc, CM_Nombre_Compania, CM_Nombre_Comercial, CM_Logo1, CM_Logo2, CM_Logo3 " +
                         "FROM compania WHERE CM_Nombre_Compania = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, nombreEmpresa);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                lblRuc.setText("RUC: " + rs.getString("CM_Ruc"));
                lblNombre.setText("Razón Social: " + rs.getString("CM_Nombre_Compania"));
                lblComercial.setText("Nombre Comercial: " + rs.getString("CM_Nombre_Comercial"));

                mostrarImagenEnLabel(lblLogo1, rs.getBytes("CM_Logo1"));
                mostrarImagenEnLabel(lblLogo2, rs.getBytes("CM_Logo2"));
                mostrarImagenEnLabel(lblLogo3, rs.getBytes("CM_Logo3"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void mostrarImagenEnLabel(JLabel label, byte[] imgBytes) {
        if (imgBytes != null) {
            ImageIcon icon = new ImageIcon(imgBytes);
            Image img = icon.getImage();
            Image dimg = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            label.setIcon(new ImageIcon(dimg));
        } else {
            label.setIcon(null);
        }
    }

    public static void main(String[] args) {
        DatabaseConfig.init();
        SwingUtilities.invokeLater(() -> new EmpresaUI().setVisible(true));
    }
}

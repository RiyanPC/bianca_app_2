package main;

import Config.DatabaseConfig;
import java.awt.*;
import java.sql.*;
import javax.swing.*;

public class EmpresasUI extends JFrame {
    private JComboBox<String> comboEmpresas;
    private JLabel lblId, lblRuc, lblNombreCompania, lblNombreComercial;
    private JPanel panelLogos;

    public EmpresasUI() {
        setTitle("Empresas");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel izquierdo
        JPanel panelIzquierdo = new JPanel();
        panelIzquierdo.setLayout(new BorderLayout());
        panelIzquierdo.setPreferredSize(new Dimension(300, 400));
        add(panelIzquierdo, BorderLayout.WEST);

        // Panel datos
        JPanel panelDatos = new JPanel(new GridLayout(5, 1));
        lblId = new JLabel("ID: ");
        lblRuc = new JLabel("RUC: ");
        lblNombreCompania = new JLabel("Compañía: ");
        lblNombreComercial = new JLabel("Comercial: ");

        panelDatos.add(lblId);
        panelDatos.add(lblRuc);
        panelDatos.add(lblNombreCompania);
        panelDatos.add(lblNombreComercial);

        panelIzquierdo.add(panelDatos, BorderLayout.NORTH);

        // Panel de imagenes
        panelLogos = new JPanel();
        panelLogos.setLayout(new BoxLayout(panelLogos, BoxLayout.Y_AXIS));
        panelIzquierdo.add(panelLogos, BorderLayout.CENTER);

        // Panel superior
        JPanel panelSuperior = new JPanel();
        comboEmpresas = new JComboBox<>();
        cargarEmpresas();
        comboEmpresas.addActionListener(e -> mostrarDatosEmpresa((String) comboEmpresas.getSelectedItem()));
        panelSuperior.add(new JLabel("Seleccione empresa:"));
        panelSuperior.add(comboEmpresas);
        add(panelSuperior, BorderLayout.NORTH);

        // Panel derecho
        JPanel panelDerecho = new JPanel();
        panelDerecho.setBackground(Color.LIGHT_GRAY);
        add(panelDerecho, BorderLayout.CENTER);
    }

    private void cargarEmpresas() {
        try {
            Connection conn = DatabaseConfig.getConnection();
            String sql = "SELECT CM_Nombre_Compania FROM compania";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                comboEmpresas.addItem(rs.getString("CM_Nombre_Compania"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void mostrarDatosEmpresa(String nombre) {
        try {
            Connection conn = DatabaseConfig.getConnection();
            String sql = "SELECT CM_IdCompania, CM_Ruc, CM_Nombre_Compania, CM_Nombre_Comercial, " +
                         "CM_Logo1, CM_Logo2, CM_Logo3 FROM compania WHERE CM_Nombre_Compania = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, nombre);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                lblId.setText("ID: " + rs.getInt("CM_IdCompania"));
                lblRuc.setText("RUC: " + rs.getString("CM_Ruc"));
                lblNombreCompania.setText("Compañía: " + rs.getString("CM_Nombre_Compania"));
                lblNombreComercial.setText("Comercial: " + rs.getString("CM_Nombre_Comercial"));

                // Remover imagenes si ya habian antes:
                panelLogos.removeAll();

                // Añadir imágenes solo si existen
                addImagenSiExiste(panelLogos, rs.getBytes("CM_Logo1"));
                addImagenSiExiste(panelLogos, rs.getBytes("CM_Logo2"));
                addImagenSiExiste(panelLogos, rs.getBytes("CM_Logo3"));

                panelLogos.revalidate();
                panelLogos.repaint();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void addImagenSiExiste(JPanel panel, byte[] imgBytes) {
        if (imgBytes != null && imgBytes.length > 0) {
            JLabel label = new JLabel();
            ImageIcon icon = new ImageIcon(imgBytes);
            Image scaled = icon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
            label.setIcon(new ImageIcon(scaled));
            label.setAlignmentX(Component.CENTER_ALIGNMENT);
            panel.add(label);
        }
    }

    public static void main(String[] args) {
        DatabaseConfig.init();
        SwingUtilities.invokeLater(() -> new EmpresasUI().setVisible(true));
    }
}

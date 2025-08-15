package Forms;

import Config.DatabaseConfig;
import java.awt.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class PanelDatosRelacionados extends JPanel {

    private JTabbedPane tabs;

    public PanelDatosRelacionados() {
        setLayout(new BorderLayout());
        tabs = new JTabbedPane();
        add(tabs, BorderLayout.CENTER);
    }

    public void cargarDatos(int idEmpresa) {
        tabs.removeAll();

        tabs.addTab("Sucursales", crearTabla(
            "SELECT id_sucursal, direccion, telefono, ciudad FROM sucursal WHERE id_compania = ?",
            new String[]{"ID", "Dirección", "Teléfono", "Ciudad"},
            idEmpresa
        ));

        tabs.addTab("Empleados", crearTabla(
            "SELECT id_empleado, nombre, cargo, email FROM empleado WHERE id_compania = ?",
            new String[]{"ID", "Nombre", "Cargo", "Email"},
            idEmpresa
        ));

        tabs.addTab("Productos", crearTabla(
            "SELECT id_producto, codigo, nombre, precio FROM producto WHERE id_compania = ?",
            new String[]{"ID", "Código", "Nombre", "Precio"},
            idEmpresa
        ));

        tabs.addTab("Ventas", crearTabla(
            "SELECT id_venta, fecha, cliente, total FROM venta WHERE id_compania = ?",
            new String[]{"ID", "Fecha", "Cliente", "Total"},
            idEmpresa
        ));
    }

    private JScrollPane crearTabla(String query, String[] columnas, int idEmpresa) {
        DefaultTableModel model = new DefaultTableModel(columnas, 0);
        JTable table = new JTable(model);

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, idEmpresa);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Object[] fila = new Object[columnas.length];
                for (int i = 0; i < columnas.length; i++) {
                    fila[i] = rs.getObject(i + 1);
                }
                model.addRow(fila);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new JScrollPane(table);
    }
}

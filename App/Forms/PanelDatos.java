package Forms;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

public class PanelDatos extends JPanel {

    private JLabel lblId, lblRuc, lblNombreCompania, lblNombreComercial;

    public PanelDatos() {
        setLayout(new GridBagLayout());
        setBackground(new Color(245, 245, 245)); // gris suave
        setBorder(new TitledBorder(
                new EtchedBorder(EtchedBorder.LOWERED), 
                "Información de la Empresa",
                TitledBorder.LEFT, 
                TitledBorder.TOP, 
                new Font("Arial", Font.BOLD, 14), 
                Color.DARK_GRAY
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;

        lblId = crearEtiqueta("ID: ");
        lblRuc = crearEtiqueta("RUC: ");
        lblNombreCompania = crearEtiqueta("Nombre Compañía: ");
        lblNombreComercial = crearEtiqueta("Nombre Comercial: ");

        add(lblId, gbc);
        gbc.gridy++;
        add(lblRuc, gbc);
        gbc.gridy++;
        add(lblNombreCompania, gbc);
        gbc.gridy++;
        add(lblNombreComercial, gbc);
    }

    private JLabel crearEtiqueta(String texto) {
        JLabel label = new JLabel(texto);
        label.setFont(new Font("SansSerif", Font.PLAIN, 13));
        label.setForeground(Color.BLACK);
        return label;
    }

    public void setDatos(int id, String ruc, String nombreCompania, String nombreComercial) {
        lblId.setText("ID: " + id);
        lblRuc.setText("RUC: " + ruc);
        lblNombreCompania.setText("Nombre Compañía: " + nombreCompania);
        lblNombreComercial.setText("Nombre Comercial: " + nombreComercial);
    }
}

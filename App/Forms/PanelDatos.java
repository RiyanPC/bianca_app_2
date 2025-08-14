package Forms;

import java.awt.*;
import javax.swing.*;

public class PanelDatos extends JPanel {
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

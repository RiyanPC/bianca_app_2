package Forms;

import Controller.Empresa;
import java.awt.*;
import javax.swing.*;

public class EmpresaFormDialog extends JDialog {

    private JTextField txtRuc;
    private JTextField txtNombreCompania;
    private JTextField txtNombreComercial;
    private JButton btnGuardar;

    // La empresa que estamos editando
    private Empresa empresa;

    public EmpresaFormDialog(Frame parent, Empresa empresa) {
        super(parent, "Editar Empresa", true);
        this.empresa = empresa;

        setSize(400, 250);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());

        // --- Panel de campos ---
        JPanel panelCampos = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        txtRuc = new JTextField(empresa.ruc);
        txtNombreCompania   = new JTextField(empresa.nombreCompania);
        txtNombreComercial  = new JTextField(empresa.nombreComercial);

        gbc.gridx = 0; gbc.gridy = 0;
        panelCampos.add(new JLabel("RUC:"), gbc);
        gbc.gridx = 1;
        panelCampos.add(txtRuc, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        panelCampos.add(new JLabel("Nombre Compañía:"), gbc);
        gbc.gridx = 1;
        panelCampos.add(txtNombreCompania, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        panelCampos.add(new JLabel("Nombre Comercial:"), gbc);
        gbc.gridx = 1;
        panelCampos.add(txtNombreComercial, gbc);

        // --- Botón Guardar cambios ---
        btnGuardar = new JButton("Guardar cambios");
        JPanel panelBoton = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelBoton.add(btnGuardar);

        add(panelCampos, BorderLayout.CENTER);
        add(panelBoton, BorderLayout.SOUTH);

        // Acción del botón: actualizar directamente el objeto recibido
        btnGuardar.addActionListener(e -> {
            empresa.ruc              = txtRuc.getText();
            empresa.nombreCompania   = txtNombreCompania.getText();
            empresa.nombreComercial  = txtNombreComercial.getText();
            dispose();
        });
    }
}


package Forms;

import Controller.EmpresasController;
import Controller.Empresa;
import javax.swing.*;
import java.awt.*;

public class EmpresasUI extends JFrame {
    private PanelDatos panelDatos;
    private PanelLogos panelLogos;
    private JComboBox<String> comboEmpresas;
    private JCheckBox chkVerLogos;
    private EmpresasController controller;

    public EmpresasUI() {
        setTitle("Empresas");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        controller = new EmpresasController();

        JPanel panelIzquierdo = new JPanel(new BorderLayout());
        panelIzquierdo.setPreferredSize(new Dimension(300, 500));
        add(panelIzquierdo, BorderLayout.WEST);

        panelDatos = new PanelDatos();
        panelLogos = new PanelLogos();

        panelIzquierdo.add(panelDatos, BorderLayout.NORTH);
        panelIzquierdo.add(panelLogos, BorderLayout.CENTER);

        JPanel panelSuperior = new JPanel(new FlowLayout(FlowLayout.LEFT));
        comboEmpresas = new JComboBox<>();
        chkVerLogos = new JCheckBox("Ver logos", true);

        for (String empresa : controller.obtenerListaEmpresas()) {
            comboEmpresas.addItem(empresa);
        }

        comboEmpresas.addActionListener(e -> mostrarDatosEmpresa());
        chkVerLogos.addActionListener(e -> mostrarDatosEmpresa());

        panelSuperior.add(new JLabel("Seleccione empresa:"));
        panelSuperior.add(comboEmpresas);
        panelSuperior.add(chkVerLogos);
        add(panelSuperior, BorderLayout.NORTH);

        add(new JPanel(), BorderLayout.CENTER);
    }

    private void mostrarDatosEmpresa() {
        String seleccionada = (String) comboEmpresas.getSelectedItem();
        Empresa empresa = controller.obtenerDatosEmpresa(seleccionada);

        if (empresa != null) {
            panelDatos.setDatos(empresa.id, empresa.ruc, empresa.nombreCompania, empresa.nombreComercial);
            if (chkVerLogos.isSelected()) {
                panelLogos.mostrarLogos(empresa.logo1, empresa.logo2, empresa.logo3);
            } else {
                panelLogos.limpiar();
            }
        }
    }
}

package Forms;

import Controller.Empresa;
import Controller.EmpresasController;
import java.awt.*;
import javax.swing.*;

public class EmpresasUI extends JFrame {
    private PanelDatos panelDatos;
    private PanelLogos panelLogos;
    private PanelDatosRelacionados panelDerecho;
    private JComboBox<String> comboEmpresas;
    private JCheckBox chkVerLogos;
    private EmpresasController controller;

    public EmpresasUI() {
        controller = new EmpresasController();
        setTitle("Empresas");
        setSize(1000, 500); // más ancho para ver panel derecho
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        initUI();
        initEvents();
        cargarEmpresas();
    }

    private void initUI() {
        // Panel izquierdo (datos y logos)
        panelDatos = new PanelDatos();
        panelLogos = new PanelLogos();
        JPanel panelIzquierdo = new JPanel(new BorderLayout());
        panelIzquierdo.setPreferredSize(new Dimension(300, 500));
        panelIzquierdo.add(panelDatos, BorderLayout.NORTH);
        panelIzquierdo.add(panelLogos, BorderLayout.CENTER);
        add(panelIzquierdo, BorderLayout.WEST);

        // Panel superior (controles)
        comboEmpresas = new JComboBox<>();
        chkVerLogos = new JCheckBox("Ver logos", true);
        JPanel panelSuperior = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelSuperior.add(new JLabel("Seleccione empresa:"));
        panelSuperior.add(comboEmpresas);
        panelSuperior.add(chkVerLogos);
        add(panelSuperior, BorderLayout.NORTH);

        // Panel derecho (datos relacionados)
        panelDerecho = new PanelDatosRelacionados();
        add(panelDerecho, BorderLayout.CENTER);
    }

    private void initEvents() {
        comboEmpresas.addActionListener(e -> mostrarDatosEmpresa());
        chkVerLogos.addActionListener(e -> mostrarDatosEmpresa());
    }

    private void cargarEmpresas() {
        for (String empresa : controller.obtenerListaEmpresas()) {
            comboEmpresas.addItem(empresa);
        }
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

            // Cargar tablas relacionadas
            panelDerecho.cargarDatos(empresa.id);
        }
    }
}


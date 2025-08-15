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
    private JButton btnEditar;
    private JButton btnToggleLogos; // <-- agregado 3
    private EmpresasController controller;

    private JPanel panelIzquierdo;

    public EmpresasUI() {
        controller = new EmpresasController();
        setTitle("Empresas");
        setSize(1000, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        initUI();
        initEvents();
        cargarEmpresas();
    }

    private void initUI() {
        // Panel izquierdo
        panelDatos = new PanelDatos();
        panelLogos = new PanelLogos();
        panelIzquierdo = new JPanel(new BorderLayout());
        panelIzquierdo.setPreferredSize(new Dimension(300, 500));
        panelIzquierdo.add(panelDatos, BorderLayout.NORTH);

        add(panelIzquierdo, BorderLayout.WEST);

        // Panel superior
        comboEmpresas = new JComboBox<>();
        btnEditar = new JButton("Editar");
        btnEditar.setEnabled(false);

        btnToggleLogos = new JButton(
            "Logos", UIManager.getIcon("Tree.collapsedIcon")); // icono inicial

        JPanel panelSuperior = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelSuperior.add(new JLabel("Seleccione empresa:"));
        panelSuperior.add(comboEmpresas);
        panelSuperior.add(btnEditar);
        panelSuperior.add(btnToggleLogos);
        add(panelSuperior, BorderLayout.NORTH);

        // Panel derecho > datos relacionados
        panelDerecho = new PanelDatosRelacionados();
        add(panelDerecho, BorderLayout.CENTER);
    }

    private void initEvents() {
        comboEmpresas.addActionListener(e -> {
            mostrarDatosEmpresa();
            btnEditar.setEnabled(comboEmpresas.getSelectedItem() != null);
        });

        btnEditar.addActionListener(e -> {
            String seleccionada = (String) comboEmpresas.getSelectedItem();
            if (seleccionada != null) {
                Empresa empresa = controller.obtenerDatosEmpresa(seleccionada);
                if (empresa != null) {
                    EmpresaFormDialog dialog = new EmpresaFormDialog(this, empresa);
                    dialog.setVisible(true);
                    panelDatos.setDatos(empresa.id, empresa.ruc, empresa.nombreCompania, empresa.nombreComercial);
                }
            }
        });

        // Botón para mostrar/ocultar los logos
        btnToggleLogos.addActionListener(e -> {
            if (isPanelLogosVisible()) {
                ocultarPanelLogos();
            } else {
                mostrarPanelLogos();
            }
        });
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
            panelDerecho.cargarDatos(empresa.id);

            if (isPanelLogosVisible()) {
                panelLogos.mostrarLogos(empresa.logo1, empresa.logo2, empresa.logo3);
            }
        }
    }

    // --- Mostrar / ocultar panel logos ---

    private boolean isPanelLogosVisible() {
        for (Component c : panelIzquierdo.getComponents()) {
            if (c == panelLogos) {
                return true;
            }
        }
        return false;
    }

    private void mostrarPanelLogos() {
        panelIzquierdo.add(panelLogos, BorderLayout.SOUTH);
        panelIzquierdo.revalidate();
        panelIzquierdo.repaint();
        btnToggleLogos.setIcon(UIManager.getIcon("Tree.expandedIcon")); // icono
    }

    private void ocultarPanelLogos() {
        panelIzquierdo.remove(panelLogos);
        panelIzquierdo.revalidate();
        panelIzquierdo.repaint();
        btnToggleLogos.setIcon(UIManager.getIcon("Tree.collapsedIcon")); // icono
    }
}

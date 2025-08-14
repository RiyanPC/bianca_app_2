package main;

import Config.DatabaseConfig;
import Forms.EmpresasUI;
import javax.swing.SwingUtilities;

public class MainApp {
    public static void main(String[] args) {
        //inicializar xd
        DatabaseConfig.init();
        SwingUtilities.invokeLater(() -> new EmpresasUI().setVisible(true));
    }
}

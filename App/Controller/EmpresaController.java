package Controller;

import Config.DatabaseConfig;
import Forms.EmpresaForm;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmpresaController {
    public static List<EmpresaForm> listarEmpresas() {
        List<EmpresaForm> empresas = new ArrayList<>();
        try (Connection conn = DatabaseConfig.getConnection()) {
            String sql = "SELECT CM_IdCompania, CM_Ruc, CM_Nombre_Compania, CM_Nombre_Comercial, CM_Host, CM_Database, CM_User, CM_Password FROM empresas";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                EmpresaForm e = new EmpresaForm();
                e.setId(rs.getInt("CM_IdCompania"));
                e.setRuc(rs.getString("CM_Ruc"));
                e.setNombreCompania(rs.getString("CM_Nombre_Compania"));
                e.setNombreComercial(rs.getString("CM_Nombre_Comercial"));
                e.setHost(rs.getString("CM_Host"));
                e.setDatabase(rs.getString("CM_Database"));
                e.setUser(rs.getString("CM_User"));
                e.setPassword(rs.getString("CM_Password"));
                empresas.add(e);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return empresas;
    }
}

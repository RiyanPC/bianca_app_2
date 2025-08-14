package Controller;

import Config.DatabaseConfig;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmpresasController {

        public List<String> obtenerListaEmpresas() {
        List<String> empresas = new ArrayList<>();
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT CM_Nombre_Compania FROM compania");
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                empresas.add(rs.getString("CM_Nombre_Compania"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return empresas;
    }

    public Empresa obtenerDatosEmpresa(String nombreEmpresa) {
        if (nombreEmpresa == null) return null;

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(
                     "SELECT CM_IdCompania, CM_Ruc, CM_Nombre_Compania, CM_Nombre_Comercial, " +
                             "CM_Logo1, CM_Logo2, CM_Logo3 " +
                             "FROM compania WHERE CM_Nombre_Compania = ?")) {

            ps.setString(1, nombreEmpresa);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Empresa(
                        rs.getInt("CM_IdCompania"),
                        rs.getString("CM_Ruc"),
                        rs.getString("CM_Nombre_Compania"),
                        rs.getString("CM_Nombre_Comercial"),
                        rs.getBytes("CM_Logo1"),
                        rs.getBytes("CM_Logo2"),
                        rs.getBytes("CM_Logo3")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

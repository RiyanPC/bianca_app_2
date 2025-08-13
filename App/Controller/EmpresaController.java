package Controller;

import Config.DatabaseConfig;
import Forms.EmpresaForm;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmpresaController {

    public List<EmpresaForm> obtenerEmpresas() {
        List<EmpresaForm> lista = new ArrayList<>();

        String sql = "SELECT CM_IdCompania, CM_Ruc, CM_Nombre_Compania, CM_Nombre_Comercial, "
                   + "CM_Logo1, CM_Logo2, CM_Logo3 "
                   + "FROM compania";

        try (Connection con = DatabaseConfig.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                EmpresaForm emp = new EmpresaForm();
                emp.setId(rs.getInt("CM_IdCompania"));
                emp.setRuc(rs.getString("CM_Ruc"));
                emp.setNombreCompania(rs.getString("CM_Nombre_Compania"));
                emp.setNombreComercial(rs.getString("CM_Nombre_Comercial"));

                // Guardamos los logos como bytes
                emp.setLogo1(rs.getBytes("CM_Logo1"));
                emp.setLogo2(rs.getBytes("CM_Logo2"));
                emp.setLogo3(rs.getBytes("CM_Logo3"));

                lista.add(emp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}

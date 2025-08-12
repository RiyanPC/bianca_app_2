package Forms;

import java.awt.Image;
import javax.swing.*;

public class EmpresaForm {
    private int id;
    private String ruc;
    private String nombreCompania;
    private String nombreComercial;
    private String host;
    private String database;
    private String user;
    private String password;
    private byte[] logoBytes; // puede ser NULL

    // getters / setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getRuc() { return ruc; }
    public void setRuc(String ruc) { this.ruc = ruc; }
    public String getNombreCompania() { return nombreCompania; }
    public void setNombreCompania(String nombreCompania) { this.nombreCompania = nombreCompania; }
    public String getNombreComercial() { return nombreComercial; }
    public void setNombreComercial(String nombreComercial) { this.nombreComercial = nombreComercial; }
    public String getHost() { return host; }
    public void setHost(String host) { this.host = host; }
    public String getDatabase() { return database; }
    public void setDatabase(String database) { this.database = database; }
    public String getUser() { return user; }
    public void setUser(String user) { this.user = user; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public byte[] getLogoBytes() { return logoBytes; }
    public void setLogoBytes(byte[] logoBytes) { this.logoBytes = logoBytes; }

    // toString ayuda al JComboBox (muestra nombre comercial)
    @Override
    public String toString() {
        return nombreComercial != null ? nombreComercial : ("Empresa " + id);
    }

    // Convierte bytes a ImageIcon escalada
    public ImageIcon getLogoIcon(int width, int height) {
        if (logoBytes == null) return null;
        ImageIcon icon = new ImageIcon(logoBytes);
        Image img = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(img);
    }
}

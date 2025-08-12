package Forms;

public class EmpresaForm {
    private int id;
    private String ruc;
    private String nombreCompania;
    private String nombreComercial;
    private String host;
    private String database;
    private String user;
    private String password;

    // Getters y setters
    public int getId() {
        return id;
    }   
    public void setId(int id) {
        this.id = id;
    }
    public String getRuc() {
        return ruc;
    }
    public void setRuc(String ruc) {
        this.ruc = ruc;
    }
    public String getNombreCompania() {
        return nombreCompania;
    }
    public void setNombreCompania(String nombreCompania) {
        this.nombreCompania = nombreCompania;
    }
    public String getNombreComercial() {
        return nombreComercial;
    }
    public void setNombreComercial(String nombreComercial) {
        this.nombreComercial = nombreComercial;
    }
    public String getHost() {
        return host;
    }
    public void setHost(String host) {
        this.host = host;
    }
    public String getDatabase() {
        return database;
    }
    public void setDatabase(String database) {
        this.database = database;
    }
    public String getUser() {
        return user;
    }
    public void setUser(String user) {
        this.user = user;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}

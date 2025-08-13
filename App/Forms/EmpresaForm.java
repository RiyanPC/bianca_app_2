package Forms;

public class EmpresaForm {
    private int id;
    private String ruc;
    private String nombreCompania;
    private String nombreComercial;
    private byte[] logo1;
    private byte[] logo2;
    private byte[] logo3;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getRuc() { return ruc; }
    public void setRuc(String ruc) { this.ruc = ruc; }

    public String getNombreCompania() { return nombreCompania; }
    public void setNombreCompania(String nombreCompania) { this.nombreCompania = nombreCompania; }

    public String getNombreComercial() { return nombreComercial; }
    public void setNombreComercial(String nombreComercial) { this.nombreComercial = nombreComercial; }

    public byte[] getLogo1() { return logo1; }
    public void setLogo1(byte[] logo1) { this.logo1 = logo1; }

    public byte[] getLogo2() { return logo2; }
    public void setLogo2(byte[] logo2) { this.logo2 = logo2; }

    public byte[] getLogo3() { return logo3; }
    public void setLogo3(byte[] logo3) { this.logo3 = logo3; }

    // Para que comboBox muestre el nombre comercial
    @Override
    public String toString() {
        return nombreComercial;
    }
}

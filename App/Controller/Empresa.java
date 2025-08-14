package Controller;

public class Empresa {
    public int id;
    public String ruc;
    public String nombreCompania;
    public String nombreComercial;
    public byte[] logo1;
    public byte[] logo2;
    public byte[] logo3;

    public Empresa(int id, String ruc, String nombreCompania, String nombreComercial,
                   byte[] logo1, byte[] logo2, byte[] logo3) {
        this.id = id;
        this.ruc = ruc;
        this.nombreCompania = nombreCompania;
        this.nombreComercial = nombreComercial;
        this.logo1 = logo1;
        this.logo2 = logo2;
        this.logo3 = logo3;
    }
}


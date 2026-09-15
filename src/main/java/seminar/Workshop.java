package seminar;

public class Workshop extends Acara {
    private String tools;

    public Workshop(int id, String namaWorkshop, String pemateri, String tanggal, int kuota, String tools) {
        super(id, namaWorkshop, pemateri, tanggal, kuota);
        this.tools = tools;
    }

    public String getTools() {
        return tools;
    }

    public void setTools(String tools) {
        this.tools = tools;
    }

    @Override
    public String getJenisAcara() {
        return "Workshop";
    }

    @Override
    public String toString() {
        return super.toString() + " | Tools: " + tools;
    }
}
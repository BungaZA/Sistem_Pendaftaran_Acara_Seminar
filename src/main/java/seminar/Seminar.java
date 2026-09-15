package seminar;

public class Seminar extends Acara {

    public Seminar(int id, String namaSeminar, String pemateri, String tanggal, int kuota) {
        super(id, namaSeminar, pemateri, tanggal, kuota);
    }

    public String getNamaSeminar() {
        return namaAcara;
    }

    public void setNamaSeminar(String namaSeminar) {
        this.namaAcara = namaSeminar;
    }

    @Override
    public String getJenisAcara() {
        return "Seminar";
    }
}
package seminar;

public class Acara {
    protected int id;
    protected String namaAcara;
    protected String pemateri;
    protected String tanggal;
    protected int kuota;
    protected int pendaftar;

    public Acara(int id, String namaAcara, String pemateri, String tanggal, int kuota) {
        this.id = id;
        this.namaAcara = namaAcara;
        this.pemateri = pemateri;
        this.tanggal = tanggal;
        this.kuota = kuota;
        this.pendaftar = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNamaAcara() {
        return namaAcara;
    }

    public void setNamaAcara(String namaAcara) {
        this.namaAcara = namaAcara;
    }

    public String getPemateri() {
        return pemateri;
    }

    public void setPemateri(String pemateri) {
        this.pemateri = pemateri;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public int getKuota() {
        return kuota;
    }

    public void setKuota(int kuota) {
        this.kuota = kuota;
    }

    public int getPendaftar() {
        return pendaftar;
    }

    public void setPendaftar(int pendaftar) {
        this.pendaftar = pendaftar;
    }

    public boolean isKuotaPenuh() {
        return pendaftar >= kuota;
    }

    public boolean tambahPendaftar() {
        if (!isKuotaPenuh()) {
            pendaftar++;
            return true;
        }
        return false;
    }

    public String getJenisAcara() {
        return "Acara";
    }

    @Override
    public String toString() {
        return "ID: " + id +
               " | Jenis: " + getJenisAcara() +
               " | Nama: " + namaAcara +
               " | Pemateri: " + pemateri +
               " | Tanggal: " + tanggal +
               " | Kuota: " + kuota +
               " | Terdaftar: " + pendaftar +
               " | Sisa: " + (kuota - pendaftar);
    }
}

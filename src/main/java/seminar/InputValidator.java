package seminar;

public class InputValidator {
    
    public static boolean validasiNama(String nama) {
        if (nama == null || nama.trim().isEmpty()) {
            return false;
        }
        return nama.matches("^[a-zA-Z\\s.,]+$");
    }

    public static boolean validasiTanggal(String tanggal) {
        if (tanggal == null || tanggal.isEmpty()) {
            return false;
        }
        return tanggal.matches("^\\d{4}-\\d{2}-\\d{2}$");
    }

    public static boolean validasiKuota(int kuota) {
        return kuota > 0;
    }

    public static boolean validasiId(int id) {
        return id > 0;
    }

    public static boolean validasiPilihanMenu(String pilihan) {
        if (pilihan == null || pilihan.isEmpty()) {
            return false;
        }
        return pilihan.matches("^[1-7]$");
    }
}

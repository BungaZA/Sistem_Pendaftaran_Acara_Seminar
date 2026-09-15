package seminar;

import java.util.ArrayList;
import java.util.Scanner;

public class SeminarCRUD {
    private ArrayList<Acara> daftarAcara;
    private Scanner scanner;

    public SeminarCRUD() {
        daftarAcara = new ArrayList<>();
        scanner = new Scanner(System.in);
        tambahAcaraOtomatis(new Seminar(0, "Inovasi AI dalam Pendidikan", "Dr. Budi Santoso", "2026-10-15", 50));
        tambahAcaraOtomatis(new Seminar(0, "Cyber Security 2026", "Prof. Dewi Lestari", "2026-10-20", 30));
        tambahAcaraOtomatis(new Seminar(0, "Digital Marketing Strategy", "Andi Wijaya, S.Kom", "2026-10-25", 40));
    }

    public void tambahSeminar() {
        System.out.println("\n=== TAMBAH SEMINAR BARU ===");
        System.out.print("Masukkan Nama Seminar: ");
        String nama = scanner.nextLine();
        System.out.print("Masukkan Nama Pemateri: ");
        String pemateri = scanner.nextLine();
        System.out.print("Masukkan Tanggal (YYYY-MM-DD): ");
        String tanggal = scanner.nextLine();
        System.out.print("Masukkan Kuota Peserta: ");
        int kuota = scanner.nextInt();
        scanner.nextLine();

        if (InputValidator.validasiNama(nama) &&
            InputValidator.validasiNama(pemateri) &&
            InputValidator.validasiTanggal(tanggal) &&
            InputValidator.validasiKuota(kuota)) {

            int id = daftarAcara.size() + 1;
            Seminar seminar = new Seminar(id, nama, pemateri, tanggal, kuota);
            daftarAcara.add(seminar);
            System.out.println("\n✓ Seminar berhasil ditambahkan!");
        } else {
            System.out.println("\n✗ Data tidak valid! Periksa kembali input Anda.");
        }
    }

    public void tampilkanSeminar() {
        System.out.println("\n=== DAFTAR ACARA ===");
        if (daftarAcara.isEmpty()) {
            System.out.println("Belum ada acara yang terdaftar.");
            return;
        }

        System.out.println("Total Acara: " + daftarAcara.size() + "\n");
        for (Acara a : daftarAcara) {
            System.out.println(a); // polymorphism via toString()
            System.out.println("---------------------------");
        }
    }

    public Acara cariAcaraById(int id) {
        for (Acara a : daftarAcara) {
            if (a.getId() == id) {
                return a;
            }
        }
        return null;
    }

    public void cariSeminar() {
        System.out.println("\n=== CARI ACARA ===");
        System.out.print("Masukkan ID Acara: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Acara acara = cariAcaraById(id);
        if (acara != null) {
            System.out.println("\nDetail Acara:");
            System.out.println(acara);
        } else {
            System.out.println("\n✗ Acara dengan ID " + id + " tidak ditemukan.");
        }
    }

    public void updateSeminar() {
        System.out.println("\n=== UPDATE ACARA ===");
        tampilkanSeminar();
        System.out.print("\nMasukkan ID Acara yang akan diupdate: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Acara acara = cariAcaraById(id);
        if (acara == null) {
            System.out.println("\n✗ Acara dengan ID " + id + " tidak ditemukan.");
            return;
        }

        System.out.println("\nData saat ini:");
        System.out.println(acara);
        System.out.println("\nMasukkan data baru (kosongkan jika tidak ingin mengubah):");

        System.out.print("Nama Acara [" + acara.getNamaAcara() + "]: ");
        String nama = scanner.nextLine();
        if (!nama.isEmpty() && InputValidator.validasiNama(nama)) {
            acara.setNamaAcara(nama);
        }

        System.out.print("Nama Pemateri [" + acara.getPemateri() + "]: ");
        String pemateri = scanner.nextLine();
        if (!pemateri.isEmpty() && InputValidator.validasiNama(pemateri)) {
            acara.setPemateri(pemateri);
        }

        System.out.print("Tanggal [" + acara.getTanggal() + "]: ");
        String tanggal = scanner.nextLine();
        if (!tanggal.isEmpty() && InputValidator.validasiTanggal(tanggal)) {
            acara.setTanggal(tanggal);
        }

        System.out.print("Kuota [" + acara.getKuota() + "]: ");
        String kuotaStr = scanner.nextLine();
        if (!kuotaStr.isEmpty()) {
            try {
                int kuota = Integer.parseInt(kuotaStr);
                if (InputValidator.validasiKuota(kuota)) {
                    acara.setKuota(kuota);
                }
            } catch (NumberFormatException e) {
                System.out.println("✗ Format kuota tidak valid!");
            }
        }

        System.out.println("\n✓ Acara berhasil diupdate!");
    }

    public void hapusSeminar() {
        System.out.println("\n=== HAPUS ACARA ===");
        tampilkanSeminar();
        System.out.print("\nMasukkan ID Acara yang akan dihapus: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Acara acara = cariAcaraById(id);
        if (acara == null) {
            System.out.println("\n✗ Acara dengan ID " + id + " tidak ditemukan.");
            return;
        }

        System.out.print("Apakah Anda yakin ingin menghapus acara '" +
                         acara.getNamaAcara() + "'? (y/n): ");
        String konfirmasi = scanner.nextLine();

        if (konfirmasi.equalsIgnoreCase("y")) {
            daftarAcara.remove(acara);
            System.out.println("\n✓ Acara berhasil dihapus!");
        } else {
            System.out.println("\nPenghapusan dibatalkan.");
        }
    }

    public void daftarPeserta() {
        System.out.println("\n=== PENDAFTARAN PESERTA ACARA ===");
        tampilkanSeminar();
        System.out.print("\nMasukkan ID Acara yang ingin diikuti: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Acara acara = cariAcaraById(id);
        if (acara == null) {
            System.out.println("\n✗ Acara dengan ID " + id + " tidak ditemukan.");
            return;
        }

        if (acara.isKuotaPenuh()) {
            System.out.println("\n✗ Maaf, kuota acara '" + acara.getNamaAcara() + "' sudah penuh!");
            System.out.println("Pendaftar saat ini: " + acara.getPendaftar() + "/" + acara.getKuota());
            return;
        }

        System.out.print("Masukkan Nama Peserta: ");
        String namaPeserta = scanner.nextLine();

        if (!InputValidator.validasiNama(namaPeserta)) {
            System.out.println("\n✗ Nama peserta tidak valid!");
            return;
        }

        acara.tambahPendaftar();
        System.out.println("\n✓ Pendaftaran berhasil!");
        System.out.println("Nama Peserta: " + namaPeserta);
        System.out.println("Acara: " + acara.getNamaAcara());
        System.out.println("Jenis: " + acara.getJenisAcara());
        System.out.println("Sisa Kuota: " + (acara.getKuota() - acara.getPendaftar()));
    }

    private void tambahAcaraOtomatis(Acara acara) {
        acara.setId(daftarAcara.size() + 1);
        daftarAcara.add(acara);
    }
}
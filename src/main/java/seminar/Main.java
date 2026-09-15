package seminar;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SeminarCRUD crud = new SeminarCRUD();
        String pilihan;

        System.out.println("SISTEM PENDAFTARAN ACARA SEMINAR");
        System.out.println("MANAJEMEN SEMINAR 2026");

        do {
            System.out.println("\nMENU UTAMA");
            System.out.println("1. Tambah Seminar");
            System.out.println("2. Lihat Daftar Seminar");
            System.out.println("3. Cari Seminar");
            System.out.println("4. Update Seminar");
            System.out.println("5. Hapus Seminar");
            System.out.println("6. Daftar Peserta Seminar");
            System.out.println("7. Keluar");
            System.out.print("Pilih menu (1-7): ");

            pilihan = scanner.nextLine();

            if (pilihan.equals("1")) {
                crud.tambahSeminar();
            } else if (pilihan.equals("2")) {
                crud.tampilkanSeminar();
            } else if (pilihan.equals("3")) {
                crud.cariSeminar();
            } else if (pilihan.equals("4")) {
                crud.updateSeminar();
            } else if (pilihan.equals("5")) {
                crud.hapusSeminar();
            } else if (pilihan.equals("6")) {
                crud.daftarPeserta();
            } else if (pilihan.equals("7")) {
                System.out.println("Terima kasih telah menggunakan");
                System.out.println("Sistem Pendaftaran Acara Seminar");
            } else {
                System.out.println("\n✗ Pilihan tidak valid! Silakan pilih menu 1-7.");
            }

        } while (!pilihan.equals("7"));

        scanner.close();
    }
}
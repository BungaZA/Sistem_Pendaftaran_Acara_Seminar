# Sistem Pendaftaran Acara Seminar

## 📋 Identitas Mahasiswa

| Keterangan | Detail |
|------------|--------|
| **Nama**   | [Nama Mahasiswa] |
| **NIM**    | [NIM Mahasiswa] |
| **Kelas**  | [Kelas] |
| **Mata Kuliah** | Pemrograman Berorientasi Objek |

> **Catatan:** Silakan ganti `[Nama Mahasiswa]`, `[NIM Mahasiswa]`, dan `[Kelas]` dengan data Anda yang sebenarnya.

---

## 📖 Penjelasan Studi Kasus

### Latar Belakang
Program **Sistem Pendaftaran Acara Seminar** merupakan aplikasi berbasis *Command Line Interface* (CLI) yang dibangun menggunakan bahasa pemrograman **Java**. Aplikasi ini dirancang untuk membantu pengelolaan data acara seminar, mulai dari penambahan, pencarian, pembaruan, penghapusan, hingga pendaftaran peserta.

### Fitur Utama
1. **Tambah Seminar** — Menambahkan data seminar baru (nama, pemateri, tanggal, kuota).
2. **Lihat Daftar Seminar** — Menampilkan seluruh data acara yang terdaftar.
3. **Cari Seminar** — Mencari data acara berdasarkan ID.
4. **Update Seminar** — Memperbarui data acara yang sudah ada.
5. **Hapus Seminar** — Menghapus data acara dari daftar.
6. **Daftar Peserta** — Mendaftarkan peserta ke acara tertentu dengan validasi kuota.
7. **Keluar** — Mengakhiri program.

### Konsep OOP yang Diterapkan
- **Encapsulation** — Field `private`/`protected` dengan getter & setter.
- **Inheritance** — Class `Seminar` dan `Workshop` mewarisi class `Acara`.
- **Polymorphism** — Method `getJenisAcara()` dan `toString()` di-override.
- **Abstraction** — Class `Acara` sebagai blueprint umum.
- **Collection** — Penggunaan `ArrayList<Acara>` untuk menyimpan data.

---

## 🏗️ Diagram Kelas / Hierarki Class

### Diagram Hierarki (ASCII)

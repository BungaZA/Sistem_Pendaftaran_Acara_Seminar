# Sistem Pendaftaran Acara Seminar (SeminarCRUD)

---

## 1. Identitas Mahasiswa

| Keterangan | Data |
|------------|------|
| **Nama** | *[Isi Nama Lengkap Anda]* |
| **NIM** | *[Isi NIM Anda]* |
| **Mata Kuliah** | Pemrograman Berorientasi Objek |
| **Tahun** | 2026 |

> ⚠️ **Catatan:** Silakan ganti bagian `[Isi Nama Lengkap Anda]` dan `[Isi NIM Anda]` sesuai data Anda sebelum melakukan commit/push ke repository.

---

## 2. Penjelasan Studi Kasus

**Sistem Pendaftaran Acara Seminar** adalah aplikasi berbasis *console* (CLI) yang dibangun menggunakan bahasa pemrograman **Java**. Studi kasus ini dipilih karena merepresentasikan permasalahan nyata dalam pengelolaan acara, khususnya seminar, yang membutuhkan pengelolaan data secara terstruktur.

### Fitur Utama

Aplikasi ini menerapkan operasi **CRUD (Create, Read, Update, Delete)** serta fitur pendaftaran peserta:

1. **Create** — Menambah data seminar baru (nama, pemateri, tanggal, kuota).
2. **Read** — Menampilkan seluruh daftar seminar yang terdaftar.
3. **Search** — Mencari seminar berdasarkan ID.
4. **Update** — Mengubah data seminar yang sudah ada.
5. **Delete** — Menghapus data seminar dengan konfirmasi.
6. **Daftar Peserta** — Mendaftarkan peserta ke seminar tertentu dengan validasi kuota.

### Validasi Input (InputValidator)

- `validasiNama()` — hanya huruf, spasi, titik, dan koma.
- `validasiTanggal()` — format `YYYY-MM-DD`.
- `validasiKuota()` — harus lebih dari 0.
- `validasiId()` — harus lebih dari 0.
- `validasiPilihanMenu()` — hanya angka 1–7.

### Struktur Data

Data seminar disimpan dalam `ArrayList<Seminar>` yang bersifat *in-memory* (belum menggunakan database). Saat program dijalankan, terdapat **3 data seminar otomatis** sebagai data awal (seed data).

---

## 3. Diagram Kelas / Hierarki Class

Berikut diagram kelas sederhana dari aplikasi:

```
+---------------------+         +----------------------+
|        Main         |         |    InputValidator    |
+---------------------+         +----------------------+
| + main(String[]):void|        | + validasiNama()     |
+----------+----------+         | + validasiTanggal()  |
           |                    | + validasiKuota()    |
           | uses               | + validasiId()       |
           v                    | + validasiPilihanMenu()|
+---------------------+         +----------------------+
|     SeminarCRUD     |
+---------------------+
| - daftarSeminar: ArrayList<Seminar>
| - scanner: Scanner
+---------------------+
| + tambahSeminar(): void
| + tampilkanSeminar(): void
| + cariSeminarById(int): Seminar
| + cariSeminar(): void
| + updateSeminar(): void
| + hapusSeminar(): void
| + daftarPeserta(): void
| - tambahSeminarOtomatis(...): void
+----------+----------+
           |
           | has-a (agregasi)
           v
+---------------------+
|       Seminar       |
+---------------------+
| - id: int
| - namaSeminar: String
| - pemateri: String
| - tanggal: String
| - kuota: int
| - pendaftar: int
+---------------------+
| + getId(): int
| + getNamaSeminar(): String
| + setNamaSeminar(String): void
| + getPemateri(): String
| + setPemateri(String): void
| + getTanggal(): String
| + setTanggal(String): void
| + getKuota(): int
| + setKuota(int): void
| + getPendaftar(): int
| + setPendaftar(int): void
| + isKuotaPenuh(): boolean
| + tambahPendaftar(): boolean
| + toString(): String
+---------------------+
```

### Penjelasan Hubungan Antar Class

| Hubungan | Penjelasan |
|----------|------------|
| `Main` → `SeminarCRUD` | **Asosiasi (uses)** — `Main` membuat objek `SeminarCRUD` dan memanggil method-nya berdasarkan pilihan menu. |
| `SeminarCRUD` → `Seminar` | **Agregasi (has-a)** — `SeminarCRUD` memiliki `ArrayList<Seminar>` sebagai wadah data. |
| `SeminarCRUD` → `InputValidator` | **Dependensi** — `SeminarCRUD` menggunakan method statis dari `InputValidator` untuk memvalidasi input. |
| `Main` → `InputValidator` | **Dependensi tidak langsung** — melalui `SeminarCRUD`. |

---

## 4. Penjelasan Bagian Kode yang Menerapkan Inheritance

> ⚠️ **Catatan Penting:** Pada kode yang dilampirkan, **belum terdapat penerapan inheritance (pewarisan)** secara eksplisit. Semua class (`Main`, `SeminarCRUD`, `Seminar`, `InputValidator`) berdiri sendiri dan hanya berhubungan melalui **asosiasi, agregasi, dan dependensi**, bukan pewarisan.

### Kondisi Saat Ini (Tanpa Inheritance)

```java
public class Main { ... }          // tidak extends apa pun
public class SeminarCRUD { ... }   // tidak extends apa pun
public class Seminar { ... }       // tidak extends apa pun
public class InputValidator { ... }// tidak extends apa pun
```

Semua class hanya mewarisi secara implisit dari `java.lang.Object`.

### Rekomendasi Penerapan Inheritance

Agar memenuhi kriteria "menerapkan inheritance", kode dapat direfaktor dengan menambahkan **superclass** abstrak, misalnya `Orang` (Person), lalu `Pemateri` dan `Peserta` mewarisinya:

```java
// Superclass
public abstract class Orang {
    protected String nama;

    public Orang(String nama) {
        this.nama = nama;
    }

    public String getNama() { return nama; }
    public void setNama(String nama) { this.nama = nama; }

    public abstract String getPeran();
}

// Subclass 1
public class Pemateri extends Orang {
    public Pemateri(String nama) { super(nama); }

    @Override
    public String getPeran() { return "Pemateri"; }
}

// Subclass 2
public class Peserta extends Orang {
    public Peserta(String nama) { super(nama); }

    @Override
    public String getPeran() { return "Peserta"; }
}
```

Dengan refaktor ini, `Pemateri` dan `Peserta` **mewarisi** atribut `nama` serta method `getNama()` dan `setNama()` dari `Orang`, sekaligus meng-*override* method abstrak `getPeran()`. Inilah bentuk nyata **inheritance** dalam OOP.

### Contoh Penerapan pada `Seminar`

```java
public class Seminar {
    private Pemateri pemateri;   // menggunakan objek Pemateri (subclass Orang)
    // ...
}
```

---

## 5. Tangkapan Layar (Screenshot) Program Saat Berjalan

> 📸 **Petunjuk:** Jalankan program `Main.java`, lalu ambil screenshot pada setiap menu berikut, dan simpan file-nya di folder `screenshots/` pada repository Anda.

### Contoh Struktur Folder Repository

```
seminar-crud/
├── src/
│   └── seminar/
│       ├── Main.java
│       ├── SeminarCRUD.java
│       ├── Seminar.java
│       └── InputValidator.java
├── screenshots/
│   ├── 01-menu-utama.png
│   ├── 02-tambah-seminar.png
│   ├── 03-daftar-seminar.png
│   ├── 04-cari-seminar.png
│   ├── 05-update-seminar.png
│   ├── 06-hapus-seminar.png
│   └── 07-daftar-peserta.png
└── README.md
```

### Daftar Screenshot yang Perlu Diambil

| No | Nama File | Skenario |
|----|-----------|----------|
| 1 | `01-menu-utama.png` | Tampilan menu utama (pilihan 1–7) |
| 2 | `02-tambah-seminar.png` | Proses menambah seminar baru + notifikasi berhasil |
| 3 | `03-daftar-seminar.png` | Menampilkan seluruh daftar seminar |
| 4 | `04-cari-seminar.png` | Mencari seminar berdasarkan ID (ditemukan & tidak ditemukan) |
| 5 | `05-update-seminar.png` | Mengubah data seminar |
| 6 | `06-hapus-seminar.png` | Menghapus seminar dengan konfirmasi y/n |
| 7 | `07-daftar-peserta.png` | Mendaftarkan peserta + cek sisa kuota |

### Cara Menyisipkan Screenshot ke README

```markdown
### Tampilan Menu Utama
![Menu Utama](screenshots/01-menu-utama.png)

### Proses Tambah Seminar
![Tambah Seminar](screenshots/02-tambah-seminar.png)

### Daftar Seminar
![Daftar Seminar](screenshots/03-daftar-seminar.png)
```

---

## 6. Cara Menjalankan Program

### Prasyarat

- **JDK 8** atau lebih baru
- **IDE**: IntelliJ IDEA / NetBeans / Eclipse / VS Code
- **Terminal** (opsional)

### Langkah Kompilasi & Menjalankan

```bash
# 1. Clone repository
git clone https://github.com/username/seminar-crud.git
cd seminar-crud

# 2. Kompilasi
javac -d bin src/seminar/*.java

# 3. Jalankan
java -cp bin seminar.Main
```

### Contoh Alur Penggunaan

1. Pilih menu `2` untuk melihat 3 seminar bawaan.
2. Pilih menu `1` untuk menambah seminar baru.
3. Pilih menu `6` untuk mendaftarkan peserta ke seminar.
4. Pilih menu `7` untuk keluar.

---

## 7. Kesimpulan

Aplikasi **Sistem Pendaftaran Acara Seminar** ini mendemonstrasikan penerapan konsep **OOP** dalam Java, meliputi:

- **Enkapsulasi** — atribut `private` dengan getter/setter pada class `Seminar`.
- **Abstraksi** — pemisahan logika validasi ke class `InputValidator`.
- **Modularitas** — pemisahan tanggung jawab antar class (`Main`, `SeminarCRUD`, `Seminar`, `InputValidator`).
- **Inheritance** — *[diisi setelah refaktor]* dengan superclass `Orang` dan subclass `Pemateri` & `Peserta`.

---

## 8. Lisensi

Proyek ini dibuat untuk keperluan **tugas akademik**. Bebas digunakan sebagai referensi pembelajaran dengan mencantumkan sumber.

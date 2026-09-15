# Sistem Pendaftaran Acara Seminar

## Identitas Mahasiswa

| Keterangan | Data |
|---|---|
| **Nama** | *Bunga Zulfa Aqila* |
| **NIM** | *2509116024* |
| **Mata Kuliah** | Pemrograman Berorientasi Objek |
---

## Penjelasan Studi Kasus

Program ini merupakan **Sistem Pendaftaran Acara Seminar** berbasis *console* (CLI) yang dibuat menggunakan bahasa pemrograman **Java**. Studi kasus ini dipilih karena merepresentasikan permasalahan nyata dalam pengelolaan acara, khususnya seminar, yang mencakup:

1. **Manajemen Data Acara** — Pengguna dapat menambah, melihat, mencari, mengubah, dan menghapus data acara (CRUD).
2. **Pendaftaran Peserta** — Pengguna dapat mendaftarkan peserta ke sebuah acara dengan memperhatikan kuota yang tersedia.
3. **Validasi Input** — Setiap input divalidasi (nama, tanggal, kuota) agar data yang masuk konsisten.
4. **Dua Jenis Acara** — Program mendukung dua jenis acara: **Seminar** dan **Workshop**, yang memiliki atribut umum yang sama namun dapat dibedakan jenisnya.

Tujuan dari studi kasus ini adalah untuk menerapkan konsep **Pemrograman Berorientasi Objek (OOP)**, khususnya:

- **Encapsulation** — Field private/protected dengan getter & setter.
- **Inheritance** — Class `Seminar` dan `Workshop` mewarisi class `Acara`.
- **Polymorphism** — Penggunaan `ArrayList<Acara>` dan method `getJenisAcara()` serta `toString()` yang di-*override*.
- **Abstraction** — Penyederhanaan struktur dengan parent class `Acara`.

---

## Hierarki Class (Diagram Kelas Sederhana)

```
                +----------------------+
                |        Acara         |
                +----------------------+
                | # id                 |
                | # namaAcara          |
                | # pemateri           |
                | # tanggal            |
                | # kuota              |
                | # pendaftar          |
                +----------------------+
                | + getId()            |
                | + getNamaAcara()     |
                | + getPemateri()      |
                | + getTanggal()       |
                | + getKuota()         |
                | + getPendaftar()     |
                | + isKuotaPenuh()     |
                | + tambahPendaftar()  |
                | + getJenisAcara()    |
                | + toString()         |
                +----------+-----------+
                           |
              +------------+------------+
              |                         |
              ▼                         ▼
     +------------------+      +------------------+
     |     Seminar      |      |     Workshop     |
     +------------------+      +------------------+
     |                  |      | - tools          |
     +------------------+      +------------------+
     | + getNamaSeminar |      | + getTools()     |
     | + setNamaSeminar |      | + setTools()     |
     | + getJenisAcara()|      | + getJenisAcara()|
     +------------------+      | + toString()     |
                               +------------------+
```

### Penjelasan Hierarki

| Class | Peran | Keterangan |
|---|---|---|
| `Acara` | **Parent / Superclass** | Menyimpan atribut & method umum untuk semua jenis acara. |
| `Seminar` | **Child / Subclass** | Mewarisi `Acara`, menambahkan method kompatibilitas (`getNamaSeminar`, `setNamaSeminar`). |
| `Workshop` | **Child / Subclass** | Mewarisi `Acara`, menambahkan atribut khusus `tools`. |
| `SeminarCRUD` | **Controller** | Mengelola `ArrayList<Acara>` dan seluruh operasi CRUD. |
| `InputValidator` | **Utility** | Kumpulan method statis untuk validasi input. |
| `Main` | **Entry Point** | Menjalankan program dan menampilkan menu utama. |

---

## Penjelasan Bagian Kode yang Menerapkan Inheritance

### 1. Deklarasi Parent Class `Acara`

```java
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
```

> **Poin penting:** keyword `protected` memungkinkan field diakses oleh subclass, dan method `getJenisAcara()` serta `toString()` dirancang agar bisa di-*override*.

---

### 2. Child Class `Seminar` Menggunakan `extends`

```java
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
```

> **Poin penting:**
> - `extends Acara` → `Seminar` mewarisi seluruh field & method `Acara`.
> - `super(...)` → memanggil constructor parent.
> - `@Override` → menimpa method `getJenisAcara()` milik parent.

---

### 3. Child Class `Workshop` (Contoh Ekstensi)

```java
public class Workshop extends Acara {
    private String tools;

    public Workshop(int id, String namaWorkshop, String pemateri, String tanggal, int kuota, String tools) {
        super(id, namaWorkshop, pemateri, tanggal, kuota);
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
```

> **Poin penting:** `super.toString()` memanggil implementasi milik parent, lalu ditambahkan atribut khusus `tools`.

---

### 4. Penerapan Polymorphism di `SeminarCRUD`

```java
private ArrayList<Acara> daftarAcara;
...
public Acara cariAcaraById(int id) {
    for (Acara a : daftarAcara) {
        if (a.getId() == id) {
            return a;
        }
    }
    return null;
}
```

> **Poin penting:** `ArrayList<Acara>` dapat menampung objek `Seminar`, `Workshop`, atau subclass lain dari `Acara`. Method `toString()` dan `getJenisAcara()` yang dipanggil akan menyesuaikan dengan objek aslinya (runtime polymorphism).

---

### 5. Kesimpulan Penerapan Inheritance

| Konsep | Lokasi Penerapan |
|---|---|
| `extends` | `Seminar extends Acara`, `Workshop extends Acara` |
| `super(...)` | Constructor child memanggil constructor parent |
| `super.method()` | `Workshop.toString()` memanggil `Acara.toString()` |
| `@Override` | `getJenisAcara()` & `toString()` di subclass |
| Akses `protected` | Field `Acara` diakses langsung oleh subclass |
| Polymorphism | `ArrayList<Acara>` menampung berbagai subclass |

---

## Tangkapan Layar (Screenshot) Program Saat Dijalankan

> **Catatan:** Ganti bagian di bawah ini dengan screenshot asli hasil *running* program di komputermu.

### 1. Tampilan Menu Utama

```
SISTEM PENDAFTARAN ACARA SEMINAR
MANAJEMEN SEMINAR 2026

MENU UTAMA
1. Tambah Seminar
2. Lihat Daftar Seminar
3. Cari Seminar
4. Update Seminar
5. Hapus Seminar
6. Daftar Peserta Seminar
7. Keluar
Pilih menu (1-7):
```

![Screenshot Menu Utama](screenshots/menu-utama.png)

---

### 2. Tampilan Daftar Acara (dengan Jenis Acara)

```
=== DAFTAR ACARA ===
Total Acara: 3

ID: 1 | Jenis: Seminar | Nama: Inovasi AI dalam Pendidikan | Pemateri: Dr. Budi Santoso | Tanggal: 2026-10-15 | Kuota: 50 | Terdaftar: 0 | Sisa: 50
---------------------------
ID: 2 | Jenis: Seminar | Nama: Cyber Security 2026 | Pemateri: Prof. Dewi Lestari | Tanggal: 2026-10-20 | Kuota: 30 | Terdaftar: 0 | Sisa: 30
---------------------------
ID: 3 | Jenis: Seminar | Nama: Digital Marketing Strategy | Pemateri: Andi Wijaya, S.Kom | Tanggal: 2026-10-25 | Kuota: 40 | Terdaftar: 0 | Sisa: 40
---------------------------
```

![Screenshot Daftar Acara](screenshots/daftar-acara.png)

---

### 3. Tampilan Pendaftaran Peserta

```
=== PENDAFTARAN PESERTA ACARA ===
Masukkan ID Acara yang ingin diikuti: 1
Masukkan Nama Peserta: Rina Marlina

✓ Pendaftaran berhasil!
Nama Peserta: Rina Marlina
Acara: Inovasi AI dalam Pendidikan
Jenis: Seminar
Sisa Kuota: 49
```

![Screenshot Pendaftaran Peserta](screenshots/pendaftaran-peserta.png)

---

### 4. Tampilan Update Acara

```
=== UPDATE ACARA ===
Masukkan ID Acara yang akan diupdate: 2

Data saat ini:
ID: 2 | Jenis: Seminar | Nama: Cyber Security 2026 | Pemateri: Prof. Dewi Lestari | Tanggal: 2026-10-20 | Kuota: 30 | Terdaftar: 0 | Sisa: 30

Masukkan data baru (kosongkan jika tidak ingin mengubah):
Nama Acara [Cyber Security 2026]: Cyber Security & Ethical Hacking
Nama Pemateri [Prof. Dewi Lestari]:
Tanggal [2026-10-20]:
Kuota [30]: 35

✓ Acara berhasil diupdate!
```

![Screenshot Update Acara](screenshots/update-acara.png)

---

### 5. Tampilan Hapus Acara

```
=== HAPUS ACARA ===
Masukkan ID Acara yang akan dihapus: 3
Apakah Anda yakin ingin menghapus acara 'Digital Marketing Strategy'? (y/n): y

✓ Acara berhasil dihapus!
```

![Screenshot Hapus Acara](screenshots/hapus-acara.png)

---

## Struktur Folder Proyek

```
SeminarApp/
├── src/
│   └── seminar/
│       ├── Acara.java
│       ├── Seminar.java
│       ├── Workshop.java
│       ├── SeminarCRUD.java
│       ├── InputValidator.java
│       └── Main.java
├── screenshots/
│   ├── menu-utama.png
│   ├── daftar-acara.png
│   ├── pendaftaran-peserta.png
│   ├── update-acara.png
│   └── hapus-acara.png
└── README.md
```

---

## Cara Menjalankan Program

1. Pastikan **JDK** sudah terinstal (minimal Java 8).
2. Clone repository ini:
   ```bash
   git clone https://github.com/username/SeminarApp.git
   ```
3. Masuk ke direktori proyek:
   ```bash
   cd SeminarApp/src
   ```
4. Kompilasi semua file Java:
   ```bash
   javac seminar/*.java
   ```
5. Jalankan program:
   ```bash
   java seminar.Main
   ```

---

## Kesimpulan

Program **Sistem Pendaftaran Acara Seminar** ini berhasil menerapkan konsep **inheritance** melalui class `Acara` sebagai parent dan `Seminar` serta `Workshop` sebagai child. Dengan struktur ini, kode menjadi lebih **reusable**, **mudah diperluas**, dan **menerapkan polymorphism** melalui `ArrayList<Acara>`. Ke depannya, program dapat dikembangkan lebih lanjut dengan menambahkan jenis acara lain seperti `Webinar` atau `Pelatihan` hanya dengan membuat subclass baru dari `Acara`.



# Aplikasi Manajemen Stok Barang Gudang

Aplikasi desktop sederhana untuk mengelola stok barang gudang menggunakan Java Swing dan penyimpanan data berbasis file CSV. Aplikasi ini menyediakan fitur login, tampilan daftar barang, pencarian, serta operasi CRUD (tambah, ubah, hapus) data barang.[mainApp.java][loginKaryawanAPP.java]

## Fitur Utama

- **Login Karyawan**  
  Aplikasi dimulai dengan form login yang meminta username dan password sebelum masuk ke jendela utama.[loginKaryawanAPP.java]

- **Tampilan Daftar Barang**  
  Menampilkan data barang dalam bentuk tabel dengan kolom: ID, NAMA, STOK, HARGA, dan TOTAL (Rp).[mainApp.java]

- **Pencarian Barang**  
  Pengguna dapat memfilter data barang secara dinamis berdasarkan teks yang diketik di field pencarian.[mainApp.java]

- **Operasi CRUD**  
  - **Tambah Barang (ADD)** – membuka form untuk menambah data barang baru.  
  - **Ubah Barang (UPDATE)** – membuka form untuk mengubah stok dan harga barang yang dipilih.  
  - **Hapus Barang (DELETE)** – menghapus baris barang yang dipilih dari tabel dan file CSV.[mainApp.java][updateClassAPP.java]

- **Perhitungan Otomatis**  
  Kolom TOTAL dihitung otomatis sebagai `STOK × HARGA` untuk setiap barang, dan total nilai inventaris gudang ditampilkan di bagian bawah.[mainApp.java]

- **Penyimpanan Data CSV**  
  Semua data barang disimpan dan dibaca dari file `DataBarang.csv` menggunakan kelas utilitas `CSVutil`.[mainApp.java][CSVutil.java]

## Struktur Proyek

```
stok-barang/
└── src/
    └── main/
        └── java/
            ├── org/
            │   └── example/
            │       ├── Table/
            │       │   ├── MyTableModel.java
            │       ├── UI/
            │       │   ├── loginKaryawanAPP.java
            │       │   ├── mainApp.java
            │       │   ├── updateClassAPP.java
            │       │   ├── deleteClassAPP.java
            │       │   └── addClassAPP.java
            │       └── util/
            │           └── CSVutil.java
            └── DataBarang.csv
```

## Cara Menjalankan

1. Pastikan sudah terinstall:
   - Java Development Kit (JDK) versi 8 atau lebih baru.
   - IDE seperti IntelliJ IDEA, Eclipse, atau NetBeans (opsional).

2. Buat file `DataBarang.csv` di direktori `src/main/java/` dengan struktur kolom:
ID,NAMA,STOK,HARGA,TOTAL(Rp)
1,Barang A,10,50000,500000
2,Barang B,5,75000,375000


3. Compile dan jalankan kelas utama:
javac -d bin src/main/java/org/example/UI/loginKaryawanAPP.java
java -cp bin org.example.UI.loginKaryawanAPP


4. Masukkan kredensial login:
- Username: `admin`
- Password: `UMM1964`

5. Setelah login berhasil, akan muncul jendela utama aplikasi manajemen stok barang.[loginKaryawanAPP.java][mainApp.java]

## Kredensial Login

- **Username**: `admin`
- **Password**: `UMM1964`

Kredensial ini disimpan secara hardcode di kelas `loginKaryawanAPP.java` dan hanya untuk penggunaan internal.[loginKaryawanAPP.java]

## Format File CSV

File `DataBarang.csv` harus memiliki header dan format sebagai berikut:

ID,NAMA,STOK,HARGA,TOTAL(Rp)
1,Barang A,10,50000,500000
2,Barang B,5,75000,375000


- Kolom `TOTAL(Rp)` diisi otomatis oleh aplikasi sebagai `STOK × HARGA`.[mainApp.java][CSVutil.java]

## Catatan Pengembangan

- Aplikasi ini menggunakan Java Swing untuk antarmuka grafis dan tidak menggunakan database eksternal (hanya file CSV).[mainApp.java][CSVutil.java]
- Untuk keamanan yang lebih baik, kredensial login sebaiknya dipindahkan ke file konfigurasi atau database di proyek lanjutan.[loginKaryawanAPP.java]
- Pastikan struktur direktori dan path file (`FILE_PATH`) sesuai dengan struktur proyek saat dijalankan.[mainApp.java][updateClassAPP.java][CSVutil.java]



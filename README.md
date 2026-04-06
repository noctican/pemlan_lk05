# LK05 - Generic Class

## Kelompok
<table>
    <thead>
        <tr>
            <th>Nama</th>
            <th>NIM</th>
            <th>Kontribusi</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td>Candra Andika Putra</td>
            <td>255150200111035</td>
            <td>
            </td>
        </tr>
        <tr>
            <td>Haidar Nadhifa Putra</td>
            <td>255150201111031</td>
            <td>
            </td>
        </tr>
        <tr>
            <td>Bagas Zakaria</td>
            <td>255150207111064</td>
            <td>
            </td>
        </tr>
        <tr>
            <td>Muhammad Hilmi Isnaeni</td>
            <td>255150201111026</td>
            <td>
            </td>
        </tr>
        <tr>
            <td>Noval Zakky Ramadhan</td>
            <td>255150201111030</td>
            <td>
            </td>
        </tr>
    </tbody>
</table>

## Tujuan Latihan Kerja 05
Implementasi dari pemahaman terhadap materi generic class

## Studi Kasus
PT Asuransi BPJS adalah perusahaan asuransi kesehatan besar yang memiliki server pusat data nasabah. Mereka bekerja sama dengan ratusan Rumah Sakit Mitra Keluarga di seluruh negeri.

Saat ini, terjadi masalah integrasi:
1. Ketidakseragaman Data: Beberapa rumah sakit meminta data pasien versi lama (V1), sementara yang lain sudah menggunakan versi baru (V2) yang memiliki field tambahan (misal: riwayat alergi).
2. Kebocoran Data: Terkadang rumah sakit menerima data sensitif (seperti Nomor KTP atau Diagnosa Penyakit Khusus) yang seharusnya disembunyikan (masked) tergantung pada level akses dokter yang login.
3. Kode yang Rapuh: Tim backend asuransi harus membuat endpoint API berbeda untuk setiap jenis permintaan data (/getPatientV1, /getPatientV2, /getClaimHistory, dll), sehingga kode menjadi sangat berulang (boilerplate) dan sulit dirawat.

### 1. Tugas Anda
Anda ditunjuk sebagai Lead Developer untuk membangun MediGuard Integration Gateway. Sistem ini harus menggunakan Java Generics secara maksimal untuk menciptakan satu jalur API yang aman, fleksibel, dan tahan terhadap perubahan struktur data di masa depan.
### 2. Requirement Teknis (Kebutuhan Sistem)
Sistem yang Anda bangun harus memenuhi ketentuan berikut:
#### A. Struktur Data Berjenjang (Bounded Types)
1. Buat interface dasar MedicalRecord yang wajib dimiliki semua data medis.
2. Buat interface Versioned yang memiliki method getVersion().
3. Semua data yang ditransfer (DTO) harus mengimplementasikan MedicalRecord & Versioned.
4. Buat minimal 2 implementasi data: PatientProfileV1 dan PatientProfileV2 (V2 memiliki field tambahan).
#### B. Generic API Response dengan Keamanan
1. Buat kelas SecureResponse<T> yang membungkus data.
2. Kelas ini harus memiliki mekanisme Data Masking otomatis.
3. Gunakan Generic Constraint untuk memastikan hanya data yang memiliki level keamanan tertentu yang bisa diproses.
    - Contoh: T extends MedicalRecord & Confidential.
    - Interface Confidential harus memiliki method getSecurityLevel() (misal: PUBLIC, RESTRICTED, SECRET).
#### C. Generic Gateway Service (Inti Masalah)
1. Buat satu kelas service IntegrationGateway<T>.
2. Kelas ini harus memiliki method tunggal fetchData(String patientId, int requesterClearanceLevel).
3. Logika Bisnis:
    - Jika requesterClearanceLevel lebih rendah dari data.getSecurityLevel(), maka field sensitif pada data harus otomatis di-masking (misal: "123456" menjadi "******") sebelum dibungkus ke SecureResponse.
    - Proses masking harus terjadi secara generik (tidak boleh ada if (data instanceof PatientProfileV1) secara eksplisit di Gateway). Gunakan pola desain atau interface untuk menangani masking.
#### D. Ekstensibilitas
1. Jika di masa depan ada data baru, misalnya ClaimHistoryV1, sistem harus bisa menerimanya tanpa mengubah kode inti pada IntegrationGateway. Cukup buat kelas baru yang implements interface yang sesuai.
### 3. Indikator Keberhasilan (Evaluation Rubric)
Proyek Anda akan dinilai berdasarkan kesesuaian dengan kebutuhan (requirement) berikut. Pastikan setiap poin terpenuhi untuk mendapatkan nilai maksimal.

| No | Indikator Penilaian | Kriteria Lulus (_Pass_) | Bobot |
|:--:| -- | -- |:--:|
| 1 | Compile-Time Safety | Kode berhasil dikompilasi tanpa warning unchecked. Tidak ada casting manual (Type) di kode bisnis utama. | 20% |
| 2 | Implementasi Generic Bound | Menggunakan extends pada definisi kelas/metode untuk membatasi tipe data (misal: <T extends MedicalRecord>). | 25% |
| 3 | Keamanan Data (Masking) | Sistem berhasil menyembunyikan data sensitif secara otomatis berdasarkan level akses tanpa hardcoding tipe kelas di Gateway. | 30% |
| 4 | Prinsip DRY (Don't Repeat Yourself) | Tidak ada duplikasi logika untuk menangani V1 dan V2. Penambahan tipe data baru tidak memerlukan perubahan pada kelas Gateway. | 15% |
| 5 | Kelengkapan Fitur | Terdapat minimal 2 versi data (V1, V2) dan simulasi permintaan dari rumah sakit berhasil menampilkan hasil yang benar. | 10% |
| Total |  |  | 100% |

### 4. Panduan Arsitektur (Hint Solusi)
Berikut adalah kerangka dasar agar Anda menyelesaian studi kasus latihan kerja 05. Berikut ini hanya contoh. Anda bisa memodifikasi untuk melengkapinya.

_Interface Kontrak_
```java
// Kontrak dasar semua data medis
public interface MedicalRecord {
    String getPatientId();
}
// Kontrak untuk versi data
public interface Versioned {
    int getVersion();
}
// Kontrak untuk data yang butuh keamanan
public interface Confidential {
    int getSecurityLevel(); // 1=Public, 2=Restricted, 3=Secret
    void maskSensitiveData(); // Method untuk melakukan masking pada objek itu sendiri
}
```
_Implementasi Data (ini hanya contoh)_
```java
public class PatientProfileV1 implements MedicalRecord, Versioned, Confidential {
    private String patientId;
    private String name;
    private String ssn; // Data sensitif
    private int securityLevel = 2; // Restricted
    // Constructor, Getter, Setter...
    @Override
    public void maskSensitiveData() {
        if (this.ssn != null) {
            this.ssn = "******"; // Logika masking spesifik untuk kelas ini
        }
    }
 // Implementasi interface lainnya...
}
```

_Generic Response Wrapper_
```java
public class SecureResponse<T extends MedicalRecord & Confidential> {
    private boolean success;
    private T data;
    private String warningMessage; // Misal: "Some data masked due to low clearance"
    // Constructor & Getter...
}
```

_Generic Gateway_
```java
public class IntegrationGateway<T extends MedicalRecord & Versioned & Confidential> {
    // Simulasi database
    private T mockDatabaseRecord;
    public IntegrationGateway(T record) {
        this.mockDatabaseRecord = record;
    }
    public SecureResponse<T> fetchData(String id, int clearanceLevel) {
        // 1. Validasi ID
        // 2. Cek Level Keamanan
        // Jika clearanceLevel < mockDatabaseRecord.getSecurityLevel()
        // Maka panggil mockDatabaseRecord.maskSensitiveData()
        // 3. Bungkus dalam SecureResponse
        // 4. Return
    }
}
```

### 5. Batasan:
- Multiple Bounds (&): Gunakan tipe ganda (extends A & B & C). Ini memastikan objek yang masuk memiliki kemampuan sebagai Data Medis, memiliki Versi, dan memiliki Level Keamanan sekaligus.
- Perilaku Polimorfik pada Masking: jangan menggunakan if (t instanceof PatientV1) di dalam Gateway. Anda harus memaksa objek T untuk menyembunyikan datanya sendiri melalui method maskSensitiveData() yang dijamin ada oleh interface Confidential. Ini adalah penerapan Tell, Don't Ask principle bersama Generics.
- Integritas Data: Sistem harus menjamin bahwa data yang keluar dari Gateway selalu aman tanpa bergantung pada siapa yang memanggil method tersebut. Keamanan dibungkus dalam tipe data itu sendiri.

### 6. Tugas:
- Implementasikan kode berdasarkan spesifikasi di atas.
- Buat kelas MainSimulation (AppTun) yang mensimulasikan:
    - Dokter dengan akses rendah (hanya bisa lihat nama, KTP tertutup).
    - Dokter dengan akses tinggi (bisa lihat semua data).
    - Permintaan data V1 dan V2 menggunakan Gateway yang sama.
- Sertakan screenshot hasil console yang membuktikan data ter-masking sesuai level akses dan berikan penjelasannya
- Kumpulkan pada tanggal 08 April 2026 Jam 17.00 WIB
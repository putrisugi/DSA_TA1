package main;

// ============================================================
// CLASS Lagu - Merepresentasikan setiap lagu dalam playlist
// Menerapkan konsep ENKAPSULASI: semua atribut bersifat private
// dan hanya dapat diakses melalui getter dan setter.
// ============================================================
class Lagu {
    // Atribut private (Enkapsulasi)
    private String judul;
    private String artis;
    private double durasi; // dalam menit

    /**
     * Constructor untuk membuat objek Lagu baru.
     * @param judul  - judul lagu
     * @param artis  - nama artis/penyanyi
     * @param durasi - durasi lagu dalam menit
     */
    public Lagu(String judul, String artis, double durasi) {
        this.judul  = judul;
        this.artis  = artis;
        this.durasi = durasi;
    }

    // --- GETTER ---

    /** Mengembalikan judul lagu */
    public String getJudul() {
        return judul;
    }

    /** Mengembalikan nama artis */
    public String getArtis() {
        return artis;
    }

    /** Mengembalikan durasi lagu */
    public double getDurasi() {
        return durasi;
    }

    // --- SETTER ---

    /** Mengubah judul lagu */
    public void setJudul(String judul) {
        this.judul = judul;
    }

    /** Mengubah nama artis */
    public void setArtis(String artis) {
        this.artis = artis;
    }

    /** Mengubah durasi lagu */
    public void setDurasi(double durasi) {
        this.durasi = durasi;
    }

    /**
     * Menampilkan informasi lengkap sebuah lagu:
     * judul, artis, dan durasi dalam format menit:detik.
     */
    public void tampilkanInfo() {
        int menit = (int) durasi;
        int detik = (int) Math.round((durasi - menit) * 60);
        System.out.printf("  Judul  : %s%n", judul);
        System.out.printf("  Artis  : %s%n", artis);
        System.out.printf("  Durasi : %d:%02d menit%n", menit, detik);
    }
}

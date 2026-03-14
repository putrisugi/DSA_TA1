package main;
import java.util.Scanner;

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

//Parent Class - Class User
class User { 
  protected String nama;

  //Constructor
  public User(String nama) {
      this.nama = nama;
  }

  //Getter untuk mengambil nilai nama dari luar class
  public String getNama() {
      return nama;
  }

  //Method tampilanMenu() *konsep Polymorphism
  public void tampilkanMenu() {
      System.out.println("Halo, " + nama + "!");
  }

  // Method lihatSemuaLagu() di parent class
  public void lihatSemuaLagu(Lagu[] playlist, int jumlahLagu) {
      if (jumlahLagu == 0) {
          System.out.println("[!] Playlist masih kosong.");
          return;
       }
      System.out.println("Daftar Lagu");
      for (int i = 0; i < jumlahLagu; i++) {
          System.out.println((i + 1) + ".");
          playlist[i].tampilkanInfo();
      }   
}
}

//Child Class - Class Admin
class Admin extends User {

  //Constructor Admin memanggil constructor User
  public Admin(String nama) {
      super (nama); //Inheritance: meneruskan nama ke parent class
  }

  //Override tampilkanMenu() dari User
  @Override
  public void tampilkanMenu() {
      System.out.println("  [ADMIN] Halo, " + nama + "!");
      System.out.println("  1. Tambah Lagu");
      System.out.println("  2. Lihat Semua Lagu");
      System.out.println("  3. Keluar");
  }

  //Method khusus Admin: menambahkan lagu baru ke array playlist
  public int tambahLagu(Lagu[] playlist, int jumlahLagu, Lagu lagubaru) {

        // Cek apakah array masih ada slot kosong
      if (jumlahLagu >= playlist.length) {
          System.out.println("[!] Playlist penuh! Maksimal " + playlist.length + " lagu.");
          return jumlahLagu; // kembalikan jumlah yang tidak berubah
      }

      // Simpan lagu baru di slot berikutnya
      playlist[jumlahLagu] = lagubaru;
      jumlahLagu++; // tambah penghitung lagu

      System.out.println("[+] Berhasil menambahkan: \"" + lagubaru.getJudul() + "\"");
      return jumlahLagu;
  }
}

//Child Class - Class Member
class Member extends User {

  //Constructor Member memanggil constructor User via super()
  public Member(String nama) {
      super(nama); // Inheritance: meneruskan nama ke parent class
  }

  //Override tampilkanMenu() dari User
  @Override
  public void tampilkanMenu() {
      System.out.println("  [MEMBER] Halo, " + nama + "!");
      System.out.println("  1. Lihat Semua Lagu");
      System.out.println("  2. Cari Lagu");
      System.out.println("  3. Rata-rata Durasi");
      System.out.println(" 4. Keluar");
  }

  //Method menampilkan semua lagu dalam playlist
  public void lihatSemuaLagu(Lagu[] playlist, int jumlahLagu) {

       // Cek apakah playlist kosong
      if (jumlahLagu == 0) {
          System.out.println("[!] Playlist masih kosong.");
          return;
      }

      System.out.println("Daftar Lagu di Playlist");
      for (int i = 0; i < jumlahLagu; i++) {
          System.out.println((i + 1) + ".");
          playlist[i].tampilkanInfo(); // panggil method dari objek Lagu
      }
  }

  //Method mencari lagu berdasarkan judul
  public void cariLagu(Lagu[] playlist, int jumlahLagu, String keyword) {

      boolean ditemukan = false;

       // Bandingkan judul tiap lagu dengan keyword
      for (int i = 0; i < jumlahLagu; i++) {
          if (playlist[i].getJudul().equalsIgnoreCase(keyword)) {
              System.out.println("[*] Lagu ditemukan!");
              playlist[i].tampilkanInfo();
              ditemukan = true;
          }
      }

      // Jika tidak ada yang cocok, tampilkan pesan
      if (!ditemukan) {
          System.out.println("[!] Lagu \"" + keyword + "\" tidak ditemukan.");
      }
  }

  //Method menghitung rata-rata durasi semua lagu
   public void hitungRataRataDurasi(Lagu[] playlist, int jumlahLagu) {

      // Cek apakah playlist kosong
      if (jumlahLagu == 0) {
          System.out.println("[!] Playlist kosong.");
          return;
      }

      double totalDurasi = 0;

      // Jumlahkan semua durasi
      for (int i = 0; i < jumlahLagu; i++) {
          totalDurasi += playlist[i].getDurasi();
      }

      // Hitung rata-rata dan tampilkan
      double rataRata = totalDurasi / jumlahLagu;
      System.out.printf("[*] Total durasi    : %.2f menit%n", totalDurasi);
      System.out.printf("[*] Rata-rata durasi: %.2f menit%n", rataRata);
  }
}

  
public class PlaylistOOP {
  public static void main(String[] args) {

      // Inisialisasi array playlist dan Scanner
      Lagu[] playlist = new Lagu[10];
      int jumlahLagu  = 0;
      Scanner scanner = new Scanner(System.in);

      int pilihPeran = 0;

      // Loop luar - balik ke pilih peran setelah keluar dari Admin/Member
      while (pilihPeran != 3) {

  // Pilih peran
  System.out.println("");
  System.out.println("  Selamat datang di Playlist!");
  System.out.println("");
  System.out.println("Pilih peran:");
  System.out.println("  1. Admin");
  System.out.println("  2. Member");
  System.out.println("  3. Keluar");
  System.out.println("");
  System.out.print("Masukkan pilihan: ");
  pilihPeran = scanner.nextInt();
  scanner.nextLine();

  // SESI ADMIN 
  if (pilihPeran == 1) {
      Admin admin = new Admin("Admin");
      int pilihMenu = 0;

      while (pilihMenu != 3) {
          admin.tampilkanMenu();
          System.out.print("Masukkan pilihan: ");
          pilihMenu = scanner.nextInt();
          scanner.nextLine();

          if (pilihMenu == 1) {
              System.out.print("Masukkan judul lagu : ");
              String judul = scanner.nextLine();
              System.out.print("Masukkan artis      : ");
              String artis = scanner.nextLine();
              System.out.print("Masukkan durasi     : ");
              double durasi = scanner.nextDouble();
              scanner.nextLine();
              jumlahLagu = admin.tambahLagu(playlist, jumlahLagu,
                              new Lagu(judul, artis, durasi));

          } else if (pilihMenu == 2) {
              admin.lihatSemuaLagu(playlist, jumlahLagu);

          } else if (pilihMenu == 3) {
              System.out.println("Keluar dari sesi Admin.");

          } else {
              System.out.println("[!] Pilihan tidak valid.");
          }
      }

  // SESI MEMBER
  } else if (pilihPeran == 2) {
      Member member = new Member("Member");
      int pilihMenu = 0;

      while (pilihMenu != 4) {
          member.tampilkanMenu();
          System.out.print("Masukkan pilihan: ");
          pilihMenu = scanner.nextInt();
          scanner.nextLine();

          if (pilihMenu == 1) {
              member.lihatSemuaLagu(playlist, jumlahLagu);

          } else if (pilihMenu == 2) {
              System.out.print("Masukkan judul yang dicari: ");
              String keyword = scanner.nextLine();
              member.cariLagu(playlist, jumlahLagu, keyword);

          } else if (pilihMenu == 3) {
              member.hitungRataRataDurasi(playlist, jumlahLagu);

          } else if (pilihMenu == 4) {
              System.out.println("Keluar dari sesi Member.");

          } else {
              System.out.println("[!] Pilihan tidak valid.");
          }
      }

  } else if (pilihPeran == 3) {
      System.out.println("Sampai jumpa!");

  } else {
      System.out.println("[!] Pilihan tidak valid.");
  }
}

      // Tutup scanner
      scanner.close();
      System.out.println("");
      System.out.println("      Program selesai.");
      System.out.println("");
  }
}

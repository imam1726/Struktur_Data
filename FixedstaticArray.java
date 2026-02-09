import java.util.Scanner;

public class FixedstaticArray {
    static String[] nim = new String[10];
    static String[] nama = new String[10];
    static int count = 0;
    static Scanner input = new Scanner(System.in);
    
    public static void main(String[] args) {
        System.out.println("=== PROGRAM ARRAY MAHASISWA ===");
        
        int pilih;
        do {
            System.out.println("\n=== MENU ===");
            System.out.println("1. Insert at beginning");
            System.out.println("2. Insert at given position");
            System.out.println("3. Insert at end");
            System.out.println("4. Delete from beginning");
            System.out.println("5. Delete given position");
            System.out.println("6. Delete from end");
            System.out.println("7. Delete first occurence");
            System.out.println("8. Show data");
            System.out.println("9. Exit");
            System.out.print("Pilih: ");
            
            pilih = input.nextInt();
            input.nextLine();
            
            switch(pilih) {
                case 1: insertAwal(); break;
                case 2: insertPosisi(); break;
                case 3: insertAkhir(); break;
                case 4: deleteAwal(); break;
                case 5: deletePosisi(); break;
                case 6: deleteAkhir(); break;
                case 7: deletePertama(); break;
                case 8: showData(); break;
                case 9: System.out.println("Program selesai"); break;
                default: System.out.println("Pilihan salah!");
            }
        } while(pilih != 9);
    }
    
    // 1. Insert at beginning
    static void insertAwal() {
        if(count >= 10) {
            System.out.println("Array penuh!");
            return;
        }
        
        System.out.print("NIM: ");
        String n = input.nextLine();
        System.out.print("Nama: ");
        String nm = input.nextLine();
        
        if(nim[0] == null) {
            count++;
        }
        
        nim[0] = n;
        nama[0] = nm;
        System.out.println("Data ditambahkan di No. 1");
        System.out.println("Count: " + count);
    }
    
    // 2. Insert at given position
    static void insertPosisi() {
        if(count >= 10) {
            System.out.println("Array penuh!");
            return;
        }
        
        System.out.print("No. (1-10): ");
        int no = input.nextInt();
        input.nextLine();
        
        if(no < 1 || no > 10) {
            System.out.println("No. harus 1-10!");
            return;
        }
        
        System.out.print("NIM: ");
        String n = input.nextLine();
        System.out.print("Nama: ");
        String nm = input.nextLine();
        
        int idx = no - 1;
        
        if(nim[idx] == null) {
            count++;
        }
        
        nim[idx] = n;
        nama[idx] = nm;
        System.out.println("Data ditambahkan di No. " + no);
        System.out.println("Count: " + count);
    }
    
    // 3. Insert at end
    static void insertAkhir() {
        if(count >= 10) {
            System.out.println("Array penuh!");
            return;
        }
        
        System.out.print("NIM: ");
        String n = input.nextLine();
        System.out.print("Nama: ");
        String nm = input.nextLine();
        
        if(nim[9] == null) {
            count++;
        }
        
        nim[9] = n;
        nama[9] = nm;
        System.out.println("Data ditambahkan di No. 10");
        System.out.println("Count: " + count);
    }
    
    // 4. Delete from beginning
    static void deleteAwal() {
        if(nim[0] == null) {
            System.out.println("No. 1 kosong!");
            return;
        }
        
        System.out.println("Menghapus No. 1: " + nim[0] + " - " + nama[0]);
        nim[0] = null;
        nama[0] = null;
        count--;
        System.out.println("Data dihapus");
        System.out.println("Count: " + count);
    }
    
    // 5. Delete given position
    static void deletePosisi() {
        if(count == 0) {
            System.out.println("Array kosong!");
            return;
        }
        
        System.out.print("No. yang dihapus (1-10): ");
        int no = input.nextInt();
        input.nextLine();
        
        if(no < 1 || no > 10) {
            System.out.println("No. harus 1-10!");
            return;
        }
        
        int idx = no - 1;
        
        if(nim[idx] == null) {
            System.out.println("No. " + no + " kosong!");
            return;
        }
        
        System.out.println("Menghapus No. " + no + ": " + nim[idx] + " - " + nama[idx]);
        nim[idx] = null;
        nama[idx] = null;
        count--;
        System.out.println("Data dihapus");
        System.out.println("Count: " + count);
    }
    
    // 6. Delete from end
    static void deleteAkhir() {
        if(nim[9] == null) {
            System.out.println("No. 10 kosong!");
            return;
        }
        
        System.out.println("Menghapus No. 10: " + nim[9] + " - " + nama[9]);
        nim[9] = null;
        nama[9] = null;
        count--;
        System.out.println("Data dihapus");
        System.out.println("Count: " + count);
    }
    
    // 7. Delete first occurence
    static void deletePertama() {
        if(count == 0) {
            System.out.println("Array kosong!");
            return;
        }
        
        System.out.print("Masukkan NIM yang akan dihapus: ");
        String cari = input.nextLine();
        
        for(int i = 0; i < 10; i++) {
            if(nim[i] != null && nim[i].equals(cari)) {
                System.out.println("Data ditemukan di No. " + (i+1) + ": " + nim[i] + " - " + nama[i]);
                nim[i] = null;
                nama[i] = null;
                count--;
                System.out.println("Data dihapus");
                System.out.println("Count: " + count);
                return;
            }
        }
        
        System.out.println("Data tidak ditemukan!");
    }
    
    // 8. Show data - dengan format "No." dan titik
    static void showData() {
        System.out.println("\n=== DATA MAHASISWA ===");
        System.out.println("Jumlah: " + count + " data");
        
        if(count == 0) {
            System.out.println("Tidak ada data");
            return;
        }
        
        System.out.println("\nNo.\tNIM\tNama");
        System.out.println("------------------------");
        
        for(int i = 0; i < 10; i++) {
            if(nim[i] != null) {
                System.out.println((i+1) + ".\t" + nim[i] + "\t" + nama[i]);
            }
        }
    }
}
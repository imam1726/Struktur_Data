import java.util.Scanner;

public class ArrayMahasiswa {
    static String[] nim = new String[10];
    static String[] nama = new String[10];
    static int count = 0;
    static Scanner input = new Scanner(System.in);
    
    public static void main(String[] args) {
        int pilih;
        
        do {
            System.out.println("\n1. Insert at beginning");
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
                case 9: System.out.println("Exit"); break;
                default: System.out.println("Invalid");
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
        
        for(int i = count; i > 0; i--) {
            nim[i] = nim[i-1];
            nama[i] = nama[i-1];
        }
        
        nim[0] = n;
        nama[0] = nm;
        count++; // Count +1
        System.out.println("Data ditambahkan di awal");
    }
    
    // 2. Insert at given position
    static void insertPosisi() {
        if(count >= 10) {
            System.out.println("Array penuh!");
            return;
        }
        
        System.out.print("Posisi (1-" + (count+1) + "): ");
        int pos = input.nextInt();
        input.nextLine();
        
        if(pos < 1 || pos > count + 1) {
            System.out.println("Posisi invalid!");
            return;
        }
        
        System.out.print("NIM: ");
        String n = input.nextLine();
        System.out.print("Nama: ");
        String nm = input.nextLine();
        
        for(int i = count; i >= pos; i--) {
            nim[i] = nim[i-1];
            nama[i] = nama[i-1];
        }
        
        nim[pos-1] = n;
        nama[pos-1] = nm;
        count++; // Count +1
        System.out.println("Data ditambahkan di posisi " + pos);
    }
    
    // 3. Insert at end
    static void insertAkhir() {
        if(count >= 10) {
            System.out.println("Array penuh!");
            return;
        }
        
        System.out.print("NIM: ");
        nim[count] = input.nextLine();
        System.out.print("Nama: ");
        nama[count] = input.nextLine();
        count++; // Count +1
        System.out.println("Data ditambahkan di akhir");
    }
    
    // 4. Delete from beginning
    static void deleteAwal() {
        if(count == 0) {
            System.out.println("Array kosong!");
            return;
        }
        
        System.out.println("Hapus: " + nim[0] + " - " + nama[0]);
        
        for(int i = 0; i < count - 1; i++) {
            nim[i] = nim[i+1];
            nama[i] = nama[i+1];
        }
        
        nim[count-1] = null;
        nama[count-1] = null;
        count--; // Count -1
        System.out.println("Data dihapus");
    }
    
    // 5. Delete given position
    static void deletePosisi() {
        if(count == 0) {
            System.out.println("Array kosong!");
            return;
        }
        
        System.out.print("Posisi (1-" + count + "): ");
        int pos = input.nextInt();
        input.nextLine();
        
        if(pos < 1 || pos > count) {
            System.out.println("Posisi invalid!");
            return;
        }
        
        System.out.println("Hapus: " + nim[pos-1] + " - " + nama[pos-1]);
        
        for(int i = pos-1; i < count - 1; i++) {
            nim[i] = nim[i+1];
            nama[i] = nama[i+1];
        }
        
        nim[count-1] = null;
        nama[count-1] = null;
        count--; // Count -1
        System.out.println("Data dihapus");
    }
    
    // 6. Delete from end
    static void deleteAkhir() {
        if(count == 0) {
            System.out.println("Array kosong!");
            return;
        }
        
        System.out.println("Hapus: " + nim[count-1] + " - " + nama[count-1]);
        nim[count-1] = null;
        nama[count-1] = null;
        count--; // Count -1
        System.out.println("Data dihapus");
    }
    
    // 7. Delete first occurence
    static void deletePertama() {
        if(count == 0) {
            System.out.println("Array kosong!");
            return;
        }
        
        System.out.print("NIM yang dihapus: ");
        String cari = input.nextLine();
        
        int pos = -1;
        for(int i = 0; i < count; i++) {
            boolean sama = true;
            if(nim[i] == null || cari == null) sama = false;
            else if(nim[i].length() != cari.length()) sama = false;
            else {
                for(int j = 0; j < nim[i].length(); j++) {
                    if(nim[i].charAt(j) != cari.charAt(j)) {
                        sama = false;
                        break;
                    }
                }
            }
            if(sama) {
                pos = i;
                break;
            }
        }
        
        if(pos == -1) {
            System.out.println("Data tidak ditemukan!");
            return;
        }
        
        System.out.println("Hapus: " + nim[pos] + " - " + nama[pos]);
        
        for(int i = pos; i < count - 1; i++) {
            nim[i] = nim[i+1];
            nama[i] = nama[i+1];
        }
        
        nim[count-1] = null;
        nama[count-1] = null;
        count--; // Count -1
        System.out.println("Data dihapus");
    }
    
    // 8. Show data
    static void showData() {
        if(count == 0) {
            System.out.println("Array kosong!");
            return;
        }
        
        System.out.println("\nData Mahasiswa:");
        System.out.println("Count: " + count);
        for(int i = 0; i < count; i++) {
            System.out.println((i+1) + ". " + nim[i] + " - " + nama[i]);
        }
    }
}

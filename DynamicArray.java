import java.util.ArrayList;
import java.util.Scanner;

public class DynamicArray {
    static ArrayList<String> nim = new ArrayList<>();
    static ArrayList<String> nama = new ArrayList<>();
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
        System.out.print("NIM: ");
        String n = input.nextLine();
        System.out.print("Nama: ");
        String nm = input.nextLine();
        
        nim.add(0, n);
        nama.add(0, nm);
        System.out.println("Data ditambahkan di awal");
    }
    
    // 2. Insert at given position
    static void insertPosisi() {
        System.out.print("Posisi (1-" + (nim.size()+1) + "): ");
        int pos = input.nextInt();
        input.nextLine();
        
        if(pos < 1 || pos > nim.size() + 1) {
            System.out.println("Posisi invalid!");
            return;
        }
        
        System.out.print("NIM: ");
        String n = input.nextLine();
        System.out.print("Nama: ");
        String nm = input.nextLine();
        
        nim.add(pos-1, n);
        nama.add(pos-1, nm);
        System.out.println("Data ditambahkan di posisi " + pos);
    }
    
    // 3. Insert at end
    static void insertAkhir() {
        System.out.print("NIM: ");
        String n = input.nextLine();
        System.out.print("Nama: ");
        String nm = input.nextLine();
        
        nim.add(n);
        nama.add(nm);
        System.out.println("Data ditambahkan di akhir");
    }
    
    // 4. Delete from beginning
    static void deleteAwal() {
        if(nim.isEmpty()) {
            System.out.println("Array kosong!");
            return;
        }
        
        System.out.println("Hapus: " + nim.get(0) + " - " + nama.get(0));
        nim.remove(0);
        nama.remove(0);
        System.out.println("Data dihapus");
    }
    
    // 5. Delete given position
    static void deletePosisi() {
        if(nim.isEmpty()) {
            System.out.println("Array kosong!");
            return;
        }
        
        System.out.print("Posisi (1-" + nim.size() + "): ");
        int pos = input.nextInt();
        input.nextLine();
        
        if(pos < 1 || pos > nim.size()) {
            System.out.println("Posisi invalid!");
            return;
        }
        
        System.out.println("Hapus: " + nim.get(pos-1) + " - " + nama.get(pos-1));
        nim.remove(pos-1);
        nama.remove(pos-1);
        System.out.println("Data dihapus");
    }
    
    // 6. Delete from end
    static void deleteAkhir() {
        if(nim.isEmpty()) {
            System.out.println("Array kosong!");
            return;
        }
        
        int last = nim.size() - 1;
        System.out.println("Hapus: " + nim.get(last) + " - " + nama.get(last));
        nim.remove(last);
        nama.remove(last);
        System.out.println("Data dihapus");
    }
    
    // 7. Delete first occurence
    static void deletePertama() {
        if(nim.isEmpty()) {
            System.out.println("Array kosong!");
            return;
        }
        
        System.out.print("NIM yang dihapus: ");
        String cari = input.nextLine();
        
        int pos = -1;
        for(int i = 0; i < nim.size(); i++) {
            boolean sama = true;
            if(nim.get(i) == null || cari == null) sama = false;
            else if(nim.get(i).length() != cari.length()) sama = false;
            else {
                for(int j = 0; j < nim.get(i).length(); j++) {
                    if(nim.get(i).charAt(j) != cari.charAt(j)) {
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
        
        System.out.println("Hapus: " + nim.get(pos) + " - " + nama.get(pos));
        nim.remove(pos);
        nama.remove(pos);
        System.out.println("Data dihapus");
    }
    
    // 8. Show data
    static void showData() {
        if(nim.isEmpty()) {
            System.out.println("Array kosong!");
            return;
        }
        
        System.out.println("\nData Mahasiswa:");
        System.out.println("Count: " + nim.size());
        for(int i = 0; i < nim.size(); i++) {
            System.out.println((i+1) + ". " + nim.get(i) + " - " + nama.get(i));
        }
    }
}
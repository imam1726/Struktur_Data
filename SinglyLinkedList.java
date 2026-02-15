import java.util.Scanner;

class Node {
    String nim;
    String nama;
    Node next;
    
    public Node(String nim, String nama) {
        this.nim = nim;
        this.nama = nama;
        this.next = null;
    }
}

class LinkedListManager {
    private Node head;
    private Node tail;
    private int count;
    
    public LinkedListManager() {
        this.head = null;
        this.tail = null;
        this.count = 0;
    }
    
    // 1. Insert at beginning
    public void insertBeginning(String nim, String nama) {
        Node newNode = new Node(nim, nama);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.next = head;
            head = newNode;
        }
        count++;
        System.out.println("✓ Data berhasil ditambahkan di awal");
    }
    
    // 2. Insert at given position (PERBAIKAN)
    public void insertPosition(int pos, String nim, String nama) {
        // Validasi posisi
        if (pos < 1 || pos > count + 1) {
            System.out.println("✗ Posisi tidak valid! Harus antara 1 - " + (count + 1));
            return;
        }
        
        // Kasus khusus: insert di awal
        if (pos == 1) {
            insertBeginning(nim, nama);
            return;
        }
        
        // Kasus khusus: insert di akhir
        if (pos == count + 1) {
            insertEnd(nim, nama);
            return;
        }
        
        // Insert di posisi tengah
        Node newNode = new Node(nim, nama);
        Node current = head;
        
        // Traverse ke node sebelum posisi yang dituju
        for (int i = 1; i < pos - 1; i++) {
            current = current.next;
        }
        
        // Sisipkan node baru
        newNode.next = current.next;
        current.next = newNode;
        count++;
        System.out.println("✓ Data berhasil ditambahkan di posisi " + pos);
    }
    
    // 3. Insert at end
    public void insertEnd(String nim, String nama) {
        Node newNode = new Node(nim, nama);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        count++;
        System.out.println("✓ Data berhasil ditambahkan di akhir");
    }
    
    // 4. Delete from beginning
    public void deleteBeginning() {
        if (head == null) {
            System.out.println("✗ List kosong");
            return;
        }
        
        System.out.println("✓ Menghapus: " + head.nim + " - " + head.nama);
        head = head.next;
        if (head == null) {
            tail = null;
        }
        count--;
        System.out.println("✓ Data berhasil dihapus dari awal");
    }
    
    // 5. Delete at given position (PERBAIKAN)
    public void deletePosition(int pos) {
        if (head == null) {
            System.out.println("✗ List kosong");
            return;
        }
        
        if (pos < 1 || pos > count) {
            System.out.println("✗ Posisi tidak valid! Harus antara 1 - " + count);
            return;
        }
        
        if (pos == 1) {
            deleteBeginning();
            return;
        }
        
        Node current = head;
        Node prev = null;
        
        // Traverse ke posisi yang akan dihapus
        for (int i = 1; i < pos; i++) {
            prev = current;
            current = current.next;
        }
        
        System.out.println("✓ Menghapus: " + current.nim + " - " + current.nama);
        prev.next = current.next;
        
        // Update tail jika menghapus node terakhir
        if (prev.next == null) {
            tail = prev;
        }
        
        count--;
        System.out.println("✓ Data di posisi " + pos + " berhasil dihapus");
    }
    
    // 6. Delete from end (PERBAIKAN)
    public void deleteEnd() {
        if (head == null) {
            System.out.println("✗ List kosong");
            return;
        }
        
        if (head == tail) {
            System.out.println("✓ Menghapus: " + head.nim + " - " + head.nama);
            head = null;
            tail = null;
        } else {
            Node current = head;
            // Traverse sampai sebelum node terakhir
            while (current.next != tail) {
                current = current.next;
            }
            System.out.println("✓ Menghapus: " + tail.nim + " - " + tail.nama);
            current.next = null;
            tail = current;
        }
        count--;
        System.out.println("✓ Data berhasil dihapus dari akhir");
    }
    
    // 7. Delete first occurrence by NIM (PERBAIKAN)
    public void deleteFirstOccurrence(String nim) {
        if (head == null) {
            System.out.println("✗ List kosong");
            return;
        }
        
        // Cek apakah data di head
        if (head.nim.equals(nim)) {
            deleteBeginning();
            return;
        }
        
        Node current = head;
        Node prev = null;
        boolean found = false;
        
        // Cari node dengan NIM yang sesuai
        while (current != null) {
            if (current.nim.equals(nim)) {
                found = true;
                break;
            }
            prev = current;
            current = current.next;
        }
        
        if (!found) {
            System.out.println("✗ NIM " + nim + " tidak ditemukan");
            return;
        }
        
        System.out.println("✓ Menghapus: " + current.nim + " - " + current.nama);
        prev.next = current.next;
        
        // Update tail jika menghapus node terakhir
        if (prev.next == null) {
            tail = prev;
        }
        
        count--;
        System.out.println("✓ Data dengan NIM " + nim + " berhasil dihapus");
    }
    
    // 8. Show all data
    public void showData() {
        if (head == null) {
            System.out.println("✗ List kosong");
            return;
        }
        
        System.out.println("\n" + "=".repeat(50));
        System.out.println("          DAFTAR MAHASISWA");
        System.out.println("=".repeat(50));
        System.out.println("Total data: " + count);
        System.out.println("-".repeat(50));
        
        Node current = head;
        int no = 1;
        while (current != null) {
            System.out.printf("%2d. NIM: %-12s | Nama: %-25s%n", 
                no, current.nim, current.nama);
            current = current.next;
            no++;
        }
        System.out.println("=".repeat(50) + "\n");
    }
    
    // Method untuk mendapatkan jumlah data
    public int getCount() {
        return count;
    }
    
    public void displayMenu() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("        MENU SINGLY LINKED LIST");
        System.out.println("=".repeat(50));
        System.out.println("1. Insert at beginning");
        System.out.println("2. Insert at given position");
        System.out.println("3. Insert at end");
        System.out.println("4. Delete from beginning");
        System.out.println("5. Delete given position");
        System.out.println("6. Delete from end");
        System.out.println("7. Delete first occurrence (by NIM)");
        System.out.println("8. Show data");
        System.out.println("9. Exit");
        System.out.println("-".repeat(50));
    }
}

public class SinglyLinkedList {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LinkedListManager list = new LinkedListManager();
        
        while (true) {
            list.displayMenu();
            System.out.print("Pilih menu (1-9): ");
            
            int choice;
            try {
                choice = scanner.nextInt();
                scanner.nextLine(); // Membersihkan buffer
            } catch (Exception e) {
                System.out.println("✗ Input tidak valid! Harap masukkan angka.");
                scanner.nextLine(); // Membersihkan buffer
                continue;
            }
            
            switch (choice) {
                case 1: // Insert at beginning
                    System.out.println("\n--- INSERT DI AWAL ---");
                    System.out.print("Masukkan NIM : ");
                    String nim1 = scanner.nextLine();
                    System.out.print("Masukkan Nama: ");
                    String nama1 = scanner.nextLine();
                    list.insertBeginning(nim1, nama1);
                    break;
                    
                case 2: // Insert at given position
                    System.out.println("\n--- INSERT DI POSISI TERTENTU ---");
                    System.out.print("Masukkan posisi (1 - " + (list.getCount() + 1) + "): ");
                    int pos;
                    try {
                        pos = scanner.nextInt();
                        scanner.nextLine();
                    } catch (Exception e) {
                        System.out.println("✗ Posisi harus angka!");
                        scanner.nextLine();
                        break;
                    }
                    
                    System.out.print("Masukkan NIM : ");
                    String nim2 = scanner.nextLine();
                    System.out.print("Masukkan Nama: ");
                    String nama2 = scanner.nextLine();
                    list.insertPosition(pos, nim2, nama2);
                    break;
                    
                case 3: // Insert at end
                    System.out.println("\n--- INSERT DI AKHIR ---");
                    System.out.print("Masukkan NIM : ");
                    String nim3 = scanner.nextLine();
                    System.out.print("Masukkan Nama: ");
                    String nama3 = scanner.nextLine();
                    list.insertEnd(nim3, nama3);
                    break;
                    
                case 4: // Delete from beginning
                    System.out.println("\n--- HAPUS DARI AWAL ---");
                    list.deleteBeginning();
                    break;
                    
                case 5: // Delete at given position
                    System.out.println("\n--- HAPUS DI POSISI TERTENTU ---");
                    if (list.getCount() == 0) {
                        System.out.println("✗ List kosong!");
                        break;
                    }
                    
                    System.out.print("Masukkan posisi yang akan dihapus (1 - " + list.getCount() + "): ");
                    int delPos;
                    try {
                        delPos = scanner.nextInt();
                        scanner.nextLine();
                    } catch (Exception e) {
                        System.out.println("✗ Posisi harus angka!");
                        scanner.nextLine();
                        break;
                    }
                    list.deletePosition(delPos);
                    break;
                    
                case 6: // Delete from end
                    System.out.println("\n--- HAPUS DARI AKHIR ---");
                    list.deleteEnd();
                    break;
                    
                case 7: // Delete first occurrence by NIM
                    System.out.println("\n--- HAPUS BERDASARKAN NIM ---");
                    System.out.print("Masukkan NIM yang akan dihapus: ");
                    String delNim = scanner.nextLine();
                    list.deleteFirstOccurrence(delNim);
                    break;
                    
                case 8: // Show data
                    System.out.println("\n--- TAMPILKAN DATA ---");
                    list.showData();
                    break;
                    
                case 9: // Exit
                    System.out.println("\n" + "=".repeat(50));
                    System.out.println("     TERIMA KASIH! PROGRAM SELESAI");
                    System.out.println("=".repeat(50));
                    scanner.close();
                    return;
                    
                default:
                    System.out.println("\n✗ Pilihan tidak valid! Silakan pilih 1-9.");
            }
            
            // Tampilkan jumlah data setelah operasi (kecuali show data)
            if (choice != 8 && choice != 9) {
                System.out.println("ℹ Jumlah data sekarang: " + list.getCount());
            }
        }
    }
}

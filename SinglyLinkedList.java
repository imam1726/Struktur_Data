import java.util.Scanner;

class Node {
    String nim;
    String nama;
    Node next;
    
    Node(String nim, String nama) {
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
        head = null;
        tail = null;
        count = 0;
    }
    
    public void insertAwal(String nim, String nama) {
        Node node = new Node(nim, nama);
        if (head == null) {
            head = node;
            tail = node;
        } else {
            node.next = head;
            head = node;
        }
        count++;
        System.out.println("Data ditambahkan");
    }
    
    public void insertPosisi(int pos, String nim, String nama) {
        if (pos < 1 || pos > count + 1) {
            System.out.println("Posisi tidak valid");
            return;
        }
        
        if (pos == 1) {
            insertAwal(nim, nama);
            return;
        }
        
        if (pos == count + 1) {
            insertAkhir(nim, nama);
            return;
        }
        
        Node node = new Node(nim, nama);
        Node current = head;
        for (int i = 1; i < pos - 1; i++) {
            current = current.next;
        }
        node.next = current.next;
        current.next = node;
        count++;
        System.out.println("Data ditambahkan");
    }
    
    public void insertAkhir(String nim, String nama) {
        Node node = new Node(nim, nama);
        if (head == null) {
            head = node;
            tail = node;
        } else {
            tail.next = node;
            tail = node;
        }
        count++;
        System.out.println("Data ditambahkan");
    }
    
    public void hapusAwal() {
        if (head == null) {
            System.out.println("List kosong");
            return;
        }
        
        System.out.println("Menghapus: " + head.nim + " - " + head.nama);
        head = head.next;
        if (head == null) {
            tail = null;
        }
        count--;
        System.out.println("Data dihapus");
    }
    
    public void hapusPosisi(int pos) {
        if (head == null) {
            System.out.println("List kosong");
            return;
        }
        
        if (pos < 1 || pos > count) {
            System.out.println("Posisi tidak valid");
            return;
        }
        
        if (pos == 1) {
            hapusAwal();
            return;
        }
        
        Node current = head;
        for (int i = 1; i < pos - 1; i++) {
            current = current.next;
        }
        
        Node hapus = current.next;
        System.out.println("Menghapus: " + hapus.nim + " - " + hapus.nama);
        current.next = hapus.next;
        
        if (current.next == null) {
            tail = current;
        }
        count--;
        System.out.println("Data dihapus");
    }
    
    public void hapusAkhir() {
        if (head == null) {
            System.out.println("List kosong");
            return;
        }
        
        if (head == tail) {
            System.out.println("Menghapus: " + head.nim + " - " + head.nama);
            head = null;
            tail = null;
        } else {
            Node current = head;
            while (current.next != tail) {
                current = current.next;
            }
            System.out.println("Menghapus: " + tail.nim + " - " + tail.nama);
            current.next = null;
            tail = current;
        }
        count--;
        System.out.println("Data dihapus");
    }
    
    public void hapusNIM(String nim) {
        if (head == null) {
            System.out.println("List kosong");
            return;
        }
        
        if (head.nim.equals(nim)) {
            hapusAwal();
            return;
        }
        
        Node current = head;
        while (current.next != null && !current.next.nim.equals(nim)) {
            current = current.next;
        }
        
        if (current.next == null) {
            System.out.println("NIM tidak ditemukan");
            return;
        }
        
        Node hapus = current.next;
        System.out.println("Menghapus: " + hapus.nim + " - " + hapus.nama);
        current.next = hapus.next;
        
        if (current.next == null) {
            tail = current;
        }
        count--;
        System.out.println("Data dihapus");
    }
    
    public void tampilData() {
        if (head == null) {
            System.out.println("List kosong");
            return;
        }
        
        System.out.println("\nData Mahasiswa:");
        Node current = head;
        int no = 1;
        while (current != null) {
            System.out.println(no + ". " + current.nim + " - " + current.nama);
            current = current.next;
            no++;
        }
        System.out.println("Total: " + count + " data\n");
    }
    
    public int getCount() {
        return count;
    }
}

// CLASS UTAMA DENGAN MAIN METHOD
public class SinglyLinkedList {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        LinkedListManager list = new LinkedListManager();
        
        while (true) {
            System.out.println("\n=== MENU ===");
            System.out.println("1. Insert awal");
            System.out.println("2. Insert posisi");
            System.out.println("3. Insert akhir");
            System.out.println("4. Hapus awal");
            System.out.println("5. Hapus posisi");
            System.out.println("6. Hapus akhir");
            System.out.println("7. Hapus NIM");
            System.out.println("8. Tampil data");
            System.out.println("9. Keluar");
            System.out.print("Pilih: ");
            
            int pilih;
            try {
                pilih = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Input harus angka!");
                continue;
            }
            
            switch (pilih) {
                case 1:
                    System.out.print("NIM: ");
                    String nim1 = sc.nextLine();
                    System.out.print("Nama: ");
                    String nama1 = sc.nextLine();
                    list.insertAwal(nim1, nama1);
                    break;
                    
                case 2:
                    System.out.print("Posisi (1 - " + (list.getCount() + 1) + "): ");
                    int pos;
                    try {
                        pos = Integer.parseInt(sc.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Posisi harus angka!");
                        continue;
                    }
                    System.out.print("NIM: ");
                    String nim2 = sc.nextLine();
                    System.out.print("Nama: ");
                    String nama2 = sc.nextLine();
                    list.insertPosisi(pos, nim2, nama2);
                    break;
                    
                case 3:
                    System.out.print("NIM: ");
                    String nim3 = sc.nextLine();
                    System.out.print("Nama: ");
                    String nama3 = sc.nextLine();
                    list.insertAkhir(nim3, nama3);
                    break;
                    
                case 4:
                    list.hapusAwal();
                    break;
                    
                case 5:
                    if (list.getCount() == 0) {
                        System.out.println("List kosong!");
                        continue;
                    }
                    System.out.print("Posisi (1 - " + list.getCount() + "): ");
                    int posHapus;
                    try {
                        posHapus = Integer.parseInt(sc.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Posisi harus angka!");
                        continue;
                    }
                    list.hapusPosisi(posHapus);
                    break;
                    
                case 6:
                    list.hapusAkhir();
                    break;
                    
                case 7:
                    System.out.print("NIM yang akan dihapus: ");
                    String nimHapus = sc.nextLine();
                    list.hapusNIM(nimHapus);
                    break;
                    
                case 8:
                    list.tampilData();
                    break;
                    
                case 9:
                    System.out.println("Program selesai!");
                    sc.close();
                    return;
                    
                default:
                    System.out.println("Pilihan tidak valid!");
            }
        }
    }
}

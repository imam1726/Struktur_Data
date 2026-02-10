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

class LinkedList {
    private Node head;
    private int count;
    
    public LinkedList() {
        head = null;
        count = 0;
    }
    
    // 1. Insert at beginning
    public void insertFirst(String nim, String nama) {
        Node newNode = new Node(nim, nama);
        newNode.next = head;
        head = newNode;
        count++;
        System.out.println("Data ditambahkan di awal");
    }
    
    // 2. Insert at position
    public void insertAt(String nim, String nama, int pos) {
        if (pos < 1 || pos > count + 1) {
            System.out.println("Posisi tidak valid");
            return;
        }
        
        if (pos == 1) {
            insertFirst(nim, nama);
            return;
        }
        
        Node newNode = new Node(nim, nama);
        Node current = head;
        
        for (int i = 1; i < pos - 1; i++) {
            current = current.next;
        }
        
        newNode.next = current.next;
        current.next = newNode;
        count++;
        System.out.println("Data ditambahkan di posisi " + pos);
    }
    
    // 3. Insert at end
    public void insertLast(String nim, String nama) {
        Node newNode = new Node(nim, nama);
        
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        count++;
        System.out.println("Data ditambahkan di akhir");
    }
    
    // 4. Delete from beginning
    public void deleteFirst() {
        if (head == null) {
            System.out.println("List kosong");
            return;
        }
        
        System.out.println("Menghapus: " + head.nim + " - " + head.nama);
        head = head.next;
        count--;
    }
    
    // 5. Delete at position
    public void deleteAt(int pos) {
        if (pos < 1 || pos > count) {
            System.out.println("Posisi tidak valid");
            return;
        }
        
        if (pos == 1) {
            deleteFirst();
            return;
        }
        
        Node current = head;
        Node prev = null;
        
        for (int i = 1; i < pos; i++) {
            prev = current;
            current = current.next;
        }
        
        System.out.println("Menghapus: " + current.nim + " - " + current.nama);
        prev.next = current.next;
        count--;
    }
    
    // 6. Delete from end
    public void deleteLast() {
        if (head == null) {
            System.out.println("List kosong");
            return;
        }
        
        if (head.next == null) {
            System.out.println("Menghapus: " + head.nim + " - " + head.nama);
            head = null;
        } else {
            Node current = head;
            Node prev = null;
            
            while (current.next != null) {
                prev = current;
                current = current.next;
            }
            
            System.out.println("Menghapus: " + current.nim + " - " + current.nama);
            prev.next = null;
        }
        count--;
    }
    
    // 7. Delete by NIM
    public void deleteByNim(String nim) {
        if (head == null) {
            System.out.println("List kosong");
            return;
        }
        
        if (head.nim.equals(nim)) {
            deleteFirst();
            return;
        }
        
        Node current = head;
        Node prev = null;
        
        while (current != null && !current.nim.equals(nim)) {
            prev = current;
            current = current.next;
        }
        
        if (current == null) {
            System.out.println("NIM tidak ditemukan");
            return;
        }
        
        System.out.println("Menghapus: " + current.nim + " - " + current.nama);
        prev.next = current.next;
        count--;
    }
    
    // 8. Show all data
    public void show() {
        if (head == null) {
            System.out.println("List kosong");
            return;
        }
        
        System.out.println("\n=== Data Mahasiswa ===");
        System.out.println("Jumlah: " + count);
        
        Node current = head;
        int no = 1;
        while (current != null) {
            System.out.println(no + ". " + current.nim + " - " + current.nama);
            current = current.next;
            no++;
        }
        System.out.println();
    }
    
    public int getCount() {
        return count;
    }
}

public class SinglyLinkedList {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        LinkedList list = new LinkedList();
        
        while (true) {
            System.out.println("\n=== MENU ===");
            System.out.println("1. Insert di awal");
            System.out.println("2. Insert di posisi");
            System.out.println("3. Insert di akhir");
            System.out.println("4. Hapus dari awal");
            System.out.println("5. Hapus dari posisi");
            System.out.println("6. Hapus dari akhir");
            System.out.println("7. Hapus by NIM");
            System.out.println("8. Tampilkan data");
            System.out.println("9. Keluar");
            System.out.print("Pilih: ");
            
            int pilih = sc.nextInt();
            sc.nextLine();
            
            switch (pilih) {
                case 1:
                    System.out.print("NIM: ");
                    String nim1 = sc.nextLine();
                    System.out.print("Nama: ");
                    String nama1 = sc.nextLine();
                    list.insertFirst(nim1, nama1);
                    break;
                    
                case 2:
                    System.out.print("NIM: ");
                    String nim2 = sc.nextLine();
                    System.out.print("Nama: ");
                    String nama2 = sc.nextLine();
                    System.out.print("Posisi (1-" + (list.getCount() + 1) + "): ");
                    int pos = sc.nextInt();
                    sc.nextLine();
                    list.insertAt(nim2, nama2, pos);
                    break;
                    
                case 3:
                    System.out.print("NIM: ");
                    String nim3 = sc.nextLine();
                    System.out.print("Nama: ");
                    String nama3 = sc.nextLine();
                    list.insertLast(nim3, nama3);
                    break;
                    
                case 4:
                    list.deleteFirst();
                    break;
                    
                case 5:
                    if (list.getCount() == 0) {
                        System.out.println("List kosong");
                    } else {
                        System.out.print("Posisi (1-" + list.getCount() + "): ");
                        int posDel = sc.nextInt();
                        sc.nextLine();
                        list.deleteAt(posDel);
                    }
                    break;
                    
                case 6:
                    list.deleteLast();
                    break;
                    
                case 7:
                    System.out.print("NIM yang akan dihapus: ");
                    String nimDel = sc.nextLine();
                    list.deleteByNim(nimDel);
                    break;
                    
                case 8:
                    list.show();
                    break;
                    
                case 9:
                    System.out.println("Program selesai");
                    sc.close();
                    return;
                    
                default:
                    System.out.println("Pilihan salah");
            }
        }
    }
}
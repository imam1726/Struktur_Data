import java.util.Scanner;

public class CircularDoublyLinkedList {
    
    // Node sebagai static inner class
    static class Node {
        String berita;
        Node next;
        Node prev;
        
        Node(String berita) {
            this.berita = berita;
            this.next = null;
            this.prev = null;
        }
    }
    
    static Node head = null;
    static Node tail = null;
    static int size = 0;
    
    static void insertBerita(String berita) {
        Node newNode = new Node(berita);
        
        if (head == null) {
            head = newNode;
            tail = newNode;
            head.next = head;
            head.prev = head;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            newNode.next = head;
            head.prev = newNode;
            tail = newNode;
        }
        size++;
        System.out.println("Berita ditambahkan");
    }
    
    static boolean hapusBerita(int nomor) {
        if (head == null || nomor < 1 || nomor > size) {
            return false;
        }
        
        Node current = head;
        for (int i = 1; i < nomor; i++) {
            current = current.next;
        }
        
        if (size == 1) {
            head = null;
            tail = null;
        } else {
            current.prev.next = current.next;
            current.next.prev = current.prev;
            
            if (current == head) {
                head = current.next;
            }
            if (current == tail) {
                tail = current.prev;
            }
        }
        size--;
        return true;
    }
    
    static void tampilForward() throws InterruptedException {
        if (head == null) {
            System.out.println("Tidak ada berita");
            return;
        }
        
        System.out.println("\nBERITA FORWARD:");
        Node current = head;
        int nomor = 1;
        do {
            System.out.println(nomor + ". " + current.berita);
            current = current.next;
            nomor++;
            Thread.sleep(3000);
        } while (current != head);
    }
    
    static void tampilBackward() throws InterruptedException {
        if (head == null) {
            System.out.println("Tidak ada berita");
            return;
        }
        
        System.out.println("\nBERITA BACKWARD:");
        Node current = tail;
        int nomor = size;
        do {
            System.out.println(nomor + ". " + current.berita);
            current = current.prev;
            nomor--;
            Thread.sleep(3000);
        } while (current != tail);
    }
    
    static void tampilBerita(int nomor) {
        if (head == null || nomor < 1 || nomor > size) {
            System.out.println("Nomor tidak valid");
            return;
        }
        
        Node current = head;
        for (int i = 1; i < nomor; i++) {
            current = current.next;
        }
        System.out.println("Berita " + nomor + ": " + current.berita);
    }
    
    static void tampilSemua() {
        if (head == null) {
            System.out.println("Tidak ada berita");
            return;
        }
        
        System.out.println("\nDAFTAR BERITA:");
        Node current = head;
        int nomor = 1;
        do {
            System.out.println(nomor + ". " + current.berita);
            current = current.next;
            nomor++;
        } while (current != head);
    }
    
    public static void main(String[] args) throws InterruptedException {
        Scanner input = new Scanner(System.in);
        int pilih;
        
        do {
            System.out.println("\nMENU:");
            System.out.println("1. Insert berita");
            System.out.println("2. Hapus berita");
            System.out.println("3. Tampilkan berita secara forward");
            System.out.println("4. Tampilkan berita secara backward");
            System.out.println("5. Tampilkan berita tertentu");
            System.out.println("6. Exit");
            System.out.print("Pilih: ");
            pilih = input.nextInt();
            input.nextLine();
            
            switch(pilih) {
                case 1:
                    System.out.print("Masukkan teks berita: ");
                    String berita = input.nextLine();
                    insertBerita(berita);
                    break;
                    
                case 2:
                    tampilSemua();
                    if (head != null) {
                        System.out.print("Masukkan nomor berita yang akan dihapus: ");
                        int nomorHapus = input.nextInt();
                        if (hapusBerita(nomorHapus)) {
                            System.out.println("Berita nomor " + nomorHapus + " berhasil dihapus");
                        } else {
                            System.out.println("Gagal menghapus");
                        }
                    }
                    break;
                    
                case 3:
                    tampilForward();
                    break;
                    
                case 4:
                    tampilBackward();
                    break;
                    
                case 5:
                    tampilSemua();
                    if (head != null) {
                        System.out.print("Masukkan nomor berita yang ingin ditampilkan: ");
                        int nomorTampil = input.nextInt();
                        tampilBerita(nomorTampil);
                    }
                    break;
                    
                case 6:
                    System.out.println("Program selesai");
                    break;
                    
                default:
                    System.out.println("Pilihan tidak valid");
            }
        } while (pilih != 6);
        
        input.close();
    }
}
import java.util.Random;
import java.util.HashSet;
import java.util.Scanner;

class DoubleHashingHashTable {
    private Integer[] table;
    private int size;
    private final int EMPTY = -1;

    public DoubleHashingHashTable(int size) {
        this.size = size;
        table = new Integer[size];
        for (int i = 0; i < size; i++) table[i] = EMPTY;
    }

    private int hash1(int key) {
        return Math.abs(key) % size;
    }

    private int hash2(int key) {
        // second hash function: prime less than size, e.g., 97
        return 1 + (Math.abs(key) % 97);
    }

    public boolean insert(int key) {
        int index = hash1(key);
        int step = hash2(key);
        int i = 0;
        while (table[index] != EMPTY && table[index] != key) {
            i++;
            index = (hash1(key) + i * step) % size;
            if (i >= size) return false;
        }
        if (table[index] == key) return false;
        table[index] = key;
        return true;
    }

    public boolean delete(int key) {
        int index = hash1(key);
        int step = hash2(key);
        int i = 0;
        while (table[index] != EMPTY) {
            if (table[index] == key) {
                table[index] = EMPTY;
                return true;
            }
            i++;
            index = (hash1(key) + i * step) % size;
            if (i >= size) break;
        }
        return false;
    }

    public boolean search(int key) {
        int index = hash1(key);
        int step = hash2(key);
        int i = 0;
        while (table[index] != EMPTY) {
            if (table[index] == key) return true;
            i++;
            index = (hash1(key) + i * step) % size;
            if (i >= size) break;
        }
        return false;
    }

    public SearchResult searchWithDetail(int key) {
        int h1 = hash1(key);
        int step = hash2(key);
        System.out.println("\n=== PROSES PENCARIAN DOUBLE HASHING ===");
        System.out.println("Data dicari: " + key);
        System.out.println("Hash1 (h1) = " + h1);
        System.out.println("Hash2 (step) = " + step);
        System.out.println("Rumus: index = (h1 + i * step) mod size");
        int i = 0;
        int index = h1;
        while (table[index] != EMPTY && i < size) {
            System.out.println("  i=" + i + " -> index " + index + " : " + table[index]);
            if (table[index] == key) {
                System.out.println("  >>> DITEMUKAN pada indeks " + index);
                return new SearchResult(true, index, i);
            }
            i++;
            index = (h1 + i * step) % size;
        }
        System.out.println("  >>> TIDAK DITEMUKAN setelah " + i + " langkah");
        return new SearchResult(false, -1, -1);
    }

    public void display() {
        System.out.println("\nHash Table (Double Hashing):");
        for (int i = 0; i < size; i++) {
            System.out.printf("%3d: ", i);
            if (table[i] == EMPTY) System.out.println("null");
            else System.out.println(table[i]);
        }
    }
}

public class DoubleHashing {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DoubleHashingHashTable ht = new DoubleHashingHashTable(101);
        Random rand = new Random();
        HashSet<Integer> unique = new HashSet<>();
        while (unique.size() < 100) unique.add(rand.nextInt(1000));
        for (int num : unique) ht.insert(num);
        System.out.println("100 unique random numbers inserted.");

        while (true) {
            System.out.println("\n--- MENU DOUBLE HASHING ---");
            System.out.println("1. INPUT DATA");
            System.out.println("2. HAPUS DATA");
            System.out.println("3. CARI DATA (detail)");
            System.out.println("4. TAMPILKAN DATA");
            System.out.println("5. EXIT");
            System.out.print("Pilihan: ");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Angka: ");
                    int in = scanner.nextInt();
                    if (ht.search(in)) System.out.println("Sudah ada.");
                    else if (ht.insert(in)) System.out.println("Berhasil ditambahkan.");
                    else System.out.println("Tabel penuh.");
                    break;
                case 2:
                    System.out.print("Angka: ");
                    int del = scanner.nextInt();
                    if (ht.delete(del)) System.out.println("Terhapus.");
                    else System.out.println("Tidak ditemukan.");
                    break;
                case 3:
                    System.out.print("Angka: ");
                    int src = scanner.nextInt();
                    ht.searchWithDetail(src);
                    break;
                case 4:
                    ht.display();
                    break;
                case 5:
                    System.out.println("Keluar.");
                    scanner.close();
                    return;
                default: System.out.println("Pilihan salah.");
            }
        }
    }
}
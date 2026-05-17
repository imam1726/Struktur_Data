import java.util.Random;
import java.util.HashSet;
import java.util.Scanner;

class LinearProbingHashTable {
    private Integer[] table;
    private int size;
    private final int EMPTY = -1; // tanda slot kosong

    public LinearProbingHashTable(int size) {
        this.size = size;
        table = new Integer[size];
        for (int i = 0; i < size; i++) table[i] = EMPTY;
    }

    private int hash(int key) {
        return Math.abs(key) % size;
    }

    public boolean insert(int key) {
        int index = hash(key);
        int startIndex = index;
        int i = 0;
        while (table[index] != EMPTY && table[index] != key) {
            i++;
            index = (startIndex + i) % size;
            if (index == startIndex) return false; // tabel penuh
        }
        if (table[index] == key) return false; // duplikat
        table[index] = key;
        return true;
    }

    public boolean delete(int key) {
        int index = hash(key);
        int startIndex = index;
        int i = 0;
        while (table[index] != EMPTY) {
            if (table[index] == key) {
                table[index] = EMPTY;
                // rehash untuk menutup lubang (opsional, tapi perlu agar pencarian tetap benar)
                rehashAfterDelete(index);
                return true;
            }
            i++;
            index = (startIndex + i) % size;
            if (index == startIndex) break;
        }
        return false;
    }

    // Rehash sederhana untuk linear probing: pindahkan elemen berikutnya jika perlu
    private void rehashAfterDelete(int deletedIndex) {
        int nextIndex = (deletedIndex + 1) % size;
        while (table[nextIndex] != EMPTY) {
            int key = table[nextIndex];
            int originalHash = hash(key);
            // jika key seharusnya berada di posisi <= deletedIndex atau melewati karena probing
            // kita pindahkan ke deletedIndex jika memungkinkan
            boolean shouldMove = false;
            if (originalHash <= deletedIndex) {
                if (nextIndex < originalHash || nextIndex > deletedIndex) shouldMove = true;
            } else {
                if (nextIndex > originalHash && nextIndex > deletedIndex) shouldMove = true;
            }
            if (shouldMove) {
                table[deletedIndex] = key;
                table[nextIndex] = EMPTY;
                deletedIndex = nextIndex;
            }
            nextIndex = (nextIndex + 1) % size;
        }
    }

    public boolean search(int key) {
        int index = hash(key);
        int startIndex = index;
        int i = 0;
        while (table[index] != EMPTY) {
            if (table[index] == key) return true;
            i++;
            index = (startIndex + i) % size;
            if (index == startIndex) break;
        }
        return false;
    }

    public SearchResult searchWithDetail(int key) {
        int index = hash(key);
        int startIndex = index;
        int i = 0;
        System.out.println("\n=== PROSES PENCARIAN LINEAR PROBING ===");
        System.out.println("Data dicari: " + key);
        System.out.println("Hash awal: " + index);
        System.out.println("Probing step: 1, 2, 3, ... (linear)");
        int step = 0;
        while (table[index] != EMPTY && step < size) {
            System.out.println("  Step " + step + ": index " + index + " -> " + table[index]);
            if (table[index] == key) {
                System.out.println("  >>> DITEMUKAN pada indeks " + index);
                return new SearchResult(true, index, step);
            }
            step++;
            index = (startIndex + step) % size;
        }
        System.out.println("  >>> TIDAK DITEMUKAN setelah " + step + " langkah");
        return new SearchResult(false, -1, -1);
    }

    public void display() {
        System.out.println("\nHash Table (Linear Probing):");
        for (int i = 0; i < size; i++) {
            System.out.printf("%3d: ", i);
            if (table[i] == EMPTY) System.out.println("null");
            else System.out.println(table[i]);
        }
    }
}

class SearchResult {
    boolean found;
    int index;
    int steps;
    SearchResult(boolean found, int index, int steps) {
        this.found = found;
        this.index = index;
        this.steps = steps;
    }
}

public class HashLinearProbing {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LinearProbingHashTable ht = new LinearProbingHashTable(101);
        Random rand = new Random();
        HashSet<Integer> unique = new HashSet<>();
        while (unique.size() < 100) unique.add(rand.nextInt(1000));
        for (int num : unique) ht.insert(num);
        System.out.println("100 unique random numbers inserted.");

        while (true) {
            System.out.println("\n--- MENU LINEAR PROBING ---");
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
                    else System.out.println("Tabel penuh atau gagal.");
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
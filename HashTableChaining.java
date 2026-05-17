import java.util.LinkedList;
import java.util.Random;
import java.util.HashSet;
import java.util.Scanner;

class HashTable {
    private LinkedList<Integer>[] table;
    private int size;

    @SuppressWarnings("unchecked")
    public HashTable(int size) {
        this.size = size;
        table = new LinkedList[size];
        for (int i = 0; i < size; i++) {
            table[i] = new LinkedList<>();
        }
    }

    private int hashFunction(int key) {
        return Math.abs(key) % size;
    }

    public void insert(int key) {
        int index = hashFunction(key);
        if (!table[index].contains(key)) {
            table[index].add(key);
        }
    }

    public boolean delete(int key) {
        int index = hashFunction(key);
        return table[index].remove(Integer.valueOf(key));
    }

    public boolean search(int key) {
        int index = hashFunction(key);
        return table[index].contains(key);
    }

    public SearchResult searchWithDetail(int key) {
        int hashValue = Math.abs(key);
        int index = hashValue % size;
        
        System.out.println("\n=== PROSES PENCARIAN ===");
        System.out.println("Data yang dicari: " + key);
        System.out.println("Nilai hash (abs): " + hashValue);
        System.out.println("Index hasil modulo (" + hashValue + " % " + size + ") = " + index);
        System.out.println("Bucket index: " + index);
        
        LinkedList<Integer> chain = table[index];
        
        if (chain.isEmpty()) {
            System.out.println("Status: Bucket kosong");
            return new SearchResult(false, index, -1);
        }
        
        System.out.print("Isi bucket [" + index + "]: ");
        for (int i = 0; i < chain.size(); i++) {
            System.out.print(chain.get(i));
            if (i < chain.size() - 1) System.out.print(" -> ");
        }
        System.out.println();
        
        int position = -1;
        for (int i = 0; i < chain.size(); i++) {
            System.out.println("  Memeriksa posisi ke-" + i + ": " + chain.get(i));
            if (chain.get(i) == key) {
                position = i;
                System.out.println("  >>> DATA DITEMUKAN pada posisi ke-" + i + " dalam chain");
                break;
            }
        }
        
        if (position == -1) {
            System.out.println("  >>> DATA TIDAK DITEMUKAN dalam chain");
        }
        
        return new SearchResult(position != -1, index, position);
    }

    public void display() {
        System.out.println("\nHash Table Contents:");
        for (int i = 0; i < size; i++) {
            System.out.print(i + ": ");
            if (table[i].isEmpty()) {
                System.out.println("null");
            } else {
                for (int num : table[i]) {
                    System.out.print(num + " -> ");
                }
                System.out.println("null");
            }
        }
    }
}

class SearchResult {
    boolean found;
    int bucketIndex;
    int positionInChain;
    
    SearchResult(boolean found, int bucketIndex, int positionInChain) {
        this.found = found;
        this.bucketIndex = bucketIndex;
        this.positionInChain = positionInChain;
    }
}

public class HashTableChaining {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HashTable hashTable = new HashTable(101);
        Random rand = new Random();
        HashSet<Integer> uniqueNumbers = new HashSet<>();

        System.out.println("Generating 100 unique random numbers...");
        while (uniqueNumbers.size() < 100) {
            uniqueNumbers.add(rand.nextInt(1000));
        }

        for (int num : uniqueNumbers) {
            hashTable.insert(num);
        }

        System.out.println("100 unique random numbers have been inserted.");

        while (true) {
            System.out.println("\n--- MENU ---");
            System.out.println("1. INPUT DATA");
            System.out.println("2. HAPUS DATA");
            System.out.println("3. CARI DATA");
            System.out.println("4. TAMPILKAN DATA");
            System.out.println("5. EXIT");
            System.out.print("Pilihan: ");
            
            int choice;
            try {
                choice = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Input harus angka!");
                scanner.next();
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.print("Masukkan angka yang akan ditambahkan: ");
                    int input = scanner.nextInt();
                    if (hashTable.search(input)) {
                        System.out.println("Data sudah ada!");
                    } else {
                        hashTable.insert(input);
                        System.out.println("Data berhasil ditambahkan.");
                    }
                    break;
                case 2:
                    System.out.print("Masukkan angka yang akan dihapus: ");
                    int del = scanner.nextInt();
                    if (hashTable.delete(del)) {
                        System.out.println("Data berhasil dihapus.");
                    } else {
                        System.out.println("Data tidak ditemukan.");
                    }
                    break;
                case 3:
                    System.out.print("Masukkan angka yang dicari: ");
                    int search = scanner.nextInt();
                    SearchResult result = hashTable.searchWithDetail(search);
                    System.out.println("\n=== HASIL PENCARIAN ===");
                    if (result.found) {
                        System.out.println("STATUS: DATA DITEMUKAN");
                        System.out.println("Lokasi: Bucket index [" + result.bucketIndex + "]");
                        System.out.println("Posisi dalam chain: ke-" + result.positionInChain);
                    } else {
                        System.out.println("STATUS: DATA TIDAK DITEMUKAN");
                        System.out.println("Bucket yang diperiksa: [" + result.bucketIndex + "]");
                    }
                    break;
                case 4:
                    hashTable.display();
                    break;
                case 5:
                    System.out.println("Terima kasih! Keluar program.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Pilihan tidak valid. Masukkan 1-5.");
            }
        }
    }
}
import java.io.*;
import java.util.*;

class Node {
    int id;
    String nama;
    
    Node(int id, String nama) {
        this.id = id;
        this.nama = nama;
    }
}

class MinHeap {
    ArrayList<Node> heap;
    
    MinHeap() {
        heap = new ArrayList<>();
    }
    
    // Tambah data ke min-heap
    void insert(int id, String nama) {
        heap.add(new Node(id, nama));
        heapifyUp(heap.size() - 1);
    }
    
    void heapifyUp(int idx) {
        int parent = (idx - 1) / 2;
        if (idx > 0 && heap.get(parent).id > heap.get(idx).id) {
            Node temp = heap.get(parent);
            heap.set(parent, heap.get(idx));
            heap.set(idx, temp);
            heapifyUp(parent);
        }
    }
    
    void heapifyDown(int idx) {
        int smallest = idx;
        int left = 2 * idx + 1;
        int right = 2 * idx + 2;
        
        if (left < heap.size() && heap.get(left).id < heap.get(smallest).id) {
            smallest = left;
        }
        if (right < heap.size() && heap.get(right).id < heap.get(smallest).id) {
            smallest = right;
        }
        
        if (smallest != idx) {
            Node temp = heap.get(idx);
            heap.set(idx, heap.get(smallest));
            heap.set(smallest, temp);
            heapifyDown(smallest);
        }
    }
    
    // Hapus data dari min-heap berdasarkan ID
    boolean deleteById(int id) {
        for (int i = 0; i < heap.size(); i++) {
            if (heap.get(i).id == id) {
                heap.set(i, heap.get(heap.size() - 1));
                heap.remove(heap.size() - 1);
                heapifyDown(i);
                heapifyUp(i);
                return true;
            }
        }
        return false;
    }
    
    // Mendapatkan data urut ascending (tanpa merusak heap asli)
    ArrayList<Node> getSorted() {
        ArrayList<Node> temp = new ArrayList<>();
        for (Node n : heap) {
            temp.add(new Node(n.id, n.nama));
        }
        
        ArrayList<Node> result = new ArrayList<>();
        
        while (!temp.isEmpty()) {
            // Extract min
            result.add(temp.get(0));
            temp.set(0, temp.get(temp.size() - 1));
            temp.remove(temp.size() - 1);
            
            int idx = 0;
            while (true) {
                int smallest = idx;
                int left = 2 * idx + 1;
                int right = 2 * idx + 2;
                
                if (left < temp.size() && temp.get(left).id < temp.get(smallest).id) {
                    smallest = left;
                }
                if (right < temp.size() && temp.get(right).id < temp.get(smallest).id) {
                    smallest = right;
                }
                
                if (smallest != idx) {
                    Node swap = temp.get(idx);
                    temp.set(idx, temp.get(smallest));
                    temp.set(smallest, swap);
                    idx = smallest;
                } else {
                    break;
                }
            }
        }
        return result;
    }
}

class MaxHeap {
    ArrayList<Node> heap;
    
    MaxHeap() {
        heap = new ArrayList<>();
    }
    
    // Tambah data ke max-heap
    void insert(int id, String nama) {
        heap.add(new Node(id, nama));
        heapifyUp(heap.size() - 1);
    }
    
    void heapifyUp(int idx) {
        int parent = (idx - 1) / 2;
        if (idx > 0 && heap.get(parent).id < heap.get(idx).id) {
            Node temp = heap.get(parent);
            heap.set(parent, heap.get(idx));
            heap.set(idx, temp);
            heapifyUp(parent);
        }
    }
    
    void heapifyDown(int idx) {
        int largest = idx;
        int left = 2 * idx + 1;
        int right = 2 * idx + 2;
        
        if (left < heap.size() && heap.get(left).id > heap.get(largest).id) {
            largest = left;
        }
        if (right < heap.size() && heap.get(right).id > heap.get(largest).id) {
            largest = right;
        }
        
        if (largest != idx) {
            Node temp = heap.get(idx);
            heap.set(idx, heap.get(largest));
            heap.set(largest, temp);
            heapifyDown(largest);
        }
    }
    
    // Hapus data dari max-heap berdasarkan ID
    boolean deleteById(int id) {
        for (int i = 0; i < heap.size(); i++) {
            if (heap.get(i).id == id) {
                heap.set(i, heap.get(heap.size() - 1));
                heap.remove(heap.size() - 1);
                heapifyDown(i);
                heapifyUp(i);
                return true;
            }
        }
        return false;
    }
    
    // Mendapatkan data urut descending (tanpa merusak heap asli)
    ArrayList<Node> getSorted() {
        ArrayList<Node> temp = new ArrayList<>();
        for (Node n : heap) {
            temp.add(new Node(n.id, n.nama));
        }
        
        ArrayList<Node> result = new ArrayList<>();
        
        while (!temp.isEmpty()) {
            // Extract max
            result.add(temp.get(0));
            temp.set(0, temp.get(temp.size() - 1));
            temp.remove(temp.size() - 1);
            
            int idx = 0;
            while (true) {
                int largest = idx;
                int left = 2 * idx + 1;
                int right = 2 * idx + 2;
                
                if (left < temp.size() && temp.get(left).id > temp.get(largest).id) {
                    largest = left;
                }
                if (right < temp.size() && temp.get(right).id > temp.get(largest).id) {
                    largest = right;
                }
                
                if (largest != idx) {
                    Node swap = temp.get(idx);
                    temp.set(idx, temp.get(largest));
                    temp.set(largest, swap);
                    idx = largest;
                } else {
                    break;
                }
            }
        }
        return result;
    }
}

public class HeapDataStructure {
    MinHeap minHeap;
    MaxHeap maxHeap;
    
    HeapDataStructure() {
        minHeap = new MinHeap();
        maxHeap = new MaxHeap();
    }
    
    // (1) Tambah data ke min-heap dan max-heap sekaligus
    void tambahData(int id, String nama) {
        minHeap.insert(id, nama);
        maxHeap.insert(id, nama);
        System.out.println("✓ Data berhasil ditambahkan: (" + id + ", " + nama + ")");
    }
    
    // (2) Tampilkan data ascending menggunakan Min-Heap
    void tampilAscending() {
        if (minHeap.heap.isEmpty()) {
            System.out.println("\n⚠ Min-Heap kosong!");
            return;
        }
        System.out.println("\n=== DATA URUT ASCENDING (MIN-HEAP) ===");
        System.out.println("ID\t\tNama");
        System.out.println("--------------------------------");
        ArrayList<Node> sortedData = minHeap.getSorted();
        for (Node node : sortedData) {
            System.out.println(node.id + "\t\t" + node.nama);
        }
    }
    
    // (3) Tampilkan data descending menggunakan Max-Heap
    void tampilDescending() {
        if (maxHeap.heap.isEmpty()) {
            System.out.println("\n⚠ Max-Heap kosong!");
            return;
        }
        System.out.println("\n=== DATA URUT DESCENDING (MAX-HEAP) ===");
        System.out.println("ID\t\tNama");
        System.out.println("--------------------------------");
        ArrayList<Node> sortedData = maxHeap.getSorted();
        for (Node node : sortedData) {
            System.out.println(node.id + "\t\t" + node.nama);
        }
    }
    
    // (4) Hapus data dari min-heap
    void hapusDariMinHeap(int id) {
        if (minHeap.deleteById(id)) {
            System.out.println("✓ Data ID " + id + " berhasil dihapus dari Min-Heap");
        } else {
            System.out.println("✗ Data ID " + id + " tidak ditemukan di Min-Heap");
        }
    }
    
    // (5) Hapus data dari max-heap
    void hapusDariMaxHeap(int id) {
        if (maxHeap.deleteById(id)) {
            System.out.println("✓ Data ID " + id + " berhasil dihapus dari Max-Heap");
        } else {
            System.out.println("✗ Data ID " + id + " tidak ditemukan di Max-Heap");
        }
    }
    
    // Load data awal dari CSV (path langsung di kodingan)
    void loadFromCSV() {
        String filename = "/Users/clumsy/coding/struktur-data-py-java/HeapDataStructure/data.csv";
        int count = 0;
        
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            boolean firstLine = true;
            
            while ((line = br.readLine()) != null) {
                if (firstLine) {
                    firstLine = false;
                    continue;
                }
                if (line.trim().isEmpty()) continue;
                
                String[] data = line.split(",");
                if (data.length >= 2) {
                    try {
                        int id = Integer.parseInt(data[0].trim());
                        String nama = data[1].trim();
                        tambahData(id, nama);
                        count++;
                    } catch (NumberFormatException e) {
                        System.out.println("Baris tidak valid: " + line);
                    }
                }
            }
            System.out.println("\n✅ Data berhasil dimuat dari " + filename);
            System.out.println("Total data: " + count);
        } catch (FileNotFoundException e) {
            System.out.println("\n❌ File tidak ditemukan: " + filename);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    public static void main(String[] args) {
        HeapDataStructure heap = new HeapDataStructure();
        Scanner scanner = new Scanner(System.in);
        
        // Starting data awal - load dari CSV
        System.out.println("Memuat data dari file CSV...");
        heap.loadFromCSV();
        
        while (true) {
            System.out.println("\n╔════════════════════════════════════════╗");
            System.out.println("║          HEAP DATA STRUCTURE           ║");
            System.out.println("╠════════════════════════════════════════╣");
            System.out.println("║ 1. Tambah data ke Min-Heap & Max-Heap  ║");
            System.out.println("║ 2. Tampil ascending (Min-Heap)         ║");
            System.out.println("║ 3. Tampil descending (Max-Heap)        ║");
            System.out.println("║ 4. Hapus data dari Min-Heap            ║");
            System.out.println("║ 5. Hapus data dari Max-Heap            ║");
            System.out.println("║ 0. Keluar                              ║");
            System.out.println("╚════════════════════════════════════════╝");
            System.out.print("Pilihan: ");
            
            String pilihan = scanner.nextLine().trim();
            
            if (pilihan.equals("1")) {
                try {
                    System.out.print("Masukkan ID: ");
                    int id = Integer.parseInt(scanner.nextLine());
                    System.out.print("Masukkan Nama: ");
                    String nama = scanner.nextLine();
                    heap.tambahData(id, nama);
                } catch (NumberFormatException e) {
                    System.out.println("ID harus berupa angka!");
                }
            } else if (pilihan.equals("2")) {
                heap.tampilAscending();
            } else if (pilihan.equals("3")) {
                heap.tampilDescending();
            } else if (pilihan.equals("4")) {
                try {
                    System.out.print("Masukkan ID yang akan dihapus dari Min-Heap: ");
                    int id = Integer.parseInt(scanner.nextLine());
                    heap.hapusDariMinHeap(id);
                } catch (NumberFormatException e) {
                    System.out.println("ID harus berupa angka!");
                }
            } else if (pilihan.equals("5")) {
                try {
                    System.out.print("Masukkan ID yang akan dihapus dari Max-Heap: ");
                    int id = Integer.parseInt(scanner.nextLine());
                    heap.hapusDariMaxHeap(id);
                } catch (NumberFormatException e) {
                    System.out.println("ID harus berupa angka!");
                }
            } else if (pilihan.equals("0")) {
                System.out.println("Terima kasih!");
                break;
            } else {
                System.out.println("Pilihan tidak valid!");
            }
        }
        
        scanner.close();
    }
}
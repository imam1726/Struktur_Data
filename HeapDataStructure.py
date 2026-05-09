import csv

class Node:
    def __init__(self, id, nama):
        self.id = id
        self.nama = nama

class MinHeap:
    def __init__(self):
        self.heap = []
    
    def insert(self, id, nama):
        """Tambah data ke min-heap"""
        self.heap.append(Node(id, nama))
        self._heapify_up(len(self.heap) - 1)
    
    def _heapify_up(self, idx):
        parent = (idx - 1) // 2
        if idx > 0 and self.heap[parent].id > self.heap[idx].id:
            self.heap[parent], self.heap[idx] = self.heap[idx], self.heap[parent]
            self._heapify_up(parent)
    
    def _heapify_down(self, idx):
        smallest = idx
        left = 2 * idx + 1
        right = 2 * idx + 2
        
        if left < len(self.heap) and self.heap[left].id < self.heap[smallest].id:
            smallest = left
        if right < len(self.heap) and self.heap[right].id < self.heap[smallest].id:
            smallest = right
        
        if smallest != idx:
            self.heap[idx], self.heap[smallest] = self.heap[smallest], self.heap[idx]
            self._heapify_down(smallest)
    
    def delete_by_id(self, id):
        """Hapus data dari min-heap berdasarkan ID"""
        for i in range(len(self.heap)):
            if self.heap[i].id == id:
                self.heap[i] = self.heap[-1]
                self.heap.pop()
                self._heapify_down(i)
                self._heapify_up(i)
                return True
        return False
    
    def get_sorted(self):
        """Mendapatkan data urut ascending (tanpa merusak heap asli)"""
        temp = self.heap.copy()
        result = []
        while temp:
            # Extract min
            result.append(temp[0])
            temp[0] = temp[-1]
            temp.pop()
            idx = 0
            while True:
                smallest = idx
                left = 2 * idx + 1
                right = 2 * idx + 2
                if left < len(temp) and temp[left].id < temp[smallest].id:
                    smallest = left
                if right < len(temp) and temp[right].id < temp[smallest].id:
                    smallest = right
                if smallest != idx:
                    temp[idx], temp[smallest] = temp[smallest], temp[idx]
                    idx = smallest
                else:
                    break
        return result
    
    def display(self):
        for node in self.heap:
            print(f"{node.id}\t\t{node.nama}")

class MaxHeap:
    def __init__(self):
        self.heap = []
    
    def insert(self, id, nama):
        """Tambah data ke max-heap"""
        self.heap.append(Node(id, nama))
        self._heapify_up(len(self.heap) - 1)
    
    def _heapify_up(self, idx):
        parent = (idx - 1) // 2
        if idx > 0 and self.heap[parent].id < self.heap[idx].id:
            self.heap[parent], self.heap[idx] = self.heap[idx], self.heap[parent]
            self._heapify_up(parent)
    
    def _heapify_down(self, idx):
        largest = idx
        left = 2 * idx + 1
        right = 2 * idx + 2
        
        if left < len(self.heap) and self.heap[left].id > self.heap[largest].id:
            largest = left
        if right < len(self.heap) and self.heap[right].id > self.heap[largest].id:
            largest = right
        
        if largest != idx:
            self.heap[idx], self.heap[largest] = self.heap[largest], self.heap[idx]
            self._heapify_down(largest)
    
    def delete_by_id(self, id):
        """Hapus data dari max-heap berdasarkan ID"""
        for i in range(len(self.heap)):
            if self.heap[i].id == id:
                self.heap[i] = self.heap[-1]
                self.heap.pop()
                self._heapify_down(i)
                self._heapify_up(i)
                return True
        return False
    
    def get_sorted(self):
        """Mendapatkan data urut descending (tanpa merusak heap asli)"""
        temp = self.heap.copy()
        result = []
        while temp:
            # Extract max
            result.append(temp[0])
            temp[0] = temp[-1]
            temp.pop()
            idx = 0
            while True:
                largest = idx
                left = 2 * idx + 1
                right = 2 * idx + 2
                if left < len(temp) and temp[left].id > temp[largest].id:
                    largest = left
                if right < len(temp) and temp[right].id > temp[largest].id:
                    largest = right
                if largest != idx:
                    temp[idx], temp[largest] = temp[largest], temp[idx]
                    idx = largest
                else:
                    break
        return result
    
    def display(self):
        for node in self.heap:
            print(f"{node.id}\t\t{node.nama}")

class HeapDataStructure:
    def __init__(self):
        self.minHeap = MinHeap()
        self.maxHeap = MaxHeap()
    
    # (1) Tambah data ke min-heap dan max-heap sekaligus
    def tambahData(self, id, nama):
        self.minHeap.insert(id, nama)
        self.maxHeap.insert(id, nama)
        print(f"✓ Data berhasil ditambahkan: ({id}, {nama})")
    
    # (2) Tampilkan data ascending menggunakan Min-Heap
    def tampilAscending(self):
        if not self.minHeap.heap:
            print("\n⚠ Min-Heap kosong!")
            return
        print("\n=== DATA URUT ASCENDING (MIN-HEAP) ===")
        print("ID\t\tNama")
        print("--------------------------------")
        sorted_data = self.minHeap.get_sorted()
        for node in sorted_data:
            print(f"{node.id}\t\t{node.nama}")
    
    # (3) Tampilkan data descending menggunakan Max-Heap
    def tampilDescending(self):
        if not self.maxHeap.heap:
            print("\n⚠ Max-Heap kosong!")
            return
        print("\n=== DATA URUT DESCENDING (MAX-HEAP) ===")
        print("ID\t\tNama")
        print("--------------------------------")
        sorted_data = self.maxHeap.get_sorted()
        for node in sorted_data:
            print(f"{node.id}\t\t{node.nama}")
    
    # (4) Hapus data dari min-heap
    def hapusDariMinHeap(self, id):
        if self.minHeap.delete_by_id(id):
            print(f"✓ Data ID {id} berhasil dihapus dari Min-Heap")
        else:
            print(f"✗ Data ID {id} tidak ditemukan di Min-Heap")
    
    # (5) Hapus data dari max-heap
    def hapusDariMaxHeap(self, id):
        if self.maxHeap.delete_by_id(id):
            print(f"✓ Data ID {id} berhasil dihapus dari Max-Heap")
        else:
            print(f"✗ Data ID {id} tidak ditemukan di Max-Heap")
    
    # Load data awal dari CSV (path langsung di kodingan)
    def loadFromCSV(self):
        filename = "/Users/clumsy/coding/struktur-data-py-java/HeapDataStructure/data.csv"
        count = 0
        try:
            with open(filename, 'r') as file:
                csv_reader = csv.reader(file)
                firstLine = True
                for row in csv_reader:
                    if firstLine:
                        firstLine = False
                        continue
                    if len(row) >= 2 and row[0].strip():
                        id = int(row[0].strip())
                        nama = row[1].strip()
                        self.tambahData(id, nama)
                        count += 1
            print(f"\n✅ Data berhasil dimuat dari {filename}")
            print(f"Total data: {count}")
        except FileNotFoundError:
            print(f"\n❌ File tidak ditemukan: {filename}")
        except Exception as e:
            print(f"Error: {e}")

def main():
    heap = HeapDataStructure()
    
    # Starting data awal - load dari CSV
    print("Memuat data dari file CSV...")
    heap.loadFromCSV()
    
    while True:
        print("\n╔════════════════════════════════════════╗")
        print("║          HEAP DATA STRUCTURE           ║")
        print("╠════════════════════════════════════════╣")
        print("║ 1. Tambah data ke Min-Heap & Max-Heap  ║")
        print("║ 2. Tampil ascending (Min-Heap)         ║")
        print("║ 3. Tampil descending (Max-Heap)        ║")
        print("║ 4. Hapus data dari Min-Heap            ║")
        print("║ 5. Hapus data dari Max-Heap            ║")
        print("║ 0. Keluar                              ║")
        print("╚════════════════════════════════════════╝")
        
        pilihan = input("Pilihan: ")
        
        if pilihan == '1':
            id = int(input("Masukkan ID: "))
            nama = input("Masukkan Nama: ")
            heap.tambahData(id, nama)
        elif pilihan == '2':
            heap.tampilAscending()
        elif pilihan == '3':
            heap.tampilDescending()
        elif pilihan == '4':
            id = int(input("Masukkan ID yang akan dihapus dari Min-Heap: "))
            heap.hapusDariMinHeap(id)
        elif pilihan == '5':
            id = int(input("Masukkan ID yang akan dihapus dari Max-Heap: "))
            heap.hapusDariMaxHeap(id)
        elif pilihan == '0':
            print("Terima kasih!")
            break
        else:
            print("Pilihan tidak valid!")

if __name__ == "__main__":
    main()
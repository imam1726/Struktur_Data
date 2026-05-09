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
        """Hapus data dari min-heap berdasarkan ID (PERBAIKAN)"""
        # Cari index data yang mau dihapus
        index_to_delete = -1
        for i in range(len(self.heap)):
            if self.heap[i].id == id:
                index_to_delete = i
                break
        
        if index_to_delete == -1:
            return False
        
        # Swap dengan data terakhir
        last_index = len(self.heap) - 1
        self.heap[index_to_delete], self.heap[last_index] = self.heap[last_index], self.heap[index_to_delete]
        
        # Hapus data terakhir
        self.heap.pop()
        
        # Perbaiki heap jika masih ada data
        if index_to_delete < len(self.heap):
            self._heapify_down(index_to_delete)
            self._heapify_up(index_to_delete)
        
        return True
    
    def get_sorted(self):
        """Mendapatkan data urut ascending (tanpa merusak heap asli)"""
        if not self.heap:
            return []
        
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
        """Hapus data dari max-heap berdasarkan ID (PERBAIKAN)"""
        # Cari index data yang mau dihapus
        index_to_delete = -1
        for i in range(len(self.heap)):
            if self.heap[i].id == id:
                index_to_delete = i
                break
        
        if index_to_delete == -1:
            return False
        
        # Swap dengan data terakhir
        last_index = len(self.heap) - 1
        self.heap[index_to_delete], self.heap[last_index] = self.heap[last_index], self.heap[index_to_delete]
        
        # Hapus data terakhir
        self.heap.pop()
        
        # Perbaiki heap jika masih ada data
        if index_to_delete < len(self.heap):
            self._heapify_down(index_to_delete)
            self._heapify_up(index_to_delete)
        
        return True
    
    def get_sorted(self):
        """Mendapatkan data urut descending (tanpa merusak heap asli)"""
        if not self.heap:
            return []
        
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

class HeapDataStructure:
    def __init__(self):
        self.minHeap = MinHeap()
        self.maxHeap = MaxHeap()
    
    def tambahData(self, id, nama):
        """Tambah data ke min-heap dan max-heap sekaligus"""
        self.minHeap.insert(id, nama)
        self.maxHeap.insert(id, nama)
        print(f"✓ Data berhasil ditambahkan: ({id}, {nama})")
    
    def tampilAscending(self):
        """Tampilkan data ascending menggunakan Min-Heap"""
        if not self.minHeap.heap:
            print("\n⚠ Min-Heap kosong!")
            return
        print("\n=== DATA URUT ASCENDING (MIN-HEAP) ===")
        print("ID\t\tNama")
        print("--------------------------------")
        sorted_data = self.minHeap.get_sorted()
        for node in sorted_data:
            print(f"{node.id}\t\t{node.nama}")
    
    def tampilDescending(self):
        """Tampilkan data descending menggunakan Max-Heap"""
        if not self.maxHeap.heap:
            print("\n⚠ Max-Heap kosong!")
            return
        print("\n=== DATA URUT DESCENDING (MAX-HEAP) ===")
        print("ID\t\tNama")
        print("--------------------------------")
        sorted_data = self.maxHeap.get_sorted()
        for node in sorted_data:
            print(f"{node.id}\t\t{node.nama}")
    
    def hapusDariMinHeap(self, id):
        """Hapus data dari min-heap"""
        if self.minHeap.delete_by_id(id):
            # Hapus juga dari max-heap biar konsisten
            self.maxHeap.delete_by_id(id)
            print(f"✓ Data ID {id} berhasil dihapus dari Min-Heap & Max-Heap")
        else:
            print(f"✗ Data ID {id} tidak ditemukan")
    
    def hapusDariMaxHeap(self, id):
        """Hapus data dari max-heap"""
        if self.maxHeap.delete_by_id(id):
            # Hapus juga dari min-heap biar konsisten
            self.minHeap.delete_by_id(id)
            print(f"✓ Data ID {id} berhasil dihapus dari Max-Heap & Min-Heap")
        else:
            print(f"✗ Data ID {id} tidak ditemukan")
    
    def loadFromCSV(self):
        """Load data awal dari CSV (path langsung di kodingan)"""
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
            try:
                id = int(input("Masukkan ID: "))
                nama = input("Masukkan Nama: ")
                heap.tambahData(id, nama)
            except ValueError:
                print("ID harus berupa angka!")
        elif pilihan == '2':
            heap.tampilAscending()
        elif pilihan == '3':
            heap.tampilDescending()
        elif pilihan == '4':
            try:
                id = int(input("Masukkan ID yang akan dihapus dari Min-Heap: "))
                heap.hapusDariMinHeap(id)
            except ValueError:
                print("ID harus berupa angka!")
        elif pilihan == '5':
            try:
                id = int(input("Masukkan ID yang akan dihapus dari Max-Heap: "))
                heap.hapusDariMaxHeap(id)
            except ValueError:
                print("ID harus berupa angka!")
        elif pilihan == '0':
            print("Terima kasih!")
            break
        else:
            print("Pilihan tidak valid!")

if __name__ == "__main__":
    main()

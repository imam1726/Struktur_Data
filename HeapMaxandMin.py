class MaxHeap:
    def __init__(self):
        self.heap = []
        
    def parent(self, i): return (i - 1) // 2
    def left_child(self, i): return 2 * i + 1
    def right_child(self, i): return 2 * i + 2
    
    def insert(self, value):
        self.heap.append(value)
        self.heapify_up(len(self.heap) - 1)
        
    def heapify_up(self, i):
        while i > 0 and self.heap[i] > self.heap[self.parent(i)]:
            # Swap
            self.heap[i], self.heap[self.parent(i)] = self.heap[self.parent(i)], self.heap[i]
            i = self.parent(i)
            
    def extract_max(self):
        if not self.heap: return None
        max_val = self.heap[0]
        self.heap[0] = self.heap[-1]
        self.heap.pop()
        if self.heap:
            self.heapify_down(0)
        return max_val
        
    def heapify_down(self, i):
        largest = i
        left = self.left_child(i)
        right = self.right_child(i)
        
        if left < len(self.heap) and self.heap[left] > self.heap[largest]:
            largest = left
        if right < len(self.heap) and self.heap[right] > self.heap[largest]:
            largest = right
            
        if largest != i:
            # Swap
            self.heap[i], self.heap[largest] = self.heap[largest], self.heap[i]
            self.heapify_down(largest)
            
    def get_max(self):
        return self.heap[0] if self.heap else None
        
    def display(self):
        print(self.heap)

class MinHeap:
    def __init__(self):
        self.heap = []
        
    def parent(self, i): return (i - 1) // 2
    def left_child(self, i): return 2 * i + 1
    def right_child(self, i): return 2 * i + 2
    
    def insert(self, value):
        self.heap.append(value)
        self.heapify_up(len(self.heap) - 1)
        
    def heapify_up(self, i):
        while i > 0 and self.heap[i] < self.heap[self.parent(i)]:
            # Swap
            self.heap[i], self.heap[self.parent(i)] = self.heap[self.parent(i)], self.heap[i]
            i = self.parent(i)
            
    def extract_min(self):
        if not self.heap: return None
        min_val = self.heap[0]
        self.heap[0] = self.heap[-1]
        self.heap.pop()
        if self.heap:
            self.heapify_down(0)
        return min_val
        
    def heapify_down(self, i):
        smallest = i
        left = self.left_child(i)
        right = self.right_child(i)
        
        if left < len(self.heap) and self.heap[left] < self.heap[smallest]:
            smallest = left
        if right < len(self.heap) and self.heap[right] < self.heap[smallest]:
            smallest = right
            
        if smallest != i:
            # Swap
            self.heap[i], self.heap[smallest] = self.heap[smallest], self.heap[i]
            self.heapify_down(smallest)
            
    def get_min(self):
        return self.heap[0] if self.heap else None
        
    def display(self):
        print(self.heap)

if __name__ == "__main__":
    data = [50, 30, 40, 10, 20, 35, 45]
    
    print("MAX-HEAP")
    print("=" * 50)
    
    max_heap = MaxHeap()
    print(f"Memasukkan data: {data}")
    for num in data:
        max_heap.insert(num)
        print(f"Setelah insert {num}: ", end="")
        max_heap.display()
        
    print(f"\nNilai maksimum (root): {max_heap.get_max()}")
    
    print("\nMelakukan extractMax:")
    for _ in range(3):
        removed = max_heap.extract_max()
        print(f"Extract {removed} -> Heap: ", end="")
        max_heap.display()
        
    print("\n")
    print("MIN-HEAP")
    print("=" * 50)
    
    min_heap = MinHeap()
    print(f"Memasukkan data: {data}")
    for num in data:
        min_heap.insert(num)
        print(f"Setelah insert {num}: ", end="")
        min_heap.display()
        
    print(f"\nNilai minimum (root): {min_heap.get_min()}")
    
    print("\nMelakukan extractMin:")
    for _ in range(3):
        removed = min_heap.extract_min()
        print(f"Extract {removed} -> Heap: ", end="")
        min_heap.display()
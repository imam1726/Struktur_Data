import java.util.ArrayList;
import java.util.Arrays;

class MaxHeap {
    private ArrayList<Integer> heap;
    
    public MaxHeap() {
        heap = new ArrayList<>();
    }
    
    private int parent(int i) { return (i - 1) / 2; }
    private int leftChild(int i) { return 2 * i + 1; }
    private int rightChild(int i) { return 2 * i + 2; }
    
    public void insert(int value) {
        heap.add(value);
        heapifyUp(heap.size() - 1);
    }
    
    private void heapifyUp(int i) {
        while (i > 0 && heap.get(i) > heap.get(parent(i))) {
            int temp = heap.get(i);
            heap.set(i, heap.get(parent(i)));
            heap.set(parent(i), temp);
            i = parent(i);
        }
    }
    
    public Integer extractMax() {
        if (heap.isEmpty()) return null;
        int maxVal = heap.get(0);
        heap.set(0, heap.get(heap.size() - 1));
        heap.remove(heap.size() - 1);
        if (!heap.isEmpty()) heapifyDown(0);
        return maxVal;
    }
    
    private void heapifyDown(int i) {
        int largest = i;
        int left = leftChild(i);
        int right = rightChild(i);
        if (left < heap.size() && heap.get(left) > heap.get(largest)) largest = left;
        if (right < heap.size() && heap.get(right) > heap.get(largest)) largest = right;
        if (largest != i) {
            int temp = heap.get(i);
            heap.set(i, heap.get(largest));
            heap.set(largest, temp);
            heapifyDown(largest);
        }
    }
    
    public Integer getMax() { return heap.isEmpty() ? null : heap.get(0); }
    public void display() { System.out.println(heap); }
}

class MinHeap {
    private ArrayList<Integer> heap;
    
    public MinHeap() {
        heap = new ArrayList<>();
    }
    
    private int parent(int i) { return (i - 1) / 2; }
    private int leftChild(int i) { return 2 * i + 1; }
    private int rightChild(int i) { return 2 * i + 2; }
    
    public void insert(int value) {
        heap.add(value);
        heapifyUp(heap.size() - 1);
    }
    
    private void heapifyUp(int i) {
        while (i > 0 && heap.get(i) < heap.get(parent(i))) {
            int temp = heap.get(i);
            heap.set(i, heap.get(parent(i)));
            heap.set(parent(i), temp);
            i = parent(i);
        }
    }
    
    public Integer extractMin() {
        if (heap.isEmpty()) return null;
        int minVal = heap.get(0);
        heap.set(0, heap.get(heap.size() - 1));
        heap.remove(heap.size() - 1);
        if (!heap.isEmpty()) heapifyDown(0);
        return minVal;
    }
    
    private void heapifyDown(int i) {
        int smallest = i;
        int left = leftChild(i);
        int right = rightChild(i);
        if (left < heap.size() && heap.get(left) < heap.get(smallest)) smallest = left;
        if (right < heap.size() && heap.get(right) < heap.get(smallest)) smallest = right;
        if (smallest != i) {
            int temp = heap.get(i);
            heap.set(i, heap.get(smallest));
            heap.set(smallest, temp);
            heapifyDown(smallest);
        }
    }
    
    public Integer getMin() { return heap.isEmpty() ? null : heap.get(0); }
    public void display() { System.out.println(heap); }
}

public class HeapMaxandMin {
    public static void main(String[] args) {
        int[] data = {50, 30, 40, 10, 20, 35, 45};
        
        System.out.println("MAX-HEAP");
        System.out.println("=".repeat(50));
        
        MaxHeap maxHeap = new MaxHeap();
        System.out.println("Memasukkan data: " + Arrays.toString(data));
        for (int num : data) {
            maxHeap.insert(num);
            System.out.print("Setelah insert " + num + ": ");
            maxHeap.display();
        }
        
        System.out.println("\nNilai maksimum (root): " + maxHeap.getMax());
        
        System.out.println("\nMelakukan extractMax:");
        for (int i = 0; i < 3; i++) {
            int removed = maxHeap.extractMax();
            System.out.print("Extract " + removed + " -> Heap: ");
            maxHeap.display();
        }
        
        System.out.println("\n");
        System.out.println("MIN-HEAP");
        System.out.println("=".repeat(50));
        
        MinHeap minHeap = new MinHeap();
        System.out.println("Memasukkan data: " + Arrays.toString(data));
        for (int num : data) {
            minHeap.insert(num);
            System.out.print("Setelah insert " + num + ": ");
            minHeap.display();
        }
        
        System.out.println("\nNilai minimum (root): " + minHeap.getMin());
        
        System.out.println("\nMelakukan extractMin:");
        for (int i = 0; i < 3; i++) {
            int removed = minHeap.extractMin();
            System.out.print("Extract " + removed + " -> Heap: ");
            minHeap.display();
        }
    }
}
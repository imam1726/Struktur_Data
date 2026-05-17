class QuadraticProbingHashTable:
    EMPTY = -1
    def __init__(self, size):
        self.size = size
        self.table = [self.EMPTY] * size

    def _hash(self, key):
        return abs(key) % self.size

    def insert(self, key):
        index = self._hash(key)
        start = index
        i = 1
        while self.table[index] != self.EMPTY and self.table[index] != key:
            index = (start + i*i) % self.size
            i += 1
            if i > self.size:
                return False
        if self.table[index] == key:
            return False
        self.table[index] = key
        return True

    def delete(self, key):
        index = self._hash(key)
        start = index
        i = 1
        while self.table[index] != self.EMPTY:
            if self.table[index] == key:
                self.table[index] = self.EMPTY
                return True
            index = (start + i*i) % self.size
            i += 1
            if i > self.size:
                break
        return False

    def search(self, key):
        index = self._hash(key)
        start = index
        i = 1
        while self.table[index] != self.EMPTY:
            if self.table[index] == key:
                return True
            index = (start + i*i) % self.size
            i += 1
            if i > self.size:
                break
        return False

    def search_with_detail(self, key):
        index = self._hash(key)
        start = index
        i = 1
        print("\n=== PROSES PENCARIAN QUADRATIC PROBING ===")
        print(f"Data dicari: {key}")
        print(f"Hash awal: {index}")
        print("Probing step: 1^2, 2^2, 3^2, ...")
        step = 0
        idx = index
        while self.table[idx] != self.EMPTY and step < self.size:
            print(f"  Step {step}: index {idx} -> {self.table[idx]}")
            if self.table[idx] == key:
                print(f"  >>> DITEMUKAN pada indeks {idx}")
                return True, idx, step
            step += 1
            idx = (start + step*step) % self.size
        print(f"  >>> TIDAK DITEMUKAN setelah {step} langkah")
        return False, -1, -1

    def display(self):
        print("\nHash Table (Quadratic Probing):")
        for i, val in enumerate(self.table):
            print(f"{i:3}: {'null' if val == self.EMPTY else val}")

def main():
    import random
    ht = QuadraticProbingHashTable(101)
    unique = set()
    while len(unique) < 100:
        unique.add(random.randint(0, 999))
    for num in unique:
        ht.insert(num)
    print("100 unique random numbers inserted.")

    while True:
        print("\n--- MENU QUADRATIC PROBING ---")
        print("1. INPUT DATA")
        print("2. HAPUS DATA")
        print("3. CARI DATA (detail)")
        print("4. TAMPILKAN DATA")
        print("5. EXIT")
        choice = input("Pilihan: ")
        if choice == '1':
            val = int(input("Angka: "))
            if ht.search(val):
                print("Sudah ada.")
            elif ht.insert(val):
                print("Berhasil ditambahkan.")
            else:
                print("Tabel penuh.")
        elif choice == '2':
            val = int(input("Angka: "))
            if ht.delete(val):
                print("Terhapus.")
            else:
                print("Tidak ditemukan.")
        elif choice == '3':
            val = int(input("Angka: "))
            ht.search_with_detail(val)
        elif choice == '4':
            ht.display()
        elif choice == '5':
            print("Keluar.")
            break
        else:
            print("Pilihan salah.")

if __name__ == "__main__":
    main()
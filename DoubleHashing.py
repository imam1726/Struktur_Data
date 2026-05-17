class DoubleHashingHashTable:
    EMPTY = -1
    def __init__(self, size):
        self.size = size
        self.table = [self.EMPTY] * size

    def _hash1(self, key):
        return abs(key) % self.size

    def _hash2(self, key):
        # second hash: prime 97
        return 1 + (abs(key) % 97)

    def insert(self, key):
        h1 = self._hash1(key)
        step = self._hash2(key)
        i = 0
        idx = h1
        while self.table[idx] != self.EMPTY and self.table[idx] != key:
            i += 1
            idx = (h1 + i * step) % self.size
            if i >= self.size:
                return False
        if self.table[idx] == key:
            return False
        self.table[idx] = key
        return True

    def delete(self, key):
        h1 = self._hash1(key)
        step = self._hash2(key)
        i = 0
        idx = h1
        while self.table[idx] != self.EMPTY:
            if self.table[idx] == key:
                self.table[idx] = self.EMPTY
                return True
            i += 1
            idx = (h1 + i * step) % self.size
            if i >= self.size:
                break
        return False

    def search(self, key):
        h1 = self._hash1(key)
        step = self._hash2(key)
        i = 0
        idx = h1
        while self.table[idx] != self.EMPTY:
            if self.table[idx] == key:
                return True
            i += 1
            idx = (h1 + i * step) % self.size
            if i >= self.size:
                break
        return False

    def search_with_detail(self, key):
        h1 = self._hash1(key)
        step = self._hash2(key)
        print("\n=== PROSES PENCARIAN DOUBLE HASHING ===")
        print(f"Data dicari: {key}")
        print(f"Hash1 (h1) = {h1}")
        print(f"Hash2 (step) = {step}")
        print("Rumus: index = (h1 + i * step) mod size")
        i = 0
        idx = h1
        while self.table[idx] != self.EMPTY and i < self.size:
            print(f"  i={i} -> index {idx} : {self.table[idx]}")
            if self.table[idx] == key:
                print(f"  >>> DITEMUKAN pada indeks {idx}")
                return True, idx, i
            i += 1
            idx = (h1 + i * step) % self.size
        print(f"  >>> TIDAK DITEMUKAN setelah {i} langkah")
        return False, -1, -1

    def display(self):
        print("\nHash Table (Double Hashing):")
        for i, val in enumerate(self.table):
            print(f"{i:3}: {'null' if val == self.EMPTY else val}")

def main():
    import random
    ht = DoubleHashingHashTable(101)
    unique = set()
    while len(unique) < 100:
        unique.add(random.randint(0, 999))
    for num in unique:
        ht.insert(num)
    print("100 unique random numbers inserted.")

    while True:
        print("\n--- MENU DOUBLE HASHING ---")
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
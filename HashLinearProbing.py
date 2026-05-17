class LinearProbingHashTable:
    EMPTY = -1
    def __init__(self, size):
        self.size = size
        self.table = [self.EMPTY] * size

    def _hash(self, key):
        return abs(key) % self.size

    def insert(self, key):
        index = self._hash(key)
        start = index
        i = 0
        while self.table[index] != self.EMPTY and self.table[index] != key:
            i += 1
            index = (start + i) % self.size
            if index == start:
                return False
        if self.table[index] == key:
            return False
        self.table[index] = key
        return True

    def delete(self, key):
        index = self._hash(key)
        start = index
        i = 0
        while self.table[index] != self.EMPTY:
            if self.table[index] == key:
                self.table[index] = self.EMPTY
                # rehash sederhana untuk linear probing (optional)
                self._rehash_from(index)
                return True
            i += 1
            index = (start + i) % self.size
            if index == start:
                break
        return False

    def _rehash_from(self, deleted):
        nxt = (deleted + 1) % self.size
        while self.table[nxt] != self.EMPTY:
            key = self.table[nxt]
            orig_hash = self._hash(key)
            # rule: jika key harusnya di posisi <= deleted atau melompat karena probing
            if (orig_hash <= deleted and (nxt < orig_hash or nxt > deleted)) or \
               (orig_hash > deleted and nxt > orig_hash and nxt > deleted):
                self.table[deleted] = key
                self.table[nxt] = self.EMPTY
                deleted = nxt
            nxt = (nxt + 1) % self.size

    def search(self, key):
        index = self._hash(key)
        start = index
        i = 0
        while self.table[index] != self.EMPTY:
            if self.table[index] == key:
                return True
            i += 1
            index = (start + i) % self.size
            if index == start:
                break
        return False

    def search_with_detail(self, key):
        index = self._hash(key)
        start = index
        i = 0
        print("\n=== PROSES PENCARIAN LINEAR PROBING ===")
        print(f"Data dicari: {key}")
        print(f"Hash awal: {index}")
        print("Probing step: 1, 2, 3, ...")
        step = 0
        while self.table[index] != self.EMPTY and step < self.size:
            print(f"  Step {step}: index {index} -> {self.table[index]}")
            if self.table[index] == key:
                print(f"  >>> DITEMUKAN pada indeks {index}")
                return True, index, step
            step += 1
            index = (start + step) % self.size
        print(f"  >>> TIDAK DITEMUKAN setelah {step} langkah")
        return False, -1, -1

    def display(self):
        print("\nHash Table (Linear Probing):")
        for i, val in enumerate(self.table):
            print(f"{i:3}: {'null' if val == self.EMPTY else val}")

def main():
    import random
    ht = LinearProbingHashTable(101)
    unique = set()
    while len(unique) < 100:
        unique.add(random.randint(0, 999))
    for num in unique:
        ht.insert(num)
    print("100 unique random numbers inserted.")

    while True:
        print("\n--- MENU LINEAR PROBING ---")
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
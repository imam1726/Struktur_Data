class HashTable:
    def __init__(self, size):
        self.size = size
        self.table = [[] for _ in range(size)]

    def _hash(self, key):
        return abs(key) % self.size

    def insert(self, key):
        index = self._hash(key)
        if key not in self.table[index]:
            self.table[index].append(key)

    def delete(self, key):
        index = self._hash(key)
        if key in self.table[index]:
            self.table[index].remove(key)
            return True
        return False

    def search(self, key):
        index = self._hash(key)
        return key in self.table[index]

    def search_with_detail(self, key):
        hash_value = abs(key)
        index = hash_value % self.size
        
        print("\n=== PROSES PENCARIAN ===")
        print(f"Data yang dicari: {key}")
        print(f"Nilai hash (abs): {hash_value}")
        print(f"Index hasil modulo ({hash_value} % {self.size}) = {index}")
        print(f"Bucket index: {index}")
        
        chain = self.table[index]
        
        if not chain:
            print("Status: Bucket kosong")
            return {'found': False, 'bucketIndex': index, 'positionInChain': -1}
        
        print(f"Isi bucket [{index}]: ", end="")
        for i, val in enumerate(chain):
            print(val, end="")
            if i < len(chain) - 1:
                print(" -> ", end="")
        print()
        
        position = -1
        for i, val in enumerate(chain):
            print(f"  Memeriksa posisi ke-{i}: {val}")
            if val == key:
                position = i
                print(f"  >>> DATA DITEMUKAN pada posisi ke-{i} dalam chain")
                break
        
        if position == -1:
            print("  >>> DATA TIDAK DITEMUKAN dalam chain")
        
        return {'found': position != -1, 'bucketIndex': index, 'positionInChain': position}

    def display(self):
        print("\nHash Table Contents:")
        for i, chain in enumerate(self.table):
            print(f"{i}: ", end="")
            if not chain:
                print("null")
            else:
                for val in chain:
                    print(f"{val} -> ", end="")
                print("null")

import random

def main():
    hash_table = HashTable(101)
    unique_numbers = set()

    print("Generating 100 unique random numbers...")
    while len(unique_numbers) < 100:
        unique_numbers.add(random.randint(0, 999))

    for num in unique_numbers:
        hash_table.insert(num)

    print("100 unique random numbers have been inserted.")

    while True:
        print("\n--- MENU ---")
        print("1. INPUT DATA")
        print("2. HAPUS DATA")
        print("3. CARI DATA")
        print("4. TAMPILKAN DATA")
        print("5. EXIT")
        choice = input("Pilihan: ")

        if choice == '1':
            try:
                val = int(input("Masukkan angka yang akan ditambahkan: "))
                if hash_table.search(val):
                    print("Data sudah ada!")
                else:
                    hash_table.insert(val)
                    print("Data berhasil ditambahkan.")
            except ValueError:
                print("Input harus angka!")

        elif choice == '2':
            try:
                val = int(input("Masukkan angka yang akan dihapus: "))
                if hash_table.delete(val):
                    print("Data berhasil dihapus.")
                else:
                    print("Data tidak ditemukan.")
            except ValueError:
                print("Input harus angka!")

        elif choice == '3':
            try:
                val = int(input("Masukkan angka yang dicari: "))
                result = hash_table.search_with_detail(val)
                print("\n=== HASIL PENCARIAN ===")
                if result['found']:
                    print(f"STATUS: DATA DITEMUKAN")
                    print(f"Lokasi: Bucket index [{result['bucketIndex']}]")
                    print(f"Posisi dalam chain: ke-{result['positionInChain']}")
                else:
                    print(f"STATUS: DATA TIDAK DITEMUKAN")
                    print(f"Bucket yang diperiksa: [{result['bucketIndex']}]")
            except ValueError:
                print("Input harus angka!")

        elif choice == '4':
            hash_table.display()

        elif choice == '5':
            print("Terima kasih! Keluar program.")
            break

        else:
            print("Pilihan tidak valid. Masukkan 1-5.")

if __name__ == "__main__":
    main()
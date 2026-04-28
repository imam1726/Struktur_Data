class FixedstaticArray:
    def __init__(self):
        self.nim = [None] * 10
        self.nama = [None] * 10
        self.count = 0
    
    def insert_awal(self):
        """1. Insert at beginning - selalu timpa index 0"""
        if self.count >= 10:
            print("Array penuh!")
            return
        
        n = input("NIM: ")
        nm = input("Nama: ")
        
        if self.nim[0] is None:
            self.count += 1
        
        self.nim[0] = n
        self.nama[0] = nm
        print("Data ditambahkan di No. 1")
        print(f"Count: {self.count}")
    
    def insert_posisi(self):
        """2. Insert at given position - timpa index yang dipilih"""
        if self.count >= 10:
            print("Array penuh!")
            return
        
        try:
            no = int(input("No. (1-10): "))
        except ValueError:
            print("No. harus 1-10!")
            return
        
        if no < 1 or no > 10:
            print("No. harus 1-10!")
            return
        
        n = input("NIM: ")
        nm = input("Nama: ")
        
        idx = no - 1
        
        if self.nim[idx] is None:
            self.count += 1
        
        self.nim[idx] = n
        self.nama[idx] = nm
        print(f"Data ditambahkan di No. {no}")
        print(f"Count: {self.count}")
    
    def insert_akhir(self):
        """3. Insert at end - selalu timpa index 9"""
        if self.count >= 10:
            print("Array penuh!")
            return
        
        n = input("NIM: ")
        nm = input("Nama: ")
        
        if self.nim[9] is None:
            self.count += 1
        
        self.nim[9] = n
        self.nama[9] = nm
        print("Data ditambahkan di No. 10")
        print(f"Count: {self.count}")
    
    def delete_awal(self):
        """4. Delete from beginning - hapus index 0"""
        if self.nim[0] is None:
            print("No. 1 kosong!")
            return
        
        print(f"Menghapus No. 1: {self.nim[0]} - {self.nama[0]}")
        self.nim[0] = None
        self.nama[0] = None
        self.count -= 1
        print("Data dihapus")
        print(f"Count: {self.count}")
    
    def delete_posisi(self):
        """5. Delete given position - hapus index yang dipilih"""
        if self.count == 0:
            print("Array kosong!")
            return
        
        try:
            no = int(input("No. yang dihapus (1-10): "))
        except ValueError:
            print("No. harus 1-10!")
            return
        
        if no < 1 or no > 10:
            print("No. harus 1-10!")
            return
        
        idx = no - 1
        
        if self.nim[idx] is None:
            print(f"No. {no} kosong!")
            return
        
        print(f"Menghapus No. {no}: {self.nim[idx]} - {self.nama[idx]}")
        self.nim[idx] = None
        self.nama[idx] = None
        self.count -= 1
        print("Data dihapus")
        print(f"Count: {self.count}")
    
    def delete_akhir(self):
        """6. Delete from end - hapus index 9"""
        if self.nim[9] is None:
            print("No. 10 kosong!")
            return
        
        print(f"Menghapus No. 10: {self.nim[9]} - {self.nama[9]}")
        self.nim[9] = None
        self.nama[9] = None
        self.count -= 1
        print("Data dihapus")
        print(f"Count: {self.count}")
    
    def delete_pertama(self):
        """7. Delete first occurence - cari NIM pertama yang cocok"""
        if self.count == 0:
            print("Array kosong!")
            return
        
        cari = input("Masukkan NIM yang akan dihapus: ")
        
        for i in range(10):
            if self.nim[i] is not None and self.nim[i] == cari:
                print(f"Data ditemukan di No. {i+1}: {self.nim[i]} - {self.nama[i]}")
                self.nim[i] = None
                self.nama[i] = None
                self.count -= 1
                print("Data dihapus")
                print(f"Count: {self.count}")
                return
        
        print("Data tidak ditemukan!")
    
    def show_data(self):
        """8. Show data - tampilkan yang tidak null"""
        print("\n=== DATA MAHASISWA ===")
        print(f"Jumlah: {self.count} data")
        
        if self.count == 0:
            print("Tidak ada data")
            return
        
        print("\nNo.\tNIM\tNama")
        print("------------------------")
        
        for i in range(10):
            if self.nim[i] is not None:
                print(f"{i+1}.\t{self.nim[i]}\t{self.nama[i]}")
    
    def run(self):
        """Main program loop"""
        print("=== PROGRAM ARRAY MAHASISWA ===")
        
        while True:
            print("\n=== MENU ===")
            print("1. Insert at beginning")
            print("2. Insert at given position")
            print("3. Insert at end")
            print("4. Delete from beginning")
            print("5. Delete given position")
            print("6. Delete from end")
            print("7. Delete first occurence")
            print("8. Show data")
            print("9. Exit")
            
            try:
                pilih = int(input("Pilih: "))
            except ValueError:
                print("Pilihan salah!")
                continue
            
            if pilih == 1:
                self.insert_awal()
            elif pilih == 2:
                self.insert_posisi()
            elif pilih == 3:
                self.insert_akhir()
            elif pilih == 4:
                self.delete_awal()
            elif pilih == 5:
                self.delete_posisi()
            elif pilih == 6:
                self.delete_akhir()
            elif pilih == 7:
                self.delete_pertama()
            elif pilih == 8:
                self.show_data()
            elif pilih == 9:
                print("Program selesai")
                break
            else:
                print("Pilihan salah!")


if __name__ == "__main__":
    app = FixedstaticArray()
    app.run()
class DynamicArray:
    def __init__(self):
        self.nim = []      # Dynamic array (list kosong)
        self.nama = []     # Dynamic array (list kosong)
    
    def insert_awal(self):
        """1. Insert at beginning"""
        n = input("NIM: ")
        nm = input("Nama: ")
        
        self.nim.insert(0, n)
        self.nama.insert(0, nm)
        print("Data ditambahkan di awal")
    
    def insert_posisi(self):
        """2. Insert at given position"""
        try:
            pos = int(input(f"Posisi (1-{len(self.nim)+1}): "))
        except ValueError:
            print("Posisi invalid!")
            return
        
        if pos < 1 or pos > len(self.nim) + 1:
            print("Posisi invalid!")
            return
        
        n = input("NIM: ")
        nm = input("Nama: ")
        
        self.nim.insert(pos-1, n)
        self.nama.insert(pos-1, nm)
        print(f"Data ditambahkan di posisi {pos}")
    
    def insert_akhir(self):
        """3. Insert at end"""
        n = input("NIM: ")
        nm = input("Nama: ")
        
        self.nim.append(n)
        self.nama.append(nm)
        print("Data ditambahkan di akhir")
    
    def delete_awal(self):
        """4. Delete from beginning"""
        if not self.nim:
            print("Array kosong!")
            return
        
        print(f"Hapus: {self.nim[0]} - {self.nama[0]}")
        self.nim.pop(0)
        self.nama.pop(0)
        print("Data dihapus")
    
    def delete_posisi(self):
        """5. Delete given position"""
        if not self.nim:
            print("Array kosong!")
            return
        
        try:
            pos = int(input(f"Posisi (1-{len(self.nim)}): "))
        except ValueError:
            print("Posisi invalid!")
            return
        
        if pos < 1 or pos > len(self.nim):
            print("Posisi invalid!")
            return
        
        print(f"Hapus: {self.nim[pos-1]} - {self.nama[pos-1]}")
        self.nim.pop(pos-1)
        self.nama.pop(pos-1)
        print("Data dihapus")
    
    def delete_akhir(self):
        """6. Delete from end"""
        if not self.nim:
            print("Array kosong!")
            return
        
        print(f"Hapus: {self.nim[-1]} - {self.nama[-1]}")
        self.nim.pop()
        self.nama.pop()
        print("Data dihapus")
    
    def delete_pertama(self):
        """7. Delete first occurence"""
        if not self.nim:
            print("Array kosong!")
            return
        
        cari = input("NIM yang dihapus: ")
        
        pos = -1
        for i in range(len(self.nim)):
            sama = True
            if self.nim[i] is None or cari is None:
                sama = False
            elif len(self.nim[i]) != len(cari):
                sama = False
            else:
                for j in range(len(self.nim[i])):
                    if self.nim[i][j] != cari[j]:
                        sama = False
                        break
            if sama:
                pos = i
                break
        
        if pos == -1:
            print("Data tidak ditemukan!")
            return
        
        print(f"Hapus: {self.nim[pos]} - {self.nama[pos]}")
        self.nim.pop(pos)
        self.nama.pop(pos)
        print("Data dihapus")
    
    def show_data(self):
        """8. Show data"""
        if not self.nim:
            print("Array kosong!")
            return
        
        print("\nData Mahasiswa:")
        print(f"Count: {len(self.nim)}")
        for i in range(len(self.nim)):
            print(f"{i+1}. {self.nim[i]} - {self.nama[i]}")
    
    def run(self):
        """Main program loop"""
        while True:
            print("\n1. Insert at beginning")
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
                print("Invalid")
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
                print("Exit")
                break
            else:
                print("Invalid")


if __name__ == "__main__":
    app = DynamicArray()
    app.run()
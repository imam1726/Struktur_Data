class ArrayMahasiswa:
    def __init__(self):
        self.nim = [None] * 10
        self.nama = [None] * 10
        self.count = 0
    
    def insert_awal(self):
        """1. Insert at beginning"""
        if self.count >= 10:
            print("Array penuh!")
            return
        
        n = input("NIM: ")
        nm = input("Nama: ")
        
        # Geser semua elemen ke kanan
        for i in range(self.count, 0, -1):
            self.nim[i] = self.nim[i-1]
            self.nama[i] = self.nama[i-1]
        
        self.nim[0] = n
        self.nama[0] = nm
        self.count += 1
        print("Data ditambahkan di awal")
    
    def insert_posisi(self):
        """2. Insert at given position"""
        if self.count >= 10:
            print("Array penuh!")
            return
        
        try:
            pos = int(input(f"Posisi (1-{self.count+1}): "))
        except ValueError:
            print("Input harus angka!")
            return
        
        if pos < 1 or pos > self.count + 1:
            print("Posisi invalid!")
            return
        
        n = input("NIM: ")
        nm = input("Nama: ")
        
        # Geser elemen dari posisi ke kanan
        for i in range(self.count, pos-1, -1):
            self.nim[i] = self.nim[i-1]
            self.nama[i] = self.nama[i-1]
        
        self.nim[pos-1] = n
        self.nama[pos-1] = nm
        self.count += 1
        print(f"Data ditambahkan di posisi {pos}")
    
    def insert_akhir(self):
        """3. Insert at end"""
        if self.count >= 10:
            print("Array penuh!")
            return
        
        self.nim[self.count] = input("NIM: ")
        self.nama[self.count] = input("Nama: ")
        self.count += 1
        print("Data ditambahkan di akhir")
    
    def delete_awal(self):
        """4. Delete from beginning"""
        if self.count == 0:
            print("Array kosong!")
            return
        
        print(f"Hapus: {self.nim[0]} - {self.nama[0]}")
        
        # Geser semua elemen ke kiri
        for i in range(self.count - 1):
            self.nim[i] = self.nim[i+1]
            self.nama[i] = self.nama[i+1]
        
        self.nim[self.count-1] = None
        self.nama[self.count-1] = None
        self.count -= 1
        print("Data dihapus")
    
    def delete_posisi(self):
        """5. Delete given position"""
        if self.count == 0:
            print("Array kosong!")
            return
        
        try:
            pos = int(input(f"Posisi (1-{self.count}): "))
        except ValueError:
            print("Input harus angka!")
            return
        
        if pos < 1 or pos > self.count:
            print("Posisi invalid!")
            return
        
        print(f"Hapus: {self.nim[pos-1]} - {self.nama[pos-1]}")
        
        # Geser elemen dari posisi ke kiri
        for i in range(pos-1, self.count - 1):
            self.nim[i] = self.nim[i+1]
            self.nama[i] = self.nama[i+1]
        
        self.nim[self.count-1] = None
        self.nama[self.count-1] = None
        self.count -= 1
        print("Data dihapus")
    
    def delete_akhir(self):
        """6. Delete from end"""
        if self.count == 0:
            print("Array kosong!")
            return
        
        print(f"Hapus: {self.nim[self.count-1]} - {self.nama[self.count-1]}")
        self.nim[self.count-1] = None
        self.nama[self.count-1] = None
        self.count -= 1
        print("Data dihapus")
    
    def delete_pertama(self):
        """7. Delete first occurrence"""
        if self.count == 0:
            print("Array kosong!")
            return
        
        cari = input("NIM yang dihapus: ")
        
        # Cari posisi NIM (versi Python yang lebih simpel)
        pos = -1
        for i in range(self.count):
            if self.nim[i] == cari:  # Di Python, bandingkan langsung
                pos = i
                break
        
        if pos == -1:
            print("Data tidak ditemukan!")
            return
        
        print(f"Hapus: {self.nim[pos]} - {self.nama[pos]}")
        
        # Geser elemen
        for i in range(pos, self.count - 1):
            self.nim[i] = self.nim[i+1]
            self.nama[i] = self.nama[i+1]
        
        self.nim[self.count-1] = None
        self.nama[self.count-1] = None
        self.count -= 1
        print("Data dihapus")
    
    def show_data(self):
        """8. Show data"""
        if self.count == 0:
            print("Array kosong!")
            return
        
        print("\nData Mahasiswa:")
        print(f"Count: {self.count}")
        for i in range(self.count):
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
                print("Input harus angka!")
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


# Jalankan program
if __name__ == "__main__":
    app = ArrayMahasiswa()
    app.run()

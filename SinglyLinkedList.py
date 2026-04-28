class Node:
    def __init__(self, nim, nama):
        self.nim = nim
        self.nama = nama
        self.next = None


class LinkedListManager:
    def __init__(self):
        self.head = None
        self.tail = None
        self.count = 0
    
    def insert_awal(self, nim, nama):
        """Insert di awal"""
        node = Node(nim, nama)
        if self.head is None:
            self.head = node
            self.tail = node
        else:
            node.next = self.head
            self.head = node
        self.count += 1
        print("Data ditambahkan")
    
    def insert_posisi(self, pos, nim, nama):
        """Insert di posisi tertentu"""
        if pos < 1 or pos > self.count + 1:
            print("Posisi tidak valid")
            return
        
        if pos == 1:
            self.insert_awal(nim, nama)
            return
        
        if pos == self.count + 1:
            self.insert_akhir(nim, nama)
            return
        
        node = Node(nim, nama)
        current = self.head
        for i in range(1, pos - 1):
            current = current.next
        node.next = current.next
        current.next = node
        self.count += 1
        print("Data ditambahkan")
    
    def insert_akhir(self, nim, nama):
        """Insert di akhir"""
        node = Node(nim, nama)
        if self.head is None:
            self.head = node
            self.tail = node
        else:
            self.tail.next = node
            self.tail = node
        self.count += 1
        print("Data ditambahkan")
    
    def hapus_awal(self):
        """Hapus dari awal"""
        if self.head is None:
            print("List kosong")
            return
        
        print(f"Menghapus: {self.head.nim} - {self.head.nama}")
        self.head = self.head.next
        if self.head is None:
            self.tail = None
        self.count -= 1
        print("Data dihapus")
    
    def hapus_posisi(self, pos):
        """Hapus dari posisi tertentu"""
        if self.head is None:
            print("List kosong")
            return
        
        if pos < 1 or pos > self.count:
            print("Posisi tidak valid")
            return
        
        if pos == 1:
            self.hapus_awal()
            return
        
        current = self.head
        for i in range(1, pos - 1):
            current = current.next
        
        hapus = current.next
        print(f"Menghapus: {hapus.nim} - {hapus.nama}")
        current.next = hapus.next
        
        if current.next is None:
            self.tail = current
        
        self.count -= 1
        print("Data dihapus")
    
    def hapus_akhir(self):
        """Hapus dari akhir"""
        if self.head is None:
            print("List kosong")
            return
        
        if self.head == self.tail:
            print(f"Menghapus: {self.head.nim} - {self.head.nama}")
            self.head = None
            self.tail = None
        else:
            current = self.head
            while current.next != self.tail:
                current = current.next
            print(f"Menghapus: {self.tail.nim} - {self.tail.nama}")
            current.next = None
            self.tail = current
        
        self.count -= 1
        print("Data dihapus")
    
    def hapus_nim(self, nim):
        """Hapus berdasarkan NIM (first occurence)"""
        if self.head is None:
            print("List kosong")
            return
        
        # Kalau NIM di head
        if self.head.nim == nim:
            self.hapus_awal()
            return
        
        current = self.head
        while current.next is not None and current.next.nim != nim:
            current = current.next
        
        if current.next is None:
            print("NIM tidak ditemukan")
            return
        
        hapus = current.next
        print(f"Menghapus: {hapus.nim} - {hapus.nama}")
        current.next = hapus.next
        
        # FIX BUG: Update tail kalau yang dihapus adalah tail
        if hapus == self.tail:
            self.tail = current
        
        self.count -= 1
        print("Data dihapus")
    
    def tampil_data(self):
        """Tampilkan semua data"""
        if self.head is None:
            print("List kosong")
            return
        
        print("\nData Mahasiswa:")
        current = self.head
        no = 1
        while current is not None:
            print(f"{no}. {current.nim} - {current.nama}")
            current = current.next
            no += 1
        print(f"Total: {self.count} data\n")
    
    def get_count(self):
        return self.count


class SinglyLinkedList:
    def run(self):
        """Main program"""
        list_manager = LinkedListManager()
        
        while True:
            print("\n=== MENU ===")
            print("1. Insert awal")
            print("2. Insert posisi")
            print("3. Insert akhir")
            print("4. Hapus awal")
            print("5. Hapus posisi")
            print("6. Hapus akhir")
            print("7. Hapus NIM")
            print("8. Tampil data")
            print("9. Keluar")
            
            try:
                pilih = int(input("Pilih: "))
            except ValueError:
                print("Input harus angka!")
                continue
            
            if pilih == 1:
                nim1 = input("NIM: ")
                nama1 = input("Nama: ")
                list_manager.insert_awal(nim1, nama1)
            
            elif pilih == 2:
                try:
                    pos = int(input(f"Posisi (1 - {list_manager.get_count() + 1}): "))
                except ValueError:
                    print("Posisi harus angka!")
                    continue
                nim2 = input("NIM: ")
                nama2 = input("Nama: ")
                list_manager.insert_posisi(pos, nim2, nama2)
            
            elif pilih == 3:
                nim3 = input("NIM: ")
                nama3 = input("Nama: ")
                list_manager.insert_akhir(nim3, nama3)
            
            elif pilih == 4:
                list_manager.hapus_awal()
            
            elif pilih == 5:
                if list_manager.get_count() == 0:
                    print("List kosong!")
                    continue
                try:
                    pos_hapus = int(input(f"Posisi (1 - {list_manager.get_count()}): "))
                except ValueError:
                    print("Posisi harus angka!")
                    continue
                list_manager.hapus_posisi(pos_hapus)
            
            elif pilih == 6:
                list_manager.hapus_akhir()
            
            elif pilih == 7:
                nim_hapus = input("NIM yang akan dihapus: ")
                list_manager.hapus_nim(nim_hapus)
            
            elif pilih == 8:
                list_manager.tampil_data()
            
            elif pilih == 9:
                print("Program selesai!")
                break
            
            else:
                print("Pilihan tidak valid!")


if __name__ == "__main__":
    app = SinglyLinkedList()
    app.run()        node = Node(nim, nama)
        current = self.head
        for i in range(pos - 2):
            current = current.next
        node.next = current.next
        current.next = node
        self.count += 1
        print("Data ditambahkan")
    
    def insert_akhir(self, nim, nama):
        node = Node(nim, nama)
        if not self.head:
            self.head = node
            self.tail = node
        else:
            self.tail.next = node
            self.tail = node
        self.count += 1
        print("Data ditambahkan")
    
    def hapus_awal(self):
        if not self.head:
            print("List kosong")
            return
        
        print(f"Menghapus: {self.head.nim} - {self.head.nama}")
        self.head = self.head.next
        if not self.head:
            self.tail = None
        self.count -= 1
        print("Data dihapus")
    
    def hapus_posisi(self, pos):
        if not self.head:
            print("List kosong")
            return
        
        if pos < 1 or pos > self.count:
            print("Posisi tidak valid")
            return
        
        if pos == 1:
            self.hapus_awal()
            return
        
        current = self.head
        for i in range(pos - 2):
            current = current.next
        
        hapus = current.next
        print(f"Menghapus: {hapus.nim} - {hapus.nama}")
        current.next = hapus.next
        
        if not current.next:
            self.tail = current
        self.count -= 1
        print("Data dihapus")
    
    def hapus_akhir(self):
        if not self.head:
            print("List kosong")
            return
        
        if self.head == self.tail:
            print(f"Menghapus: {self.head.nim} - {self.head.nama}")
            self.head = None
            self.tail = None
        else:
            current = self.head
            while current.next != self.tail:
                current = current.next
            print(f"Menghapus: {self.tail.nim} - {self.tail.nama}")
            current.next = None
            self.tail = current
        
        self.count -= 1
        print("Data dihapus")
    
    def hapus_nim(self, nim):
        if not self.head:
            print("List kosong")
            return
        
        if self.head.nim == nim:
            self.hapus_awal()
            return
        
        current = self.head
        while current.next and current.next.nim != nim:
            current = current.next
        
        if not current.next:
            print("NIM tidak ditemukan")
            return
        
        hapus = current.next
        print(f"Menghapus: {hapus.nim} - {hapus.nama}")
        current.next = hapus.next
        
        if not current.next:
            self.tail = current
        self.count -= 1
        print("Data dihapus")
    
    def tampil_data(self):
        if not self.head:
            print("List kosong")
            return
        
        print("\nData Mahasiswa:")
        current = self.head
        no = 1
        while current:
            print(f"{no}. {current.nim} - {current.nama}")
            current = current.next
            no += 1
        print(f"Total: {self.count} data\n")

def main():
    list = SinglyLinkedList()
    
    while True:
        print("\n=== MENU ===")
        print("1. Insert awal")
        print("2. Insert posisi")
        print("3. Insert akhir")
        print("4. Hapus awal")
        print("5. Hapus posisi")
        print("6. Hapus akhir")
        print("7. Hapus NIM")
        print("8. Tampil data")
        print("9. Keluar")
        
        try:
            pilih = int(input("Pilih: "))
        except ValueError:
            print("Input harus angka!")
            continue
        
        if pilih == 1:
            nim = input("NIM: ")
            nama = input("Nama: ")
            list.insert_awal(nim, nama)
            
        elif pilih == 2:
            try:
                pos = int(input(f"Posisi (1 - {list.count + 1}): "))
            except ValueError:
                print("Posisi harus angka!")
                continue
            nim = input("NIM: ")
            nama = input("Nama: ")
            list.insert_posisi(pos, nim, nama)
            
        elif pilih == 3:
            nim = input("NIM: ")
            nama = input("Nama: ")
            list.insert_akhir(nim, nama)
            
        elif pilih == 4:
            list.hapus_awal()
            
        elif pilih == 5:
            if list.count == 0:
                print("List kosong!")
                continue
            try:
                pos = int(input(f"Posisi (1 - {list.count}): "))
            except ValueError:
                print("Posisi harus angka!")
                continue
            list.hapus_posisi(pos)
            
        elif pilih == 6:
            list.hapus_akhir()
            
        elif pilih == 7:
            nim = input("NIM yang akan dihapus: ")
            list.hapus_nim(nim)
            
        elif pilih == 8:
            list.tampil_data()
            
        elif pilih == 9:
            print("Program selesai!")
            break
            
        else:
            print("Pilihan tidak valid!")

if __name__ == "__main__":
    main()

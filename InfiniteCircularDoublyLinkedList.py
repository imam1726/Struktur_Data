import threading
import time
import sys
import select
import os

class Node:
    def __init__(self, berita):
        self.berita = berita
        self.next = None
        self.prev = None


class InfiniteCircularDoublyLinkedList:
    def __init__(self):
        self.head = None
        self.tail = None
        self.size = 0
        self.stop_loop = False
    
    def insert_berita(self, berita):
        """Insert berita ke linked list"""
        new_node = Node(berita)
        
        if self.head is None:
            self.head = new_node
            self.tail = new_node
            self.head.next = self.head
            self.head.prev = self.head
        else:
            self.tail.next = new_node
            new_node.prev = self.tail
            new_node.next = self.head
            self.head.prev = new_node
            self.tail = new_node
        
        self.size += 1
        print("Berita ditambahkan")
    
    def hapus_berita(self, nomor):
        """Hapus berita berdasarkan nomor"""
        if self.head is None or nomor < 1 or nomor > self.size:
            return False
        
        current = self.head
        for i in range(1, nomor):
            current = current.next
        
        if self.size == 1:
            self.head = None
            self.tail = None
        else:
            current.prev.next = current.next
            current.next.prev = current.prev
            
            if current == self.head:
                self.head = current.next
            if current == self.tail:
                self.tail = current.prev
        
        self.size -= 1
        return True
    
    def cek_input_keyboard(self, timeout=0.1):
        """Cek apakah ada input dari keyboard (non-blocking)"""
        if os.name == 'nt':  # Windows
            import msvcrt
            if msvcrt.kbhit():
                key = msvcrt.getch()
                if key == b' ' or key == b'\r':
                    return True
        else:  # Unix/Linux/Mac
            ready, _, _ = select.select([sys.stdin], [], [], timeout)
            if ready:
                key = sys.stdin.read(1)
                if key == ' ' or key == '\n':
                    return True
        return False
    
    def tampil_forward(self):
        """Tampilkan berita secara forward (loop)"""
        if self.head is None:
            print("Tidak ada berita")
            return
        
        print("\nBERITA FORWARD (Tekan SPASI atau ENTER untuk berhenti):")
        current = self.head
        nomor = 1
        self.stop_loop = False
        
        def check_stop():
            """Thread untuk mengecek input keyboard"""
            input()  # Tunggu input
            self.stop_loop = True
        
        # Start thread untuk deteksi keyboard
        stop_thread = threading.Thread(target=check_stop, daemon=True)
        stop_thread.start()
        
        try:
            while not self.stop_loop:
                print(f"{nomor}. {current.berita}")
                
                current = current.next
                nomor += 1
                if current == self.head:
                    nomor = 1  # reset nomor
                
                time.sleep(3)
        except KeyboardInterrupt:
            pass
        
        print("\nBerhenti")
    
    def tampil_backward(self):
        """Tampilkan berita secara backward (loop)"""
        if self.head is None:
            print("Tidak ada berita")
            return
        
        print("\nBERITA BACKWARD (Tekan SPASI atau ENTER untuk berhenti):")
        current = self.tail
        nomor = self.size
        self.stop_loop = False
        
        def check_stop():
            """Thread untuk mengecek input keyboard"""
            input()
            self.stop_loop = True
        
        # Start thread untuk deteksi keyboard
        stop_thread = threading.Thread(target=check_stop, daemon=True)
        stop_thread.start()
        
        try:
            while not self.stop_loop:
                print(f"{nomor}. {current.berita}")
                
                current = current.prev
                nomor -= 1
                if current == self.tail:
                    nomor = self.size  # reset nomor
                
                time.sleep(3)
        except KeyboardInterrupt:
            pass
        
        print("\nBerhenti")
    
    def tampil_berita(self, nomor):
        """Tampilkan berita tertentu berdasarkan nomor"""
        if self.head is None or nomor < 1 or nomor > self.size:
            print("Nomor tidak valid")
            return
        
        current = self.head
        for i in range(1, nomor):
            current = current.next
        
        print(f"Berita {nomor}: {current.berita}")
    
    def tampil_semua(self):
        """Tampilkan semua berita"""
        if self.head is None:
            print("Tidak ada berita")
            return
        
        print("\nDAFTAR BERITA:")
        current = self.head
        nomor = 1
        while True:
            print(f"{nomor}. {current.berita}")
            current = current.next
            nomor += 1
            if current == self.head:
                break
    
    def run(self):
        """Main program loop"""
        while True:
            print("\nMENU:")
            print("1. Insert berita")
            print("2. Hapus berita")
            print("3. Tampilkan berita secara forward (loop)")
            print("4. Tampilkan berita secara backward (loop)")
            print("5. Tampil berita tertentu")
            print("6. Exit")
            
            try:
                pilih = int(input("Pilih: "))
            except ValueError:
                print("Pilihan tidak valid")
                continue
            
            if pilih == 1:
                berita = input("Masukkan teks berita: ")
                self.insert_berita(berita)
            
            elif pilih == 2:
                self.tampil_semua()
                if self.head is not None:
                    try:
                        nomor_hapus = int(input("Masukkan nomor berita yang akan dihapus: "))
                        if self.hapus_berita(nomor_hapus):
                            print(f"Berita nomor {nomor_hapus} berhasil dihapus")
                        else:
                            print("Gagal menghapus")
                    except ValueError:
                        print("Input tidak valid")
            
            elif pilih == 3:
                self.tampil_forward()
            
            elif pilih == 4:
                self.tampil_backward()
            
            elif pilih == 5:
                self.tampil_semua()
                if self.head is not None:
                    try:
                        nomor_tampil = int(input("Masukkan nomor berita yang ingin ditampilkan: "))
                        self.tampil_berita(nomor_tampil)
                    except ValueError:
                        print("Input tidak valid")
            
            elif pilih == 6:
                print("Program selesai")
                break
            
            else:
                print("Pilihan tidak valid")


if __name__ == "__main__":
    app = InfiniteCircularDoublyLinkedList()
    app.run()
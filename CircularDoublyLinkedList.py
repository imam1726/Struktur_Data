import time

class Node:
    def __init__(self, berita):
        self.berita = berita
        self.next = None
        self.prev = None

head = None
tail = None
size = 0

def insertBerita(berita):
    global head, tail, size
    newNode = Node(berita)
    
    if head is None:
        head = newNode
        tail = newNode
        head.next = head
        head.prev = head
    else:
        tail.next = newNode
        newNode.prev = tail
        newNode.next = head
        head.prev = newNode
        tail = newNode
    size += 1
    print("Berita ditambahkan")

def hapusBerita(nomor):
    global head, tail, size
    if head is None or nomor < 1 or nomor > size:
        return False
    
    current = head
    for i in range(1, nomor):
        current = current.next
    
    if size == 1:
        head = None
        tail = None
    else:
        current.prev.next = current.next
        current.next.prev = current.prev
        
        if current == head:
            head = current.next
        if current == tail:
            tail = current.prev
    size -= 1
    return True

def tampilForward():
    if head is None:
        print("Tidak ada berita")
        return
    
    print("\nBERITA FORWARD:")
    current = head
    nomor = 1
    while True:
        print(f"{nomor}. {current.berita}")
        current = current.next
        nomor += 1
        time.sleep(3)
        if current == head:
            break

def tampilBackward():
    if head is None:
        print("Tidak ada berita")
        return
    
    print("\nBERITA BACKWARD:")
    current = tail
    nomor = size
    while True:
        print(f"{nomor}. {current.berita}")
        current = current.prev
        nomor -= 1
        time.sleep(3)
        if current == tail:
            break

def tampilBerita(nomor):
    if head is None or nomor < 1 or nomor > size:
        print("Nomor tidak valid")
        return
    
    current = head
    for i in range(1, nomor):
        current = current.next
    print(f"Berita {nomor}: {current.berita}")

def tampilSemua():
    if head is None:
        print("Tidak ada berita")
        return
    
    print("\nDAFTAR BERITA:")
    current = head
    nomor = 1
    while True:
        print(f"{nomor}. {current.berita}")
        current = current.next
        nomor += 1
        if current == head:
            break

def main():
    while True:
        print("\nMENU:")
        print("1. Insert berita")
        print("2. Hapus berita")
        print("3. Tampilkan berita secara forward")
        print("4. Tampilkan berita secara backward")
        print("5. Tampil berita tertentu")
        print("6. Exit")
        
        try:
            pilih = int(input("Pilih: "))
        except:
            print("Input angka")
            continue
        
        if pilih == 1:
            berita = input("Berita: ")
            insertBerita(berita)
        
        elif pilih == 2:
            tampilSemua()
            if head is not None:
                try:
                    nomor = int(input("Nomor yang dihapus: "))
                    if hapusBerita(nomor):
                        print(f"Berita {nomor} dihapus")
                    else:
                        print("Gagal menghapus")
                except:
                    print("Input salah")
        
        elif pilih == 3:
            tampilForward()
        
        elif pilih == 4:
            tampilBackward()
        
        elif pilih == 5:
            tampilSemua()
            if head is not None:
                try:
                    nomor = int(input("Nomor yang ditampil: "))
                    tampilBerita(nomor)
                except:
                    print("Input salah")
        
        elif pilih == 6:
            print("Selesai")
            break
        
        else:
            print("Pilihan salah")

if __name__ == "__main__":
    main()
import csv

class Node:
    def __init__(self, id, nama):
        self.id = id
        self.nama = nama
        self.left = None
        self.right = None

class BinarySearchTree:
    def __init__(self):
        self.root = None

    def insert(self, id, nama):
        self.root = self._insert_rec(self.root, id, nama)

    def _insert_rec(self, root, id, nama):
        if root is None:
            return Node(id, nama)
        if id < root.id:
            root.left = self._insert_rec(root.left, id, nama)
        elif id > root.id:
            root.right = self._insert_rec(root.right, id, nama)
        return root

    def search(self, id):
        return self._search_rec(self.root, id)

    def _search_rec(self, root, id):
        if root is None or root.id == id:
            return root
        if id < root.id:
            return self._search_rec(root.left, id)
        return self._search_rec(root.right, id)

    def delete(self, id):
        self.root = self._delete_rec(self.root, id)

    def _delete_rec(self, root, id):
        if root is None:
            return root
        if id < root.id:
            root.left = self._delete_rec(root.left, id)
        elif id > root.id:
            root.right = self._delete_rec(root.right, id)
        else:
            if root.left is None:
                return root.right
            elif root.right is None:
                return root.left
            root.id = self._min_value(root.right)
            root.right = self._delete_rec(root.right, root.id)
        return root

    def _min_value(self, root):
        min_val = root.id
        while root.left is not None:
            min_val = root.left.id
            root = root.left
        return min_val

    def get_size(self):
        return self._get_size_rec(self.root)

    def _get_size_rec(self, root):
        if root is None:
            return 0
        return 1 + self._get_size_rec(root.left) + self._get_size_rec(root.right)

    def inorder(self):
        self._inorder_rec(self.root)
        print()

    def _inorder_rec(self, root):
        if root is not None:
            self._inorder_rec(root.left)
            print(f"({root.id}, {root.nama})", end=" ")
            self._inorder_rec(root.right)

    def preorder(self):
        self._preorder_rec(self.root)
        print()

    def _preorder_rec(self, root):
        if root is not None:
            print(f"({root.id}, {root.nama})", end=" ")
            self._preorder_rec(root.left)
            self._preorder_rec(root.right)

    def postorder(self):
        self._postorder_rec(self.root)
        print()

    def _postorder_rec(self, root):
        if root is not None:
            self._postorder_rec(root.left)
            self._postorder_rec(root.right)
            print(f"({root.id}, {root.nama})", end=" ")

    def load_from_csv(self, filename):
        count = 0
        try:
            with open(filename, 'r', encoding='utf-8') as file:
                first_line = True
                for line in file:
                    if first_line:
                        first_line = False
                        continue
                    if line.strip():
                        data = line.strip().split(',')
                        if len(data) >= 2:
                            try:
                                id = int(data[0].strip())
                                nama = data[1].strip()
                                self.insert(id, nama)
                                count += 1
                            except ValueError:
                                print(f"Error parsing ID: {data[0]}")
            print(f"Data berhasil dimuat dari {filename}")
            print(f"Total data: {count}")
        except FileNotFoundError:
            print(f"File tidak ditemukan: {filename}")
        except Exception as e:
            print(f"Error: {e}")

    def display_all_data(self):
        if self.root is None:
            print("\n=== BELUM ADA DATA ===")
            print("BST masih kosong!")
            return
        print("\n=== SEMUA DATA ===")
        print("ID\t\tNama")
        print("----------------------")
        self._display_all_data_rec(self.root)

    def _display_all_data_rec(self, root):
        if root is not None:
            self._display_all_data_rec(root.left)
            print(f"{root.id}\t\t{root.nama}")
            self._display_all_data_rec(root.right)

if __name__ == "__main__":
    bst = BinarySearchTree()
    
    print("Memuat data dari file CSV...")
    bst.load_from_csv("/Users/clumsy/coding/struktur-data-py-java/BinarySearchTree/data.csv")
    
    while True:
        print("\n╔════════════════════════════════╗")
        print("║        Binary Search Tree      ║")
        print("╠════════════════════════════════╣")
        print("║ 1. Tambah Data                 ║")
        print("║ 2. Cari Data                   ║")
        print("║ 3. Hapus Data                  ║")
        print("║ 4. Inorder Traversal           ║")
        print("║ 5. Preorder Traversal          ║")
        print("║ 6. Postorder Traversal         ║")
        print("║ 7. Tampilkan Semua Data        ║")
        print("║ 8. Keluar                      ║")
        print("╚════════════════════════════════╝")
        
        try:
            pilihan = int(input("Pilihan: "))
        except ValueError:
            print("Masukkan angka yang valid!")
            continue
        
        if pilihan == 1:
            id = int(input("Masukkan ID: "))
            nama = input("Masukkan Nama: ")
            bst.insert(id, nama)
            print("✓ Data berhasil ditambahkan!")
        elif pilihan == 2:
            id = int(input("Masukkan ID yang dicari: "))
            result = bst.search(id)
            if result:
                print(f"✓ Ditemukan: ID={result.id}, Nama={result.nama}")
            else:
                print("✗ Data tidak ditemukan!")
        elif pilihan == 3:
            id = int(input("Masukkan ID yang akan dihapus: "))
            bst.delete(id)
            print("✓ Data berhasil dihapus!")
        elif pilihan == 4:
            print("Inorder Traversal: ", end="")
            bst.inorder()
        elif pilihan == 5:
            print("Preorder Traversal: ", end="")
            bst.preorder()
        elif pilihan == 6:
            print("Postorder Traversal: ", end="")
            bst.postorder()
        elif pilihan == 7:
            bst.display_all_data()
        elif pilihan == 8:
            print("Terima kasih!")
            break
        else:
            print("Pilihan tidak valid!")
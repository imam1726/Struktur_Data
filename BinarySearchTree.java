import java.io.*;
import java.util.*;

class Node {
    int id;
    String nama;
    Node left, right;

    Node(int id, String nama) {
        this.id = id;
        this.nama = nama;
        left = null;
        right = null;
    }
}

public class BinarySearchTree {
    Node root;

    BinarySearchTree() {
        root = null;
    }

    void insert(int id, String nama) {
        root = insertRec(root, id, nama);
    }

    Node insertRec(Node root, int id, String nama) {
        if (root == null) {
            return new Node(id, nama);
        }
        if (id < root.id) {
            root.left = insertRec(root.left, id, nama);
        } else if (id > root.id) {
            root.right = insertRec(root.right, id, nama);
        }
        return root;
    }

    Node search(int id) {
        return searchRec(root, id);
    }

    Node searchRec(Node root, int id) {
        if (root == null || root.id == id) {
            return root;
        }
        if (id < root.id) {
            return searchRec(root.left, id);
        }
        return searchRec(root.right, id);
    }

    void delete(int id) {
        root = deleteRec(root, id);
    }

    Node deleteRec(Node root, int id) {
        if (root == null) {
            return root;
        }
        if (id < root.id) {
            root.left = deleteRec(root.left, id);
        } else if (id > root.id) {
            root.right = deleteRec(root.right, id);
        } else {
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }
            root.id = minValue(root.right);
            root.right = deleteRec(root.right, root.id);
        }
        return root;
    }

    int minValue(Node root) {
        int min = root.id;
        while (root.left != null) {
            min = root.left.id;
            root = root.left;
        }
        return min;
    }

    int getSize() {
        return getSizeRec(root);
    }

    int getSizeRec(Node root) {
        if (root == null) {
            return 0;
        }
        return 1 + getSizeRec(root.left) + getSizeRec(root.right);
    }

    void inorder() {
        inorderRec(root);
        System.out.println();
    }

    void inorderRec(Node root) {
        if (root != null) {
            inorderRec(root.left);
            System.out.print("(" + root.id + "," + root.nama + ") ");
            inorderRec(root.right);
        }
    }

    void preorder() {
        preorderRec(root);
        System.out.println();
    }

    void preorderRec(Node root) {
        if (root != null) {
            System.out.print("(" + root.id + "," + root.nama + ") ");
            preorderRec(root.left);
            preorderRec(root.right);
        }
    }

    void postorder() {
        postorderRec(root);
        System.out.println();
    }

    void postorderRec(Node root) {
        if (root != null) {
            postorderRec(root.left);
            postorderRec(root.right);
            System.out.print("(" + root.id + "," + root.nama + ") ");
        }
    }

    void loadFromCSV(String filename) {
        int count = 0;
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            String line;
            boolean firstLine = true;
            while ((line = br.readLine()) != null) {
                if (firstLine) {
                    firstLine = false;
                    continue;
                }
                if (line.trim().isEmpty()) continue;
                String[] data = line.split(",");
                if (data.length >= 2) {
                    int id = Integer.parseInt(data[0].trim());
                    String nama = data[1].trim();
                    insert(id, nama);
                    count++;
                }
            }
            br.close();
            System.out.println("Data berhasil dimuat dari " + filename);
            System.out.println("Total data: " + count);
        } catch (FileNotFoundException e) {
            System.out.println("File tidak ditemukan: " + filename);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    void displayAllData() {
        if (root == null) {
            System.out.println("\n=== BELUM ADA DATA ===");
            System.out.println("BST masih kosong!");
            return;
        }
        System.out.println("\n=== SEMUA DATA ===");
        System.out.println("ID\t\tNama");
        System.out.println("----------------------");
        displayAllDataRec(root);
    }

    void displayAllDataRec(Node root) {
        if (root != null) {
            displayAllDataRec(root.left);
            System.out.println(root.id + "\t\t" + root.nama);
            displayAllDataRec(root.right);
        }
    }

    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Memuat data dari file CSV...");
    // Gunakan path lengkap
    bst.loadFromCSV("/Users/clumsy/coding/struktur-data-py-java/BinarySearchTree/data.csv");

        int pilihan;
        do {
            System.out.println("\n╔════════════════════════════════╗");
            System.out.println("║        Binary Search Tree      ║");
            System.out.println("╠════════════════════════════════╣");
            System.out.println("║ 1. Tambah Data                 ║");
            System.out.println("║ 2. Cari Data                   ║");
            System.out.println("║ 3. Hapus Data                  ║");
            System.out.println("║ 4. Inorder Traversal           ║");
            System.out.println("║ 5. Preorder Traversal          ║");
            System.out.println("║ 6. Postorder Traversal         ║");
            System.out.println("║ 7. Tampilkan Semua Data        ║");
            System.out.println("║ 8. Keluar                      ║");
            System.out.println("╚════════════════════════════════╝");
            System.out.print("Pilihan: ");
            
            try {
                pilihan = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Masukkan angka yang valid!");
                scanner.nextLine();
                pilihan = 0;
                continue;
            }

            switch (pilihan) {
                case 1:
                    System.out.print("Masukkan ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Masukkan Nama: ");
                    String nama = scanner.nextLine();
                    bst.insert(id, nama);
                    System.out.println("✓ Data berhasil ditambahkan!");
                    break;
                    
                case 2:
                    System.out.print("Masukkan ID yang dicari: ");
                    id = scanner.nextInt();
                    Node result = bst.search(id);
                    if (result != null)
                        System.out.println("✓ Ditemukan: ID=" + result.id + ", Nama=" + result.nama);
                    else
                        System.out.println("✗ Data tidak ditemukan!");
                    break;
                    
                case 3:
                    System.out.print("Masukkan ID yang akan dihapus: ");
                    id = scanner.nextInt();
                    bst.delete(id);
                    System.out.println("✓ Data berhasil dihapus!");
                    break;
                    
                case 4:
                    System.out.print("Inorder Traversal: ");
                    bst.inorder();
                    break;
                    
                case 5:
                    System.out.print("Preorder Traversal: ");
                    bst.preorder();
                    break;
                    
                case 6:
                    System.out.print("Postorder Traversal: ");
                    bst.postorder();
                    break;
                    
                case 7:
                    bst.displayAllData();
                    break;
                    
                case 8:
                    System.out.println("Terima kasih!");
                    break;
                    
                default:
                    System.out.println("Pilihan tidak valid!");
            }
        } while (pilihan != 8);

        scanner.close();
    }
}
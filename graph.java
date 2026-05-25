import java.util.*;

public class graph {
    private Map<String, List<String>> adjList = new HashMap<>();

    public void addVertex(String v) {
        if (!adjList.containsKey(v)) {
            adjList.put(v, new ArrayList<>());
            System.out.println("Vertex '" + v + "' ditambah.");
        } else {
            System.out.println("Vertex sudah wujud.");
        }
    }

    public void removeVertex(String v) {
        if (adjList.containsKey(v)) {
            for (String neighbor : adjList.get(v)) {
                adjList.get(neighbor).remove(v);
            }
            adjList.remove(v);
            System.out.println("Vertex '" + v + "' dihapus.");
        } else {
            System.out.println("Vertex tidak ditemui.");
        }
    }

    public void addEdge(String v1, String v2) {
        if (adjList.containsKey(v1) && adjList.containsKey(v2)) {
            if (!adjList.get(v1).contains(v2)) {
                adjList.get(v1).add(v2);
                adjList.get(v2).add(v1);
                System.out.println("Edge antara '" + v1 + "' dan '" + v2 + "' ditambah.");
            } else {
                System.out.println("Edge sudah wujud.");
            }
        } else {
            System.out.println("Salah satu atau kedua-dua vertex tidak ditemui.");
        }
    }

    public void removeEdge(String v1, String v2) {
        if (adjList.containsKey(v1) && adjList.containsKey(v2)) {
            if (adjList.get(v1).contains(v2)) {
                adjList.get(v1).remove(v2);
                adjList.get(v2).remove(v1);
                System.out.println("Edge antara '" + v1 + "' dan '" + v2 + "' dihapus.");
            } else {
                System.out.println("Edge tidak ditemui.");
            }
        } else {
            System.out.println("Vertex tidak ditemui.");
        }
    }

    public void displayMatrix() {
        if (adjList.isEmpty()) {
            System.out.println("Graph kosong.");
            return;
        }

        List<String> vertices = new ArrayList<>(adjList.keySet());
        Collections.sort(vertices);

        System.out.println("\nAdjacency Matrix:");
        System.out.print("  ");
        for (String v : vertices) {
            System.out.print(v + " ");
        }
        System.out.println();

        for (String v1 : vertices) {
            System.out.print(v1 + " ");
            for (String v2 : vertices) {
                if (adjList.get(v1).contains(v2)) {
                    System.out.print("1 ");
                } else {
                    System.out.print("0 ");
                }
            }
            System.out.println();
        }
    }

    public void dfs(String start) {
        if (!adjList.containsKey(start)) {
            System.out.println("Vertex awal tidak ditemui.");
            return;
        }
        System.out.print("Traversal DFS: ");
        Set<String> visited = new HashSet<>();
        dfsUtil(start, visited);
        System.out.println();
    }

    private void dfsUtil(String vertex, Set<String> visited) {
        visited.add(vertex);
        System.out.print(vertex + " ");

        List<String> neighbors = new ArrayList<>(adjList.get(vertex));
        Collections.sort(neighbors);
        
        for (String neighbor : neighbors) {
            if (!visited.contains(neighbor)) {
                dfsUtil(neighbor, visited);
            }
        }
    }

    public void bfs(String start) {
        if (!adjList.containsKey(start)) {
            System.out.println("Vertex awal tidak ditemui.");
            return;
        }

        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();

        visited.add(start);
        queue.add(start);

        System.out.print("Traversal BFS: ");
        while (!queue.isEmpty()) {
            String vertex = queue.poll();
            System.out.print(vertex + " ");

            List<String> neighbors = new ArrayList<>(adjList.get(vertex));
            Collections.sort(neighbors);

            for (String neighbor : neighbors) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        graph g = new graph();
        Scanner scanner = new Scanner(System.in);
        int pilihan;

        do {
            System.out.println("\nMENU:");
            System.out.println("1. Tambah Vertex");
            System.out.println("2. Hapus Vertex");
            System.out.println("3. Tambah Edge");
            System.out.println("4. Hapus Edge");
            System.out.println("5. Tampilkan graph (Matrix)");
            System.out.println("6. Traversal DFS");
            System.out.println("7. Traversal BFS");
            System.out.println("8. Quit");
            System.out.print("Pilih menu (1-8): ");
            
            pilihan = scanner.nextInt();
            scanner.nextLine(); 

            switch (pilihan) {
                case 1:
                    System.out.print("Masukkan nama vertex: ");
                    g.addVertex(scanner.nextLine());
                    break;
                case 2:
                    System.out.print("Masukkan nama vertex untuk dihapus: ");
                    g.removeVertex(scanner.nextLine());
                    break;
                case 3:
                    System.out.print("Masukkan vertex 1: ");
                    String v1 = scanner.nextLine();
                    System.out.print("Masukkan vertex 2: ");
                    String v2 = scanner.nextLine();
                    g.addEdge(v1, v2);
                    break;
                case 4:
                    System.out.print("Masukkan vertex 1: ");
                    String e1 = scanner.nextLine();
                    System.out.print("Masukkan vertex 2: ");
                    String e2 = scanner.nextLine();
                    g.removeEdge(e1, e2);
                    break;
                case 5:
                    g.displayMatrix();
                    break;
                case 6:
                    System.out.print("Masukkan vertex awal untuk DFS: ");
                    g.dfs(scanner.nextLine());
                    break;
                case 7:
                    System.out.print("Masukkan vertex awal untuk BFS: ");
                    g.bfs(scanner.nextLine());
                    break;
                case 8:
                    System.out.println("Program tamat.");
                    break;
                default:
                    System.out.println("Pilihan tidak sah.");
            }
        } while (pilihan != 8);
        
        scanner.close();
    }
}
from collections import deque

class Graph:
    def __init__(self):
        self.graph = {}

    def add_vertex(self, v):
        if v not in self.graph:
            self.graph[v] = []
            print(f"Vertex '{v}' ditambah.")
        else:
            print("Vertex sudah wujud.")

    def remove_vertex(self, v):
        if v in self.graph:
            for neighbor in self.graph[v]:
                self.graph[neighbor].remove(v)
            del self.graph[v]
            print(f"Vertex '{v}' dihapus.")
        else:
            print("Vertex tidak ditemui.")

    def add_edge(self, v1, v2):
        if v1 in self.graph and v2 in self.graph:
            if v2 not in self.graph[v1]:
                self.graph[v1].append(v2)
                self.graph[v2].append(v1)
                print(f"Edge antara '{v1}' dan '{v2}' ditambah.")
            else:
                print("Edge sudah wujud.")
        else:
            print("Salah satu atau kedua-dua vertex tidak ditemui.")

    def remove_edge(self, v1, v2):
        if v1 in self.graph and v2 in self.graph:
            if v2 in self.graph[v1]:
                self.graph[v1].remove(v2)
                self.graph[v2].remove(v1)
                print(f"Edge antara '{v1}' dan '{v2}' dihapus.")
            else:
                print("Edge tidak ditemui.")
        else:
            print("Vertex tidak ditemui.")

    def display_matrix(self):
        vertices = sorted(list(self.graph.keys()))
        if not vertices:
            print("Graph kosong.")
            return
        
        print("\nAdjacency Matrix:")
        print("  " + " ".join(vertices))
        for v1 in vertices:
            row = [v1]
            for v2 in vertices:
                if v2 in self.graph[v1]:
                    row.append("1")
                else:
                    row.append("0")
            print(" ".join(row))

    def dfs(self, start, visited=None, is_first=True):
        if start not in self.graph:
            print("Vertex awal tidak ditemui.")
            return
        if visited is None:
            visited = set()
            print("Traversal DFS:", end=" ")
        visited.add(start)
        print(start, end=" ")
        for neighbor in sorted(self.graph[start]):
            if neighbor not in visited:
                self.dfs(neighbor, visited, False)
        if is_first:
            print()

    def bfs(self, start):
        if start not in self.graph:
            print("Vertex awal tidak ditemui.")
            return
        visited = set([start])
        queue = deque([start])
        print("Traversal BFS:", end=" ")
        while queue:
            vertex = queue.popleft()
            print(vertex, end=" ")
            for neighbor in sorted(self.graph[vertex]):
                if neighbor not in visited:
                    visited.add(neighbor)
                    queue.append(neighbor)
        print()

def main():
    g = Graph()
    while True:
        print("\nMENU:")
        print("1. Tambah Vertex")
        print("2. Hapus Vertex")
        print("3. Tambah Edge")
        print("4. Hapus Edge")
        print("5. Tampilkan graph (Matrix)")
        print("6. Traversal DFS")
        print("7. Traversal BFS")
        print("8. Quit")
        
        pilihan = input("Pilih menu (1-8): ")
        
        if pilihan == '1':
            v = input("Masukkan nama vertex: ")
            g.add_vertex(v)
        elif pilihan == '2':
            v = input("Masukkan nama vertex untuk dihapus: ")
            g.remove_vertex(v)
        elif pilihan == '3':
            v1 = input("Masukkan vertex 1: ")
            v2 = input("Masukkan vertex 2: ")
            g.add_edge(v1, v2)
        elif pilihan == '4':
            v1 = input("Masukkan vertex 1: ")
            v2 = input("Masukkan vertex 2: ")
            g.remove_edge(v1, v2)
        elif pilihan == '5':
            g.display_matrix()
        elif pilihan == '6':
            start = input("Masukkan vertex awal untuk DFS: ")
            g.dfs(start)
        elif pilihan == '7':
            start = input("Masukkan vertex awal untuk BFS: ")
            g.bfs(start)
        elif pilihan == '8':
            print("Program tamat.")
            break
        else:
            print("Pilihan tidak sah.")

if __name__ == "__main__":
    main()
class MatrixOperations:
    def __init__(self):
        self.matrix_storage = {}
        self.active_matrix = None
    
    def show_menu(self):
        print("\n=========================================")
        print("            MATRIX OPERATIONS")
        print("=========================================")
        print("1. Create Matrix")
        print("2. Select Matrix")
        print("3-a. Sort the matrix row-wise")
        print("3-b. Sort the matrix column-wise")
        print("4-a. Rotate Matrix Clockwise by 1")
        print("4-b. Rotate Matrix Counter-Clockwise by 1")
        print("4-c. Rotate a matrix by 90")
        print("4-d. Rotate a matrix by 180")
        print("5-a. Row-wise traversal of matrix")
        print("5-b. Column-wise traversal of matrix")
        print("6. Print matrix in spiral form")
        print("7. Transpose matrix")
        print("8. List Matrix")
        print("9. Delete Matrix")
        print("10. Quit")
        print()
        if self.active_matrix:
            print(f"Active Matrix: {self.active_matrix}")
        else:
            print("Active Matrix: None")
    
    def create_matrix(self):
        print()
        name = input("Enter matrix name: ").strip()
        
        if not name:
            print("Matrix name cannot be empty!")
            return
        
        if name in self.matrix_storage:
            confirm = input("Matrix exists! Overwrite? (y/n): ").lower()
            if confirm != 'y':
                print("Canceled.")
                return
        
        rows = int(input("Enter number of rows: "))
        cols = int(input("Enter number of columns: "))
        
        matrix = []
        print("Enter matrix elements (separate with space for each row):")
        
        for i in range(rows):
            while True:
                row_input = input(f"Row {i+1}: ").strip().split()
                if len(row_input) == cols:
                    break
                print(f"Invalid! Enter {cols} numbers:")
            
            row = [int(x) for x in row_input]
            matrix.append(row)
        
        self.matrix_storage[name] = matrix
        self.active_matrix = name
        print(f"\nMatrix '{name}' created and set as active!")
    
    def select_matrix(self):
        if not self.matrix_storage:
            print("\nNo matrices available! Create one first.")
            return
        
        self.list_matrix()
        name = input("\nEnter matrix name to select: ").strip()
        
        if name in self.matrix_storage:
            self.active_matrix = name
            print(f"\nMatrix '{name}' is now active!")
        else:
            print(f"\nMatrix '{name}' not found!")
    
    def sort_row_wise(self):
        if not self.check_active_matrix():
            return
        
        matrix = self.matrix_storage[self.active_matrix]
        print("\nBefore sorting:")
        self.print_matrix(matrix)
        
        for row in matrix:
            row.sort()
        
        print("\nAfter sorting:")
        self.print_matrix(matrix)
        print("\nMatrix sorted row-wise.")
    
    def sort_column_wise(self):
        if not self.check_active_matrix():
            return
        
        matrix = self.matrix_storage[self.active_matrix]
        print("\nBefore sorting:")
        self.print_matrix(matrix)
        
        rows = len(matrix)
        cols = len(matrix[0])
        
        for j in range(cols):
            column = [matrix[i][j] for i in range(rows)]
            column.sort()
            for i in range(rows):
                matrix[i][j] = column[i]
        
        print("\nAfter sorting:")
        self.print_matrix(matrix)
        print("\nMatrix sorted column-wise.")
    
    def rotate_clockwise_by_1(self):
        if not self.check_active_matrix():
            return
        
        matrix = self.matrix_storage[self.active_matrix]
        print("\nBefore rotation:")
        self.print_matrix(matrix)
        
        rows = len(matrix)
        cols = len(matrix[0])
        
        if rows == 0 or cols == 0:
            return
        
        top, bottom = 0, rows - 1
        left, right = 0, cols - 1
        
        while top < bottom and left < right:
            prev = matrix[top + 1][left]
            
            for j in range(left, right + 1):
                temp = matrix[top][j]
                matrix[top][j] = prev
                prev = temp
            top += 1
            
            for i in range(top, bottom + 1):
                temp = matrix[i][right]
                matrix[i][right] = prev
                prev = temp
            right -= 1
            
            if top <= bottom:
                for j in range(right, left - 1, -1):
                    temp = matrix[bottom][j]
                    matrix[bottom][j] = prev
                    prev = temp
                bottom -= 1
            
            if left <= right:
                for i in range(bottom, top - 1, -1):
                    temp = matrix[i][left]
                    matrix[i][left] = prev
                    prev = temp
                left += 1
        
        print("\nAfter rotation:")
        self.print_matrix(matrix)
        print("\nMatrix rotated clockwise by 1 position.")
    
    def rotate_counter_clockwise_by_1(self):
        if not self.check_active_matrix():
            return
        
        matrix = self.matrix_storage[self.active_matrix]
        print("\nBefore rotation:")
        self.print_matrix(matrix)
        
        for _ in range(3):
            rows = len(matrix)
            cols = len(matrix[0])
            
            if rows == 0 or cols == 0:
                return
            
            top, bottom = 0, rows - 1
            left, right = 0, cols - 1
            
            while top < bottom and left < right:
                prev = matrix[top + 1][left]
                
                for j in range(left, right + 1):
                    temp = matrix[top][j]
                    matrix[top][j] = prev
                    prev = temp
                top += 1
                
                for i in range(top, bottom + 1):
                    temp = matrix[i][right]
                    matrix[i][right] = prev
                    prev = temp
                right -= 1
                
                if top <= bottom:
                    for j in range(right, left - 1, -1):
                        temp = matrix[bottom][j]
                        matrix[bottom][j] = prev
                        prev = temp
                    bottom -= 1
                
                if left <= right:
                    for i in range(bottom, top - 1, -1):
                        temp = matrix[i][left]
                        matrix[i][left] = prev
                        prev = temp
                    left += 1
        
        print("\nAfter rotation:")
        self.print_matrix(matrix)
        print("\nMatrix rotated counter-clockwise by 1 position.")
    
    def rotate_by_90(self):
        if not self.check_active_matrix():
            return
        
        matrix = self.matrix_storage[self.active_matrix]
        print("\nBefore rotation:")
        self.print_matrix(matrix)
        
        rows = len(matrix)
        cols = len(matrix[0])
        result = [[0 for _ in range(rows)] for _ in range(cols)]
        
        for i in range(rows):
            for j in range(cols):
                result[j][rows - 1 - i] = matrix[i][j]
        
        self.matrix_storage[self.active_matrix] = result
        
        print("\nAfter rotation:")
        self.print_matrix(result)
        print("\nMatrix rotated by 90 degrees.")
    
    def rotate_by_180(self):
        if not self.check_active_matrix():
            return
        
        matrix = self.matrix_storage[self.active_matrix]
        print("\nBefore rotation:")
        self.print_matrix(matrix)
        
        rows = len(matrix)
        cols = len(matrix[0])
        result = [[0 for _ in range(cols)] for _ in range(rows)]
        
        for i in range(rows):
            for j in range(cols):
                result[rows - 1 - i][cols - 1 - j] = matrix[i][j]
        
        self.matrix_storage[self.active_matrix] = result
        
        print("\nAfter rotation:")
        self.print_matrix(result)
        print("\nMatrix rotated by 180 degrees.")
    
    def row_wise_traversal(self):
        if not self.check_active_matrix():
            return
        
        matrix = self.matrix_storage[self.active_matrix]
        print("\nRow-wise traversal:")
        for row in matrix:
            for val in row:
                print(val, end=" ")
        print()
    
    def column_wise_traversal(self):
        if not self.check_active_matrix():
            return
        
        matrix = self.matrix_storage[self.active_matrix]
        print("\nColumn-wise traversal:")
        rows = len(matrix)
        cols = len(matrix[0])
        for j in range(cols):
            for i in range(rows):
                print(matrix[i][j], end=" ")
        print()
    
    def print_spiral_form(self):
        if not self.check_active_matrix():
            return
        
        matrix = self.matrix_storage[self.active_matrix]
        if not matrix:
            return
        
        rows = len(matrix)
        cols = len(matrix[0])
        top, bottom = 0, rows - 1
        left, right = 0, cols - 1
        
        print("\nMatrix in spiral form:")
        while top <= bottom and left <= right:
            for j in range(left, right + 1):
                print(matrix[top][j], end=" ")
            top += 1
            
            for i in range(top, bottom + 1):
                print(matrix[i][right], end=" ")
            right -= 1
            
            if top <= bottom:
                for j in range(right, left - 1, -1):
                    print(matrix[bottom][j], end=" ")
                bottom -= 1
            
            if left <= right:
                for i in range(bottom, top - 1, -1):
                    print(matrix[i][left], end=" ")
                left += 1
        print()
    
    def transpose_matrix(self):
        if not self.check_active_matrix():
            return
        
        matrix = self.matrix_storage[self.active_matrix]
        print("\nBefore transpose:")
        self.print_matrix(matrix)
        
        rows = len(matrix)
        cols = len(matrix[0])
        result = [[0 for _ in range(rows)] for _ in range(cols)]
        
        for i in range(rows):
            for j in range(cols):
                result[j][i] = matrix[i][j]
        
        self.matrix_storage[self.active_matrix] = result
        
        print("\nAfter transpose:")
        self.print_matrix(result)
        print("\nMatrix transposed.")
    
    def list_matrix(self):
        if not self.matrix_storage:
            print("\nNo matrices available.")
            return
        
        print("\nList of Matrices:")
        print("----------------------------------------")
        for name, matrix in self.matrix_storage.items():
            active = " [ACTIVE]" if name == self.active_matrix else ""
            print(f"  {name}{active} ({len(matrix)}x{len(matrix[0])})")
        print("----------------------------------------")
    
    def delete_matrix(self):
        if not self.matrix_storage:
            print("\nNo matrices to delete.")
            return
        
        self.list_matrix()
        name = input("\nEnter matrix name to delete: ").strip()
        
        if name not in self.matrix_storage:
            print(f"\nMatrix '{name}' not found!")
            return
        
        confirm = input("Are you sure? (y/n): ").lower()
        
        if confirm == 'y':
            del self.matrix_storage[name]
            print(f"\nMatrix '{name}' deleted!")
            
            if self.active_matrix == name:
                if self.matrix_storage:
                    self.active_matrix = next(iter(self.matrix_storage))
                    print(f"Active matrix changed to: {self.active_matrix}")
                else:
                    self.active_matrix = None
        else:
            print("\nDeletion canceled.")
    
    def check_active_matrix(self):
        if not self.active_matrix or self.active_matrix not in self.matrix_storage:
            print("\nNo active matrix! Please create or select a matrix first.")
            return False
        return True
    
    def print_matrix(self, matrix):
        for row in matrix:
            for val in row:
                print(f"{val:4d}", end="")
            print()
    
    def run(self):
        while True:
            self.show_menu()
            choice = input("Enter your choice: ").strip().lower()
            
            if choice == "10":
                break
            
            if choice == "1":
                self.create_matrix()
            elif choice == "2":
                self.select_matrix()
            elif choice in ["3-a", "3a"]:
                self.sort_row_wise()
            elif choice in ["3-b", "3b"]:
                self.sort_column_wise()
            elif choice in ["4-a", "4a"]:
                self.rotate_clockwise_by_1()
            elif choice in ["4-b", "4b"]:
                self.rotate_counter_clockwise_by_1()
            elif choice in ["4-c", "4c"]:
                self.rotate_by_90()
            elif choice in ["4-d", "4d"]:
                self.rotate_by_180()
            elif choice in ["5-a", "5a"]:
                self.row_wise_traversal()
            elif choice in ["5-b", "5b"]:
                self.column_wise_traversal()
            elif choice == "6":
                self.print_spiral_form()
            elif choice == "7":
                self.transpose_matrix()
            elif choice == "8":
                self.list_matrix()
            elif choice == "9":
                self.delete_matrix()
            else:
                print("Invalid choice!")

if __name__ == "__main__":
    app = MatrixOperations()
    app.run()
import copy

class MatrixPreserve:
    def __init__(self):
        self.original_matrix = None
    
    def normalize_choice(self, choice):
        choice = choice.lower().strip()
        
        if len(choice) == 3 and choice[1] == '-':
            return choice
        
        if len(choice) == 2 and choice[0] in '12345' and choice[1] in 'abcd':
            return f"{choice[0]}-{choice[1]}"
        
        if len(choice) == 2 and choice[0] in 'abcd' and choice[1] in '12345':
            return f"{choice[1]}-{choice[0]}"
        
        if choice in ['4', '5', '6']:
            return choice
        
        return choice
    
    def input_matrix(self):
        rows = int(input("Enter number of rows: "))
        cols = int(input("Enter number of columns: "))
        
        matrix = []
        print("Enter matrix elements (pisahkan dengan spasi untuk setiap baris):")
        
        for i in range(rows):
            while True:
                row_input = input(f"Baris ke-{i+1}: ").strip().split()
                if len(row_input) == cols:
                    break
                print(f"Input tidak valid! Masukkan {cols} angka:")
            
            row = [int(x) for x in row_input]
            matrix.append(row)
        
        self.original_matrix = matrix
    
    def print_matrix(self, matrix):
        for row in matrix:
            for val in row:
                print(f"{val:4d}", end="")
            print()
    
    def sort_row_wise(self, matrix):
        for row in matrix:
            row.sort()
        print("\nMatrix sorted row-wise.")
        return matrix
    
    def sort_column_wise(self, matrix):
        rows = len(matrix)
        cols = len(matrix[0])
        
        for j in range(cols):
            column = [matrix[i][j] for i in range(rows)]
            column.sort()
            for i in range(rows):
                matrix[i][j] = column[i]
        
        print("\nMatrix sorted column-wise.")
        return matrix
    
    def rotate_clockwise_by_1(self, matrix):
        if not matrix or not matrix[0]:
            return matrix
        
        rows, cols = len(matrix), len(matrix[0])
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
        
        print("\nMatrix rotated clockwise by 1 position.")
        return matrix
    
    def rotate_counter_clockwise_by_1(self, matrix):
        for _ in range(3):
            matrix = self.rotate_clockwise_by_1(matrix)
        print("\nMatrix rotated counter-clockwise by 1 position.")
        return matrix
    
    def rotate_by_90(self, matrix):
        rows, cols = len(matrix), len(matrix[0])
        result = [[0 for _ in range(rows)] for _ in range(cols)]
        
        for i in range(rows):
            for j in range(cols):
                result[j][rows - 1 - i] = matrix[i][j]
        
        print("\nMatrix rotated by 90 degrees.")
        return result
    
    def rotate_by_180(self, matrix):
        rows, cols = len(matrix), len(matrix[0])
        result = [[0 for _ in range(cols)] for _ in range(rows)]
        
        for i in range(rows):
            for j in range(cols):
                result[rows - 1 - i][cols - 1 - j] = matrix[i][j]
        
        print("\nMatrix rotated by 180 degrees.")
        return result
    
    def row_wise_traversal(self, matrix):
        print("\nRow-wise traversal:")
        for row in matrix:
            for val in row:
                print(val, end=" ")
        print()
    
    def column_wise_traversal(self, matrix):
        print("\nColumn-wise traversal:")
        rows, cols = len(matrix), len(matrix[0])
        for j in range(cols):
            for i in range(rows):
                print(matrix[i][j], end=" ")
        print()
    
    def print_spiral_form(self, matrix):
        if not matrix:
            return
        
        rows, cols = len(matrix), len(matrix[0])
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
    
    def transpose_matrix(self, matrix):
        rows, cols = len(matrix), len(matrix[0])
        result = [[0 for _ in range(rows)] for _ in range(cols)]
        
        for i in range(rows):
            for j in range(cols):
                result[j][i] = matrix[i][j]
        
        print("\nMatrix transposed.")
        return result
    
    def run(self):
        while True:
            print("\n=== MATRIX OPERATIONS ===")
            print("1-a. Sort the Matrix Row-Wise")
            print("1-b. Sort the Matrix Column-Wise")
            print("2-a. Rotate Matrix Clockwise by 1")
            print("2-b. Rotate Matrix Counter-Clockwise by 1")
            print("2-c. Rotate the Matrix by 90")
            print("2-d. Rotate the Matrix by 180")
            print("3-a. Row-wise Traversal of Matrix")
            print("3-b. Column-Wise Traversal of Matrix")
            print("4. Print Matrix In Spiral Form")
            print("5. Transpose Matrix")
            print("6. Quit")
            
            choice = input("Enter your choice: ")
            
            if choice == "6":
                break
            
            if self.original_matrix is None:
                self.input_matrix()
            
            working_matrix = copy.deepcopy(self.original_matrix)
            
            print("\nOriginal Matrix:")
            self.print_matrix(self.original_matrix)
            
            choice = self.normalize_choice(choice)
            
            if choice == "1-a":
                working_matrix = self.sort_row_wise(working_matrix)
                print("\nResult Matrix:")
                self.print_matrix(working_matrix)
            elif choice == "1-b":
                working_matrix = self.sort_column_wise(working_matrix)
                print("\nResult Matrix:")
                self.print_matrix(working_matrix)
            elif choice == "2-a":
                working_matrix = self.rotate_clockwise_by_1(working_matrix)
                print("\nResult Matrix:")
                self.print_matrix(working_matrix)
            elif choice == "2-b":
                working_matrix = self.rotate_counter_clockwise_by_1(working_matrix)
                print("\nResult Matrix:")
                self.print_matrix(working_matrix)
            elif choice == "2-c":
                working_matrix = self.rotate_by_90(working_matrix)
                print("\nResult Matrix:")
                self.print_matrix(working_matrix)
            elif choice == "2-d":
                working_matrix = self.rotate_by_180(working_matrix)
                print("\nResult Matrix:")
                self.print_matrix(working_matrix)
            elif choice == "3-a":
                self.row_wise_traversal(working_matrix)
            elif choice == "3-b":
                self.column_wise_traversal(working_matrix)
            elif choice == "4":
                self.print_spiral_form(working_matrix)
            elif choice == "5":
                working_matrix = self.transpose_matrix(working_matrix)
                print("\nResult Matrix:")
                self.print_matrix(working_matrix)
            else:
                print("Invalid choice!")

if __name__ == "__main__":
    app = MatrixPreserve()
    app.run()
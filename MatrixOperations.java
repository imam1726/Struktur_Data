import java.util.Arrays;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

public class MatrixOperations {
    private static Scanner scanner = new Scanner(System.in);
    private static HashMap<String, int[][]> matrixStorage = new HashMap<>();
    private static String activeMatrix = null;
    
    public static void main(String[] args) {
        while (true) {
            showMenu();
            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine().trim().toLowerCase();
            
            if (choice.equals("10")) {
                break;
            }
            
            switch (choice) {
                case "1":
                    createMatrix();
                    break;
                case "2":
                    selectMatrix();
                    break;
                case "3-a":
                case "3a":
                    sortRowWise();
                    break;
                case "3-b":
                case "3b":
                    sortColumnWise();
                    break;
                case "4-a":
                case "4a":
                    rotateClockwiseBy1();
                    break;
                case "4-b":
                case "4b":
                    rotateCounterClockwiseBy1();
                    break;
                case "4-c":
                case "4c":
                    rotateBy90();
                    break;
                case "4-d":
                case "4d":
                    rotateBy180();
                    break;
                case "5-a":
                case "5a":
                    rowWiseTraversal();
                    break;
                case "5-b":
                case "5b":
                    columnWiseTraversal();
                    break;
                case "6":
                    printSpiralForm();
                    break;
                case "7":
                    transposeMatrix();
                    break;
                case "8":
                    listMatrix();
                    break;
                case "9":
                    deleteMatrix();
                    break;
                default:
                    System.out.println("Invalid choice!");
                    break;
            }
        }
        scanner.close();
    }
    
    private static void showMenu() {
        System.out.println("\n=========================================");
        System.out.println("            MATRIX OPERATIONS");
        System.out.println("=========================================");
        System.out.println("1. Create Matrix");
        System.out.println("2. Select Matrix");
        System.out.println("3-a. Sort the matrix row-wise");
        System.out.println("3-b. Sort the matrix column-wise");
        System.out.println("4-a. Rotate Matrix Clockwise by 1");
        System.out.println("4-b. Rotate Matrix Counter-Clockwise by 1");
        System.out.println("4-c. Rotate a matrix by 90");
        System.out.println("4-d. Rotate a matrix by 180");
        System.out.println("5-a. Row-wise traversal of matrix");
        System.out.println("5-b. Column-wise traversal of matrix");
        System.out.println("6. Print matrix in spiral form");
        System.out.println("7. Transpose matrix");
        System.out.println("8. List Matrix");
        System.out.println("9. Delete Matrix");
        System.out.println("10. Quit");
        System.out.println();
        if (activeMatrix != null) {
            System.out.println("Active Matrix: " + activeMatrix);
        } else {
            System.out.println("Active Matrix: None");
        }
    }
    
    private static void createMatrix() {
        System.out.print("\nEnter matrix name: ");
        String name = scanner.nextLine().trim();
        
        if (name.isEmpty()) {
            System.out.println("Matrix name cannot be empty!");
            return;
        }
        
        if (matrixStorage.containsKey(name)) {
            System.out.print("Matrix exists! Overwrite? (y/n): ");
            String confirm = scanner.nextLine().trim().toLowerCase();
            if (!confirm.equals("y")) {
                System.out.println("Canceled.");
                return;
            }
        }
        
        System.out.print("Enter number of rows: ");
        int rows = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter number of columns: ");
        int cols = Integer.parseInt(scanner.nextLine());
        
        int[][] matrix = new int[rows][cols];
        System.out.println("Enter matrix elements (separate with space for each row):");
        
        for (int i = 0; i < rows; i++) {
            System.out.print("Row " + (i+1) + ": ");
            String[] numbers = scanner.nextLine().trim().split(" ");
            
            while (numbers.length != cols) {
                System.out.print("Invalid! Enter " + cols + " numbers: ");
                numbers = scanner.nextLine().trim().split(" ");
            }
            
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = Integer.parseInt(numbers[j]);
            }
        }
        
        matrixStorage.put(name, matrix);
        activeMatrix = name;
        System.out.println("\nMatrix '" + name + "' created and set as active!");
    }
    
    private static void selectMatrix() {
        if (matrixStorage.isEmpty()) {
            System.out.println("\nNo matrices available! Create one first.");
            return;
        }
        
        listMatrix();
        System.out.print("\nEnter matrix name to select: ");
        String name = scanner.nextLine().trim();
        
        if (matrixStorage.containsKey(name)) {
            activeMatrix = name;
            System.out.println("\nMatrix '" + name + "' is now active!");
        } else {
            System.out.println("\nMatrix '" + name + "' not found!");
        }
    }
    
    private static void sortRowWise() {
        if (!checkActiveMatrix()) return;
        
        int[][] matrix = matrixStorage.get(activeMatrix);
        System.out.println("\nBefore sorting:");
        printMatrix(matrix);
        
        for (int[] row : matrix) {
            Arrays.sort(row);
        }
        
        System.out.println("\nAfter sorting:");
        printMatrix(matrix);
        System.out.println("\nMatrix sorted row-wise.");
    }
    
    private static void sortColumnWise() {
        if (!checkActiveMatrix()) return;
        
        int[][] matrix = matrixStorage.get(activeMatrix);
        System.out.println("\nBefore sorting:");
        printMatrix(matrix);
        
        int rows = matrix.length;
        int cols = matrix[0].length;
        
        for (int j = 0; j < cols; j++) {
            int[] column = new int[rows];
            for (int i = 0; i < rows; i++) {
                column[i] = matrix[i][j];
            }
            Arrays.sort(column);
            for (int i = 0; i < rows; i++) {
                matrix[i][j] = column[i];
            }
        }
        
        System.out.println("\nAfter sorting:");
        printMatrix(matrix);
        System.out.println("\nMatrix sorted column-wise.");
    }
    
    private static void rotateClockwiseBy1() {
        if (!checkActiveMatrix()) return;
        
        int[][] matrix = matrixStorage.get(activeMatrix);
        System.out.println("\nBefore rotation:");
        printMatrix(matrix);
        
        int rows = matrix.length;
        int cols = matrix[0].length;
        
        if (rows == 0 || cols == 0) return;
        
        int top = 0, bottom = rows - 1, left = 0, right = cols - 1;
        
        while (top < bottom && left < right) {
            int prev = matrix[top + 1][left];
            
            for (int j = left; j <= right; j++) {
                int temp = matrix[top][j];
                matrix[top][j] = prev;
                prev = temp;
            }
            top++;
            
            for (int i = top; i <= bottom; i++) {
                int temp = matrix[i][right];
                matrix[i][right] = prev;
                prev = temp;
            }
            right--;
            
            if (top <= bottom) {
                for (int j = right; j >= left; j--) {
                    int temp = matrix[bottom][j];
                    matrix[bottom][j] = prev;
                    prev = temp;
                }
                bottom--;
            }
            
            if (left <= right) {
                for (int i = bottom; i >= top; i--) {
                    int temp = matrix[i][left];
                    matrix[i][left] = prev;
                    prev = temp;
                }
                left++;
            }
        }
        
        System.out.println("\nAfter rotation:");
        printMatrix(matrix);
        System.out.println("\nMatrix rotated clockwise by 1 position.");
    }
    
    private static void rotateCounterClockwiseBy1() {
        if (!checkActiveMatrix()) return;
        
        int[][] matrix = matrixStorage.get(activeMatrix);
        System.out.println("\nBefore rotation:");
        printMatrix(matrix);
        
        for (int k = 0; k < 3; k++) {
            int rows = matrix.length;
            int cols = matrix[0].length;
            
            if (rows == 0 || cols == 0) return;
            
            int top = 0, bottom = rows - 1, left = 0, right = cols - 1;
            
            while (top < bottom && left < right) {
                int prev = matrix[top + 1][left];
                
                for (int j = left; j <= right; j++) {
                    int temp = matrix[top][j];
                    matrix[top][j] = prev;
                    prev = temp;
                }
                top++;
                
                for (int i = top; i <= bottom; i++) {
                    int temp = matrix[i][right];
                    matrix[i][right] = prev;
                    prev = temp;
                }
                right--;
                
                if (top <= bottom) {
                    for (int j = right; j >= left; j--) {
                        int temp = matrix[bottom][j];
                        matrix[bottom][j] = prev;
                        prev = temp;
                    }
                    bottom--;
                }
                
                if (left <= right) {
                    for (int i = bottom; i >= top; i--) {
                        int temp = matrix[i][left];
                        matrix[i][left] = prev;
                        prev = temp;
                    }
                    left++;
                }
            }
        }
        
        System.out.println("\nAfter rotation:");
        printMatrix(matrix);
        System.out.println("\nMatrix rotated counter-clockwise by 1 position.");
    }
    
    private static void rotateBy90() {
        if (!checkActiveMatrix()) return;
        
        int[][] matrix = matrixStorage.get(activeMatrix);
        System.out.println("\nBefore rotation:");
        printMatrix(matrix);
        
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] result = new int[cols][rows];
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[j][rows - 1 - i] = matrix[i][j];
            }
        }
        
        matrixStorage.put(activeMatrix, result);
        
        System.out.println("\nAfter rotation:");
        printMatrix(result);
        System.out.println("\nMatrix rotated by 90 degrees.");
    }
    
    private static void rotateBy180() {
        if (!checkActiveMatrix()) return;
        
        int[][] matrix = matrixStorage.get(activeMatrix);
        System.out.println("\nBefore rotation:");
        printMatrix(matrix);
        
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] result = new int[rows][cols];
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[rows - 1 - i][cols - 1 - j] = matrix[i][j];
            }
        }
        
        matrixStorage.put(activeMatrix, result);
        
        System.out.println("\nAfter rotation:");
        printMatrix(result);
        System.out.println("\nMatrix rotated by 180 degrees.");
    }
    
    private static void rowWiseTraversal() {
        if (!checkActiveMatrix()) return;
        
        int[][] matrix = matrixStorage.get(activeMatrix);
        System.out.println("\nRow-wise traversal:");
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
        }
        System.out.println();
    }
    
    private static void columnWiseTraversal() {
        if (!checkActiveMatrix()) return;
        
        int[][] matrix = matrixStorage.get(activeMatrix);
        System.out.println("\nColumn-wise traversal:");
        for (int j = 0; j < matrix[0].length; j++) {
            for (int i = 0; i < matrix.length; i++) {
                System.out.print(matrix[i][j] + " ");
            }
        }
        System.out.println();
    }
    
    private static void printSpiralForm() {
        if (!checkActiveMatrix()) return;
        
        int[][] matrix = matrixStorage.get(activeMatrix);
        if (matrix.length == 0) return;
        
        int rows = matrix.length, cols = matrix[0].length;
        int top = 0, bottom = rows - 1, left = 0, right = cols - 1;
        
        System.out.println("\nMatrix in spiral form:");
        while (top <= bottom && left <= right) {
            for (int j = left; j <= right; j++) {
                System.out.print(matrix[top][j] + " ");
            }
            top++;
            
            for (int i = top; i <= bottom; i++) {
                System.out.print(matrix[i][right] + " ");
            }
            right--;
            
            if (top <= bottom) {
                for (int j = right; j >= left; j--) {
                    System.out.print(matrix[bottom][j] + " ");
                }
                bottom--;
            }
            
            if (left <= right) {
                for (int i = bottom; i >= top; i--) {
                    System.out.print(matrix[i][left] + " ");
                }
                left++;
            }
        }
        System.out.println();
    }
    
    private static void transposeMatrix() {
        if (!checkActiveMatrix()) return;
        
        int[][] matrix = matrixStorage.get(activeMatrix);
        System.out.println("\nBefore transpose:");
        printMatrix(matrix);
        
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] result = new int[cols][rows];
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[j][i] = matrix[i][j];
            }
        }
        
        matrixStorage.put(activeMatrix, result);
        
        System.out.println("\nAfter transpose:");
        printMatrix(result);
        System.out.println("\nMatrix transposed.");
    }
    
    private static void listMatrix() {
        if (matrixStorage.isEmpty()) {
            System.out.println("\nNo matrices available.");
            return;
        }
        
        System.out.println("\nList of Matrices:");
        System.out.println("----------------------------------------");
        for (Map.Entry<String, int[][]> entry : matrixStorage.entrySet()) {
            String name = entry.getKey();
            int[][] matrix = entry.getValue();
            String active = (name.equals(activeMatrix)) ? " [ACTIVE]" : "";
            System.out.println("  " + name + active + " (" + matrix.length + "x" + matrix[0].length + ")");
        }
        System.out.println("----------------------------------------");
    }
    
    private static void deleteMatrix() {
        if (matrixStorage.isEmpty()) {
            System.out.println("\nNo matrices to delete.");
            return;
        }
        
        listMatrix();
        System.out.print("\nEnter matrix name to delete: ");
        String name = scanner.nextLine().trim();
        
        if (!matrixStorage.containsKey(name)) {
            System.out.println("\nMatrix '" + name + "' not found!");
            return;
        }
        
        System.out.print("Are you sure? (y/n): ");
        String confirm = scanner.nextLine().trim().toLowerCase();
        
        if (confirm.equals("y")) {
            matrixStorage.remove(name);
            System.out.println("\nMatrix '" + name + "' deleted!");
            
            if (activeMatrix != null && activeMatrix.equals(name)) {
                if (!matrixStorage.isEmpty()) {
                    activeMatrix = matrixStorage.keySet().iterator().next();
                    System.out.println("Active matrix changed to: " + activeMatrix);
                } else {
                    activeMatrix = null;
                }
            }
        } else {
            System.out.println("\nDeletion canceled.");
        }
    }
    
    private static boolean checkActiveMatrix() {
        if (!isMatrixActive()) {
            System.out.println("\nNo active matrix! Please create or select a matrix first.");
            return false;
        }
        return true;
    }
    
    private static boolean isMatrixActive() {
        return activeMatrix != null && matrixStorage.containsKey(activeMatrix);
    }
    
    private static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int val : row) {
                System.out.printf("%4d", val);
            }
            System.out.println();
        }
    }
}
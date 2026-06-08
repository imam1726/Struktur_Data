import java.util.Arrays;
import java.util.Scanner;

public class Matrix {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int[][] matrix = null;
        
        while (true) {
            System.out.println("\n=== MATRIX OPERATIONS ===");
            System.out.println("1-a. Sort the Matrix Row-Wise");
            System.out.println("1-b. Sort the Matrix Column-Wise");
            System.out.println("2-a. Rotate Matrix Clockwise by 1");
            System.out.println("2-b. Rotate Matrix Counter-Clockwise by 1");
            System.out.println("2-c. Rotate the Matrix by 90");
            System.out.println("2-d. Rotate the Matrix by 180");
            System.out.println("3-a. Row-wise Traversal of Matrix");
            System.out.println("3-b. Column-Wise Traversal of Matrix");
            System.out.println("4. Print Matrix In Spiral Form");
            System.out.println("5. Transpose Matrix");
            System.out.println("6. Quit");
            System.out.print("Enter your choice: ");
            
            String choice = scanner.nextLine().trim().toLowerCase();
            
            if (choice.equals("6")) {
                break;
            }
            
            choice = normalizeChoice(choice);
            
            if (matrix == null && !choice.equals("6")) {
                matrix = inputMatrix();
            }
            
            System.out.println("\nCurrent Matrix:");
            printMatrix(matrix);
            
            switch (choice) {
                case "1-a":
                    sortRowWise(matrix);
                    System.out.println("\nResult Matrix:");
                    printMatrix(matrix);
                    break;
                case "1-b":
                    sortColumnWise(matrix);
                    System.out.println("\nResult Matrix:");
                    printMatrix(matrix);
                    break;
                case "2-a":
                    rotateClockwiseBy1(matrix);
                    System.out.println("\nResult Matrix:");
                    printMatrix(matrix);
                    break;
                case "2-b":
                    rotateCounterClockwiseBy1(matrix);
                    System.out.println("\nResult Matrix:");
                    printMatrix(matrix);
                    break;
                case "2-c":
                    matrix = rotateBy90(matrix);
                    System.out.println("\nResult Matrix:");
                    printMatrix(matrix);
                    break;
                case "2-d":
                    matrix = rotateBy180(matrix);
                    System.out.println("\nResult Matrix:");
                    printMatrix(matrix);
                    break;
                case "3-a":
                    rowWiseTraversal(matrix);
                    break;
                case "3-b":
                    columnWiseTraversal(matrix);
                    break;
                case "4":
                    printSpiralForm(matrix);
                    break;
                case "5":
                    matrix = transposeMatrix(matrix);
                    System.out.println("\nResult Matrix:");
                    printMatrix(matrix);
                    break;
                default:
                    System.out.println("Invalid choice!");
                    break;
            }
        }
        
        scanner.close();
    }
    
    private static String normalizeChoice(String choice) {
        if (choice.matches("[1-5]-[a-d]")) {
            return choice;
        }
        if (choice.matches("[1-5][a-d]")) {
            return choice.charAt(0) + "-" + choice.charAt(1);
        }
        if (choice.matches("[a-d][1-5]")) {
            return choice.charAt(1) + "-" + choice.charAt(0);
        }
        if (choice.matches("[4-6]")) {
            return choice;
        }
        return choice;
    }
    
    private static int[][] inputMatrix() {
        System.out.print("Enter number of rows: ");
        int rows = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter number of columns: ");
        int cols = Integer.parseInt(scanner.nextLine());
        
        int[][] matrix = new int[rows][cols];
        System.out.println("Enter matrix elements (pisahkan dengan spasi untuk setiap baris):");
        
        for (int i = 0; i < rows; i++) {
            System.out.print("Baris ke-" + (i+1) + ": ");
            String[] numbers = scanner.nextLine().trim().split(" ");
            
            while (numbers.length != cols) {
                System.out.print("Input tidak valid! Masukkan " + cols + " angka: ");
                numbers = scanner.nextLine().trim().split(" ");
            }
            
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = Integer.parseInt(numbers[j]);
            }
        }
        return matrix;
    }
    
    private static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int val : row) {
                System.out.printf("%4d", val);
            }
            System.out.println();
        }
    }
    
    private static void sortRowWise(int[][] matrix) {
        for (int[] row : matrix) {
            Arrays.sort(row);
        }
        System.out.println("\nMatrix sorted row-wise.");
    }
    
    private static void sortColumnWise(int[][] matrix) {
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
        System.out.println("\nMatrix sorted column-wise.");
    }
    
    private static void rotateClockwiseBy1(int[][] matrix) {
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
        System.out.println("\nMatrix rotated clockwise by 1 position.");
    }
    
    private static void rotateCounterClockwiseBy1(int[][] matrix) {
        for (int k = 0; k < 3; k++) {
            rotateClockwiseBy1(matrix);
        }
        System.out.println("\nMatrix rotated counter-clockwise by 1 position.");
    }
    
    private static int[][] rotateBy90(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] result = new int[cols][rows];
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[j][rows - 1 - i] = matrix[i][j];
            }
        }
        System.out.println("\nMatrix rotated by 90 degrees.");
        return result;
    }
    
    private static int[][] rotateBy180(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] result = new int[rows][cols];
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[rows - 1 - i][cols - 1 - j] = matrix[i][j];
            }
        }
        System.out.println("\nMatrix rotated by 180 degrees.");
        return result;
    }
    
    private static void rowWiseTraversal(int[][] matrix) {
        System.out.println("\nRow-wise traversal:");
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
        }
        System.out.println();
    }
    
    private static void columnWiseTraversal(int[][] matrix) {
        System.out.println("\nColumn-wise traversal:");
        for (int j = 0; j < matrix[0].length; j++) {
            for (int i = 0; i < matrix.length; i++) {
                System.out.print(matrix[i][j] + " ");
            }
        }
        System.out.println();
    }
    
    private static void printSpiralForm(int[][] matrix) {
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
    
    private static int[][] transposeMatrix(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] result = new int[cols][rows];
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[j][i] = matrix[i][j];
            }
        }
        System.out.println("\nMatrix transposed.");
        return result;
    }
}
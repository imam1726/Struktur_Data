import java.util.*;

class Stack {
    private ArrayList<Double> items;
    
    public Stack() {
        items = new ArrayList<>();
    }
    
    public void push(double item) {
        items.add(item);
    }
    
    public double pop() {
        if (!isEmpty()) {
            return items.remove(items.size() - 1);
        }
        return Double.NaN;
    }
    
    public boolean isEmpty() {
        return items.isEmpty();
    }
    
    public void clear() {
        items.clear();
    }
    
    public String display() {
        if (items.isEmpty()) return "[]";
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < items.size(); i++) {
            sb.append(formatAngka(items.get(i)));
            if (i < items.size() - 1) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }
    
    private String formatAngka(double nilai) {
        if (nilai == (long) nilai) {
            return String.valueOf((long) nilai);
        } else {
            return String.valueOf(nilai);
        }
    }
}

public class StackArithmetic {
    
    private static int prioritas(String op) {
        switch (op) {
            case "+": case "-": return 1;
            case "*": case "/": return 2;
            case "^": return 3;
            default: return 0;
        }
    }
    
    private static boolean isOperator(String token) {
        return token.equals("+") || token.equals("-") || 
               token.equals("*") || token.equals("/") || token.equals("^");
    }
    
    private static boolean isOperand(String token) {
        try {
            Double.parseDouble(token);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    private static String formatAngka(double nilai) {
        if (nilai == (long) nilai) {
            return String.valueOf((long) nilai);
        } else {
            return String.valueOf(nilai);
        }
    }
    
    private static String buatGaris(String teks) {
        StringBuilder garis = new StringBuilder();
        for (int i = 0; i < teks.length(); i++) {
            garis.append("-");
        }
        return garis.toString();
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        String judul = "STACK ARITHMETIC";
        int lebar = judul.length();
        String garis = buatGaris(judul);
        
        // Judul di tengah
        System.out.println(judul);
        System.out.println(garis);
        System.out.println("Masukkan ekspresi (pisahkan spasi)");
        System.out.println("Contoh: ( 3 + 5 ) * 2 - 8 / 4");
        System.out.println("Angka: 2, 2.5, 2.50, 2.05, .5");
        System.out.println(garis);
        System.out.print("Ekspresi: ");
        
        String infix = scanner.nextLine();
        String[] tokens = infix.split(" ");
        
        // Validasi dasar
        boolean valid = true;
        for (int i = 0; i < tokens.length; i++) {
            if (!isOperand(tokens[i]) && !isOperator(tokens[i]) && 
                !tokens[i].equals("(") && !tokens[i].equals(")")) {
                System.out.println("\nError: Token '" + tokens[i] + "' tidak dikenal");
                valid = false;
                break;
            }
            
            if (i > 0 && isOperator(tokens[i]) && isOperator(tokens[i-1])) {
                System.out.println("\nError: Operator berurutan '" + tokens[i-1] + " " + tokens[i] + "'");
                valid = false;
                break;
            }
            
            if (i > 0 && isOperand(tokens[i]) && isOperand(tokens[i-1])) {
                System.out.println("\nError: Angka berurutan tanpa operator");
                valid = false;
                break;
            }
        }
        
        if (!valid) {
            System.out.println(garis);
            scanner.close();
            return;
        }
        
        // KONVERSI INFIX KE POSTFIX
        String header1 = "--- KONVERSI INFIX KE POSTFIX ---";
        System.out.println("\n" + header1);
        System.out.println(buatGaris(header1));
        
        java.util.Stack<String> opStack = new java.util.Stack<>();
        ArrayList<String> output = new ArrayList<>();
        
        for (String token : tokens) {
            if (isOperand(token)) {
                output.add(token);
                System.out.println("Token: " + token + " (angka) -> output");
            }
            else if (token.equals("(")) {
                opStack.push(token);
                System.out.println("Token: ( -> push ke stack");
            }
            else if (token.equals(")")) {
                while (!opStack.isEmpty() && !opStack.peek().equals("(")) {
                    output.add(opStack.pop());
                }
                opStack.pop(); // buang '('
                System.out.println("Token: ) -> pop stack sampai ketemu (");
            }
            else if (isOperator(token)) {
                while (!opStack.isEmpty() && isOperator(opStack.peek()) && 
                       prioritas(opStack.peek()) >= prioritas(token)) {
                    output.add(opStack.pop());
                }
                opStack.push(token);
                System.out.println("Token: " + token + " -> push ke stack");
            }
            
            System.out.println("  Output: " + output + ", Stack: " + opStack);
        }
        
        while (!opStack.isEmpty()) {
            output.add(opStack.pop());
        }
        
        String postfix = String.join(" ", output);
        System.out.println("\nHasil Postfix: " + postfix);
        
        // EVALUASI POSTFIX
        String header2 = "--- EVALUASI POSTFIX ---";
        System.out.println("\n" + header2);
        System.out.println(buatGaris(header2));
        
        Stack evalStack = new Stack();
        String[] postTokens = postfix.split(" ");
        
        for (String token : postTokens) {
            if (isOperand(token)) {
                double nilai = Double.parseDouble(token);
                evalStack.push(nilai);
                System.out.println("Token: " + token + " (angka) -> push " + formatAngka(nilai));
            }
            else if (isOperator(token)) {
                double b = evalStack.pop();
                double a = evalStack.pop();
                double hasil = 0;
                String simbol = "";
                
                switch (token) {
                    case "+": hasil = a + b; simbol = "+"; break;
                    case "-": hasil = a - b; simbol = "-"; break;
                    case "*": hasil = a * b; simbol = "×"; break;
                    case "/": hasil = a / b; simbol = "÷"; break;
                    case "^": hasil = Math.pow(a, b); simbol = "^"; break;
                }
                
                evalStack.push(hasil);
                System.out.println("Token: " + token + " (operator)");
                System.out.println("  Pop " + formatAngka(a) + " dan " + formatAngka(b));
                System.out.println("  " + formatAngka(a) + " " + simbol + " " + formatAngka(b) + 
                                 " = " + formatAngka(hasil));
            }
            
            System.out.println("  Stack: " + evalStack.display());
        }
        
        double hasilAkhir = evalStack.pop();
        System.out.println("\nHasil Akhir: " + formatAngka(hasilAkhir));
        System.out.println(garis);
        
        scanner.close();
    }
}
class Stack:
    def __init__(self):
        self.items = []
    
    def push(self, item):
        self.items.append(item)
    
    def pop(self):
        if not self.is_empty():
            return self.items.pop()
        return None
    
    def is_empty(self):
        return len(self.items) == 0
    
    def clear(self):
        self.items = []
    
    def display(self):
        return [self._format_angka(x) for x in self.items]
    
    def _format_angka(self, nilai):
        if nilai.is_integer():
            return int(nilai)
        return nilai

def prioritas(op):
    if op in ('+', '-'):
        return 1
    if op in ('*', '/'):
        return 2
    if op == '^':
        return 3
    return 0

def is_operator(token):
    return token in ('+', '-', '*', '/', '^')

def is_operand(token):
    try:
        float(token)
        return True
    except ValueError:
        return False

def format_angka(nilai):
    if nilai.is_integer():
        return str(int(nilai))
    else:
        # Hilangkan trailing zeros
        s = str(nilai)
        if '.' in s:
            s = s.rstrip('0').rstrip('.')
        return s

def buat_garis(teks):
    return "-" * len(teks)

def main():
    judul = "STACK ARITHMETIC"
    garis = buat_garis(judul)
    
    # Judul di tengah
    print(judul)
    print(garis)
    print("Masukkan ekspresi (pisahkan spasi)")
    print("Contoh: ( 3 + 5 ) * 2 - 8 / 4")
    print("Angka: 2, 2.5, 2.50, 2.05, .5")
    print(garis)
    
    infix = input("Ekspresi: ")
    tokens = infix.split()
    
    # Validasi dasar
    valid = True
    for i, token in enumerate(tokens):
        if not (is_operand(token) or is_operator(token) or token in ('(', ')')):
            print(f"\nError: Token '{token}' tidak dikenal")
            valid = False
            break
        
        if i > 0 and is_operator(token) and is_operator(tokens[i-1]):
            print(f"\nError: Operator berurutan '{tokens[i-1]} {token}'")
            valid = False
            break
        
        if i > 0 and is_operand(token) and is_operand(tokens[i-1]):
            print("\nError: Angka berurutan tanpa operator")
            valid = False
            break
    
    if not valid:
        print(garis)
        return
    
    # KONVERSI INFIX KE POSTFIX
    header1 = "--- KONVERSI INFIX KE POSTFIX ---"
    print(f"\n{header1}")
    print(buat_garis(header1))
    
    op_stack = []
    output = []
    
    for token in tokens:
        if is_operand(token):
            output.append(token)
            print(f"Token: {token} (angka) -> output")
        elif token == '(':
            op_stack.append(token)
            print("Token: ( -> push ke stack")
        elif token == ')':
            while op_stack and op_stack[-1] != '(':
                output.append(op_stack.pop())
            op_stack.pop()  # buang '('
            print("Token: ) -> pop stack sampai ketemu (")
        elif is_operator(token):
            while (op_stack and is_operator(op_stack[-1]) and 
                   prioritas(op_stack[-1]) >= prioritas(token)):
                output.append(op_stack.pop())
            op_stack.append(token)
            print(f"Token: {token} -> push ke stack")
        
        print(f"  Output: {output}, Stack: {op_stack}")
    
    while op_stack:
        output.append(op_stack.pop())
    
    postfix = ' '.join(output)
    print(f"\nHasil Postfix: {postfix}")
    
    # EVALUASI POSTFIX
    header2 = "--- EVALUASI POSTFIX ---"
    print(f"\n{header2}")
    print(buat_garis(header2))
    
    eval_stack = Stack()
    post_tokens = postfix.split()
    
    for token in post_tokens:
        if is_operand(token):
            nilai = float(token)
            eval_stack.push(nilai)
            print(f"Token: {token} (angka) -> push {format_angka(nilai)}")
        elif is_operator(token):
            b = eval_stack.pop()
            a = eval_stack.pop()
            
            if token == '+':
                hasil = a + b
                simbol = '+'
            elif token == '-':
                hasil = a - b
                simbol = '-'
            elif token == '*':
                hasil = a * b
                simbol = '×'
            elif token == '/':
                hasil = a / b
                simbol = '÷'
            elif token == '^':
                hasil = a ** b
                simbol = '^'
            
            eval_stack.push(hasil)
            print(f"Token: {token} (operator)")
            print(f"  Pop {format_angka(a)} dan {format_angka(b)}")
            print(f"  {format_angka(a)} {simbol} {format_angka(b)} = {format_angka(hasil)}")
        
        print(f"  Stack: {eval_stack.display()}")
    
    hasil_akhir = eval_stack.pop()
    print(f"\nHasil Akhir: {format_angka(hasil_akhir)}")
    print(garis)

if __name__ == "__main__":
    main()
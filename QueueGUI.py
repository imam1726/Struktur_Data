import tkinter as tk
from tkinter import ttk, messagebox
from collections import deque
import pyttsx3
import threading
import platform

class QueueGUI:
    def __init__(self, root):
        self.root = root
        self.queue = deque()
        self.counter = 0
        
        # Warna tema Beige (sama persis dengan Java)
        self.beige_bg = "#FAF5EB"      # Color(250, 245, 235)
        self.beige_panel = "#F5F0E4"    # Color(245, 240, 228)
        self.beige_border = "#D2C3AA"   # Color(210, 195, 170)
        self.beige_text = "#504132"     # Color(80, 65, 50)
        self.beige_button = "#C8B496"   # Color(200, 180, 150)
        self.beige_accent = "#AA8C64"   # Color(170, 140, 100)
        
        # Warna capsule/pill (sama persis dengan Java)
        self.capsule_white = "#FFFFFF"  # Color(255, 255, 255)
        self.capsule_border = "#DCDCDC" # Color(220, 220, 220)
        self.capsule_text = "#646464"   # Color(100, 100, 100)
        
        # Warna tombol (sama persis dengan Java)
        self.green_btn = "#4CAF50"      # Color(76, 175, 80)
        self.orange_btn = "#FF9800"     # Color(255, 152, 0)
        self.blue_btn = "#2196F3"       # Color(33, 150, 243)
        
        # Inisialisasi Text-to-Speech
        try:
            self.tts = pyttsx3.init()
            self.tts.setProperty('rate', 150)
            self.tts.setProperty('volume', 0.9)
            self.tts_available = True
        except Exception as e:
            print(f"TTS Error: {e}")
            self.tts_available = False
        
        self.setup_gui()
    
    def setup_gui(self):
        self.root.title("Sistem Antrian Bank")
        self.root.geometry("850x650")
        self.root.configure(bg=self.beige_bg)
        self.root.resizable(True, True)
        
        # Main panel (sama dengan BorderLayout)
        main_panel = tk.Frame(self.root, bg=self.beige_bg)
        main_panel.pack(fill=tk.BOTH, expand=True, padx=15, pady=15)
        
        # ==================== TOP PANEL ====================
        top_panel = tk.LabelFrame(
            main_panel,
            text="Ambil Antrian",
            font=('Segoe UI', 11, 'bold'),
            bg=self.beige_panel,
            fg=self.beige_accent,
            bd=2,
            relief=tk.GROOVE
        )
        top_panel.pack(fill=tk.X, pady=(0, 15))
        
        input_panel = tk.Frame(top_panel, bg=self.beige_panel)
        input_panel.pack(pady=15, padx=15)
        
        # Label Nama
        name_label = tk.Label(
            input_panel,
            text="Nama:",
            font=('Segoe UI', 12),
            bg=self.beige_panel,
            fg=self.beige_text
        )
        name_label.pack(side=tk.LEFT, padx=(0, 10))
        
        # TextField untuk input nama
        self.name_entry = tk.Entry(
            input_panel,
            font=('Segoe UI', 12),
            bg=self.beige_bg,
            fg=self.beige_text,
            relief=tk.SOLID,
            bd=1,
            width=25
        )
        self.name_entry.pack(side=tk.LEFT, padx=(0, 15))
        
        # Tombol capsule style
        take_button = self.create_capsule_button(
            input_panel, "Ambil Antrian", self.green_btn, "#FFFFFF"
        )
        take_button.pack(side=tk.LEFT, padx=5)
        
        call_button = self.create_capsule_button(
            input_panel, "Panggil Antrian", self.orange_btn, "#FFFFFF"
        )
        call_button.pack(side=tk.LEFT, padx=5)
        
        show_button = self.create_capsule_button(
            input_panel, "Tampilkan Antrian", self.blue_btn, "#FFFFFF"
        )
        show_button.pack(side=tk.LEFT, padx=5)
        
        # ==================== CALL PANEL ====================
        call_panel = tk.LabelFrame(
            main_panel,
            text="Antrian Dipanggil",
            font=('Segoe UI', 11, 'bold'),
            bg=self.beige_panel,
            fg=self.beige_accent,
            bd=2,
            relief=tk.GROOVE
        )
        call_panel.pack(fill=tk.X, pady=(0, 15))
        
        # Capsule label untuk antrian yang dipanggil
        self.called_label = self.create_capsule_label(call_panel, "-")
        self.called_label.pack(pady=15, padx=15)
        
        # ==================== LIST PANEL ====================
        list_panel = tk.LabelFrame(
            main_panel,
            text="Daftar Antrian",
            font=('Segoe UI', 11, 'bold'),
            bg=self.beige_panel,
            fg=self.beige_accent,
            bd=2,
            relief=tk.GROOVE
        )
        list_panel.pack(fill=tk.BOTH, expand=True)
        
        # Listbox untuk menampilkan antrian (mirip JList di Java)
        list_frame = tk.Frame(list_panel, bg=self.beige_panel)
        list_frame.pack(fill=tk.BOTH, expand=True, padx=10, pady=10)
        
        self.list_model = tk.Listbox(
            list_frame,
            font=('Segoe UI', 12),
            bg=self.beige_bg,
            fg=self.beige_text,
            selectbackground=self.beige_button,
            selectforeground="#FFFFFF",
            height=12,
            relief=tk.SOLID,
            bd=1
        )
        
        scrollbar = tk.Scrollbar(list_frame, orient=tk.VERTICAL, command=self.list_model.yview)
        self.list_model.configure(yscrollcommand=scrollbar.set)
        
        self.list_model.pack(side=tk.LEFT, fill=tk.BOTH, expand=True)
        scrollbar.pack(side=tk.RIGHT, fill=tk.Y)
        
        # ==================== STATUS BAR ====================
        self.status_label = tk.Label(
            self.root,
            text="Jumlah antrian: 0",
            font=('Segoe UI', 11),
            bg=self.beige_bg,
            fg=self.beige_text,
            relief=tk.SUNKEN,
            anchor=tk.CENTER,
            height=2
        )
        self.status_label.pack(side=tk.BOTTOM, fill=tk.X)
        
        # Event handlers
        take_button.config(command=self.take_queue)
        call_button.config(command=self.call_queue)
        show_button.config(command=self.show_queue)
        self.name_entry.bind('<Return>', lambda e: self.take_queue())
        
        # Tampilkan antrian awal
        self.show_queue()
    
    def create_capsule_button(self, parent, text, bg_color, fg_color):
        """Membuat tombol dengan efek capsule/pill"""
        button = tk.Button(
            parent,
            text=text,
            font=('Segoe UI', 11, 'bold'),
            bg=bg_color,
            fg=fg_color,
            relief=tk.RAISED,
            bd=1,
            padx=20,
            pady=8,
            cursor='hand2'
        )
        
        # Efek hover (mirip mouseEntered/mouseExited di Java)
        def on_enter(e):
            button.config(bg=self.darker_color(bg_color))
        
        def on_leave(e):
            button.config(bg=bg_color)
        
        button.bind("<Enter>", on_enter)
        button.bind("<Leave>", on_leave)
        
        return button
    
    def create_capsule_label(self, parent, text):
        """Membuat label dengan efek capsule/pill putih"""
        label = tk.Label(
            parent,
            text=text,
            font=('Segoe UI', 13, 'bold'),
            bg=self.capsule_white,
            fg=self.capsule_text,
            relief=tk.SOLID,
            bd=1,
            padx=30,
            pady=10,
            width=30
        )
        return label
    
    def darker_color(self, color):
        """Membuat warna lebih gelap (mirip darker() di Java)"""
        color = color.lstrip('#')
        r = int(color[0:2], 16)
        g = int(color[2:4], 16)
        b = int(color[4:6], 16)
        
        r = max(0, r - 30)
        g = max(0, g - 30)
        b = max(0, b - 30)
        
        return f'#{r:02x}{g:02x}{b:02x}'
    
    def speak(self, text):
        """Fitur panggilan suara (mirip speak() di Java)"""
        def _speak():
            try:
                os_name = platform.system().lower()
                
                if os_name == "darwin":  # Mac
                    import subprocess
                    subprocess.run(["say", text])
                elif os_name == "windows":  # Windows
                    if self.tts_available:
                        self.tts.say(text)
                        self.tts.runAndWait()
                elif os_name == "linux":  # Linux
                    import subprocess
                    subprocess.run(["espeak", text])
                print(f"[SUARA] {text}")
            except Exception as e:
                print(f"[SUARA GAGAL] {text} - {e}")
        
        threading.Thread(target=_speak, daemon=True).start()
    
    def take_queue(self):
        """Fitur ambil antrian (mirip takeQueue() di Java)"""
        name = self.name_entry.get().strip()
        
        if not name:
            messagebox.showwarning("Peringatan", "Masukkan nama terlebih dahulu!")
            return
        
        # Generate nomor antrian otomatis
        self.counter += 1
        customer = {'number': self.counter, 'name': name}
        self.queue.append(customer)
        
        # Tampilkan data dalam antrian
        self.show_queue()
        
        # Clear input
        self.name_entry.delete(0, tk.END)
        
        messagebox.showinfo(
            "Sukses",
            f"Antrian berhasil diambil!\nNomor: {customer['number']}\nNama: {customer['name']}"
        )
    
    def call_queue(self):
        """Fitur panggil antrian (mirip callQueue() di Java)"""
        if not self.queue:
            messagebox.showwarning("Peringatan", "Tidak ada antrian!")
            return
        
        # Ambil antrian pertama (mirip poll() di Java)
        customer = self.queue.popleft()
        
        # Menampilkan nomor dan nama yang dipanggil (dalam capsule)
        self.called_label.config(text=f"Nomor {customer['number']} - {customer['name']}")
        
        # Panggilan suara
        voice_text = f"Nomor antrian {customer['number']}, {customer['name']}, silakan menuju loket"
        self.speak(voice_text)
        
        # Tampilkan data antrian yang tersisa
        self.show_queue()
        
        messagebox.showinfo(
            "Panggil Antrian",
            f"Panggilan Antrian:\nNomor: {customer['number']}\nNama: {customer['name']}"
        )
    
    def show_queue(self):
        """Fitur tampilkan antrian (mirip showQueue() di Java)"""
        # Clear listbox
        self.list_model.delete(0, tk.END)
        
        # Tampilkan semua antrian
        if not self.queue:
            self.list_model.insert(tk.END, "Tidak ada antrian")
        else:
            for customer in self.queue:
                self.list_model.insert(tk.END, f"Nomor: {customer['number']} | Nama: {customer['name']}")
        
        # Update status bar
        self.status_label.config(text=f"Jumlah antrian: {len(self.queue)}")


if __name__ == "__main__":
    root = tk.Tk()
    app = QueueGUI(root)
    root.mainloop()
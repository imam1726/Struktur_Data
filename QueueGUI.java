import java.awt.*;
import java.util.LinkedList;
import java.util.Queue;
import javax.swing.*;

public class QueueGUI extends JFrame {
    
    // Queue menggunakan LinkedList
    private Queue<Customer> queue;
    private int counter;
    
    // Komponen GUI
    private JTextField nameEntry;
    private JList<String> queueList;
    private DefaultListModel<String> listModel;
    private JLabel calledLabel;
    private JLabel statusLabel;
    
    // Warna tema Beige
    private Color beigeBg = new Color(250, 245, 235);
    private Color beigePanel = new Color(245, 240, 228);
    private Color beigeBorder = new Color(210, 195, 170);
    private Color beigeText = new Color(80, 65, 50);
    private Color beigeButton = new Color(200, 180, 150);
    private Color beigeAccent = new Color(170, 140, 100);
    
    // Warna untuk capsule/pill
    private Color capsuleWhite = new Color(255, 255, 255);
    private Color capsuleBorder = new Color(220, 220, 220);
    private Color capsuleText = new Color(100, 100, 100);
    
    // Class Customer
    private static class Customer {
        int number;
        String name;
        
        Customer(int number, String name) {
            this.number = number;
            this.name = name;
        }
    }
    
    public QueueGUI() {
        // Inisialisasi Queue
        queue = new LinkedList<>();
        counter = 0;
        
        // Setup Frame
        setTitle("Sistem Antrian Bank");
        setSize(850, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(beigeBg);
        
        createWidgets();
        setLocationRelativeTo(null);
    }
    
    // Fitur panggilan suara
    private void speak(String text) {
        new Thread(() -> {
            try {
                String os = System.getProperty("os.name").toLowerCase();
                
                if (os.contains("win")) {
                    String command = "powershell -Command \"Add-Type -AssemblyName System.Speech; " +
                                   "$synth = New-Object System.Speech.Synthesis.SpeechSynthesizer; " +
                                   "$synth.Speak('" + text + "');\"";
                    Runtime.getRuntime().exec(command);
                } else if (os.contains("mac")) {
                    Runtime.getRuntime().exec(new String[]{"say", text});
                } else if (os.contains("linux")) {
                    Runtime.getRuntime().exec(new String[]{"espeak", text});
                }
                System.out.println("[SUARA] " + text);
            } catch (Exception e) {
                System.out.println("[SUARA GAGAL] " + text);
            }
        }).start();
    }
    
    // Method untuk membuat capsule/pill button
    private JButton createCapsuleButton(String text, Color bgColor, Color textColor) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                // Warna background
                if (getModel().isPressed()) {
                    g2.setColor(bgColor.darker());
                } else if (getModel().isRollover()) {
                    g2.setColor(bgColor.brighter());
                } else {
                    g2.setColor(bgColor);
                }
                
                // Gambar capsule (rounded rectangle)
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
                
                // Border capsule
                g2.setColor(capsuleBorder);
                g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 30, 30);
                
                // Teks
                g2.setColor(textColor);
                FontMetrics fm = g2.getFontMetrics();
                int x = (getWidth() - fm.stringWidth(getText())) / 2;
                int y = (getHeight() + fm.getAscent()) / 2 - 2;
                g2.drawString(getText(), x, y);
                
                g2.dispose();
            }
        };
        
        button.setFont(new Font("Segoe UI", Font.BOLD, 13));
        button.setForeground(textColor);
        button.setBackground(bgColor);
        button.setBorder(BorderFactory.createEmptyBorder(10, 25, 10, 25));
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setOpaque(false);
        
        return button;
    }
    
    // Method untuk membuat capsule label (untuk status)
    private JLabel createCapsuleLabel(String text) {
        JLabel label = new JLabel(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                // Background putih
                g2.setColor(capsuleWhite);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
                
                // Border abu-abu
                g2.setColor(capsuleBorder);
                g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 30, 30);
                
                // Teks
                g2.setColor(capsuleText);
                FontMetrics fm = g2.getFontMetrics();
                int x = (getWidth() - fm.stringWidth(getText())) / 2;
                int y = (getHeight() + fm.getAscent()) / 2 - 2;
                g2.drawString(getText(), x, y);
                
                g2.dispose();
            }
        };
        
        label.setFont(new Font("Segoe UI", Font.BOLD, 14));
        label.setForeground(capsuleText);
        label.setBorder(BorderFactory.createEmptyBorder(8, 20, 8, 20));
        label.setOpaque(false);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        
        return label;
    }
    
    private void createWidgets() {
        // Main panel
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        mainPanel.setBackground(beigeBg);
        
        // ==================== TOP PANEL ====================
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(beigeBorder),
            "Ambil Antrian"
        ));
        topPanel.setBackground(beigePanel);
        
        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 15));
        inputPanel.setBackground(beigePanel);
        
        JLabel nameLabel = new JLabel("Nama:");
        nameLabel.setForeground(beigeText);
        nameLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        
        nameEntry = new JTextField(25);
        nameEntry.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        nameEntry.setBackground(beigeBg);
        nameEntry.setForeground(beigeText);
        nameEntry.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(beigeBorder),
            BorderFactory.createEmptyBorder(8, 10, 8, 10)
        ));
        
        // Tombol dengan capsule style
        JButton takeButton = createCapsuleButton("Ambil Antrian", new Color(76, 175, 80), Color.WHITE);
        JButton callButton = createCapsuleButton("Panggil Antrian", new Color(255, 152, 0), Color.WHITE);
        JButton showButton = createCapsuleButton("Tampilkan Antrian", new Color(33, 150, 243), Color.WHITE);
        
        inputPanel.add(nameLabel);
        inputPanel.add(nameEntry);
        inputPanel.add(takeButton);
        inputPanel.add(callButton);
        inputPanel.add(showButton);
        
        topPanel.add(inputPanel, BorderLayout.CENTER);
        
        // ==================== CALL PANEL ====================
        JPanel callPanel = new JPanel();
        callPanel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(beigeBorder),
            "Antrian Dipanggil"
        ));
        callPanel.setBackground(beigePanel);
        callPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        
        // Capsule label untuk antrian yang dipanggil
        calledLabel = createCapsuleLabel("-");
        callPanel.add(calledLabel);
        
        // ==================== LIST PANEL ====================
        JPanel listPanel = new JPanel(new BorderLayout());
        listPanel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(beigeBorder),
            "Daftar Antrian"
        ));
        listPanel.setBackground(beigePanel);
        
        listModel = new DefaultListModel<>();
        queueList = new JList<>(listModel);
        queueList.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        queueList.setBackground(beigeBg);
        queueList.setForeground(beigeText);
        queueList.setSelectionBackground(new Color(200, 180, 150));
        queueList.setSelectionForeground(Color.WHITE);
        queueList.setFixedCellHeight(35);
        
        JScrollPane scrollPane = new JScrollPane(queueList);
        scrollPane.getViewport().setBackground(beigeBg);
        scrollPane.setBorder(BorderFactory.createLineBorder(beigeBorder));
        listPanel.add(scrollPane, BorderLayout.CENTER);
        
        // ==================== STATUS BAR ====================
        statusLabel = new JLabel("Jumlah antrian: 0");
        statusLabel.setForeground(beigeText);
        statusLabel.setBackground(beigeBg);
        statusLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        statusLabel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(beigeBorder),
            BorderFactory.createEmptyBorder(8, 10, 8, 10)
        ));
        statusLabel.setOpaque(true);
        
        // Add all to main panel
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(callPanel, BorderLayout.CENTER);
        mainPanel.add(listPanel, BorderLayout.SOUTH);
        
        add(mainPanel, BorderLayout.CENTER);
        add(statusLabel, BorderLayout.SOUTH);
        
        // Event handlers
        takeButton.addActionListener(e -> takeQueue());
        callButton.addActionListener(e -> callQueue());
        showButton.addActionListener(e -> showQueue());
        nameEntry.addActionListener(e -> takeQueue());
    }
    
    // Fitur ambil antrian
    private void takeQueue() {
        String name = nameEntry.getText().trim();
        
        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Masukkan nama terlebih dahulu!", 
                "Peringatan", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        // Generate nomor antrian otomatis
        counter++;
        Customer customer = new Customer(counter, name);
        queue.offer(customer);
        
        // Tampilkan data dalam antrian
        showQueue();
        
        // Clear input
        nameEntry.setText("");
        
        JOptionPane.showMessageDialog(this,
            "Antrian berhasil diambil!\nNomor: " + customer.number + "\nNama: " + customer.name,
            "Sukses",
            JOptionPane.INFORMATION_MESSAGE);
    }
    
    // Fitur panggil antrian
    private void callQueue() {
        if (queue.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Tidak ada antrian!", 
                "Peringatan", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        // Ambil antrian pertama
        Customer customer = queue.poll();
        
        // Menampilkan nomor dan nama yang dipanggil (dalam capsule)
        calledLabel.setText("Nomor " + customer.number + " - " + customer.name);
        
        // Panggilan suara
        String voiceText = "Nomor antrian " + customer.number + ", " + customer.name + ", silakan menuju loket";
        speak(voiceText);
        
        // Tampilkan data antrian yang tersisa
        showQueue();
        
        JOptionPane.showMessageDialog(this,
            "Panggilan Antrian:\nNomor: " + customer.number + "\nNama: " + customer.name,
            "Panggil Antrian",
            JOptionPane.INFORMATION_MESSAGE);
    }
    
    // Fitur tampilkan antrian
    private void showQueue() {
        // Clear listbox
        listModel.clear();
        
        // Tampilkan semua antrian
        if (queue.isEmpty()) {
            listModel.addElement("Tidak ada antrian");
        } else {
            for (Customer customer : queue) {
                listModel.addElement("Nomor: " + customer.number + " | Nama: " + customer.name);
            }
        }
        
        // Update status bar
        statusLabel.setText("Jumlah antrian: " + queue.size());
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new QueueGUI().setVisible(true);
        });
    }
}
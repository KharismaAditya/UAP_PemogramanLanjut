package org.example.UI;


import javax.swing.*;
import java.awt.*;

/**
 * Kelas antarmuka login sederhana untuk aplikasi manajemen stok barang.
 * Menyediakan form berisi input username dan password yang harus diisi
 * karyawan sebelum dapat mengakses jendela utama aplikasi.
 *
 * Kredensial valid disimpan secara hardcode dalam variabel Cuser dan Cpw,
 * yaitu username "admin" dan password "UMM1964", sehingga login hanya
 * berhasil jika input pengguna cocok dengan nilai tersebut.
 *
 * Setelah login berhasil, kelas ini akan membuat instance mainApp dan
 * memanggil method main() miliknya untuk menampilkan jendela utama,
 * kemudian menutup (dispose) jendela login.
 *
 * Layout jendela login menggunakan kombinasi Panel dengan FlowLayout
 * dan GridLayout untuk menata judul, field username, field password,
 * serta tombol login agar tampilan rapi dan mudah digunakan.
 */
public class loginKaryawanAPP {
    private static String Cuser = "admin";
    private static String Cpw = "UMM1964";

    static JLabel title = new JLabel("MANAJEMEN BARANG GUDANG", JLabel.CENTER);
    static JTextField username = new JTextField();
    static JPasswordField password = new JPasswordField();
    static JButton loginButton = new JButton("LOGIN");

    public static void main(String[] args) {
        JFrame frame = new JFrame("APLIKASI MANAJEMEN STOK BARANG");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setSize(400,200);
        frame.setResizable(false);

        Panel panelTitle = new Panel(new FlowLayout(FlowLayout.CENTER,40,20));
        panelTitle.add(title);
        title.setFont(new Font("ARIAL",Font.BOLD,20));
 
        JPanel panelForm = new JPanel(new GridLayout(2,2));
        panelForm.setBorder(BorderFactory.createEmptyBorder(10,40,10,40));
        panelForm.add(new JLabel("USERNAME : "));
        panelForm.add(username);
        panelForm.add(new JLabel("PASSWORD : "));
        panelForm.add(password);

        Panel panelButton = new Panel(new FlowLayout());
        panelButton.setBackground(Color.decode("#ABE0F0"));
        panelButton.add(loginButton);
        panelButton.add(panelForm);
        frame.add(panelTitle,BorderLayout.NORTH);
        frame.add(panelForm,BorderLayout.CENTER);
        frame.add(panelButton,BorderLayout.SOUTH);

        loginButton.addActionListener(e ->{
            String userInput = username.getText();
            String passwordInput = String.valueOf(password.getPassword());
            if(userInput.equals(Cuser) && passwordInput.equals(Cpw)){
                mainApp app = new  mainApp();
                app.main();
                frame.dispose();
            }else{
                JOptionPane.showMessageDialog(frame,"USER OR PASSWORD INCORRECT");
            }
        });

        frame.setVisible(true);
    }
}

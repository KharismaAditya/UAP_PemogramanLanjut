package org.example.UI;

import org.example.util.CSVutil;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/**
 * Kelas antarmuka untuk memperbarui data satu barang yang dipilih di tabel.
 * Menampilkan form berisi informasi nama barang, stok lama, dan harga lama,
 * serta field input untuk mengubah stok dan harga.
 *
 * Kelas ini menerima model tabel dan indeks baris yang akan diubah melalui
 * method pane, kemudian menampilkan jendela terpisah dengan form update.
 *
 * Saat tombol UPDATE diklik, nilai stok dan harga baru diambil dari input,
 * lalu total harga dihitung ulang sebagai hasil perkalian stok dan harga,
 * kemudian ketiga nilai tersebut disimpan kembali ke baris terkait di model.
 *
 * Setelah data pada model diperbarui, kelas ini memanggil CSVutil.saveCSV
 * untuk menyimpan perubahan ke file DataBarang.csv dan mainApp.updateTotalLabel
 * untuk memperbarui label total nilai gudang di jendela utama.
 *
 * Jika pengguna memasukkan nilai yang bukan angka untuk stok atau harga,
 * akan ditampilkan pesan kesalahan menggunakan JOptionPane agar user
 * diberi tahu bahwa input harus berupa angka.
 */
public class updateClassAPP {
    private static String FILE_PATH = System.getProperty("user.dir") + "/src/main/java/DataBarang.csv";
    private static CSVutil CSVUtil;

    JLabel title = new JLabel("FORM INPUT MAHASISWA", JLabel.CENTER);
    JTextField StokInput = new JTextField();
    JTextField HargaInput = new JTextField();
    JButton updButton = new JButton("UPDATE");

    public void pane(DefaultTableModel model, int row)
    {
        JFrame frame = new JFrame();
        frame.setSize(400,300);
        frame.setResizable(false);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);

        title.setFont(new Font("Arial", Font.BOLD, 16));
        JPanel titlePanel = new JPanel();titlePanel.setBackground(Color.decode("#B8DB80"));
        titlePanel.add(title);
        frame.add(titlePanel, BorderLayout.NORTH);

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));


        formPanel.add(new JLabel("NAMA: " + model.getValueAt(row,1).toString()));
        formPanel.add(new JLabel("STOK: (" + model.getValueAt(row,2).toString() + ")"));
        formPanel.add(StokInput);
        formPanel.add(new JLabel("HARGA:(Rp " + model.getValueAt(row,3).toString() + ")"));
        formPanel.add(HargaInput);
        frame.add(formPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.decode("#B8DB80"));
        buttonPanel.add(updButton);
        frame.add(buttonPanel, BorderLayout.SOUTH);;
        frame.setVisible(true);

        updButton.addActionListener(e -> {
            try {
                double TOTAL = Integer.parseInt(StokInput.getText()) * Integer.parseInt(HargaInput.getText());

                model.setValueAt(StokInput.getText(), row, 2);
                model.setValueAt(HargaInput.getText(), row, 3);
                model.setValueAt(TOTAL, row, 4);

                CSVUtil.saveCSV(FILE_PATH,model);
                mainApp.updateTotalLabel();
                JOptionPane.showMessageDialog(frame, "Data berhasil diupdate!");
                frame.dispose();

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Nilai dan Kehadiran harus angka!");
            }
        });
    }
}

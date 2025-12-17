package org.example.UI;

import org.example.Table.MyTableModel;
import org.example.util.CSVutil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
/**
 * Kelas antarmuka untuk menghapus data barang yang sudah ada di tabel dan file CSV.
 * Mengkonfirmasi pengguna apakah pengguna yakin untuk menghapus data terpilih pada tabel.
 *
 * Kelas ini menerima model tabel dari mainApp melalui method pane, kemudian
 * menghapus baris model jika data valid dan terkonfirmasi oleh pengguna.
 *
 * Setelah data berhasil dihapus, kelas ini memanggil CSVutil.saveCSV
 * untuk menyimpan perubahan ke file DataBarang.csv dan mainApp.updateTotalLabel
 * untuk memperbarui total nilai inventaris di jendela utama.
 */


public class deleteClassAPP {
    private static String FILE_PATH = System.getProperty("user.dir") + "/src/main/java/DataBarang.csv";
    private static CSVutil CSVUtil;
    JLabel title = new JLabel("APAKAH ANDA YAKIN INGIN MENGHAPUS DATA:");
    JButton delButton = new  JButton("DELETE");

    public void pane(DefaultTableModel model, int row){
        JFrame frame = new JFrame("KONFIRMASI HAPUS");
        frame.setLocationRelativeTo(null);
        frame.setSize(400,200);
        frame.setResizable(false);

        JPanel titlePanel = new  JPanel();
        titlePanel.setBackground(Color.decode("#FA6868"));
        titlePanel.add(title);
        title.setFont(new Font("Arial", Font.BOLD, 16));
        frame.add(titlePanel, BorderLayout.NORTH);

        JPanel panelInfo = new JPanel();
        panelInfo.setLayout(new BoxLayout(panelInfo, BoxLayout.Y_AXIS));
        panelInfo.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        panelInfo.add(new JLabel("ID: " + model.getValueAt(row,0).toString()));
        panelInfo.add(Box.createVerticalStrut(8));
        panelInfo.add(new JLabel("NAMA : " + model.getValueAt(row,1).toString()));
        panelInfo.add(Box.createVerticalStrut(8));
        panelInfo.add(new JLabel("STOK : " + model.getValueAt(row, 2).toString()));
        panelInfo.add(Box.createVerticalStrut(8));
        panelInfo.add(new JLabel("HARGA : " + model.getValueAt(row, 3).toString()));
        panelInfo.add(Box.createVerticalStrut(8));
        frame.add(panelInfo, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.decode("#FA6868"));
        buttonPanel.add(delButton);
        frame.add(buttonPanel, BorderLayout.SOUTH);
        frame.setVisible(true);

        delButton.addActionListener(e -> {
            model.removeRow(row);
            CSVutil.saveCSV(FILE_PATH,(MyTableModel) model);
            mainApp.updateTotalLabel();
            JOptionPane.showMessageDialog(frame,"Data berhasil dihapus!");
            frame.dispose();
        });
    }
}

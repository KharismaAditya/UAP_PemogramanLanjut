package org.example.UI;

import org.example.Table.MyTableModel;
import org.example.util.CSVutil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

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

        title.setFont(new Font("Arial", Font.BOLD, 16));
        JPanel titlePanel = new JPanel();titlePanel.setBackground(Color.decode("#ABE0F0"));
        titlePanel.add(title);
        frame.add(titlePanel, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridLayout(7,2));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));


        formPanel.add(new JLabel("NAMA: " + model.getValueAt(row,1).toString()));
        formPanel.add(new JLabel("STOK: (" + model.getValueAt(row,2).toString() + ")"));
        formPanel.add(StokInput);
        formPanel.add(new JLabel("HARGA:(Rp " + model.getValueAt(row,3).toString() + ")"));
        formPanel.add(HargaInput);
        frame.add(formPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.decode("#ABE0F0"));
        buttonPanel.add(updButton);
        frame.add(buttonPanel, BorderLayout.SOUTH);;
        frame.setVisible(true);

        updButton.addActionListener(e -> {
            try {
                String STOK = StokInput.getText();
                String HARGA = HargaInput.getText();
                double TOTAL = Double.parseDouble(STOK) * Double.parseDouble(HARGA);

                model.setValueAt(Double.parseDouble(STOK), row, 2);
                model.setValueAt(Double.parseDouble(HARGA), row, 3);
                model.setValueAt(TOTAL, row, 4);

                CSVUtil.saveCSV(FILE_PATH, (MyTableModel) model);
                mainApp.updateTotalLabel();
                JOptionPane.showMessageDialog(frame, "Data berhasil diupdate!");
                frame.dispose();

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Nilai dan Kehadiran harus angka!");
            }
        });
    }
}

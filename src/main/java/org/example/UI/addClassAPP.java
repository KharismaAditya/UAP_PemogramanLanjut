package org.example.UI;

import org.example.Table.MyTableModel;
import org.example.util.CSVutil;

import javax.print.attribute.standard.MediaSize;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;


public class addClassAPP {
    private static String FILE_PATH = System.getProperty("user.dir") + "/src/main/java/DataBarang.csv";
    private static CSVutil CSVUtil;


    JTextField idInput = new JTextField();
    JTextField nameInput = new JTextField();
    JTextField stokInput = new JTextField();
    JTextField priceInput = new JTextField();
    JButton addButton = new JButton("ADD");


    public void pane(DefaultTableModel model) {
        JFrame frame = new JFrame();
        frame.setSize(400,300);
        frame.setResizable(false);
        frame.setLayout(new BorderLayout());

        JLabel title = new JLabel("INPUT DATA BARANG", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 16));
        JPanel titlePanel = new JPanel();titlePanel.setBackground(Color.decode("#ABE0F0"));
        titlePanel.add(title);
        frame.add(titlePanel, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridLayout(7,2));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        formPanel.add(new JLabel("ID:"));
        formPanel.add(idInput);
        formPanel.add(new JLabel("NAMA:"));
        formPanel.add(nameInput);
        formPanel.add(new JLabel("STOK:"));
        formPanel.add(stokInput);
        formPanel.add(new JLabel("HARGA:"));
        formPanel.add(priceInput);
        frame.add(formPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.decode("#ABE0F0"));
        buttonPanel.add(addButton);
        frame.add(buttonPanel, BorderLayout.SOUTH);;
        frame.setVisible(true);

        addButton.addActionListener(e -> {
            if(isEmpty()){
                addData(model,frame);
            }else{
                JOptionPane.showMessageDialog(frame, "DATA HARUS LENGKAP");
            }

        });
    }

    public boolean isEmpty(){
        if(idInput.getText().isEmpty()||nameInput.getText().isEmpty()|| stokInput.getText().isEmpty()){
            return false;
        }
        return true;
    }

    public void addData(DefaultTableModel model, JFrame frame) {
        try{
            int ID = Integer.parseInt(idInput.getText());
            String NAMA = nameInput.getText();
            double STOK = Double.parseDouble(stokInput.getText());
            double HARGA = Double.parseDouble(priceInput.getText());
            double TOTAL = STOK * HARGA;
            if(isAlready(model,NAMA,idInput.getText())){
                model.addRow(new Object[]{
                        ID,NAMA,STOK,HARGA,TOTAL
                });

                CSVUtil.saveCSV(FILE_PATH, (MyTableModel) model);
                mainApp.updateTotalLabel();
                JOptionPane.showMessageDialog(frame, "Data berhasil ditambah!");
            }else{
                JOptionPane.showMessageDialog(frame, "Data yang anda masukkan sudah ada");
                idInput.setText("");nameInput.setText("");stokInput.setText("");priceInput.setText("");
            }
        }catch(NumberFormatException ex){
            JOptionPane.showMessageDialog(frame, "NILAI INPUT HARUS BERUPA ANGKA");
            idInput.setText("");nameInput.setText("");stokInput.setText("");priceInput.setText("");
        }
    }

    public boolean isAlready(DefaultTableModel model, String nama, String id) {
        String idInputLower = id.toLowerCase();
        String namaInputLower = nama.toLowerCase();

        for (int i = 0; i < model.getRowCount(); i++) {
            String namaTabel = model.getValueAt(i, 1).toString();
            String idTabel = model.getValueAt(i, 0).toString();
            if (idTabel.toLowerCase().equals(idInputLower) || namaTabel.toLowerCase().equals(namaInputLower)) {
                return false;
            }
        }
        return true;
    }


}

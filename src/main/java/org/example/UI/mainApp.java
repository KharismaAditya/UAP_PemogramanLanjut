package org.example.UI;

import org.example.Table.MyTableModel;
import org.example.util.CSVutil;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;

public class mainApp
{
    private static String FILE_PATH = System.getProperty("user.dir") + "/src/main/java/DataBarang.csv";
    private static CSVutil CSVUtil;

    private static JLabel totalInventaris = new JLabel();
    private static DefaultTableModel currentModel;

    public static void updateTotalLabel() {
        if (currentModel != null && totalInventaris != null) {
            double total = totalGudang(currentModel);
            totalInventaris.setText("TOTAL HARGA BARANG GUDANG : Rp." + total);
        }
    }

    public static void main( String[] args ){
        JFrame frame = new JFrame("DAFTAR STOK BARANG");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(800,500);
        frame.setLayout(new BorderLayout());
        frame.setResizable(false);

        String[] columns = {"ID","NAMA","STOK","HARGA","TOTAL(Rp)"};

        JLabel title = new JLabel("DAFTAR BARANG GUDANG");
        title.setFont(new Font("ARIAL",Font.BOLD,16));
        title.setHorizontalAlignment(JLabel.CENTER);
        JPanel titlePanel = new JPanel();titlePanel.setBackground(Color.decode("#E2852E"));
        titlePanel.add(title);
        frame.add(titlePanel,BorderLayout.NORTH);

        MyTableModel model = new MyTableModel(columns);
        JTable table = new JTable(model);
        currentModel = model;

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);

        loadCSV(FILE_PATH, model);
        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane, BorderLayout.CENTER);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.Y_AXIS));


        JPanel panel1 = new JPanel();
        updateTotalLabel();
        panel1.add(totalInventaris);
        title.setFont(new Font("ARIAL",Font.BOLD,11));
        mainPanel.add(panel1);

        JPanel panel2 = new JPanel();
        panel2.setBackground(Color.decode("#ABE0F0"));
        panel2.setLayout(new FlowLayout());
        JButton btn1 = new JButton("ADD");panel2.add(btn1);
        JButton btn2 = new JButton("UPDATE");panel2.add(btn2);
        JButton btn3 = new JButton("DELETE");panel2.add(btn3);
        mainPanel.add(panel2);
        frame.add(mainPanel,BorderLayout.SOUTH);
        frame.setVisible(true);

        btn1.addActionListener(e -> {
            addClassAPP addPane = new  addClassAPP();
            addPane.pane(model);
        });

        btn2.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row < 0) {
                JOptionPane.showMessageDialog(frame, "Pilih data yang ingin diupdate!");
                return;
            }
            updateClassAPP updatePane = new  updateClassAPP();
            updatePane.pane(model,row);
        });

        btn3.addActionListener(e -> {
            int row = table.getSelectedRow();

            if (row >= 0) {
                model.removeRow(row);
                CSVUtil.saveCSV(FILE_PATH, model);
                updateTotalLabel();
                JOptionPane.showMessageDialog(frame, "Data berhasil dihapus!");
            } else {
                JOptionPane.showMessageDialog(null, "PILIH DATA YANG INGIN DIHAPUS");
            }
        });
    }

    public static double totalGudang(DefaultTableModel model){
        Double totalGudang = 0.0;
        for(int i = 0; i < model.getRowCount(); i++){
            totalGudang += Double.parseDouble(model.getValueAt(i,4).toString());
        }

        return totalGudang;
    }

    public static void loadCSV(String path, DefaultTableModel model) {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            boolean header = true;

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");

                if (header) {
                    header = false;
                    continue;
                }
                double total = Double.parseDouble(data[2]) * Double.parseDouble(data[3]);

                Object[] row = new Object[]{
                        Integer.parseInt(data[0]),
                        data[1],
                        Double.parseDouble(data[2]),
                        Double.parseDouble(data[3]),
                        Double.parseDouble(String.valueOf(total)),
                };

                model.addRow(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}

package org.example.UI;

import org.example.Table.MyTableModel;
import org.example.util.CSVutil;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;

public class mainApp
{
    private static String FILE_PATH = System.getProperty("user.dir") + "/src/main/java/DataBarang.csv";
    private static CSVutil CSVUtil;

    static JLabel title = new JLabel("DAFTAR BARANG GUDANG");
    static JButton btn1 = new JButton("ADD");
    static JButton btn2 = new JButton("UPDATE");
    static JButton btn3 = new JButton("DELETE");


    private static JLabel totalInventaris = new JLabel();
    private static DefaultTableModel currentModel;

    public static void updateTotalLabel() {
        if (currentModel != null && totalInventaris != null) {
            double total = totalGudang(currentModel);
            totalInventaris.setText("TOTAL HARGA BARANG GUDANG : Rp." + total);
        }
    }

    public static void main(){
        JFrame frame = new JFrame("DAFTAR STOK BARANG");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(800,500);
        frame.setLayout(new BorderLayout());
        frame.setResizable(false);

        String[] columns = {"ID","NAMA","STOK","HARGA","TOTAL(Rp)"};

        title.setFont(new Font("ARIAL",Font.BOLD,16));
        title.setHorizontalAlignment(JLabel.CENTER);
        JPanel titlePanel = new JPanel();titlePanel.setBackground(Color.decode("#E2852E"));
        titlePanel.add(title);
        frame.add(titlePanel,BorderLayout.NORTH);

        MyTableModel model = new MyTableModel(columns);
        JTable table = new JTable(model);
        currentModel = model;
        TableRowSorter<DefaultTableModel> sorter =
                new TableRowSorter<>(model);
        table.setRowSorter(sorter);

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


        JPanel panel1 = new JPanel();panel1.setBackground(Color.decode("#ABE0F0"));
        updateTotalLabel();
        panel1.add(totalInventaris);
        title.setFont(new Font("ARIAL",Font.BOLD,11));
        mainPanel.add(panel1);

        JPanel panel2 = new JPanel();
        panel2.setBackground(Color.decode("#ABE0F0"));
        panel2.setLayout(new FlowLayout());
        panel2.add(btn1);
        panel2.add(btn2);
        panel2.add(btn3);
        mainPanel.add(panel2);
        frame.add(mainPanel,BorderLayout.SOUTH);

        JTextField searchField = new JTextField(20);
        searchField.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            private void search() {
                String text = searchField.getText();

                if (text.trim().length() == 0) {
                    sorter.setRowFilter(null);
                } else {
                    sorter.setRowFilter(
                            RowFilter.regexFilter("(?i)" + text)
                    );
                }
            }

            @Override
            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                search();
            }

            @Override
            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                search();
            }

            @Override
            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                search();
            }
        });

        JPanel searchPanel = new JPanel();searchPanel.setBackground(Color.decode("#ABE0F0"));
        searchPanel.add(new JLabel("Search: "));
        searchPanel.add(searchField);
        frame.add(searchPanel, BorderLayout.BEFORE_FIRST_LINE);

        frame.setVisible(true);

        btn1.addActionListener(e -> { //ADD
            addClassAPP addPane = new  addClassAPP();
            addPane.pane(model);
        });

        btn2.addActionListener(e -> { //UPDATE
            int viewRow = table.getSelectedRow();
            if (viewRow < 0) {
                JOptionPane.showMessageDialog(frame, "Pilih data yang ingin diupdate!");
                return;
            }
            int modelRow = table.convertRowIndexToModel(viewRow);
            updateClassAPP updatePane = new updateClassAPP();
            updatePane.pane(model, modelRow);
        });

        btn3.addActionListener(e -> { //DELETE
            int viewRow = table.getSelectedRow();
            if (viewRow < 0) {
                JOptionPane.showMessageDialog(frame, "PILIH DATA YANG INGIN DIHAPUS");
                return;
            }
            int modelRow = table.convertRowIndexToModel(viewRow);
            model.removeRow(modelRow);
            CSVUtil.saveCSV(FILE_PATH, model);
            updateTotalLabel();
            JOptionPane.showMessageDialog(frame, "Data berhasil dihapus!");
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

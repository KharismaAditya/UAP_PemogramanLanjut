package org.example.util;


import javax.swing.table.DefaultTableModel;
import java.io.FileWriter;
import java.io.PrintWriter;

/**
 * Kelas utilitas untuk operasi penyimpanan data tabel ke file CSV.
 * Digunakan oleh form utama dan form update untuk menyimpan perubahan
 * data barang ke file DataBarang.csv.
 *
 * Method saveCSV menulis ulang seluruh isi model tabel ke file CSV,
 * dimulai dari header kolom hingga setiap baris data, dengan pemisah
 * koma di antara nilai kolom.
 *
 * Kelas ini bekerja sama dengan MyTableModel sehingga struktur kolom
 * dan data yang disimpan konsisten dengan tampilan tabel pada aplikasi
 * Swing.
 */
public class CSVutil {
    public static void saveCSV(String path, DefaultTableModel model) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(path))) {
            for (int i = 0; i < model.getColumnCount(); i++) {
                pw.print(model.getColumnName(i));
                if (i < model.getColumnCount() - 1) pw.print(",");
            }
            pw.println();

            for (int i = 0; i < model.getRowCount(); i++) {
                for (int j = 0; j < model.getColumnCount(); j++) {
                    pw.print(model.getValueAt(i, j));
                    if (j < model.getColumnCount() - 1) pw.print(",");
                }
                pw.println();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

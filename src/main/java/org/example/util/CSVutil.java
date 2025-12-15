package org.example.util;

import org.example.Table.MyTableModel;

import java.io.FileWriter;
import java.io.PrintWriter;

public class CSVutil {
    public static void saveCSV(String path, MyTableModel model) {
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

package org.example.Table;

import javax.swing.table.DefaultTableModel;

public class MyTableModel extends DefaultTableModel {

    public MyTableModel(Object[] columns) {
        super(columns, 0);
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return switch (columnIndex) {
            case 0 -> Integer.class;
            case 2 -> Integer.class;
            case 3 -> Double.class;
            default -> String.class;
        };
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return column != 0;
    }
}
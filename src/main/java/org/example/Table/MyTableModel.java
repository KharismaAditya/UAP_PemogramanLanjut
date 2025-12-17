package org.example.Table;

import javax.swing.table.DefaultTableModel;


/**
 * Model tabel kustom untuk aplikasi manajemen barang yang
 * menurunkan {@link DefaultTableModel}. Kelas ini digunakan
 * untuk menyimpan data barang di tabel sekaligus mengatur
 * tipe data tiap kolom dan menentukan kolom mana yang boleh
 * diedit langsung oleh pengguna. Kolom pertama (ID) dibuat
 * tidak dapat diedit untuk menjaga konsistensi dan keunikan
 * identitas setiap barang, sedangkan kolom lain seperti nama,
 * stok, harga, dan total dapat dimodifikasi sesuai kebutuhan.
 */
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
            case 4 -> Double.class;
            default -> String.class;
        };
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return column != 0;
    }
}
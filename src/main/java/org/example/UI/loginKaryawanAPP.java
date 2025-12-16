package org.example.UI;

import javax.swing.*;
import java.awt.*;

public class loginKaryawanAPP {
    private static String Cuser = "admin";
    private static String Cpw = "UMM1964";

    static JLabel title = new JLabel("MANAJEMEN STOK BARANG", JLabel.CENTER);
    static JTextField username = new JTextField();
    static JPasswordField password = new JPasswordField();
    static JButton loginButton = new JButton("LOGIN");

    public static void main(String[] args) {
        JFrame frame = new JFrame("APLIKASI MANAJEMEN STOK BARANG");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400,150);
        frame.setResizable(false);

        Panel panelTitle = new Panel(new FlowLayout());
        panelTitle.add(title);
        title.setFont(new Font("ARIAL",Font.BOLD,20));

        Panel panelForm = new Panel(new GridLayout(2,2));
        panelForm.add(new JLabel("USERNAME : "));
        panelForm.add(username);
        panelForm.add(new JLabel("PASSWORD : "));
        panelForm.add(password);

        Panel panelButton = new Panel(new FlowLayout());
        panelButton.add(loginButton);
        panelButton.add(panelForm);
        frame.add(panelTitle,BorderLayout.NORTH);
        frame.add(panelForm,BorderLayout.CENTER);
        frame.add(panelButton,BorderLayout.SOUTH);

        loginButton.addActionListener(e ->{
            String userInput = username.getText();
            String passwordInput = String.valueOf(password.getPassword());
            if(userInput.equals(Cuser) && passwordInput.equals(Cpw)){
                mainApp app = new  mainApp();
                app.main();
                frame.dispose();
            }
        });

        frame.setVisible(true);
    }
}

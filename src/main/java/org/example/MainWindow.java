package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class MainWindow {
    private static final String DB_URL = "jdbc:postgresql://194.169.163.175:5432/beka";
    private static final String DB_USER = "beka";
    private static final String DB_PASSWORD = "2229444";
    private JFrame frame;
    private JButton button;
    private JTextField textField;

    public MainWindow() {
        frame = new JFrame("Программа");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setLayout(null);

        button = new JButton("кнопка");
        button.setBounds(100, 50, 100, 30);
        button.addActionListener(e -> buttonClicked());

        textField = new JTextField();
        textField.setBounds(50, 100, 200, 30);

        frame.add(button);
        frame.add(textField);
        frame.setVisible(true);
    }

    private void buttonClicked() {
        try(Connection connection = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD)) {
            String query = "SELECT * FROM public.tablicakata";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()){
                int id = resultSet.getInt("id");
                int group_id = resultSet.getInt("vasa");
                System.out.println("group_id - " + group_id);
                System.out.println("id - " + id);
            }
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }


    }



    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainWindow::new);
    }
}
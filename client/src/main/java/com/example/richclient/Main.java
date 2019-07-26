package com.example.richclient;
import javax.swing.*;

public class Main
{
    public static void main(String[] args) {
        JFrame frame = new JFrame("Rich Client");
        frame.setContentPane(new LoginForm().loginview);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}

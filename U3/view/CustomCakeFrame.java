package view;

import controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomCakeFrame extends JFrame implements ActionListener {
    private JPanel mainPanel;

    private Controller controller;

    public CustomCakeFrame(Controller controller) {
        mainPanel = new JPanel(new GridLayout(1, 1));
        setSize(600, 400);
        this.setContentPane(mainPanel);
        setupPanel();
        this.setTitle("Create your own cake!");
        this.setVisible(true);
        this.setResizable(false);
        this.controller = controller;
    }

    public void setupPanel() {
        /*
           TODO: Skapa ny tårta här och spara den.
            */
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        /*
           TODO: Skapa ny tårta här och spara den.
            */
    }
}

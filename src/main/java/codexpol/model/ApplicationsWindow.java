package codexpol.model;

import javafx.scene.control.Button;

import javax.swing.*;
import java.awt.*;

public class ApplicationsWindow extends JFrame {


    JButton button1;
    JButton button2;
    JButton button3;

    public ApplicationsWindow()
    {
        super("Hello World");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setSize(400,400);
        setLocation(120,60);
        setLayout(new FlowLayout());

        button1=new JButton();
        button2=new JButton();
        button3=new JButton();

        button1.setText("Historyjka 1");
        button2.setText("Historyjka 2");
        button3.setText("Historyjka 3");

        add(button1);
        add(button2);
        add(button3);

    }






}

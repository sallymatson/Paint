package guiStuff;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Layout extends JFrame {

    JPanel panelShapes;
    JPanel panelDraw;
    JButton lineButton;

    public Layout() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Paint");
        setLayout(new GridLayout( 2,1 ));

        panelShapes = new JPanel();
        panelShapes.setBackground(Color.orange);
        add(panelShapes);
        panelShapes.setLayout(new GridLayout(1, 4));
        lineButton = new JButton("Line");
        panelShapes.add(lineButton);
        panelShapes.add(new JButton("Square"));
        panelShapes.add(new JButton("Oval"));
        panelShapes.add(new JButton("Triangle"));

        setSize(new Dimension(500, 500));
        setVisible(true);
    }
}

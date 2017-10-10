package guiStuff;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Layout extends JFrame implements ActionListener {

    JPanel panelShapes;
    JPanel panelDraw;
    JButton lineButton;
    JButton ovalButton;
    JButton rectangleButton;
    JButton triangleButton;

    public Layout() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Paint");
        setLayout(new GridLayout(2, 1));
        // Buttons:
        lineButton = new JButton("Line");
        ovalButton = new JButton("Oval");
        rectangleButton = new JButton("Rectangle");
        triangleButton = new JButton("Triangle");

        // add listeners:
        lineButton.addActionListener(this);
        ovalButton.addActionListener(this);
        rectangleButton.addActionListener(this);
        triangleButton.addActionListener(this);

        // panel to display shape names
        panelShapes = new JPanel();
        panelShapes.setBackground(Color.orange);
        add(panelShapes);
        panelShapes.setLayout(new GridLayout(1, 4));
        panelShapes.add(lineButton);
        panelShapes.add(rectangleButton);
        panelShapes.add(triangleButton);
        panelShapes.add(ovalButton);

        setSize(new Dimension(500, 500));
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == lineButton) {
            System.out.println("line");
            // create line method
        } else if (e.getSource() == rectangleButton) {
            System.out.println("rect");
            // create rect method
        } else if (e.getSource() == triangleButton) {
            System.out.println("tri");
            // create triangle method
        } else if (e.getSource() == ovalButton) {
            System.out.println("oval");
            // create oval method
        }

        repaint();
    }
}
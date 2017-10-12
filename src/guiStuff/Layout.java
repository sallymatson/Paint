package guiStuff;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Layout extends JFrame implements ActionListener {

    JPanel panelShapes;
    JPanel panelCalculate;
    JPanel panelDraw;

    JButton lineButton;
    JButton ovalButton;
    JButton rectangleButton;
    JButton triangleButton;
    JButton getPerimeterButton;
    JButton getAreaButton;
    
    ShapeList shapeList = new ShapeList();
    
    JTextField perimeterTextField;
    JTextArea perimeterTextArea;
    JTextField areaTextField;
    JTextArea areaTextArea;
    
    public Layout() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Paint");
        setLayout(new GridLayout(2, 1));

        setupButtons();

        setupPanelShapes();

        setupPanelCalculate();

        setupPanelCanvas();

        // window settings
        setSize(new Dimension(800, 600));
        setVisible(true);
    }

    private void setupButtons() {
        // Buttons:
        lineButton = new JButton("Line");
        ovalButton = new JButton("Oval");
        rectangleButton = new JButton("Rectangle");
        triangleButton = new JButton("Triangle");
        getPerimeterButton = new JButton("Get Perimeter");
        getAreaButton = new JButton("Get Area");

        // add listeners:
        lineButton.addActionListener(this);
        ovalButton.addActionListener(this);
        rectangleButton.addActionListener(this);
        triangleButton.addActionListener(this);
        getPerimeterButton.addActionListener(this);
        getAreaButton.addActionListener(this);
    }

    private void setupPanelShapes() {
        // panel to display shape names
        panelShapes = new JPanel();
        panelShapes.setBackground(Color.orange);
        add(panelShapes);
        panelShapes.setLayout(new GridLayout(1, 4));
        panelShapes.add(lineButton);
        panelShapes.add(rectangleButton);
        panelShapes.add(triangleButton);
        panelShapes.add(ovalButton);
    }

    private void setupPanelCalculate() {
        // panel to calculate and display area and perimeter
        panelCalculate = new JPanel();
        add(panelCalculate);

        panelCalculate.setLayout(new BoxLayout(panelCalculate, BoxLayout.Y_AXIS));
        panelCalculate.add(getPerimeterButton);

        perimeterTextField = new JTextField(20);
        perimeterTextField.setMaximumSize( perimeterTextField.getPreferredSize() );
        perimeterTextField.addActionListener(this);

        perimeterTextArea = new JTextArea(5, 20);
        perimeterTextArea.setEditable(false);

        panelCalculate.add(perimeterTextField);

        panelCalculate.add(getAreaButton);

        areaTextField = new JTextField(20);
        areaTextField.addActionListener(this);

        areaTextArea = new JTextArea(5, 20);
        areaTextArea.setEditable(false);
    }

    private void setupPanelCanvas() {
        // panel to draw shapes
        panelDraw = new JPanel();
        add(panelDraw);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == lineButton) {
            System.out.println("line");
            shapeList.addShape(new Line(panelDraw.getX() + 50, panelDraw.getY() + 50, panelDraw.getX() + 250, panelDraw.getY() + 100));
            // call line constructor
            // takes points from next drag
            // draws it to the screen
        } else if (e.getSource() == rectangleButton) {
            System.out.println("rect");
            shapeList.addShape(new Rectangle(panelDraw.getX() + 50, panelDraw.getY() + 150, panelDraw.getX() + 100, panelDraw.getY() + 30));
            // create rect method
        } else if (e.getSource() == triangleButton) {
            System.out.println("tri");
            shapeList.addShape(new Triangle(panelDraw.getX() + 50, panelDraw.getY() + 150, panelDraw.getX() + 100, panelDraw.getY() + 30, panelDraw.getX() + 200, panelDraw.getY() + 40));
            // create triangle method
        } else if (e.getSource() == ovalButton) {
            System.out.println("oval");
            shapeList.addShape(new Oval(panelDraw.getX() + 50, panelDraw.getY() + 50, panelDraw.getX() + 250, panelDraw.getY() + 100));
            // create oval method
        } else if(e.getSource() == getPerimeterButton) {
            System.out.println("Calculating perimeter");
            perimeterTextField.setText("perimeter");
            //perimeterTextField.selectAll();
        } else if(e.getSource() == getAreaButton) {
            System.out.println("Calculating area");
        }

        repaint();
    }

    @Override
    public void paint( Graphics g )
    {
        super.paint(g); // call to JFrame paint()

        shapeList.drawShapes(g);
    }
}

package guiStuff;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Layout extends JFrame implements ActionListener {

    JPanel panelShapes;
    JPanel panelDraw;
    JPanel panelCalculate;
    
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
        
        panelCalculate = new JPanel();
        add(panelCalculate);
        
        panelCalculate.setLayout(new BoxLayout(panelCalculate, BoxLayout.Y_AXIS));
        panelCalculate.add(getPerimeterButton);
        
        setSize(new Dimension(500, 500));
        setVisible(true);
        
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
        
        //panelCalculate.add(Box.createVerticalStrut(10));

        setSize(new Dimension(500, 500));
        setVisible(true);
        
//        JScrollPane scrollPane = new JScrollPane(perimeterTextArea);
//        
//        //Add Components to this panel.
//        GridBagConstraints c = new GridBagConstraints();
//        c.gridwidth = GridBagConstraints.REMAINDER;
// 
//        c.fill = GridBagConstraints.HORIZONTAL;
//        add(perimeterTextField, c);
// 
//        c.fill = GridBagConstraints.BOTH;
//        c.weightx = 1.0;
//        c.weighty = 1.0;
//        add(scrollPane, c);
//        
//        setSize(new Dimension(500, 500));
//        setVisible(true);
//        
        

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == lineButton) {
            System.out.println("line");
            // call line constructor
            // takes points from next drag
            // draws it to the screen
        } else if (e.getSource() == rectangleButton) {
            System.out.println("rect");
            // create rect method
        } else if (e.getSource() == triangleButton) {
            System.out.println("tri");
            // create triangle method
        } else if (e.getSource() == ovalButton) {
            System.out.println("oval");
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

        g.drawLine( 50, 50, 250, 100 );  // xy xy from upper left
        g.drawRect( 50, 150, 100, 30 );   // x y w h
        g.drawOval( 50, 150, 100, 300 );
        g.setColor( Color.pink );
        g.fillRect(200,200,50,50 );
        g.drawString("blah blah",300,300);

        int[] x = new int[3];
        int[] y = new int[3];
        x[0] = 500; x[1] = 400; x[2] = 350;
        y[0] = 350; y[1] = 150; y[2] = 400;

        g.setColor( Color.red );
        g.fillPolygon( x, y, 3 );

    }
}
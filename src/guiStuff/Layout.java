package guiStuff;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.ChangeListener;

public class Layout extends JFrame
        implements ActionListener, MouseListener, MouseMotionListener{

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
    
    Color color;

    int downX, downY; // where the mouse is when button is pressed
    int mouseX, mouseY; // mouse last seen at
    int upX, upY;
    int trix1, trix2, triy1, triy2;

    String mouseMode = "none";
    
    public Layout() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Paint");
        setLayout(new GridLayout(2, 1));

        setupButtons();

        setupPanelShapes();

        setupPanelCalculate();

        setupPanelCanvas();

        addMouseListener(this);
        addMouseMotionListener(this);

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
        perimeterTextField.setEditable(false);

        panelCalculate.add(perimeterTextField);

        panelCalculate.add(getAreaButton);

        areaTextField = new JTextField(20);
        areaTextField.setMaximumSize(areaTextField.getPreferredSize() );
        areaTextField.addActionListener(this);

        areaTextArea = new JTextArea(5, 20);
        areaTextField.setEditable(false);
        panelCalculate.add(areaTextField);

        
        
        
    }

    private void setupPanelCanvas() {
        // panel to draw shapes
        panelDraw = new JPanel();
        add(panelDraw);
    }

    private void draw() {
    	 	
         if (mouseMode == "oval"){
            shapeList.addShape(new Oval(downX, downY, mouseX, mouseY, color));
            mouseMode = "none";
        } else if (mouseMode == "line"){
            shapeList.addShape(new Line(downX, downY, mouseX, mouseY, color));

            mouseMode = "none";
        } else if (mouseMode == "rectangle"){
            shapeList.addShape(new Rectangle(downX, downY, mouseX, mouseY, color));
            mouseMode = "none";
        } else if (mouseMode == "triangle"){
            trix1 = mouseX;
            triy1 = mouseY;
            mouseMode = "triangle2";
        } else if (mouseMode == "triangle2"){
            trix2 = mouseX;
            triy2 = mouseY;
            mouseMode = "triangle3";
        } else if (mouseMode == "triangle3"){
            shapeList.addShape(new Triangle(trix1, triy1, trix2, triy2, mouseX, mouseY, color));
            mouseMode = "none";
        }

    }

    //Mouse Listener events taken from in class examples (snappy)
    // MouseListener methods (5 of them)
    @Override public void mouseEntered ( MouseEvent m ) {}
    @Override public void mouseExited  ( MouseEvent m ) {}

    // record position of mouse when mouse button is pressed
    @Override public void mousePressed ( MouseEvent m )
    {
        mouseX = downX = m.getX();
        mouseY = downY = m.getY();
    }
    @Override public void mouseReleased( MouseEvent m )
    {
        mouseX = upX = m.getX();
        mouseY = upY = m.getY();
  
       
        draw();
       
        repaint();
    }
    @Override public void mouseClicked ( MouseEvent m ) {}

    // MouseMotionListener methods (just 2 needed)
    // when the mouse is dragged, update the mouseXY position
    @Override public void mouseDragged ( MouseEvent m )
    {
        mouseX = m.getX();
        mouseY = m.getY();
    }

    @Override public void mouseMoved   ( MouseEvent m ) {}

    @Override
    public void actionPerformed(ActionEvent e) {
    		
        
        if (e.getSource() == lineButton) {
            mouseMode = "line";
            JColorChooser jcc = new JColorChooser();
            color = jcc.showDialog(null, "Please select color", Color.red);
            
        } else if (e.getSource() == rectangleButton) {
            mouseMode = "rectangle";
            JColorChooser jcc = new JColorChooser();
            color = jcc.showDialog(null, "Please select color", Color.red);
          
        } else if (e.getSource() == triangleButton) {
            mouseMode = "triangle";
            JColorChooser jcc = new JColorChooser();
            color = jcc.showDialog(null, "Please select color", Color.red);
           
        } else if (e.getSource() == ovalButton) {
            mouseMode = "oval";
            JColorChooser jcc = new JColorChooser();
            color = jcc.showDialog(null, "Please select color", Color.red);
            
        } else if(e.getSource() == getPerimeterButton) {
            System.out.println("Calculating perimeter");
            String totalPerimeter = Double.toString(shapeList.getTotalPerimeter());     
            perimeterTextField.setText(totalPerimeter);

        } else if(e.getSource() == getAreaButton) {
            System.out.println("Calculating area");
            String totalArea = Double.toString(shapeList.getTotalArea());
            areaTextField.setText(totalArea);
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

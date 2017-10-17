package guiStuff;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.ChangeListener;

public class Layout extends JFrame
    implements ActionListener, MouseListener, MouseMotionListener {

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

    Shape selectedShape;

    enum MouseMode
    {
        none, oval, line, rectangle, triangle, triangle2, triangle3, selected, adjust
    }

    MouseMode mouseMode = MouseMode.none;
    
    public Layout() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Paint");
        setLayout(new BorderLayout());

        setupButtons();
        setupPanelShapes();
        setupPanelCalculate();
        setupPanelDraw();

        addMouseListener(this);
        addMouseMotionListener(this);

        // window settings
        setSize(new Dimension(640, 480));
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
        // panelShapes.setBackground(Color.orange);
        add(panelShapes, BorderLayout.PAGE_START);
        panelShapes.setLayout(new GridLayout(1, 4));
        panelShapes.add(lineButton);
        panelShapes.add(rectangleButton);
        panelShapes.add(triangleButton);
        panelShapes.add(ovalButton);
    }

    private void setupPanelCalculate() {
        // panel to calculate and display area and perimeter
        panelCalculate = new JPanel();
        add(panelCalculate, BorderLayout.PAGE_END);

        panelCalculate.setLayout(new BoxLayout(panelCalculate, BoxLayout.X_AXIS));
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

    private void setupPanelDraw() {
        // panel to draw shapes
        panelDraw = new JPanel();
        add(panelDraw, BorderLayout.CENTER);
        panelDraw.setBackground(Color.white);
    }

    private void draw() {
        if (mouseMode == MouseMode.oval) {
            shapeList.addShape(new Oval(downX, downY, mouseX, mouseY, color));
            mouseMode = MouseMode.none;
        } else if (mouseMode == MouseMode.line) {
            shapeList.addShape(new Line(downX, downY, mouseX, mouseY, color));
            mouseMode = MouseMode.none;
        } else if (mouseMode == MouseMode.rectangle) {
            shapeList.addShape(new Rectangle(downX, downY, mouseX, mouseY, color));
            mouseMode = MouseMode.none;
        } else if (mouseMode == MouseMode.triangle) {
            trix1 = mouseX;
            triy1 = mouseY;
            mouseMode = MouseMode.triangle2;
        } else if (mouseMode == MouseMode.triangle2) {
            trix2 = mouseX;
            triy2 = mouseY;
            mouseMode = MouseMode.triangle3;
        } else if (mouseMode == MouseMode.triangle3) {
            shapeList.addShape(new Triangle(trix1, triy1, trix2, triy2, mouseX, mouseY, color));
            mouseMode = MouseMode.none;
        }
    }

    private void trySelect(int selectX, int selectY) {
        if (mouseMode == MouseMode.none || mouseMode == MouseMode.selected) {
            Shape shape = shapeList.trySelect(selectX, selectY);
            if (shape != null) {
                if (selectedShape != null) {
                    selectedShape.setSelected(false);
                    selectedShape = null;
                }
                // select that shape
                shape.setSelected(true);
                // set mousemode to selected
                mouseMode = MouseMode.selected;
                selectedShape = shape;
            } else if (mouseMode == MouseMode.selected) {
                mouseMode = MouseMode.none;
                selectedShape.setSelected(false);
                selectedShape = null;
            }
        }
    }

    private void adjust() {
        if (mouseMode == MouseMode.adjust) {
            selectedShape.adjust(upX, upY);
            // TODO: maybe select just-created shape, even if there is a currently selected one?
            mouseMode = MouseMode.selected;
        }
    }

    // Mouse Listener events taken from in class examples (snappy)
    // MouseListener methods (5 of them)
    @Override public void mouseEntered( MouseEvent m ) {}
    @Override public void mouseExited( MouseEvent m ) {}
    @Override public void mouseClicked( MouseEvent m ) {}
    // record position of mouse when mouse button is pressed
    @Override public void mousePressed( MouseEvent m )
    {
        mouseX = downX = m.getX();
        mouseY = downY = m.getY();
        if (mouseMode == MouseMode.selected) {
            if (selectedShape.adjustHandlesContain(mouseX, mouseY)) {
                mouseMode = MouseMode.adjust;
                return;
            }
        }
        if (mouseMode == MouseMode.selected || mouseMode == MouseMode.none) {
            trySelect(m.getX(), m.getY());
        }
    }
    @Override public void mouseReleased( MouseEvent m )
    {
        mouseX = upX = m.getX();
        mouseY = upY = m.getY();

        draw();
        adjust();
        repaint();
    }

    // MouseMotionListener methods (just 2 needed)
    // when the mouse is dragged, update the mouseXY position
    @Override public void mouseDragged( MouseEvent m )
    {
        mouseX = m.getX();
        mouseY = m.getY();
    }
    @Override public void mouseMoved( MouseEvent m ) {}

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == lineButton) {
            mouseMode = MouseMode.line;
            color = JColorChooser.showDialog(null, "Please select color", color);
        }
        else if (e.getSource() == rectangleButton) {
            mouseMode = MouseMode.rectangle;
            color = JColorChooser.showDialog(null, "Please select color", color);
        }
        else if (e.getSource() == triangleButton) {
            mouseMode = MouseMode.triangle;
            color = JColorChooser.showDialog(null, "Please select color", color);
        }
        else if (e.getSource() == ovalButton) {
            mouseMode = MouseMode.oval;
            color = JColorChooser.showDialog(null, "Please select color", color);
        }
        else if(e.getSource() == getPerimeterButton) {
            String totalPerimeter = Double.toString(shapeList.getTotalPerimeter());
            perimeterTextField.setText(totalPerimeter);
        }
        else if(e.getSource() == getAreaButton) {
            String totalArea = Double.toString(shapeList.getTotalArea());
            areaTextField.setText(totalArea);
        }
    }

    @Override
    public void paint( Graphics g )
    {
        super.paint(g); // call to JFrame paint()
        shapeList.drawShapes(g);
    }
}

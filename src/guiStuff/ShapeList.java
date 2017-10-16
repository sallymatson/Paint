package guiStuff;

import java.awt.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.io.IOException;
import java.util.List;

public class ShapeList {
    private List<Shape> sl = new ArrayList<Shape>();
    private double totalArea;
    private double totalPerimeter;

    public void addShape(Shape shape) {
        sl.add(shape);

        // add to total Area and total Perimeter
        calculateTotalArea();
        calculateTotalPerimeter();
    }

    public void drawShapes(Graphics g) {
        for (Shape shape : sl) {
            shape.drawShape(g);
        }
    }

    public Shape trySelect(int x, int y) {
        // for all shapes in shapeList, starting with the last shape
        // aka the latest added, aka the shapes at the top/lowest depth
        for (int i = sl.size() -1; i >= 0; i--) {
            // check if (x, y) is inside shape
            if (sl.get(i).contains(x, y)) {
                return sl.get(i);
            }
        }
        return null;
    }

    private void calculateTotalArea(){
        this.totalArea = 0.0;
        for (Shape shape : sl){
            this.totalArea += shape.getArea();
        }
    }

    private void calculateTotalPerimeter() {
        this.totalPerimeter = 0.0;
        for (Shape shape : sl){
            this.totalPerimeter += shape.getPerimeter();
        }
    }

    public double getTotalArea() {
    		calculateTotalArea();
        return totalArea;
    }

    public double getTotalPerimeter() {
    		calculateTotalPerimeter();
        return this.totalPerimeter;
    }

}

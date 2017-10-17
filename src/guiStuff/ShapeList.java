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
    }

    public void drawShapes(Graphics g) {
        for (Shape shape : sl) {
            shape.drawShape(g);
        }
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

package guiStuff;

import java.awt.*;
import java.awt.geom.Ellipse2D;

class Triangle implements Shape {
    private int x[];
    private int y[];
    private Color color;
    private boolean selected;
    private int handleBeingAdjusted;

    private double calcSideLen(double x1, double x2, double y1, double y2) {
        //return Math.sqrt(Math.pow(2, x1-x2) + Math.pow(2, y1-y2));
        return Math.sqrt(Math.pow(x2-x1 , 2) + Math.pow(y2-y1, 2));
    }

    public double getSide1() {
        return calcSideLen(x[0], x[1], y[0], y[1]);
    }

    public double getSide2() {
        return calcSideLen(x[0], x[2], y[0], y[2]);
    }

    public double getSide3() {
        return calcSideLen(x[2], x[1], y[2], y[1]);
    }

    public Triangle(int x1, int y1, int x2, int y2, int x3, int y3, Color c) {
        x = new int[] {x1, x2, x3};
        y = new int[] {y1, y2, y3};
        color = c;
    }

    @Override
    public boolean isSelected() {
        return selected;
    }

    @Override
    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    @Override
    public double getArea(){
        double p = this.getPerimeter()/2;
        return Math.sqrt(p*(p-this.getSide1())*(p-this.getSide2())*(p-this.getSide3()));
    }

    @Override
    public double getPerimeter() {
        return this.getSide1() + this.getSide2() + this.getSide3();
    }

    @Override 
    public Color getColor() {
		return color;
    } 
    
    @Override
    public void drawShape(Graphics g) {
        g.setColor(getColor());
        g.fillPolygon( x, y, 3 );
        if (selected) {
            // draw adjust handles
            g.setColor(Color.black);
            g.drawOval(x[0] - adjustHandleRadius, y[0] - adjustHandleRadius, adjustHandleRadius * 2, adjustHandleRadius * 2);
            g.drawOval(x[1] - adjustHandleRadius, y[1] - adjustHandleRadius, adjustHandleRadius * 2, adjustHandleRadius * 2);
            g.drawOval(x[2] - adjustHandleRadius, y[2] - adjustHandleRadius, adjustHandleRadius * 2, adjustHandleRadius * 2);
        }
    }

    @Override
    public boolean contains(int x, int y) {
        Polygon poly = new Polygon(this.x, this.y, 3);
        return poly.contains(new Point(x, y));
    }

    @Override
    public boolean adjustHandlesContain(int x, int y) {
        Ellipse2D handle1 = new Ellipse2D.Double(this.x[0] - adjustHandleRadius, this.y[0] - adjustHandleRadius, adjustHandleRadius * 2, adjustHandleRadius * 2);
        Ellipse2D handle2 = new Ellipse2D.Double(this.x[1] - adjustHandleRadius, this.y[1] - adjustHandleRadius, adjustHandleRadius * 2, adjustHandleRadius * 2);
        Ellipse2D handle3 = new Ellipse2D.Double(this.x[2] - adjustHandleRadius, this.y[2] - adjustHandleRadius, adjustHandleRadius * 2, adjustHandleRadius * 2);
        // if one of the adjust handles does contain the clicked point,
        // figure out which one and keep track that we are adjusting that corner
        if (handle1.contains(x, y)) {
            handleBeingAdjusted = 1;
            return true;
        } else if (handle2.contains(x, y)) {
            handleBeingAdjusted = 2;
            return true;
        } else if (handle3.contains(x, y)) {
            handleBeingAdjusted = 3;
            return true;
        }
        return false;
    }

    @Override
    public void adjust(int x, int y) {
        this.x[handleBeingAdjusted - 1] = x;
        this.y[handleBeingAdjusted - 1] = y;
        handleBeingAdjusted = 0;
    }
}

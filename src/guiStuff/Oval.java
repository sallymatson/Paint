//
//  Created by:
//      ad1229  Aissatou Diallo
//      pj202   Peter Johnston
//      sam439  Sally Matson
//
//  Disclosures are in Layout.java
//

package guiStuff;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.Map;

class Oval implements Shape {
    private int x[];
    private int y[];
    private Color color;
    private boolean selected;
    private int handleBeingAdjusted;

    public double getRadiusX() {
        return Math.abs(x[0] - x[1]);
    }

    public double getRadiusY() {
        return Math.abs(y[0] - y[1]);
    }

    public Oval(int x1, int y1, int x2, int y2, Color c) {
        x = new int [] { Math.min(x1, x2), Math.max(x1, x2) };
        y = new int [] { Math.min(y1, y2), Math.max(y1, y2) };
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
        return Math.PI * this.getRadiusX() * this.getRadiusY();
    }

    @Override
    public double getPerimeter(){
        return 2 * Math.PI * Math.sqrt((Math.pow(this.getRadiusX(), 2) + Math.pow(this.getRadiusY(), 2)) / 2);
    }

    @Override
    public Color getColor() {
    		return color;
    }
    @Override
    public void drawShape(Graphics g) {
        g.setColor(getColor());
        g.fillOval( x[0], y[0], (int)getRadiusX(), (int)getRadiusY() );
        if (selected) {
            // draw adjust handles
            g.setColor(Color.black);
            g.drawOval(x[0] - adjustHandleRadius, y[0] - adjustHandleRadius, adjustHandleRadius * 2, adjustHandleRadius * 2);
            g.drawOval(x[1] - adjustHandleRadius, y[0] - adjustHandleRadius, adjustHandleRadius * 2, adjustHandleRadius * 2);
            g.drawOval(x[1] - adjustHandleRadius, y[1] - adjustHandleRadius, adjustHandleRadius * 2, adjustHandleRadius * 2);
            g.drawOval(x[0] - adjustHandleRadius, y[1] - adjustHandleRadius, adjustHandleRadius * 2, adjustHandleRadius * 2);
        }
    }

    @Override
    public boolean contains(int x, int y) {
        Ellipse2D oval = new Ellipse2D.Double(this.x[0], this.y[0], getRadiusX(), getRadiusY());
        return oval.contains(x, y);
    }

    @Override
    public boolean adjustHandlesContain(int x, int y) {
        Ellipse2D handle1 = new Ellipse2D.Double(this.x[0] - adjustHandleRadius, this.y[0] - adjustHandleRadius, adjustHandleRadius * 2, adjustHandleRadius * 2);
        Ellipse2D handle2 = new Ellipse2D.Double(this.x[1] - adjustHandleRadius, this.y[0] - adjustHandleRadius, adjustHandleRadius * 2, adjustHandleRadius * 2);
        Ellipse2D handle3 = new Ellipse2D.Double(this.x[1] - adjustHandleRadius, this.y[1] - adjustHandleRadius, adjustHandleRadius * 2, adjustHandleRadius * 2);
        Ellipse2D handle4 = new Ellipse2D.Double(this.x[0] - adjustHandleRadius, this.y[1] - adjustHandleRadius, adjustHandleRadius * 2, adjustHandleRadius * 2);
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
        } else if (handle4.contains(x, y)) {
            handleBeingAdjusted = 4;
            return true;
        }
        return false;
    }

    @Override
    public void adjust(int x, int y) {
        switch (handleBeingAdjusted) {
            case 1:
                this.x[0] = x;
                this.y[0] = y;
                break;
            case 2:
                this.x[1] = x;
                this.y[0] = y;
                break;
            case 3:
                this.x[1] = x;
                this.y[1] = y;
                break;
            case 4:
                this.x[0] = x;
                this.y[1] = y;
                break;
        }
        this.x = new int [] { Math.min(this.x[0], this.x[1]), Math.max(this.x[0], this.x[1]) };
        this.y = new int [] { Math.min(this.y[0], this.y[1]), Math.max(this.y[0], this.y[1]) };
        handleBeingAdjusted = 0;
    }
}

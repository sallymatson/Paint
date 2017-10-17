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

class Line implements Shape {
    private int[] x;
    private int[] y;
    private Color color;
    private boolean selected;
    private int handleBeingAdjusted;

    public double getLength() {
        return Math.sqrt(Math.pow(2, x[0]-x[1]) + Math.pow(2, y[0]-y[1]));
    }

    public Line(int x1, int y1, int x2, int y2, Color c){
        x = new int [] {x1, x2};
        y = new int [] {y1, y2};
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
    public double getArea() {
        return 0;
    }
    
    @Override
    public double getPerimeter() {
        return 0;
    }

    @Override
    public Color getColor() {
    		return color;
    }
    
    @Override
    public void drawShape(Graphics g) {
        g.setColor(getColor());
        g.drawLine( x[0], y[0], x[1], y[1] );
        if (selected) {
            // draw adjust handles
            g.setColor(Color.black);
            g.drawOval(x[0] - adjustHandleRadius, y[0] - adjustHandleRadius, adjustHandleRadius * 2, adjustHandleRadius * 2);
            g.drawOval(x[1] - adjustHandleRadius, y[1] - adjustHandleRadius, adjustHandleRadius * 2, adjustHandleRadius * 2);
        }
    }

    @Override
    public boolean contains(int x, int y) {
        double dx = this.x[1] - this.x[0];
        double dy = this.y[1] - this.y[0];
        double length = Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
        dx /= length; dx *= 6;
        dy /= length; dy *= 6;
        int[] xPoints = { (int)(this.x[0] - dy), (int)(this.x[0] + dy), (int)(this.x[1] + dy), (int)(this.x[1] - dy) };
        int[] yPoints = { (int)(this.y[0] + dx), (int)(this.y[0] - dx), (int)(this.y[1] - dx), (int)(this.y[1] + dx) };
        Polygon poly = new Polygon(xPoints, yPoints, 4);
        return poly.contains(new Point(x, y));
    }

    @Override
    public boolean adjustHandlesContain(int x, int y) {
        Ellipse2D handle1 = new Ellipse2D.Double(this.x[0] - adjustHandleRadius, this.y[0] - adjustHandleRadius, adjustHandleRadius * 2, adjustHandleRadius * 2);
        Ellipse2D handle2 = new Ellipse2D.Double(this.x[1] - adjustHandleRadius, this.y[1] - adjustHandleRadius, adjustHandleRadius * 2, adjustHandleRadius * 2);
        // if one of the adjust handles does contain the clicked point,
        // figure out which one and keep track that we are adjusting that corner
        if (handle1.contains(x, y)) {
            handleBeingAdjusted = 1;
            return true;
        } else if (handle2.contains(x, y)) {
            handleBeingAdjusted = 2;
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

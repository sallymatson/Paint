package guiStuff;

import java.awt.*;

import java.util.Map;

class Rectangle implements Shape {
    private int x [];
    private int y [];
    private Color color;
    private boolean selected;

    public int getHeight() {
        return Math.abs(y[0] - y[1]);
    }

    public int getWidth() {
        return Math.abs(x[0] - x[1]);
    }

    public Rectangle(int x1, int y1, int x2, int y2, Color c) {
        x = new int [] {Math.min(x1, x2), Math.max(x1, x2)};
        y = new int [] {Math.min(y1, y2), Math.max(y1, y2)};
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
        return this.getHeight() * this.getWidth();
    }

    @Override
    public double getPerimeter(){
        return (this.getHeight() * 2) + (this.getWidth() * 2);
    }
    
    @Override
    public Color getColor() {
		return color;
    }
    
    @Override
    public void drawShape(Graphics g) {
        g.setColor(getColor());
        g.fillRect( x[0], y[0], getWidth(), getHeight() );
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
        int[] xPoints = { this.x[0], this.x[1], this.x[1], this.x[0] };
        int[] yPoints = { this.y[0], this.y[0], this.y[1], this.y[1] };
        Polygon poly = new Polygon(xPoints, yPoints, 4);
        return poly.contains(new Point(x, y));
    }

    @Override
    public boolean adjustHandlesContain(int x, int y) {
        return false;
        // if it does contain, figure out which one and keep track that we are adjusting that corner
    }

    @Override
    public void adjust(int x, int y) {

    }
}

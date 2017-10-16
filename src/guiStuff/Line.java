package guiStuff;

import java.awt.*;

class Line implements Shape {
    private int[] x;
    private int[] y;
    private Color color;

    public double getLength(){
        return Math.sqrt(Math.pow(2, x[0]-x[1]) + Math.pow(2, y[0]-y[1]));
    }

    public Line(int x1, int y1, int x2, int y2, Color c){
        x = new int [] {x1, x2};
        y = new int [] {y1, y2};
        color = c;
    }

    @Override
    public double getArea(){
        return 0;
    }
    
    @Override
    public double getPerimeter(){
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
    }
}

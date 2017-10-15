package guiStuff;

import java.awt.*;

import java.util.Map;

class Rectangle implements Shape {
    private int x [];
    private int y [];
    private Color color;

    public int getHeight(){
        return Math.abs(y[0] - y[1]);
    }

    public int getWidth(){
        return Math.abs(x[0] - x[1]);
    }

    public Rectangle(int x1, int y1, int x2, int y2, Color c){
        x = new int [] {Math.min(x1, x2), Math.max(x1, x2)};
        y = new int [] {Math.min(y1, y2), Math.max(y1, y2)};
        color = c;
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
    }
}

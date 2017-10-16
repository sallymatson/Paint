package guiStuff;

import java.awt.*;
import java.util.Map;

class Oval implements Shape {
    private int x[];
    private int y[];
    private Color color;

    public double getRadiusX(){
        return Math.abs(x[0] - x[1]);
    }

    public double getRadiusY(){
        return Math.abs(y[0] - y[1]);
    }

    public Oval(int x1, int y1, int x2, int y2, Color c){
        x = new int [] { Math.min(x1, x2), Math.max(x1, x2) };
        y = new int [] { Math.min(y1, y2), Math.max(y1, y2) };
        color = c;
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
    }
}

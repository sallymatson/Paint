package guiStuff;

import java.awt.*;
import java.util.Map;

class Oval implements Shape {
    private int x[];
    private int y[];

    public double getRadiusX(){
        return Math.abs(x[0] - x[1]);
    }

    public double getRadiusY(){
        return Math.abs(y[0] - y[1]);
    }

    public Oval(int x1, int y1, int x2, int y2){
        x = new int [] {Math.min(x1, x2), Math.max(x1, x2)};
        y = new int [] {Math.min(y1, y2), Math.max(y1, y2)};
    }

    @Override
    public double getArea(){
        return Math.PI * this.getRadiusX() * this.getRadiusY();
    }

    @Override
    public double getPerimeter(){
        return Math.PI * Math.sqrt(2) * (Math.pow(2, this.getRadiusX()) + Math.pow(2, this.getRadiusY()));
    }

    @Override
    public void drawShape(Graphics g) {
        // TODO: set correct color
        g.setColor(Color.green);
        g.fillOval( x[0], y[0], (int)getRadiusX(), (int)getRadiusY() );
    }
}

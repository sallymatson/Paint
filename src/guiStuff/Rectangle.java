package guiStuff;

import java.util.Map;

class Rectangle implements Shape {
    private double x [];
    private double y [];

    public double getHeight(){
        return Math.abs(y[0] - y[1]);
    }

    public double getWidth(){
        return Math.abs(x[0] - x[1]);
    }

    public Rectangle(double x1, double y1, double x2, double y2){
        x = new double [] {x1, x2};
        y = new double [] {y1, y2};
    }

    @Override
    public double getArea(){
        return this.getHeight() * this.getWidth();
    }

    @Override
    public double getPerimeter(){
        return (this.getHeight() * 2) + (this.getWidth() * 2);
    }
}
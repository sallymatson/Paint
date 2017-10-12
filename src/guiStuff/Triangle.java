package guiStuff;

import java.awt.*;

class Triangle implements Shape {
    private int x[];
    private int y[];

    private double calcSideLen(double x1, double x2, double y1, double y2){
        return Math.sqrt(Math.pow(2, x1-x2) + Math.pow(2, y1-y2));
    }

    public double getSide1(){
        return calcSideLen(x[0], x[1], y[0], y[1]);
    }

    public double getSide2(){
        return calcSideLen(x[0], x[2], y[0], y[2]);
    }

    public double getSide3(){
        return calcSideLen(x[2], x[1], y[2], y[1]);
    }

    public Triangle(int x1, int y1, int x2, int y2, int x3, int y3) {
        x = new int[] {x1, x2, x3};
        y = new int[] {y1, y2, y3};
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
    public void drawShape(Graphics g) {
        // TODO: set correct color
        g.setColor(Color.yellow);
        g.fillPolygon( x, y, 3 );
    }
}

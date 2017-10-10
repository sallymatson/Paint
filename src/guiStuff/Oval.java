package guiStuff;

import java.util.Map;

class Oval implements Shape {
    private double x[];
    private double y[];

    public double getRadiusX(){
        return Math.abs(x[0] - x[1]);
    }

    public double getRadiusY(){
        return Math.abs(y[0] - y[1]);
    }

    public Oval(double x1, double y1, double x2, double y2){
        x = new double[] {x1, x2};
        y = new double[] {y1, y2};
    }

    @Override
    public double getArea(){
        return Math.PI * this.getRadiusX() * this.getRadiusY();
    }

    @Override
    public double getPerimeter(){
        return Math.PI * Math.sqrt(2) * (Math.pow(2, this.getRadiusX()) + Math.pow(2, this.getRadiusY()));
    }

}

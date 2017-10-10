package guiStuff;

class Line implements Shape {
    private double[] x;
    private double[] y;

    public double getLength(){
        return Math.sqrt(Math.pow(2, x[0]-x[1]) + Math.pow(2, y[0]-y[1]));
    }

    public Line(double x1, double y1, double x2, double y2){
        x = new double [] {x1, x2};
        y = new double [] {y1, y2};
    }

    @Override
    public double getArea(){
        return 0;
    }

    @Override
    public double getPerimeter(){
        return 0;
    }
}

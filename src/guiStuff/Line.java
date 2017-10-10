package guiStuff;

class Line implements Shape {
    private double[] x1y1;
    private double[] x2y2;

    public double getLength(){
        return Math.sqrt(Math.pow(2, x1y1[0]-x2y2[0]) + Math.pow(2, x1y1[1]-x2y2[1]));
    }


    public Line(double x1, double y1, double x2, double y2){
        x1y1[0] = x1;
        x1y1[1] = y1;
        x2y2[0] = x2;
        x2y2[1] = y2;
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

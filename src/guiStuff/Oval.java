package guiStuff;

import java.util.Map;

class Oval implements Shape {
    private double height;
    private double width;

    public double getHeight(){
        return this.height;
    }

    public double getWidth(){
        return this.width;
    }

    private void setHeight(double h) throws IllegalArgumentException {
        if (h < 0) {
            throw new IllegalArgumentException("guiStuff.Rectangle height can't be less than 0.");
        }
        this.height = h;
    }

    private void setWidth(double w) throws IllegalArgumentException {
        if (w < 0) {
            throw new IllegalArgumentException("guiStuff.Rectangle width can't be less than 0.");
        }
        this.width = w;
    }

    public Oval(Map<String, Double> args){
        this.setHeight((Double)args.get("height"));
        this.setWidth((Double)args.get("width"));
    }

    @Override
    public double getArea(){
        return Math.PI * this.getHeight()/2 * this.getWidth()/2;
    }

    @Override
    public double getPerimeter(){
        return Math.PI * Math.sqrt(2) * (Math.pow(2, (this.getHeight()/2)) + Math.pow(2, (this.getWidth()/2)));
    }
}

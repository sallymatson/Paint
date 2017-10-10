package guiStuff;

class Line implements Shape {
    private double length;

    public double getLength(){
        return this.length;
    }

    private void setLength(double ln) throws IllegalArgumentException {
        if (ln < 0) {
            throw new IllegalArgumentException("guiStuff.Line length can't be less than 0.");
        }
        this.length = ln;
    }

    public Line(double sz){
        this.setLength(sz);
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

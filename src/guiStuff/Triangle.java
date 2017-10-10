package guiStuff;

class Triangle implements Shape {
    private double side1;
    private double side2;
    private double side3;

    public double getSide1(){
        return this.side1;
    }

    public double getSide2(){
        return this.side2;
    }

    public double getSide3(){
        return this.side3;
    }

    private void setSide1(double sz) throws IllegalArgumentException {
        if (sz < 0) {
            throw new IllegalArgumentException("guiStuff.Triangle side size can't be less than 0.");
        }
        this.side1 = sz;
    }

    private void setSide2(double sz) throws IllegalArgumentException {
        if (sz < 0) {
            throw new IllegalArgumentException("guiStuff.Triangle side size can't be less than 0.");
        }
        this.side2 = sz;
    }

    private void setSide3(double sz) throws IllegalArgumentException {
        if (sz < 0) {
            throw new IllegalArgumentException("guiStuff.Triangle side size can't be less than 0.");
        }
        this.side3 = sz;
    }

    public Triangle(double s1, double s2, double s3) throws IllegalArgumentException {
        // checks to make sure that the longest isn't longer than the two shorter sides added together
        double max = Math.max(Math.max(s1,s2),s3);
        if (max * 2 > s1 + s2 + s3){
            throw new IllegalArgumentException("guiStuff.Triangle side sizes do not form a triangle.");
        }

        this.setSide1(s1);
        this.setSide2(s2);
        this.setSide3(s3);
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
}

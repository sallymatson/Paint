package guiStuff;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.io.IOException;
import java.util.List;

public class ShapeList {
    private List<Shape> sl = new ArrayList<Shape>();
    private double totalArea;
    private double totalPerimeter;

    void prepareShapes(String filename) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(filename));
            for (String line : lines) {
                String[] words = line.split(" ");
                // creates a square:
                if (words[0].equals("line")) {
                    if (words.length != 2) {
                        System.out.println("Wrong amount of args for square.");
                    } else {
                        Line shape = new Line(Double.parseDouble(words[1]));
                        sl.add(shape);
                    }
                }
                // create a circle:
                else if (words[0].equals("oval")) {
                    if (words.length != 3) {
                        System.out.println("Wrong amount of args for oval.");
                    } else {
                        Map<String, Double> map = new HashMap<String, Double>();
                        map.put("height", Double.parseDouble(words[1]));
                        map.put("width", Double.parseDouble(words[2]));
                        Oval shape = new Oval(map);
                        sl.add(shape);
                    }
                }
                // create a triangle:
                else if (words[0].equals("triangle")) {
                    if (words.length != 4) {
                        System.out.println("Wrong amount of args for triangle.");
                    } else {
                        double side_1 = Double.parseDouble(words[1]);
                        double side_2 = Double.parseDouble(words[2]);
                        double side_3 = Double.parseDouble(words[3]);
                        Triangle shape = new Triangle(side_1, side_2, side_3);
                        sl.add(shape);
                    }
                }
                // create a rectangle:
                else if (words[0].equals("rectangle")) {
                    if (words.length != 3) {
                        System.out.println("Wrong amount of args for rectangle.");
                    } else {
                        Map<String, Double> map = new HashMap<String, Double>();
                        map.put("height", Double.parseDouble(words[1]));
                        map.put("width", Double.parseDouble(words[2]));
                        Rectangle shape = new Rectangle(map);
                        sl.add(shape);
                    }
                }
                // not a blank line, thus an invalid shape:
                else if (!words[0].equals("")){
                    // return out
                    System.out.println(words[0] + ": Invalid shape type.");
                }
            }
        }
        catch (NumberFormatException e) {
            System.out.println(e.toString());
        }
        catch (IllegalArgumentException e) {
            System.out.println(e.toString());
        }
        catch (IOException exception){
            System.out.println(exception.toString());
        }
        catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(e.toString());
        }

        // add to total Area and total Perimeter
        this.calculateTotalArea();
        this.calculateTotalPerimeter();
    }

    private void calculateTotalArea(){
        this.totalArea = 0.0;
        for (Shape shape : sl){
            this.totalArea += shape.getArea();
        }
    }

    private void calculateTotalPerimeter() {
        this.totalPerimeter = 0.0;
        for (Shape shape : sl){
            this.totalPerimeter += shape.getPerimeter();
        }
    }

    public double getTotalArea() {
        return totalArea;
    }

    public double getTotalPerimeter() {
        return totalPerimeter;
    }

}
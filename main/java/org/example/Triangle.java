package org.example;

public class Triangle implements Shape {
    private String fillColor;
    private String borderColor;
    private double side1;
    private double side2;
    private double side3;
    private double height;

    public Triangle (String fillColor, String borderColor,double side1,double side2,
                     double side3){
        this.fillColor = fillColor;
        this.borderColor = borderColor;
        this.side1 = side1;
        this.side2 = side2;
        this.side3 = side3;
        this.height = height;
    }
    @Override
    public String getFillColor() {
        return fillColor;
    }
    @Override
    public String getBorderColor(){
        return borderColor;
    }
    @Override
    public double calculatePerimeter(){
        return side1 + side2 + side3;
    }
    @Override
    public double calculateArea(){
        return 1/2 * side1 * height;
    }
}

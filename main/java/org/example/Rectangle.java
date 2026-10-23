package org.example;

public class Rectangle implements Shape {
    private String fillColor;
    private  String borderColor;
    private double lenght;
    private double width;

    public Rectangle (String fillColor,String borderColor, double lenght,double width) {
        this.fillColor = fillColor;
        this.borderColor = borderColor;
        this.lenght = lenght;
        this.width = width;
    }
   @Override
    public String getFillColor() {
        return fillColor;
   }
   @Override
    public String getBorderColor() {
        return borderColor;
   }
   @Override
    public double calculatePerimeter(){
        return (lenght * width ) * 2;
   }
   @Override
    public double calculateArea(){
        return lenght * width;
   }
}

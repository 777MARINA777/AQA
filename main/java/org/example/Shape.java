package org.example;

public interface Shape {
    String getFillColor();

    String getBorderColor();

    double calculatePerimeter();

    double calculateArea();

    default void printInfo() {
        System.out.println("Цвет заливки: " + getFillColor() +
                           "цвет границы: " + getBorderColor() +
                           "периметр: " + calculatePerimeter() +
                           "площадь: " + calculateArea());
    }
}

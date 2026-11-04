package org.example;

public class TriangleArea {
    public static double Area(double base, double height) {
        if (base <= 0 || height <= 0) {
            throw new IllegalArgumentException(" Стороны должны быть больше нуля");
        }
        return (base * height) / 2;
    }
}


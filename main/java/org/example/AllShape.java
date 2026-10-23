package org.example;

public class AllShape {
    public static void main(String[] args) {
        Shape[] shapes = {
                new Circle("Коричневый, ", "Чёрный, ", 3.0),
                new Rectangle("Зеленый, ", "Синий, ", 4.0, 9.0),
                new Triangle("Красный, ", "Чёрный, ", 2.0, 3.0, 3.0)
        };
        for (Shape shape : shapes) {
            shape.printInfo();
        }
    }
}

package org.example;

import java.util.Arrays;

public class LessonOne {
    public static void main(String[] args) {
        printTreeWords();
        checkSumSing();
        printColor();
        compareNumbers();
        taskFive(3, 5);
        method6(-1);
        isnegative(-1);
        printString("Привет", 4);
        leapYear(2001);
        method10();
        method11();
        method12();
        method13();
        method14(3, 5);
    }

    public static void printTreeWords() {
        System.out.println("Orange");
        System.out.println("Banana");
        System.out.println("Apple");
    }

    public static void checkSumSing() {
        int a = 3;
        int b = 2;
        int sum = a + b;
        if (sum >= 0) {
            System.out.println("Сумма положительная");
        } else {
            System.out.println("Сумма отрицательная");
        }
    }

    public static void printColor() {
        int value = 25;
        if (value <= 0) {
            System.out.println("Красный");
        } else if (value <= 100) {
            System.out.println("Желтый");
        } else {
            System.out.println("Зеленый");
        }
    }

    public static void compareNumbers() {
        int a = 5;
        int b = 3;
        if (a >= b) {
            System.out.println("a >= b");
        } else {
            System.out.println("a < b");
        }
    }

    public static boolean taskFive(int a, int b) {
        int sum = a + b;
        return sum >= 10 && sum <= 20;
    }

    public static void method6(int b) {
        if (b >= 0) {
            System.out.println("Число положительное");
        } else {
            System.out.println("Число отрицательное");
        }
    }

    public static boolean isnegative(int number) {
        return number < 0;
    }

    public static void printString(String text, int count) {
        for (int i = 0; i < count; i++) {
            System.out.println(text);
        }
    }

    public static boolean leapYear(int year) {
        return year % 100 != 0 && year % 4 == 0 || year % 400 == 0;
    }

    public static void method10() {
        int[] array = {1, 0, 1, 0, 0};
        for (int i = 0; i < array.length; i++) {
            array[i] = (array[i] > 0) ? 0 : 1;
        }
        System.out.println(Arrays.toString(array));
    }

    public static void method11() {
        int[] array = new int[100];
        for (int i = 0; i < array.length; i++) {
            array[i] = i + 1;
        }
        for (int value : array) {
            System.out.println(value + " ");
        }
    }

    public static void method12() {
        int[] s = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        for (int i = 0; i < s.length; i++) {
            if (s[i] < 6) {
                s[i] = s[i] * 2;
            }
        }
        System.out.println(Arrays.toString(s));
    }

    public static void method13() {
        int size = 2;
        int[][] matrix = new int[size][size];
        for (int i = 0; i < size; i++) {
            matrix[i][i] = 1;
            matrix[i][size - 1 - i] = 1;
        }
        for (int[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
    }

    public static int[] method14(int len, int initialvalue) {
        int[] arr = new int[len];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = initialvalue;
        }
        return arr;
    }
}
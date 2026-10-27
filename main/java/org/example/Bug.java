package org.example;

public class Bug {
    static class MyArraySizeException extends Exception {
        public MyArraySizeException(String message) {
            super(message);
        }
    }

    static class MyArrayDataException extends Exception {
        public MyArrayDataException(String message) {
            super(message);
        }
    }

    public static void main(String[] args) {
        String[][] correctArray = {
                {"1", "2", "3", "4"},
                {"5", "6", "7", "8"},
                {"9", "10", "11", "12"},
                {"13", "14", "15", "16"}
        };

        String[][] incorrectDateArray = {
                {"1", "$", "3", "4"},
                {"5", "6", "7", "8"},
                {"9", "10", "11", "12"},
                {"13", "14", "15", "16"}
        };

        String[][] incorrectSizeArray = {
                {"1", "2"},
                {"3", "4"},
                {"5", "6"},
                {"7", "8"}
        };

        try {
            System.out.println("Сумма правильного массива: " + sumArrayElements(correctArray));
            System.out.println("Сумма массива с некорректными данными: " + sumArrayElements(incorrectDateArray));
            System.out.println("Сумма массива с неправильным размером: " + sumArrayElements(incorrectSizeArray));
        } catch (MyArrayDataException | MyArraySizeException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }

        try {
            generateArrayIndexOutOfBounds();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(" Найдено исключение ArrayIndexOutOfBounds: " + e.getMessage());
        }
    }

    public static int sumArrayElements(String[][] array) throws MyArraySizeException, MyArrayDataException {
        if (array.length != 4) {
            throw new MyArraySizeException("Массив должен быть размером 4*4, получено: "
                    + array.length + " строк");

        }
        for (String[] row : array) {
            if (array.length != 4) {
                throw new MyArraySizeException("Массив должен быть 4*4, найдена строка длиной: "
                        + row.length + " строк");
            }
        }

        int sum = 0;
        StringBuilder errors = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                try {
                    sum += Integer.parseInt(array[i][j]);
                } catch (NumberFormatException e) {
                    if (errors.length() > 0) {
                        errors.append(", ");
                    }
                    errors.append("[").append(i).append("][").append(j).append("]");
                }
            }
        }

        if (errors.length() > 0) {
            throw new MyArrayDataException("Неккоректные данные: " + errors.toString());
        }
        return sum;
    }

    public static void generateArrayIndexOutOfBounds() {
        int[] arr = new int[5];
        int value = arr[10];
    }
}


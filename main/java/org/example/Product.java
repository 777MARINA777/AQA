package org.example;

public class Product {
    private String name;
    private int productionDate;
    private String manufacturer;
    private String country;
    private int price;
    private boolean bookingStatus;

    public Product(String name, int productionDate, String manufacturer,
                   String country, int price, boolean bookingStatus) {
        this.name = name;
        this.productionDate = productionDate;
        this.manufacturer = manufacturer;
        this.country = country;
        this.price = price;
        this.bookingStatus = bookingStatus;
    }

    public static void main(String[] args) {
        Product food = new Product("RoyalCanin", 2013,
                "Royal", "France",
                5000, true);
        System.out.println("Название: " + food.name);
        System.out.println("Дата производства: " + food.productionDate);
        System.out.println("Производитель: " + food.manufacturer);
        System.out.println("Страна происхождения: " + food.country);
        System.out.println("Цена: " + food.price);
        System.out.println("Сотояние бронирования покупателем: " + food.bookingStatus);
    }
}

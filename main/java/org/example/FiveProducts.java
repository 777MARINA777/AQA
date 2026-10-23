package org.example;

public class FiveProducts {
    public static void main(String[] args) {
        Product[] lipstick = new Product[5];
        lipstick[0] = new Product("Dior", 2010,
                "LVMH", "France", 1000, true);

        lipstick[1] = new Product("Belor Design", 2019,
                "Belor", "Беларусь", 300, true);

        lipstick[2] = new Product("L'Oreal Paris", 2000,
                "L'Oreal", "France", 2000, true);

        lipstick[3] = new Product("Dior Kiss Gloss", 2000,
                "LVMH", "France", 1500, false);

        lipstick[4] = new Product("Vivienne Sabo", 2019,
                "VS", "France", 1100, true);
        for (Product l : lipstick) {
            System.out.println(l);
        }
    }
}

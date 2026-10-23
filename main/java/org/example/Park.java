package org.example;

public class Park {
    private String name;

    public Park(String name) {
        this.name = name;
    }

    public class Attraction {
        private String attractionName;
        private int price;
        private String workingHours;

        public Attraction(String attractionName, int price, String workingHours) {
            this.attractionName = attractionName;
            this.price = price;
            this.workingHours = workingHours;
        }

        public void printInfo() {
            System.out.println("Атракцион: " + attractionName);
            System.out.println("Цена: " + price);
            System.out.println("Время работы: " + workingHours);
        }
    }
        public static void main(String[] args) {
            Park park = new Park("Дисней");
            Park.Attraction ferrisWeel = park.new Attraction("Колесо обозрения",
                    5, "10:00 - 19:00");
            Park.Attraction rollerCoaster = park.new Attraction("Американские горки",
                    9, "10:00 - 19:00");
            ferrisWeel.printInfo();
            rollerCoaster.printInfo();
        }
    }

package org.example;

public class Animal {
    protected String name;
    protected static int countAnimal =0;

    public Animal (String name) {
        this.name = name;
        countAnimal++;
    }
    public static int getCountAnimal() {
        return countAnimal;
    }
    public void run (int range){
        System.out.println("Может бегать " + range + " м");
    }
    public void swim (int range) {
        System.out.println("Может плыть " + range + "  м");
    }
    public  String getName() {
        return name;
    }
}

package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Cat cat1 = new Cat("Машка");
        Cat cat2 = new Cat("Акелла");
        Dog dog1 = new Dog("Бим");
        Dog dog2 = new Dog("Лана");

        dog1.run(110);
        dog1.swim(5);
        dog2.run(705);
        dog2.swim(13);

        cat1.run(115);
        cat1.swim(2);
        cat1.isHungry(3);
        cat2.run(210);
        cat2.swim(2);
        cat2.isHungry(0);
        System.out.println("Акелла " + cat2.isHungry());

        System.out.println("Всего животных: " + Animal.getCountAnimal());
        System.out.println("Котиков: " + Cat.getCountCat());
        System.out.println("Собак: " + Dog.getCountDog());
    }
}
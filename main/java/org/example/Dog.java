package org.example;

public class Dog extends Animal {
    protected String name;
    protected static int countDog = 0;

    public Dog(String name) {
        super(name);
        countDog++;
    }

    @Override
    public void run(int range) {
        if (range > 500) {
            System.out.println(getName() + " больше не бегает");
        } else {
            System.out.println(getName() + " пробежал " + range + " м");
        }
    }

    @Override
    public void swim(int range) {
        if (range > 10) {
            System.out.println(getName() + " больше не плавает");
        } else {
            System.out.println(getName() + " пробежал " + range + " м");
        }
    }

    public static int getCountDog() {
        return countDog;
    }
}

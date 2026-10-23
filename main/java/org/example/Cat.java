package org.example;

class Cat extends Animal {
    protected String name;
    protected boolean isHungry = false;
    protected static int countCat = 0;

    public Cat(String name) {
        super(name);
        countCat++;
    }

    @Override
    public void run(int range) {
        if (range > 200) {
            System.out.println(getName() + " больше не бегает");
        } else {
            System.out.println(getName() + " пробежал " + range + " м");
        }
    }

    @Override
    public void swim(int range) {
        System.out.println(getName() + " плавать не умеет");
    }

    public void isHungry(int amount) {
        if (amount > 0) {
            isHungry = false;
            System.out.println(getName() + " поел " + amount + " порций");
        } else {
            System.out.println(getName() + " не смог поесть");
        }
    }

    public boolean isHungry() {
        return isHungry();
    }

    public static int getCountCat() {
        return countCat;
    }
}

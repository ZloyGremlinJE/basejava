package ru.javawebinar.basejava.Lambda;

public class TestGeneric {
    public static void main(String[] args) {
        System.out.println(getObjectType(new Cat()));
        System.out.println(getObjectType(new Tiger()));
        System.out.println(getObjectType(new Lion()));
        System.out.println(getObjectType(new Bull()));
        System.out.println(getObjectType(new Cow()));
        System.out.println(getObjectType(new Animal()));
    }

    public static <T>String getObjectType(T o) {

        if (o.getClass().getSimpleName().equals("Cat")) return "Кот";
        if (o.getClass().getSimpleName().equals("Tiger")) return "Тигр";
        if (o.getClass().getSimpleName().equals("Lion")) return "Лев";
        if (o.getClass().getSimpleName().equals("Bull")) return "Бык";
        if (o.getClass().getSimpleName().equals("Cow")) return "Корова";
        return "Животные";
    }

    public static class Cat extends Animal   //<--Классы наследуются!!
    {
    }

    public static class Tiger extends Cat {
    }

    public static class Lion extends Cat {
    }

    public static class Bull extends Animal {
    }

    public static class Cow extends Animal {
    }

    public static class Animal {
    }
}

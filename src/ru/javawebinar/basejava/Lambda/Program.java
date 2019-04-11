package ru.javawebinar.basejava.Lambda;

public class Program {
    public static void fire(){
        System.out.println("FIRE!!!");

    }
    public static void main(String[] args) {
        Switcher sw = new Switcher();
        Lamp lamp = new Lamp();
        Radio radio = new Radio();

        sw.addElectricityListener(lamp);
        sw.addElectricityListener(radio);
//        sw.addElectricityListener(() -> System.out.println("FIRE!!!!!"));
        sw.addElectricityListener(Program::fire);
        sw.switchOn();
    }
}

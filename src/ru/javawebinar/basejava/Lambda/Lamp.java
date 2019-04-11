package ru.javawebinar.basejava.Lambda;

public class Lamp implements ElectricityConsumer{
    @Override
    public void electricityOn() {
        lightOn();
    }

    public void lightOn(){
        System.out.println("Лампа зажглась!");
    }
}

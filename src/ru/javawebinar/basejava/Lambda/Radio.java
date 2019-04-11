package ru.javawebinar.basejava.Lambda;

public class Radio implements ElectricityConsumer {
    @Override
    public void electricityOn() {
        playMusic();
    }

    public  void playMusic(){
        System.out.println("Radio plays!");
    }
}

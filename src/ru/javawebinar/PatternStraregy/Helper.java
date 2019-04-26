package ru.javawebinar.PatternStraregy;

public class Helper {
    private  final String connectionFactory;

    public Helper(String connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public <Element> Element doSomething(StrategyInterface<Element> strategyInterface, String s){
        String message = "Общий параметр из хелпера для дальнейшей обработки";
       String ps = s + " | " + message;
        System.out.println(strategyInterface.prepareRequest(ps));
       return strategyInterface.prepareRequest(ps);
    }
}

package ru.javawebinar.PatternStraregy;

public class Base {
    public final Helper helper;

    public String value1;
    public Integer value2;
    public Boolean value3;

    public Base() {
        helper = new Helper("connectToBase!") ;
    }

    public void test1(){
        helper.doSomething(new StrategyInterface<String>() {
            @Override
            public String prepareRequest(String ps) {
                return ps;
            }
        },"Тест1");
    }
    public void test2(){
        helper.doSomething(new StrategyInterface<String>() {
            @Override
            public String prepareRequest(String ps) {
                return ps;
            }
        }, "Тест2");
    }

    public static void main(String[] args) {
        Base base = new Base();
        base.test1();
        base.test2();
    }
}

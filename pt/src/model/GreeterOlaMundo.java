package model;

public class GreeterOlaMundo implements Greeter {

    @Override
    public void perform() {
        System.out.println("Hello, World!");
    }

}

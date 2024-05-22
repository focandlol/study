package generic.ex4;

import generic.animal.Animal;

public class ComplexBox <T extends Animal>{

    private T animal;

    public void set(T animal){
        this.animal = animal;
    }

    public <T> T printAndReturn(T s){
        System.out.println("animal.getClass().getName() = " + animal.getClass().getName());
        System.out.println("s = " + s.getClass().getName());
        return s;
    }
}

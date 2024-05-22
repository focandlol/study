package generic.ex4;

import generic.animal.Animal;
import generic.animal.Cat;
import generic.animal.Dog;

public class MethodMain3 {

    public static void main(String[] args) {

        Dog dog = new Dog("dog1", 100);
        Cat cat = new Cat("cat1", 200);

        ComplexBox<Dog> hospital = new ComplexBox<>();
        hospital.set(dog);

        Cat returnCat = hospital.printAndReturn(cat);
        System.out.println("returnCat = " + returnCat);
    }
}

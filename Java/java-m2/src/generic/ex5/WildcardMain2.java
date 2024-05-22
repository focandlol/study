package generic.ex5;

import generic.animal.Animal;
import generic.animal.Cat;
import generic.animal.Dog;

public class WildcardMain2 {

    public static void main(String[] args) {
        Box<Object> objBox = new Box<>();
        Box<Animal> animalBox = new Box<>();
        Box<Dog> dogBox = new Box<>();
        Box<Cat> catBNox = new Box<>();

        writeBox(objBox);
        writeBox(animalBox);
        //writeBox(dogBox); //하한이 Animal
    }

    static void writeBox(Box<? super Animal> box){
        box.set(new Dog("dog1",100));
    }
}

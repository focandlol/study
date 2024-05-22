package generic.ex3;

import generic.animal.Cat;
import generic.animal.Dog;

public class AnimalHospitalMain {

    public static void main(String[] args) {
        DogHospital dogHospital = new DogHospital();
        CatHospital catHospital = new CatHospital();

        Dog dog = new Dog("멍1", 100);
        Cat cat = new Cat("냥1", 300);

        dogHospital.set(dog);
        dogHospital.checkUp();

        catHospital.set(cat);
        catHospital.checkUp();

        Dog biggerDog = dogHospital.bigger(new Dog("멍2", 300));
        System.out.println("biggerDog = " + biggerDog);

    }
}

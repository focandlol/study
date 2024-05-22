package generic.test.ex3;

import generic.animal.Cat;
import generic.animal.Dog;

public class AnimalHospitalMainV3 {

    public static void main(String[] args) {
        AnimalHospitalV3<Dog> dogHospital = new AnimalHospitalV3();
        AnimalHospitalV3<Cat> catHospital = new AnimalHospitalV3();

        Dog dog = new Dog("멍1", 100);
        Cat cat = new Cat("냥1", 300);

        dogHospital.set(dog);
        dogHospital.checkUp();

        catHospital.set(cat);
        catHospital.checkUp();

        //dogHospital.set(cat); //다른 타입 입력 : 컴파일 오류

        Dog biggerDog = dogHospital.bigger(new Dog("멍2", 300));
        System.out.println("biggerDog = " + biggerDog);

    }
}

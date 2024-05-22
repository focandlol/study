package generic.ex3;

import generic.animal.Cat;
import generic.animal.Dog;

public class AnimalHospitalMainV1 {

    public static void main(String[] args) {
        AnimalHospitalV1 dogHospital = new AnimalHospitalV1();
        AnimalHospitalV1 catHospital = new AnimalHospitalV1();

        Dog dog = new Dog("멍1", 100);
        Cat cat = new Cat("냥1", 300);

        dogHospital.set(dog);
        dogHospital.checkUp();

        catHospital.set(cat);
        catHospital.checkUp();

        //dogHospital.set(cat); //매개변수 체크 실패 : 컴파일 오류가 발생하지 않음

        Dog biggerDog = (Dog)dogHospital.bigger(new Dog("멍2", 300));
        System.out.println("biggerDog = " + biggerDog);

    }
}

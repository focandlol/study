package generic.ex4;

import generic.animal.Animal;

public class AnimalMethod {

    public static <T extends Animal> void checkUp(T t){
        System.out.println("동물 이름 :  " + t.getName());
        System.out.println("동물 사이즈 : " + t.getSize());
        t.sound();
    }

    public static <t extends Animal> t bigger(t t1, t t2){
        return t1.getSize() > t2.getSize() ? t1 : t2;
    }
}

package generic.ex1;

public class BoxMain3 {

    public static void main(String[] args) {
        GenericBox<Integer> integerBox = new GenericBox<Integer>();
        integerBox.set(10);
        Integer i = integerBox.get();
        System.out.println("i = " + i);

        GenericBox<String> stringBox = new GenericBox<>();
        stringBox.set("hello");
        String s = stringBox.get();
        System.out.println("s = " + s);
    }
}

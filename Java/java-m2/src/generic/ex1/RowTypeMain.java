package generic.ex1;

public class RowTypeMain {

    public static void main(String[] args) {
        GenericBox integerBox = new GenericBox();
        //<> 지정 안할 시 타입 매개변수 Object로 사용됨
        //object로 사용하고 싶을 경우에도 GenericBox<Object> objectBox = new GenericBox()로 사용할 것

        integerBox.set(10);
        Integer i = (Integer) integerBox.get();
        System.out.println("i = " + i);
    }
}

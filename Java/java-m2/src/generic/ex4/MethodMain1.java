package generic.ex4;



public class MethodMain1 {

    public static void main(String[] args) {
        Integer i =10;
        Object object = GenericMethod.objMethod(i);

        //타입 인자(Type Argument) 명시적 전달
        System.out.println("명시적 타입 인자 전달");
        Integer result = GenericMethod.<Integer>genericMethod(i);
        System.out.println("result = " + result);

        Integer integerValue = GenericMethod.<Integer>numberMethod(i);
        System.out.println("integerValue = " + integerValue);

        System.out.println("타입 추론");
        Integer result2 = GenericMethod.genericMethod(i);
        Double doubleValue2 = GenericMethod.genericMethod(10.0);
    }
}

package collection.list;

public class BatchProcessorMain {

    public static void main(String[] args) {

        MyArrayList<Integer> list = new MyArrayList<>();
        MyLinkedList<Integer> list2 = new MyLinkedList<>();
        BatchProcessor processor = new BatchProcessor(list2);
        processor.logic(5000000);
    }
}

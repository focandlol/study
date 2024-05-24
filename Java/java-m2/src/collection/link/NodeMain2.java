package collection.link;

public class NodeMain2 {

    public static void main(String[] args) {

        Node first = new Node("a");
        first.next = new Node("b");
        first.next.next = new Node("c");

        System.out.println("연결된 노드 출력하기");
        System.out.println(first);
        System.out.println(first.next);
    }
}

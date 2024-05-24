package collection.link;

import javax.lang.model.SourceVersion;

public class NodeMain3 {

    public static void main(String[] args) {
        Node first = new Node("a");
        first.next = new Node("b");
        first.next.next = new Node("c");

        System.out.println(first);

        //모든 노드 탐색
        System.out.println("모든 노드 탐색하기");
        printAll(first);

        //마지막 노드 조회
        Node lastNode = getLastNode(first);
        System.out.println("lastNode = " + lastNode);

        //특정 index의 노드 조회하기
        int index = 2;
        Node node = getNode(first, index);
        System.out.println("Indexnode = " + node);

        //데이터 추가하기
        System.out.println("데이터 추가하기");
        add(first,"D");
        System.out.println(first);
    }

    private static void printAll(Node node) {
        Node x = node;

        while(x!=null){
            System.out.println(x.item);
            x = x.next;
        }
    }

    private static Node getLastNode(Node node) {
        Node x = node;

        while(x.next != null){
            x = x.next;
        }
        return x;
    }

    private static Node getNode(Node node, int index) {
        Node x = node;
        for(int i = 0; i<index; i++){
            x = x.next;
        }
        return x;
    }

    private static void add(Node first, String d) {
        Node lastNode = getLastNode(first);
        lastNode.next = new Node(d);
    }
}

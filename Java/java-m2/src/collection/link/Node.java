package collection.link;

public class Node {

    Object item;
    Node next;

    public Node(Object item){
        this.item = item;
    }

    //idea가 자동으로 만들어준 toString()
//    @Override
//    public String toString() {
//        return "Node{" +
//                "item=" + item +
//                ", next=" + next +
//                '}';
//    }

    //직접 만든 toString()
    public String toString(){
        StringBuilder sb = new StringBuilder();
        Node x = this;
        sb.append("[");
        while (x != null){
            sb.append(x.item);
            if (x.next != null) {
                sb.append("->");
            }
            x = x.next;
        }
        sb.append("]");
        return sb.toString();
    }
}

package linkedList;

public class Practice1 {
    public static LinkedList removeDup(LinkedList list){
        LinkedList newList = new LinkedList();

        Node cur = list.head;
        while(cur != null){
            if(newList.findData(cur.data) == false){
                newList.addData(cur.data);
            }
            cur = cur.next;
        }
        return newList;
    }
    public static void main(String[] args) {
        LinkedList linkedList = new LinkedList();
        linkedList.addData(1);
        linkedList.addData(3);
        linkedList.addData(3);
        linkedList.addData(2);
        linkedList.addData(1);
        linkedList.addData(5);
        linkedList.addData(4);
        linkedList.addData(1);

        LinkedList linkedList1 = removeDup(linkedList);
        linkedList1.showData();
    }
}

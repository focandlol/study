package linkedList;

public class Practice2 {
    public static void main(String[] args) {
        LinkedList linkedList = new LinkedList();
        linkedList.addData(1);
        linkedList.addData(3);
        linkedList.addData(5);
        linkedList.addData(3);
        linkedList.addData(1);
        System.out.println(checkPalindrome(linkedList));

        LinkedList linkedList2 = new LinkedList();
        linkedList2.addData(2);
        linkedList2.addData(2);
        linkedList2.addData(3);
        linkedList2.addData(3);
        linkedList2.addData(1);
        System.out.println(checkPalindrome(linkedList2));
    }

    private static boolean checkPalindrome(LinkedList list) {
        Node cur = list.head;
        int count = 1;
        while(cur.next != null) {
            cur = cur.next;
            count++;
        }
        Node left = list.head;
        Node right = cur;
        Node prevRight = cur;

        int cnt=1;
        while(true){
            if(cnt > count/2){
                break;
            }
            if(left.data == right.data){
                left = left.next;
                right = left;
                while(right.next != prevRight) {
                    right = right.next;
                }
            }else{
                return false;
            }
            cnt++;

        }
        return true;
    }
}

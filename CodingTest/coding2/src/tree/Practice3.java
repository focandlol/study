package tree;

import java.util.LinkedList;
import java.util.Queue;

class Node2{
    char data;
    Node2 left;
    Node2 right;
    Node2 parent;

    public Node2(char data, Node2 left, Node2 right, Node2 parent) {
        this.data = data;
        this.left = left;
        this.right = right;
        this.parent = parent;
    }
}
class BinaryTree3{
    Node2 head;
    public BinaryTree3(char[] arr){
        Node2[] nodes = new Node2[arr.length];
        for(int i=0; i<arr.length; i++){
            nodes[i] = new Node2(arr[i],null,null,null);
        }

        for(int i=0; i<arr.length; i++){
            int left = i * 2 + 1;
            int right = i * 2 + 2;

            if(left < arr.length){
                nodes[i].left = nodes[left];
                nodes[left].parent = nodes[i];
            }

            if(right < arr.length){
                nodes[i].right = nodes[right];
                nodes[right].parent = nodes[i];
            }
        }
        this.head = nodes[0];
    }

    public Node2 searchNode(char data){
        Queue<Node2> queue = new LinkedList<Node2>();
        queue.add(head);

        while(!queue.isEmpty()){
            Node2 node = queue.poll();
            if(node.data == data){
                return node;
            }
            if(node.left != null) queue.add(node.left);
            if(node.right != null) queue.add(node.right);
        }
        return null;
    }

    public Integer checkSize(char data){
        Node2 cur = searchNode(data);
        Queue<Node2> queue = new LinkedList<Node2>();
        queue.add(cur);
        int count = 1;

        while(!queue.isEmpty()){
            Node2 node = queue.poll();
            if(node.left != null) {
                queue.add(node.left);
                count++;
            }
            if(node.right != null) {
                queue.add(node.right);
                count++;
            }
        }
        return count;
    }
}
public class Practice3 {
    public static void main(String[] args) {
        char[] arr = new char[10];
        for(int i=0; i<arr.length; i++){
            arr[i] = (char)('A' + i);
        }
        BinaryTree3 binaryTree3 = new BinaryTree3(arr);

        // root node
        System.out.println(binaryTree3.head.data);

        // B's child node
        Node2 node = binaryTree3.searchNode('B');
        if(node != null){
            System.out.println("b.left " + node.left.data);
            System.out.println("b.right " + node.right.data);
        }
       
        //F's parent
        Node2 F = binaryTree3.searchNode('F');
        System.out.println("F.parent " + F.parent.data);

        //leaf node
        System.out.print("leaf node : ");
        for(int i=0; i<arr.length; i++){
            Node2 cur = binaryTree3.searchNode((char)('A' + i));
            if(cur.left == null && cur.right == null){
                System.out.print(cur.data + " ");
            }
        }
        System.out.println();

        //E's depth
        Node2 E = binaryTree3.searchNode('E');
        int count = 0;
        while(E.parent != null){
            count++;
            E = E.parent;
        }
        System.out.println(count);

        //level2 node
        System.out.print("level2 node : ");
        for(int i=0; i<arr.length; i++){
            Node2 cur = binaryTree3.searchNode((char)('A' + i));
            Node2 n = cur;
            count = 0;
            while(n.parent != null){
                n = n.parent;
                count++;
            }
            if(count == 2){
                System.out.print(cur.data + " ");
            }
        }
        System.out.println();

        //height
        int maxCount = Integer.MIN_VALUE;
        for(int i=0; i<arr.length; i++){
            Node2 cur = binaryTree3.searchNode((char)('A' + i));
            count = 0;
            while(cur.parent != null){
                cur = cur.parent;
                count++;
            }
            if(count > maxCount){
                maxCount = count;
            }
        }
        System.out.println(maxCount);

        //B's size
        System.out.println(binaryTree3.checkSize('B'));
    }
}

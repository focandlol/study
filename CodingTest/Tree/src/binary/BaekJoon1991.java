package binary;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon1991 {
    static Node root;
    static int n;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        for(int i=1; i<=n; i++){
            st = new StringTokenizer(br.readLine());
            String parent = st.nextToken();
            String left = st.nextToken();
            String right = st.nextToken();

            createNode(parent,left,right);

        }

        pre(root);
        System.out.println();
        in(root);
        System.out.println();
        post(root);
//        System.out.println();
//        char a = 0 + 'A';
//        int i = 'B' - 'A';
//        System.out.println(i);
//        System.out.print(a);
    }
    //전위 순회
    static void pre(Node node){
        if(node == null){
            return;
        }
        System.out.print(node.node);
        pre(node.left);
        pre(node.right);
    }

    //중위 순회
    static void in(Node node){
        if(node == null){
            return;
        }
        in(node.left);
        System.out.print(node.node);
        in(node.right);
    }

    //후위 순회
    static void post(Node node){
        if(node == null){
            return;
        }
        post(node.left);
        post(node.right);
        System.out.print(node.node);
    }

    //노드 생성
    static void createNode(String parent, String left, String right){
        //'A'가 입력되면 루트 노드 생성
        if(parent.equals("A")) {
            root = new Node(parent);
            if (!left.equals(".")) {
                root.left = new Node(left);
            }
            if (!right.equals(".")) {
                root.right = new Node(right);
            }
        }else{
            searchNode(root,parent,left,right);
        }
    }

    //루트 노드 아닐시 노드 생성
    static void searchNode(Node node, String parent, String left, String right){
        if(node == null){
            return;
        }
        //현재 노드와 입력된 노드가 같다면 left right 삽입
        if(node.node.equals(parent)){
            if(!left.equals(".")){
                node.left = new Node(left);
            }
            if(!right.equals(".")){
                node.right = new Node(right);
            }
        }
        searchNode(node.left,parent,left,right);
        searchNode(node.right,parent,left,right);
    }

    static class Node{
        String node;
        Node left;
        Node right;

        public Node(String node) {
            this.node = node;
        }

        public String getNode() {
            return node;
        }

        public Node getLeft() {
            return left;
        }

        public Node getRight() {
            return right;
        }
    }
}

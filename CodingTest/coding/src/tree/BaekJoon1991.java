package tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon1991 {
    static Node[] arr;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        arr = new Node[n];

        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            String a = st.nextToken();
            String b = st.nextToken();
            String c = st.nextToken();

            if(arr[a.charAt(0) - 'A'] == null){
                arr[a.charAt(0) - 'A'] = new Node(a);
            }
            if(b.charAt(0) != '.'){
                if(arr[b.charAt(0) - 'A'] == null) {
                    arr[b.charAt(0) - 'A'] = new Node(b);
                }
                arr[a.charAt(0) - 'A'].left = arr[b.charAt(0) - 'A'];
            }
            if(c.charAt(0) != '.'){
                if(arr[c.charAt(0) - 'A'] == null) {
                    arr[c.charAt(0) - 'A'] = new Node(c);
                }
                arr[a.charAt(0) - 'A'].right = arr[c.charAt(0) - 'A'];
            }
        }
        preOrder(arr[0]);
        sb.append("\n");
        midOrder(arr[0]);
        sb.append("\n");
        postOrder(arr[0]);
        System.out.println(sb);
    }

    private static void postOrder(Node node) {
        if(node == null) return;
        postOrder(node.left);
        postOrder(node.right);
        sb.append(node.name);
    }

    private static void midOrder(Node node) {
        if(node == null) return;
        midOrder(node.left);
        sb.append(node.name);
        midOrder(node.right);
    }

    private static void preOrder(Node node) {
        if(node == null) return;
        sb.append(node.name);
        preOrder(node.left);
        preOrder(node.right);
    }

    static class Node{
        String name;
        Node left;
        Node right;

        public Node(String name) {
            this.name = name;
        }
    }
}

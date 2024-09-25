package tree;

public class Paper {
    public static void main(String[] args) {
        solution(1);
        solution(2);
        solution(3);
    }

    private static void solution(int n) {
        int[] binaryTree = new int[(int)Math.pow(n,2) - (n -1)];
        binaryTree[0] = 0;
        for(int i = 0; i<(int)Math.pow(2,n-1) -1; i++){
            int left = i * 2 + 1;
            int right = i * 2 + 2;
            binaryTree[left] = 0;
            binaryTree[right] = 1;
        }

        inOrder(binaryTree,0);
        System.out.println();
    }

    public static void inOrder(int[] arr, int idx){
        int left = idx * 2 + 1;
        int right = idx * 2 + 2;

        if(left < arr.length){
            inOrder(arr, left);
        }

        System.out.print(arr[idx] + " ");

        if(right < arr.length){
            inOrder(arr, right);
        }
    }
}

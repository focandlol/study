import java.util.Arrays;
import java.util.Scanner;

public class QuickSort {
    public static void main(String[] args) {
        int[] a = new int[5];
        Scanner sc = new Scanner(System.in);
        for(int i=0;i<a.length;i++) {
            a[i] = sc.nextInt();
        }
        l_pivot_sort(a, 0, a.length - 1);
        System.out.println(Arrays.toString(a));
    }

    /**
     *  왼쪽 피벗 선택 방식
     * @param a		정렬할 배열
     * @param lo	현재 부분배열의 왼쪽
     * @param hi	현재 부분배열의 오른쪽
     */
    private static void l_pivot_sort(int[] a, int lo, int hi) {

        if(lo >= hi) {
            return;
        }

        int pivot = partition(a, lo, hi);
        System.out.println(pivot);
        //System.out.println(hi);
        l_pivot_sort(a, lo, pivot - 1);
        System.out.println("hi");
        l_pivot_sort(a, pivot + 1, hi);
    }

    private static int partition(int[] a, int left, int right) {

        System.out.println("left = " + left);
        System.out.println("right = " + right);
        int lo = left;
        int hi = right;
        int pivot = a[left];		// 부분리스트의 왼쪽 요소를 피벗으로 설정

        // lo가 hi보다 작을 때 까지만 반복한다.
        while(lo < hi) {
            while(a[hi] > pivot && lo < hi) {
                hi--;
            }
            while(a[lo] <= pivot && lo < hi) {
                lo++;
            }
            swap(a, lo, hi);
        }
        swap(a, left, lo);
        System.out.println(Arrays.toString(a));
        return lo;
    }

    private static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}

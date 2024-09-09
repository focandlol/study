package sort;

import java.util.ArrayList;
import java.util.Arrays;

public class Practice3 {
    public static void main(String[] args) {
        int[][] intervals = {{2,6},{1,3},{15,18},{8,10}};
        //intervals = new int[][]{{1,3},{2,1},{3,6}};
        for(int[] item : solution(intervals)){
            System.out.print(Arrays.toString(item) + " ");
        }
        System.out.println();
    }

    private static ArrayList<int[]> solution(int[][] intervals) {
        int[][] sorted = new int[intervals.length][2];
        merge_sort(intervals,0,intervals.length-1,sorted);

        ArrayList<int[]> result = new ArrayList<>();
        int[] curInterval = intervals[0];
        result.add(curInterval);

        for(int i=1; i<intervals.length; i++){
            if(curInterval[1]>=intervals[i][0]){
                if(curInterval[1]<intervals[i][1]) {
                    curInterval[1] = intervals[i][1];
                }
            }else{
                if(curInterval[1] >= intervals[i][1]){
                    curInterval[1] = intervals[i][0];
                }else{
                    curInterval = intervals[i];
                    result.add(curInterval);
                }
            }

        }
        return result;
    }

    private static void merge_sort(int[][] intervals, int left, int right,int[][] sorted) {
        if(left >= right) return;

        int mid = (left + right) / 2;
        merge_sort(intervals,left,mid,sorted);
        merge_sort(intervals,mid+1,right,sorted);
        merge(intervals,left,right,mid,sorted);
    }

    private static void merge(int[][] intervals, int left, int right, int mid,int[][] sorted) {
        int l = left;
        int start = left;
        int r  = mid + 1;

        while(l <= mid && r <= right){
            if(intervals[l][0] <= intervals[r][0]){
                sorted[start][0] = intervals[l][0];
                sorted[start][1] = intervals[l][1];
                start++;
                l++;
            }else{
                sorted[start][0] = intervals[r][0];
                sorted[start][1] = intervals[r][1];
                start++;
                r++;
            }
        }

        if(l <= mid){
            while(l <= mid){
                sorted[start][0] = intervals[l][0];
                sorted[start][1] = intervals[l][1];
                start++;
                l++;
            }
        }else{
            while(r <= right){
                sorted[start][0] = intervals[r][0];
                sorted[start][1] = intervals[r][1];
                start++;
                r++;
            }
        }

        for(int i = left; i <= right; i++){
            intervals[i][0] = sorted[i][0];
            intervals[i][1] = sorted[i][1];
        }
    }
}

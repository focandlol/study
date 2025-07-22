package basic;

public class dsfasf {
  public static int[] solution(int[] sequence, int k) {
    int[] dap = new int[2];
    int max = Integer.MAX_VALUE;

    int start = 0;
    int end = 0;
    int sum = sequence[0];

    while(start < sequence.length){
      if(sum >= k){
        if(sum == k && end - start + 1 < max){
          dap[0] = start;
          dap[1] = end;
          max = end - start + 1;
        }
        sum -= sequence[start++];
        System.out.println("s start : " + start + " end : " + end + " sum : " + sum);
      }else{
        if(end + 1 < sequence.length){
          sum += sequence[++end];
          System.out.println("start : " + start + " end : " + end + " sum : " + sum);
        }
      }
    }

    return dap;
  }

  public static void main(String[] args) {
    int[] solution = solution(new int[]{1, 2, 3, 4, 5}, 7);
    System.out.println(solution);
  }
}


package backTracking;

public class Practice3 {
    final static int num = 10;  // 문제 수
    static int count = 0;  // 5점 이상을 받을 경우의 수

    public static void solution(int[] sols) {
        int[] submit = new int[num];
        backT(sols,submit,0,0);
        System.out.println(count);
    }

    private static void backT(int[] sols,int[] submit,int correctCnt,int idx) {
        if(num - idx + correctCnt < 5){
            return;
        }

        if(idx == num){
            if(correctCnt >= 5){
                count+=1;
            }
        }else{
            int twoInRow = 0;
            if(idx >= 2){
                if(submit[idx - 1] == submit[idx -2]){
                    twoInRow = submit[idx - 1];
                }
            }

            for(int i=1; i<=5; i++){
                if(i == twoInRow){
                    continue;
                }

                submit[idx] = i;
                if(sols[idx] == i){
                    backT(sols,submit,correctCnt+1,idx+1);
                }else{
                    backT(sols,submit,correctCnt,idx+1);
                }
                submit[idx] = 0;
            }
        }
    }

    public static void main(String[] args) {
        int[] sols = {1, 2, 3, 4, 5, 1, 2, 3, 4, 5};  // 정답 배열
        solution(sols);
    }
}
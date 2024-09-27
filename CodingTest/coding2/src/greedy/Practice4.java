package greedy;

public class Practice4 {
    public static void main(String[] args) {
        int[] gas = {1,2,3,4,5};
        int[] cost = {3,4,5,1,2};
        System.out.println(solution(gas,cost));
    }

    private static int solution(int[] gas, int[] cost) {
        int curGas = 0;
        int totalGas = 0;
        int startPos = 0;

        for (int i = 0; i < gas.length; i++) {
            curGas += gas[i] - cost[i];
            totalGas += gas[i] - cost[i];

            if (curGas < 0) {
                curGas = 0;
                startPos = i+1;
            }
        }
        return totalGas >= 0? startPos : -1;
    }
}

package practice.p1_1;

public class Practice3 {

    public static String solution(char[] str, char[] find, char[] to) {
        String dap = "";
        char[] clone = str.clone();
        int idx = 0;
        while(true){
            idx = findIndex(clone, find);
            if(idx == -1){
                break;
            }

            for(int i=0; i<idx; i++){
                dap += clone[i];
            }

            for(int i=0; i<to.length; i++){
                dap += to[i];
            }

            for(int i=idx+find.length; i<clone.length; i++){
                dap += clone[i];
            }

            clone = dap.toCharArray();
            dap = "";
        }
        return new String(clone);
    }

    private static int findIndex(char[] str, char[] find) {
        boolean match = false;
        for(int i = 0; i < str.length; i++){
            if(str[i] == find[0] && str.length -i >= find.length){
                match = true;
                for(int j = 1; j < find.length; j++){
                    if(str[i+j] != find[j]){
                        match = false;
                        break;
                    }
                }
            }
            if(match){
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        // Test code
        String str = "Hello Java, Nice to meet you! Java is fun!";
        String find = "Java";
        String to = "자바";

        // 기존 String replace
       // System.out.println(str.replace(find, to));

        // 자체 구현 replace
        char[] strExArr = str.toCharArray();
        char[] findArr = find.toCharArray();
        char[] toArr = to.toCharArray();
        System.out.println(solution(strExArr, findArr, toArr));

        strExArr = "POP".toCharArray();
        findArr = "P".toCharArray();
        toArr = "W".toCharArray();
        System.out.println(solution(strExArr, findArr, toArr));
    }
}

package basicMath.practice1;

public class Practice7 {
    public static void main(String[] args) {
        System.out.println(find("abba"));
        System.out.println(find("summuus"));
        System.out.println(find("xabba"));
        System.out.println(find("xabbay"));
        System.out.println(find("comcom"));
        System.out.println(find("comwwmoc"));
        System.out.println(find("comwwtmoc"));
    }

    private static int find(String str) {
        return as(0,str.length()-1,str.toCharArray(),0);
    }

    private static int as(int left, int right, char[] str, int count) {
        if(left >= right) return count;
        if(str[left] != str[right]){
            if(count == 0){
                if(as(left+1,right,str,1) == 1 ||as(left,right-1,str,1) == 1){
                    return 1;
                }else{
                    return 2;
                }
            }else{
                return 2;
            }
        }else{
            return as(left+1,right-1,str,count);
        }
    }
}

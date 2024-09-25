package pre.pre1_1;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class s{
    public static void main(String[] args) {
        String strValue = "One_Two##Three|Four#Five";
        String[] strArray = strValue.split("[_#|]" );

        System.out.println("strArray.length: " + strArray.length);
        System.out.println(Arrays.toString(strArray));
    }

}


package generic.test.ex2;

public class UnitUtil {

    static <T extends BioUnit> T maxHp(T a, T b){

        return a.getHp() > b.getHp() ? a : b;
    }
}

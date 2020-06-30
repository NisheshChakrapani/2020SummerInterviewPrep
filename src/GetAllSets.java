import java.util.ArrayList;
import java.util.List;

public class GetAllSets {
    public static void main(String[] args) {
        List<List<Integer>> allSets = getAllSets(8);
    }

    private static List<List<Integer>> getAllSets(int n) {
        int numSets = 1 << n;
        List<List<Integer>> allSets = new ArrayList<>();
        for (int i = 0; i < numSets; i++) {
            allSets.add(getSet(n, i));
        }
        return allSets;
    }

    private static List<Integer> getSet(int cardinality, int num) {
        List<Integer> set = new ArrayList<>();
        for (int i = 0; i < cardinality; i++) {
            if ((num & 1) != 0) {
                set.add(i);
            }
            num = num >> 1;
        }
        return set;
    }
}

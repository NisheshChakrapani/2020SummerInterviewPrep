import java.util.ArrayList;
import java.util.List;

public class StringPermutations {
    public static void main(String[] args) {
        List<String> permutations = getAllPermutations("123456");
        for (String p : permutations) System.out.println(p);
    }

    public static List<String> getAllPermutations(String s) {
        List<StringBuilder> perms = new ArrayList<>();
        perms.add(new StringBuilder());
        perms = getAllPermutations(s, 0, perms);
        return stringBuildersToStrings(perms);
    }

    private static List<StringBuilder> getAllPermutations(String s, int index, List<StringBuilder> perms) {
        if (index == s.length()) return perms;
        List<StringBuilder> permsNew = new ArrayList<>();
        for(StringBuilder sb : perms) {
            for (int i = 0; i < index+1; i++) {
                StringBuilder sbNew = new StringBuilder(sb.toString());
                sbNew.insert(i, s.charAt(index));
                permsNew.add(sbNew);
            }
        }
        return getAllPermutations(s, index+1, permsNew);
    }

    private static List<String> stringBuildersToStrings(List<StringBuilder> stringBuilders) {
        List<String> strings = new ArrayList<>(stringBuilders.size());
        for (StringBuilder sb : stringBuilders) strings.add(sb.toString());
        return strings;
    }
}

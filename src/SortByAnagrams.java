import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class SortByAnagrams {
    public static void main(String[] args) {
        String[] test = {"abc", "dqft", "cba", "tfqd", "dfqt"};
        sortByAnagrams(test);
        System.out.println(Arrays.toString(test));

        String[] test2 = {"arc", "gash", "lest", "hags", "lets", "tels", "car"};
        sortByAnagrams(test2);
        System.out.println(Arrays.toString(test2));
    }

    public static void sortByAnagrams(String[] arr) {
        Comparator<String> comp = new Comparator<>() {
            @Override
            public int compare(String o1, String o2) {
                if (isAnagram(o1, o2)) return 0;
                else return (o1.compareTo(o2));
            }
        };

        Arrays.sort(arr, comp);
    }

    public static boolean isAnagram(String s1, String s2) {
        if (s1.length() != s2.length()) return false;
        Map<Character, Integer> s1Map = new HashMap<>();
        Map<Character, Integer> s2Map = new HashMap<>();
        for (int i = 0; i < s1.length(); i++) {
            char c1 = s1.charAt(i), c2 = s2.charAt(i);
            if (s1Map.containsKey(c1)) s1Map.replace(c1, s1Map.get(c1)+1);
            else s1Map.put(c1, 1);
            if (s2Map.containsKey(c2)) s2Map.replace(c2, s2Map.get(c2)+1);
            else s2Map.put(c2, 1);
        }

        for (char c : s1.toCharArray()) {
            if (!s2Map.containsKey(c) || s1Map.get(c) != s2Map.get(c)) return false;
        }
        return true;
    }
}

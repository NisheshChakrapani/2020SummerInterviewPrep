import java.util.*;

public class AddSpacesToSentence {
    public static Set<String> dictionary = new HashSet<>();
    public static void main(String[] args) {
        setupDictionary();

        System.out.println(addSpaces("helloworld"));
        System.out.println(addSpaces("portmanteauwords"));
        System.out.println(addSpaces("aq"));
    }

    public static String addSpaces(String sentence) {
        Stack<String> words = new Stack<>();
        if (!addSpacesRecurse(sentence, 0, 0, words)) {
            return "Sorry, could not form a sentence.";
        } else {
            StringBuilder sb = new StringBuilder(words.pop());
            while (!words.isEmpty()) sb.append(" " + words.pop());
            return sb.toString();
        }
    }

    private static boolean addSpacesRecurse(String s, int start, int end, Stack<String> words) {
        if (start == s.length() && end == s.length()) return true;
        if (start < s.length() && end >= s.length()) return false;
        String currWord = s.substring(start, end+1);
        if (isWord(currWord) && addSpacesRecurse(s, end+1, end+1, words)) {
            words.push(currWord);
            return true;
        } else {
            return addSpacesRecurse(s, start, end+1, words);
        }
    }

    private static void setupDictionary() {
        dictionary.add("hello");
        dictionary.add("world");
        dictionary.add("port");
        dictionary.add("man");
        dictionary.add("tea");
        dictionary.add("words");
        dictionary.add("word");
        dictionary.add("portmanteau");
        dictionary.add("a");
    }
    private static boolean isWord(String s) {
        return dictionary.contains(s);
    }
}

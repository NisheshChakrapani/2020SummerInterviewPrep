import java.util.*;

public class AddSpacesToAllPossibleSentences {
    public static Set<String> dictionary = new HashSet<>();
    public static void main(String[] args) {
        setupDictionary();
        addSpaces("thisisawesome");
    }

    public static void addSpaces(String sentence) {
        Stack<String> words = new Stack<>();
        List<Stack<String>> sentences = new ArrayList<>();
        int sentenceCount = 0;
        while (addSpacesRecurse(sentence, 0, 0, words, sentences)) {
            sentenceCount++;
            String s = stackToString(words);
            System.out.println(s);
            sentences.add(words);
            words = new Stack<>();
        }
        if (sentenceCount == 0) {
            System.out.println("Sorry, could not form a sentence.");
        }
    }

    private static boolean addSpacesRecurse(String s, int start, int end, Stack<String> words,
                                            List<Stack<String>> sentences) {
        if (start == s.length() && end == s.length() && stackExists(words, sentences)) return false;
        else if (start == s.length() && end == s.length()) return true;
        if (start < s.length() && end >= s.length()) return false;

        String currWord = s.substring(start, end+1);
        words.push(currWord);
        if (isWord(currWord) && addSpacesRecurse(s, end+1, end+1, words, sentences)) {
            return true;
        } else {
            words.pop();
            return addSpacesRecurse(s, start, end+1, words, sentences);
        }
    }

    private static boolean stackExists(Stack<String> s, List<Stack<String>> list) {
        for (Stack<String> s2 : list) {
            if (stacksAreEqual(s, s2)) return true;
        }
        return false;
    }
    private static boolean stacksAreEqual(Stack<String> s1, Stack<String> s2) {
        if (s1 == null && s2 == null) return true;
        if (s1 == null || s2 == null) return false;
        if (s1.size() != s2.size()) return false;

        Stack<String> aux1 = new Stack<>();
        Stack<String> aux2 = new Stack<>();
        boolean allEqual = true;
        while (!s1.isEmpty()) {
            String s1Pop = s1.pop();
            String s2Pop = s2.pop();
            if (!s1Pop.equals(s2Pop)) allEqual = false;
            aux1.push(s1Pop);
            aux2.push(s2Pop);
        }

        while (!aux1.isEmpty()) s1.push(aux1.pop());
        while (!aux2.isEmpty()) s2.push(aux2.pop());

        return allEqual;
    }
    private static String stackToString(Stack<String> s) {
        StringBuilder sb = new StringBuilder();
        Stack<String> aux = new Stack<>();
        while (!s.isEmpty()) aux.push(s.pop());
        String firstWord = aux.pop();
        sb.append(firstWord);
        s.push(firstWord);
        while (!aux.isEmpty()) {
            String nextWord = aux.pop();
            sb.append(" " + nextWord);
            s.push(nextWord);
        }
        return sb.toString();
    }
    private static void setupDictionary() {
        dictionary.add("this");
        dictionary.add("is");
        dictionary.add("awesome");
        dictionary.add("awe");
        dictionary.add("some");
        dictionary.add("i");
        dictionary.add("saw");
        dictionary.add("a");
        dictionary.add("we");
        dictionary.add("his");
        dictionary.add("me");
    }
    private static boolean isWord(String s) {
        return dictionary.contains(s);
    }
}

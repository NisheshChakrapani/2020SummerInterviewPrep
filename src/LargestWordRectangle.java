import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class LargestWordRectangle {
    //Given a dictionary, create the largest possible rectangle where each row and each column
    //represents a word in the dictionary

    private static class Trie {
        private static class TrieNode {
            TrieNode[] children;
            boolean endOfWord;

            public TrieNode() {
                children = new TrieNode[26];
                Arrays.fill(children, null);
                endOfWord = false;
            }
        }

        private int size;
        private TrieNode root;

        public Trie() {
            size = 0;
            root = new TrieNode();
        }

        public int size() {
            return size;
        }

        public void insert(String key) {
            if (!exists(key)) {
                TrieNode curr = root;
                for (int i = 0; i < key.length(); i++) {
                    int index = key.charAt(i) - 'a';
                    if (curr.children[index] == null) curr.children[index] = new TrieNode();
                    curr = curr.children[index];
                }
                curr.endOfWord = true;
                size++;
            }
        }

        public boolean exists(String key) {
            TrieNode curr = root;
            for (int i = 0; i < key.length(); i++) {
                int index = key.charAt(i) - 'a';
                if (curr == null || curr.children[index] == null)
                    return false;
                curr = curr.children[index];
            }
            return (curr != null && curr.endOfWord);
        }

        public boolean prefixExists(String prefix) {
            List<String> words = startsWith(prefix);
            return words != null && words.size() > 0;
        }

        public List<String> startsWith(String prefix) {
            TrieNode start = root;
            for (int i = 0; i < prefix.length(); i++) {
                int index = prefix.charAt(i) - 'a';
                if (start == null || start.children[index] == null) return null;
                start = start.children[index];
            }
            List<String> words = new ArrayList<>();
            startsWith(start, new StringBuilder(prefix), words);
            return words;
        }

        private void startsWith(TrieNode curr, StringBuilder currWord, List<String> words) {
            if (curr == null) return;
            if (curr.endOfWord) {
                words.add(currWord.toString());
                return;
            }
            for (char c = 'a'; c <= 'z'; c++) {
                int index = c - 'a';
                if (curr != null && curr.children[index] != null) {
                    StringBuilder nextWord = new StringBuilder(currWord.toString());
                    nextWord.append(c);
                    startsWith(curr.children[index], nextWord, words);
                }
            }
        }

        public List<String> allWords() {
            List<String> words = new ArrayList<>();
            startsWith(root, new StringBuilder(), words);
            return words;
        }

        @Override
        public String toString() {
            List<String> words = this.allWords();
            StringBuilder sb = new StringBuilder();
            sb.append(words.get(0));
            for (int i = 1; i < words.size(); i++) {
                sb.append(", " + words.get(i));
            }
            sb.append("\n");
            return sb.toString();
        }
    }

    public static char[][] bestRectangle; //to be updated when finding a new
    // largest rectangle,  add each row by saying row = word.toCharArray
    public static Map<Integer, Trie> wordLengthToTrie = new HashMap<>();

    public static void main(String[] args) throws FileNotFoundException {
        Set<String> dictionary = createDictionary("2of12.txt");
        int longestWordLength = 0;


        for (String word : dictionary) {
            if (word.length() > longestWordLength) longestWordLength = word.length();
            if (!wordLengthToTrie.containsKey(word.length()))
                wordLengthToTrie.put(word.length(), new Trie());
            wordLengthToTrie.get(word.length()).insert(word);
        }

        int currLength = longestWordLength;

        //TODO: start with the largest word length and go backwards, using the trie of that
        // length to make the largest rectangle possible (traverse the trie in order, start with
        // the current word and see what all words can be put in the 2nd row, then 3rd, then so
        // on, repeat for each word in order being the 1st row)
        while (currLength > 0) {
            if (wordLengthToTrie.containsKey(currLength)) {
                Trie trie = wordLengthToTrie.get(currLength);
                List<String> trieWords = trie.allWords();
                if (bestRectangle != null && currLength * trieWords.size()
                        <= bestRectangle.length * bestRectangle[0].length)
                    continue;
                for (String startWord : trieWords) {
                    List<String> currRectangle = new ArrayList<>();
                    makeRectangle(startWord, currLength, trie, trieWords, currRectangle);
                }
            }
            currLength--;
        }

        printRectangle(bestRectangle);
    }

    private static void makeRectangle(String nextWord, int wordLength, Trie trie,
                                      List<String> trieWords, List<String> rectangle) {
        if (!columnsCanStartWords(wordLength, rectangle)) {
            return;
        }

        if ((bestRectangle == null || rectangle.size() * wordLength
                > bestRectangle.length * bestRectangle[0].length) && rectangle.size() > 0
                && allColumnsAreWords(wordLength, rectangle)) {
            bestRectangle = new char[rectangle.size()][wordLength];
            for (int i = 0; i < rectangle.size(); i++)
                bestRectangle[i] = rectangle.get(i).toCharArray();
            printRectangle(bestRectangle);
        }

        rectangle.add(nextWord);

        for (String word : trieWords) {
            if (!word.equals(nextWord) && !rectangle.contains(word)) {
                makeRectangle(word, wordLength, trie, trieWords, rectangle);
            }
        }
    }

    private static boolean columnsCanStartWords(int wordLength, List<String> rectangle) {
        for (Trie t : wordLengthToTrie.values()) {
            int counter = 0;
            for (int col = 0; col < wordLength; col++) {
                StringBuilder word = new StringBuilder();
                for (String row : rectangle) {
                    word.append(row.charAt(col));
                }
                if (t.prefixExists(word.toString())) counter++;
            }
            if (counter == wordLength) return true;
        }
        return false;
    }

    private static boolean allColumnsAreWords(int wordLength, List<String> rectangle) {
        for (Trie t : wordLengthToTrie.values()) {
            int counter = 0;
            for (int col = 0; col < wordLength; col++) {
                StringBuilder word = new StringBuilder();
                for (String row : rectangle) {
                    word.append(row.charAt(col));
                }
                if (t.exists(word.toString())) counter++;
            }
            if (counter == wordLength) return true;
        }
        return false;
    }

    private static Set<String> createDictionary(String filename) throws FileNotFoundException {
        Set<String> dictionary = new HashSet<>();
        File f = new File(filename);
        Scanner scan = new Scanner(f);
        while (scan.hasNextLine()) {
            String line = scan.nextLine();
            if (line != null && line.length() > 0 && allLetters(line)) {
                dictionary.add(line.trim().toLowerCase());
            }
        }
        return dictionary;
    }

    private static boolean allLetters(String s) {
        for (char c : s.toCharArray()) {
            if (!Character.isLetter(c)) return false;
        }
        return true;
    }

    private static void printRectangle(char[][] rectangle) {
        for (char[] word : rectangle) {
            System.out.print(Character.toString(word[0]));
            for (int i = 1; i < word.length; i++) System.out.print(" " + Character.toString(word[i]));
            System.out.println();
        }
        System.out.println("Area = " + (rectangle.length * rectangle[0].length));
        System.out.println();
    }
}

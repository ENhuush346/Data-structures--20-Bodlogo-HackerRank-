import java.util.*;

public class Solution {

    static class TrieNode {
        Map<Character, TrieNode> children = new HashMap<>();
        boolean isEndOfWord = false;
    }

    static TrieNode root = new TrieNode();

    static boolean add(String word) {
        TrieNode curr = root;
        boolean isNewWord = false;

        for (char c : word.toCharArray()) {
            if (!curr.children.containsKey(c)) {
                curr.children.put(c, new TrieNode());
                isNewWord = true;
            }
            curr = curr.children.get(c);
            if (curr.isEndOfWord) {
                // Өмнө нь бүрэн үг байсан → префикс зөрчил
                return false;
            }
        }

        if (!isNewWord) {
            // Үг бүрэн префикс болгон орж ирсэн
            return false;
        }

        curr.isEndOfWord = true;

        if (!curr.children.isEmpty()) {
            // Энэ үг өөр бусад үгийн префикс байна
            return false;
        }

        return true;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String badWord = null;

        for (int i = 0; i < n; i++) {
            String word = sc.next();
            if (badWord == null) {
                if (!add(word)) {
                    badWord = word;
                }
            }
        }

        if (badWord == null) {
            System.out.println("GOOD SET");
        } else {
            System.out.println("BAD SET");
            System.out.println(badWord);
        }

        sc.close();
    }
}

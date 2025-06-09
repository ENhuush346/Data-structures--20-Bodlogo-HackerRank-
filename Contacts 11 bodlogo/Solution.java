import java.util.*;

public class Solution {

    static class TrieNode {
        Map<Character, TrieNode> children = new HashMap<>();
        int count = 0;
    }

    static TrieNode root = new TrieNode();

    static void add(String name) {
        TrieNode curr = root;
        for (char c : name.toCharArray()) {
            curr.children.putIfAbsent(c, new TrieNode());
            curr = curr.children.get(c);
            curr.count++;
        }
    }

    static int find(String partial) {
        TrieNode curr = root;
        for (char c : partial.toCharArray()) {
            if (!curr.children.containsKey(c)) {
                return 0;
            }
            curr = curr.children.get(c);
        }
        return curr.count;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        for (int i = 0; i < n; i++) {
            String op = sc.next();
            String arg = sc.next();

            if (op.equals("add")) {
                add(arg);
            } else if (op.equals("find")) {
                System.out.println(find(arg));
            }
        }

        sc.close();
    }
}

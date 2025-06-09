import java.util.*;

public class Solution {
    static class DSU {
        int[] parent;
        int[] size;

        public DSU(int n) {
            parent = new int[n + 1];
            size = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        public int find(int a) {
            if (parent[a] != a) {
                parent[a] = find(parent[a]);
            }
            return parent[a];
        }

        public void union(int a, int b) {
            int rootA = find(a);
            int rootB = find(b);
            if (rootA != rootB) {
                if (size[rootA] < size[rootB]) {
                    int temp = rootA;
                    rootA = rootB;
                    rootB = temp;
                }
                parent[rootB] = rootA;
                size[rootA] += size[rootB];
            }
        }

        public int getSize(int a) {
            return size[find(a)];
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int q = sc.nextInt();

        DSU dsu = new DSU(n);

        for (int i = 0; i < q; i++) {
            String op = sc.next();
            int x = sc.nextInt();

            if (op.equals("M")) {
                int y = sc.nextInt();
                dsu.union(x, y);
            } else if (op.equals("Q")) {
                System.out.println(dsu.getSize(x));
            }
        }
        sc.close();
    }
}

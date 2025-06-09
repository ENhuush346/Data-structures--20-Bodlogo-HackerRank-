import java.util.*;

public class Solution {

    static class UnionFind {
        int[] parent;
        int[] size;

        public UnionFind(int n) {
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
        int n = sc.nextInt();  // тоо буюу зангилааны хамгийн их утга
        int m = sc.nextInt();  // холболтын тоо

        UnionFind uf = new UnionFind(n);

        for (int i = 0; i < m; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            uf.union(a, b);
        }

        // Холбогдсон компонентүүдийн хэмжээг хадгалах
        Set<Integer> roots = new HashSet<>();
        int minSize = Integer.MAX_VALUE;
        int maxSize = Integer.MIN_VALUE;

        for (int i = 1; i <= n; i++) {
            int root = uf.find(i);
            if (!roots.contains(root)) {
                roots.add(root);
                int size = uf.getSize(root);
                minSize = Math.min(minSize, size);
                maxSize = Math.max(maxSize, size);
            }
        }

        System.out.println(minSize + " " + maxSize);
        sc.close();
    }
}

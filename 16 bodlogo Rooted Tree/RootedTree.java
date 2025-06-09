import java.util.*;

public class RootedTree {
    static ArrayList<Integer>[] tree;
    static int[] depth;
    static int[] parent;
    static int n;

    static void dfs(int node, int par, int d) {
        parent[node] = par;
        depth[node] = d;
        for (int child : tree[node]) {
            if (child != par) {
                dfs(child, node, d + 1);
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        tree = new ArrayList[n + 1];
        depth = new int[n + 1];
        parent = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            tree[i] = new ArrayList<>();
        }

        // Модны холболтуудыг оруулах
        for (int i = 0; i < n - 1; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            tree[u].add(v);
            tree[v].add(u);
        }

        // Rooted Tree-г зангилаа 1-ээс эхлэн DFS-аар боловсруулна
        dfs(1, -1, 0);

        // Жишээ: depth болон parent-г хэвлэх
        for (int i = 1; i <= n; i++) {
            System.out.println("Node: " + i + ", Depth: " + depth[i] + ", Parent: " + parent[i]);
        }

        sc.close();
    }
}

import java.util.*;

public class Solution {

    static int N, Q;
    static ArrayList<Edge>[] graph;
    static int[] parent, depth, heavy, head, pos;
    static int[] segtree;
    static int[] edgeIndex, edgeWeight;
    static int curPos;

    static class Edge {
        int to, idx;
        Edge(int to, int idx) {
            this.to = to;
            this.idx = idx;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        graph = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            graph[i] = new ArrayList<>();
        }
        parent = new int[N];
        depth = new int[N];
        heavy = new int[N];
        Arrays.fill(heavy, -1);
        head = new int[N];
        pos = new int[N];
        edgeIndex = new int[N - 1];
        edgeWeight = new int[N - 1];

        for (int i = 0; i < N - 1; i++) {
            int u = sc.nextInt() - 1;
            int v = sc.nextInt() - 1;
            int w = sc.nextInt();
            graph[u].add(new Edge(v, i));
            graph[v].add(new Edge(u, i));
            edgeWeight[i] = w;
        }

        // 1. DFS to find parent, depth and heavy child
        parent[0] = -1;
        depth[0] = 0;
        dfs(0);

        // 2. Decompose the tree
        curPos = 0;
        decompose(0, 0);

        // 3. Build segment tree
        segtree = new int[4 * N];
        Arrays.fill(segtree, 0);
        int[] baseArray = new int[N];
        for (int i = 1; i < N; i++) {
            baseArray[pos[i]] = edgeWeight[edgeIndex[i]];
        }
        build(1, 0, N - 1, baseArray);

        Q = sc.nextInt();
        for (int i = 0; i < Q; i++) {
            int t = sc.nextInt();
            if (t == 1) {
                int u = sc.nextInt() - 1;
                int v = sc.nextInt() - 1;
                System.out.println(queryPath(u, v));
            } else {
                int idx = sc.nextInt() - 1;
                int w = sc.nextInt();
                updateEdge(idx, w);
            }
        }

        sc.close();
    }

    static int dfs(int u) {
        int size = 1;
        int maxSubtree = 0;
        for (Edge e : graph[u]) {
            int v = e.to;
            if (v != parent[u]) {
                parent[v] = u;
                depth[v] = depth[u] + 1;
                int subtreeSize = dfs(v);
                if (subtreeSize > maxSubtree) {
                    maxSubtree = subtreeSize;
                    heavy[u] = v;
                }
                edgeIndex[v] = e.idx;
                size += subtreeSize;
            }
        }
        return size;
    }

    static void decompose(int u, int h) {
        head[u] = h;
        pos[u] = curPos++;
        if (heavy[u] != -1) {
            decompose(heavy[u], h);
        }
        for (Edge e : graph[u]) {
            int v = e.to;
            if (v != parent[u] && v != heavy[u]) {
                decompose(v, v);
            }
        }
    }

    static void build(int node, int start, int end, int[] base) {
        if (start == end) {
            segtree[node] = base[start];
        } else {
            int mid = (start + end) / 2;
            build(2 * node, start, mid, base);
            build(2 * node + 1, mid + 1, end, base);
            segtree[node] = Math.max(segtree[2 * node], segtree[2 * node + 1]);
        }
    }

    static void update(int node, int start, int end, int idx, int val) {
        if (start == end) {
            segtree[node] = val;
        } else {
            int mid = (start + end) / 2;
            if (idx <= mid) update(2 * node, start, mid, idx, val);
            else update(2 * node + 1, mid + 1, end, idx, val);
            segtree[node] = Math.max(segtree[2 * node], segtree[2 * node + 1]);
        }
    }

    static int query(int node, int start, int end, int l, int r) {
        if (r < start || l > end) return Integer.MIN_VALUE;
        if (l <= start && end <= r) return segtree[node];
        int mid = (start + end) / 2;
        return Math.max(query(2 * node, start, mid, l, r), query(2 * node + 1, mid + 1, end, l, r));
    }

    static int queryPath(int u, int v) {
        int res = Integer.MIN_VALUE;
        while (head[u] != head[v]) {
            if (depth[head[u]] < depth[head[v]]) {
                int temp = u; u = v; v = temp;
            }
            int curHead = head[u];
            res = Math.max(res, query(1, 0, N - 1, pos[curHead], pos[u]));
            u = parent[curHead];
        }
        if (u == v) return res;
        if (depth[u] > depth[v]) {
            int temp = u; u = v; v = temp;
        }
        res = Math.max(res, query(1, 0, N - 1, pos[u] + 1, pos[v]));
        return res;
    }

    static void updateEdge(int idx, int val) {
        int u = -1, v = -1;
        for (int i = 0; i < N; i++) {
            if (edgeIndex[i] == idx) {
                u = i;
                break;
            }
        }
        if (u == -1) return;
        update(1, 0, N - 1, pos[u], val);
    }
}

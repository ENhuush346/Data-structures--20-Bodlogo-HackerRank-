import java.util.*;

public class Solution {
    static List<Integer>[] graph;
    static boolean[] isBlack;
    static boolean[] visited;
    static int maxDist;
    static int maxNode;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        graph = new ArrayList[n + 1];
        isBlack = new boolean[n + 1];
        visited = new boolean[n + 1];

        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        // Өнгийг оруулах: 1 хар, 0 цагаан
        for (int i = 1; i <= n; i++) {
            int color = sc.nextInt();
            isBlack[i] = (color == 1);
        }

        // Ирмэгүүдийг унших
        for (int i = 0; i < n - 1; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            graph[u].add(v);
            graph[v].add(u);
        }

        // Хар замын хамгийн уртыг олохын тулд DFS хийх
        maxDist = 0;
        maxNode = -1;

        // Хар зангилаанаас эхлэн DFS 1: хамгийн хол байгаа хар зангилааг ол
        for (int i = 1; i <= n; i++) {
            if (isBlack[i]) {
                Arrays.fill(visited, false);
                dfs(i, 0);
                break;
            }
        }

        // Хар зангилаанаас эхлэн DFS 2: хамгийн хол зайг дахин олж модны диаметрыг олно
        Arrays.fill(visited, false);
        dfs(maxNode, 0);

        System.out.println(maxDist);

        sc.close();
    }

    static void dfs(int node, int dist) {
        visited[node] = true;
        if (dist > maxDist) {
            maxDist = dist;
            maxNode = node;
        }

        for (int adj : graph[node]) {
            if (!visited[adj] && isBlack[adj]) {
                dfs(adj, dist + 1);
            }
        }
    }
}

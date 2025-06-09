import java.util.*;

public class NetworkAdministration {
    static class Edge {
        int u, v, admin;
        int devices;

        Edge(int u, int v, int admin) {
            this.u = u;
            this.v = v;
            this.admin = admin;
            this.devices = 0;
        }

        boolean connects(int x, int y) {
            return (u == x && v == y) || (u == y && v == x);
        }
    }

    static int n, m, a, q;
    static Map<String, Edge> edges = new HashMap<>();
    static Map<Integer, List<Edge>> adminEdges = new HashMap<>();
    static Map<Integer, int[]> parentMap = new HashMap<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        a = sc.nextInt();
        q = sc.nextInt();

        for (int i = 1; i <= a; i++) {
            adminEdges.put(i, new ArrayList<>());
            parentMap.put(i, new int[n + 1]);
            for (int j = 1; j <= n; j++) parentMap.get(i)[j] = j;
        }

        for (int i = 0; i < m; i++) {
            int u = sc.nextInt(), v = sc.nextInt(), admin = sc.nextInt();
            Edge e = new Edge(u, v, admin);
            edges.put(hash(u, v), e);
            adminEdges.get(admin).add(e);
            union(admin, u, v);
        }

        for (int i = 0; i < q; i++) {
            int type = sc.nextInt();
            int u = sc.nextInt(), v = sc.nextInt(), x = sc.nextInt();

            if (type == 1) {
                String key = hash(u, v);
                if (!edges.containsKey(key)) {
                    System.out.println("Wrong link");
                    continue;
                }

                Edge e = edges.get(key);
                if (e.admin == x) {
                    System.out.println("Already controlled link");
                    continue;
                }

                // Check for overload
                int overload = 0;
                for (Edge ed : adminEdges.get(x)) {
                    if (ed.u == u || ed.v == u) overload++;
                    if (ed.u == v || ed.v == v) overload++;
                }

                if (overload >= 2) {
                    System.out.println("Server overload");
                    continue;
                }

                // Check for redundancy
                int[] p = parentMap.get(x);
                if (find(p, u) == find(p, v)) {
                    System.out.println("Network redundancy");
                    continue;
                }

                // Do assignment
                int oldAdmin = e.admin;
                adminEdges.get(oldAdmin).remove(e);
                e.admin = x;
                adminEdges.get(x).add(e);
                union(x, u, v);
                System.out.println("Assignment done");

            } else if (type == 2) {
                String key = hash(u, v);
                Edge e = edges.get(key);
                e.devices = x;
                System.out.println(x + " security devices placed");

            } else if (type == 3) {
                boolean found = false;
                Set<Integer> visited = new HashSet<>();
                found = dfs(u, v, x, visited, 0);
                if (!found) System.out.println("No connection");
            }
        }
    }

    static boolean dfs(int curr, int target, int admin, Set<Integer> visited, int sum) {
        if (curr == target) {
            System.out.println(sum + " security devices placed");
            return true;
        }

        visited.add(curr);
        for (Edge e : adminEdges.get(admin)) {
            int next = -1;
            if (e.u == curr && !visited.contains(e.v)) next = e.v;
            else if (e.v == curr && !visited.contains(e.u)) next = e.u;

            if (next != -1) {
                if (dfs(next, target, admin, visited, sum + e.devices))
                    return true;
            }
        }

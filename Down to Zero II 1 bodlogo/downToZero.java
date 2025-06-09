import java.util.*;

public class Solution {

    public static int downToZero(int n) {
        boolean[] visited = new boolean[n + 1];
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{n, 0}); // {утга, алхмын тоо}
        visited[n] = true;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int value = current[0];
            int steps = current[1];

            if (value == 0) return steps;

            // Алхам 1: -1
            if (!visited[value - 1]) {
                visited[value - 1] = true;
                queue.offer(new int[]{value - 1, steps + 1});
            }

            // Алхам 2: Хуваагчийн их утгыг олох
            for (int i = 2; i <= Math.sqrt(value); i++) {
                if (value % i == 0) {
                    int factor = Math.max(i, value / i);
                    if (!visited[factor]) {
                        visited[factor] = true;
                        queue.offer(new int[]{factor, steps + 1});
                    }
                }
            }
        }

        return -1; // хүрэх боломжгүй (болохгүй тохиолдол байхгүй ч гэсэн)
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int q = sc.nextInt(); // асуултын тоо

        for (int i = 0; i < q; i++) {
            int n = sc.nextInt();
            System.out.println(downToZ

import java.util.Scanner;

public class Solution {

    static int[] fenwickTree;

    static void update(int idx, int val, int n) {
        while (idx <= n) {
            fenwickTree[idx] += val;
            idx += idx & (-idx);
        }
    }

    static int query(int idx) {
        int sum = 0;
        while (idx > 0) {
            sum += fenwickTree[idx];
            idx -= idx & (-idx);
        }
        return sum;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int q = sc.nextInt();

        fenwickTree = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            // Эхлээд массивийн элементүүд 0 тул оролт авах шаардлагагүй
        }

        for (int i = 0; i < q; i++) {
            int type = sc.nextInt();
            int x = sc.nextInt();
            int y = sc.nextInt();

            if (type == 1) {
                // x-р элементийн утгыг y-ээр нэмэх
                update(x, y, n);
            } else if (type == 2) {
                // x-с y хүртэлх нийлбэрийг хэвлэх
                System.out.println(query(y) - query(x - 1));
            }
        }
        sc.close();
    }
}

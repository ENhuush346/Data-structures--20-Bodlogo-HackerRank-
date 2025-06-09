import java.util.Arrays;
import java.util.Scanner;

public class Solution {

    static String twoArrays(int k, int[] A, int[] B) {
        Arrays.sort(A);
        Arrays.sort(B);

        int n = A.length;
        for (int i = 0; i < n; i++) {
            if (A[i] + B[n - 1 - i] < k) {
                return "NO";
            }
        }
        return "YES";
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int q = sc.nextInt();

        for (int qi = 0; qi < q; qi++) {
            int n = sc.nextInt();
            int k = sc.nextInt();

            int[] A = new int[n];
            int[] B = new int[n];

            for (int i = 0; i < n; i++) {
                A[i] = sc.nextInt();
            }
            for (int i = 0; i < n; i++) {
                B[i] = sc.nextInt();
            }

            System.out.println(twoArrays(k, A, B));
        }

        sc.close();
    }
}

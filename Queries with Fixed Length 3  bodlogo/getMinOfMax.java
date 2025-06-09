import java.util.*;

public class Solution {

    public static int getMinOfMax(int[] arr, int d) {
        Deque<Integer> deque = new LinkedList<>();
        int n = arr.length;
        int minOfMax = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            // Хэтэрсэн индекстэй элементийг хасах
            while (!deque.isEmpty() && deque.peekFirst() <= i - d) {
                deque.pollFirst();
            }

            // deque-н сүүлээс бага утгуудыг хасна (max хадгалах)
            while (!deque.isEmpty() && arr[deque.peekLast()] <= arr[i]) {
                deque.pollLast();
            }

            deque.offerLast(i); // шинэ индекс нэмэх

            // цонх дүүрэх үед max-г шалгах
            if (i >= d - 1) {
                minOfMax = Math.min(minOfMax, arr[deque.peekFirst()]);
            }
        }

        return minOfMax;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();  // массивын хэмжээ
        int q = sc.nextInt();  // хүсэлтийн тоо

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        for (int i = 0; i < q; i++) {
            int d = sc.nextInt();
            System.out.println(getMinOfMax(arr, d));
        }

        sc.close();
    }
}

import java.util.*;

public class Solution {

    public static void runningMedian(int[] arr) {
        PriorityQueue<Integer> lower = new PriorityQueue<>(Collections.reverseOrder()); // Max Heap
        PriorityQueue<Integer> higher = new PriorityQueue<>(); // Min Heap

        for (int num : arr) {
            addNumber(lower, higher, num);
            balanceHeaps(lower, higher);
            printMedian(lower, higher);
        }
    }

    private static void addNumber(PriorityQueue<Integer> lower, PriorityQueue<Integer> higher, int num) {
        if (lower.isEmpty() || num < lower.peek()) {
            lower.offer(num);
        } else {
            higher.offer(num);
        }
    }

    private static void balanceHeaps(PriorityQueue<Integer> lower, PriorityQueue<Integer> higher) {
        if (lower.size() > higher.size() + 1) {
            higher.offer(lower.poll());
        } else if (higher.size() > lower.size()) {
            lower.offer(higher.poll());
        }
    }

    private static void printMedian(PriorityQueue<Integer> lower, PriorityQueue<Integer> higher) {
        double median;
        if (lower.size() == higher.size()) {
            median = (lower.peek() + higher.peek()) / 2.0;
        } else {
            median = lower.peek();
        }
        System.out.printf("%.1f%n", median);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        runningMedian(arr);
        sc.close();
    }
}

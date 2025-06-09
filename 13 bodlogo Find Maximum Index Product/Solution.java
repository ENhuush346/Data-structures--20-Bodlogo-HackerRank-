import java.util.*;

public class Solution {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        int[] leftGreater = new int[n];
        int[] rightGreater = new int[n];

        Stack<Integer> stack = new Stack<>();

        // Зүүн талын том элемент
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] <= arr[i]) {
                stack.pop();
            }
            leftGreater[i] = stack.isEmpty() ? 0 : stack.peek() + 1; // 1-based index
            stack.push(i);
        }

        stack.clear();

        // Баруун талын том элемент
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && arr[stack.peek()] <= arr[i]) {
                stack.pop();
            }
            rightGreater[i] = stack.isEmpty() ? 0 : stack.peek() + 1; // 1-based index
            stack.push(i);
        }

        long maxProduct = 0;
        for (int i = 0; i < n; i++) {
            long product = (long) leftGreater[i] * rightGreater[i];
            if (product > maxProduct) {
                maxProduct = product;
            }
        }

        System.out.println(maxProduct);

        sc.close();
    }
}

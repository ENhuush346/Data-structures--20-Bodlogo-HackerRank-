import java.util.*;

class Order {
    int arrival;
    int duration;

    public Order(int arrival, int duration) {
        this.arrival = arrival;
        this.duration = duration;
    }
}

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        List<Order> orders = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int t = sc.nextInt();
            int d = sc.nextInt();
            orders.add(new Order(t, d));
        }

        // Arrival time-ээр эрэмбэлэх
        orders.sort(Comparator.comparingInt(o -> o.arrival));

        PriorityQueue<Order> minHeap = new PriorityQueue<>(Comparator.comparingInt(o -> o.duration));

        long currentTime = 0;
        long totalWait = 0;
        int index = 0;

        while (index < n || !minHeap.isEmpty()) {
            // Нийт ирсэн хоолнуудыг heap-д нэмэх
            while (index < n && orders.get(index).arrival <= currentTime) {
                minHeap.offer(orders.get(index));
                index++;
            }

            if (minHeap.isEmpty()) {
                // Хэрвээ хоол ирээгүй бол цагаа урагшлуулна
                currentTime = orders.get(index).arrival;
            } else {
                // Хамгийн богино хийх хугацаатай хоолыг хийх
                Order current = minHeap.poll();
                currentTime += current.duration;
                totalWait += (currentTime - current.arrival);
            }
        }

        System.out.println(totalWait / n);
        sc.close();
    }
}

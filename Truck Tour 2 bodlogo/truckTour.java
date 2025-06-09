import java.util.*;

public class Solution {

    public static int truckTour(List<List<Integer>> petrolpumps) {
        int totalPetrol = 0;
        int totalDistance = 0;
        int balance = 0;
        int startIndex = 0;

        for (int i = 0; i < petrolpumps.size(); i++) {
            int petrol = petrolpumps.get(i).get(0);
            int distance = petrolpumps.get(i).get(1);
            totalPetrol += petrol;
            totalDistance += distance;
            balance += petrol - distance;

            // Хэрэв үлдэгдэл хасах руу орвол дараагийн буудлаас эхэлнэ
            if (balance < 0) {
                startIndex = i + 1;
                balance = 0;
            }
        }

        // Нийт шатахуун хүрэлцэхгүй бол -1 (Hackerrank бодлогод ийм тохиолдол байхгүй)
        return (totalPetrol < totalDistance) ? -1 : startIndex;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        List<List<Integer>> petrolpumps = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            int petrol = scanner.nextInt();
            int distance = scanner.nextInt();
            petrolpumps.add(Arrays.asList(petrol, distance));
        }

        System.out.println(truckTour(petrolpumps));
        scanner.close();
    }
}

import java.util.*;

public class DistinctAscendingTriplets {

    public static int countTriplets(int[] arr) {
        // Алхам 1: Давхардлыг арилгана
        Set<Integer> set = new HashSet<>();
        for (int num : arr) {
            set.add(num);
        }

        // Алхам 2: Давхардалгүй утгуудыг жагсаалтад шилжүүлж, эрэмбэлнэ
        List<Integer> unique = new ArrayList<>(set);
        Collections.sort(unique);

        // Алхам 3: Өсөх дарааллын 3-тай хослолын тоог тоолно
        int n = unique.size();
        int count = 0;

        // i < j < k байх нөхцөлтэйгээр гурвалсан хослол тоолно
        for (int i = 0; i < n - 2; i++) {
            for (int j = i + 1; j < n - 1; j++) {
                for (int k = j + 1; k < n; k++) {
                    // unique[j] > unique[i] ба unique[k] > unique[j] учраас өсөх дараалал автоматаар хангагдсан байна
                    count++;
                }
            }
        }

        return count;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        sc.close();

        System.out.println(countTriplets(arr));
    }
}

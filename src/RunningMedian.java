import java.util.Collections;
import java.util.PriorityQueue;

public class RunningMedian {
    private static PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    private static PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
    public static void main(String[] args) {
        add(10);
        System.out.println(median());
        add(15);
        System.out.println(median());
        add(20);
        System.out.println(median());
    }

    public static double median() {
        if (minHeap.isEmpty() && maxHeap.isEmpty()) return Integer.MIN_VALUE;
        if (minHeap.size() > maxHeap.size()) return minHeap.peek();
        else if (maxHeap.size() > minHeap.size()) return maxHeap.peek();
        else return ((double)minHeap.peek() + (double)maxHeap.peek()) / 2;
    }

    public static void add(int n) {
        if (minHeap.isEmpty() && maxHeap.isEmpty()) minHeap.add(n);
        else {
            if (n > median()) {
                minHeap.add(n);
                while (minHeap.size() - maxHeap.size() > 1) maxHeap.add(minHeap.poll());
            } else {
                maxHeap.add(n);
                while (maxHeap.size() - minHeap.size() > 1) minHeap.add(maxHeap.poll());
            }
        }
    }
}

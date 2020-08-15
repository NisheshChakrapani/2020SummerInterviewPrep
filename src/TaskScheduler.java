import java.util.*;

public class TaskScheduler {
    private static class Triple implements Comparable<Triple> {
        char c;
        int count;
        int lastUsed = -1;
        public Triple(char c, int count) {
            this.c = c;
            this.count = count;
        }

        public int compareTo(Triple o) {
            if (this.count == o.count) return this.c - o.c;
            return this.count - o.count;
        }
    }

    public static void main(String[] args) {
        char[] tasks = new char[52];
        int i = 0;
        for (char c = 'A'; c <= 'Z'; c++) {
            tasks[i++] = c;
            tasks[i++] = c;
        }

        System.out.println(leastInterval(tasks, 2));
    }

    public static int leastInterval(char[] tasks, int n) {
        if (tasks == null || tasks.length == 0) return 0;
        if (n == 0) return tasks.length;

        Map<Character, Integer> map = new HashMap<>();
        for (char c : tasks) map.put(c, map.getOrDefault(c, 0) + 1);
        PriorityQueue<Triple> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        for (char c : map.keySet()) maxHeap.add(new Triple(c, map.get(c)));

        Queue<Triple> queue = new LinkedList<>();
        int totalCount = 0;
        while (!maxHeap.isEmpty() || !queue.isEmpty()) {
            if (maxHeap.isEmpty()) totalCount = queue.peek().lastUsed + n + 1;

            while (!queue.isEmpty() && totalCount > queue.peek().lastUsed + n)
                maxHeap.add(queue.poll());

            Triple task = maxHeap.poll();
            task.lastUsed = totalCount;
            totalCount++;
            task.count--;

            if (task.count != 0) queue.add(task);
        }

        return totalCount;
    }
}

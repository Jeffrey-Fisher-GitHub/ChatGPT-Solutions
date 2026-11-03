package turnpike;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Deque;
import java.util.List;
import java.util.Queue;

/** New quiz extensions. No methods in this file alter a live TollPlaza queue. */
public final class TurnpikeQuiz {
    private TurnpikeQuiz() { }

    public record Row(String name, double milepost, int queueSize) { }

    public static final Comparator<Row> BY_QUEUE =
            (a, b) -> Integer.compare(b.queueSize(), a.queueSize());

    // Q4: queueSize descending, then milepost ascending.
    public static final Comparator<Row> BY_QUEUE_THEN_MILEPOST = (a, b) -> {
        throw new UnsupportedOperationException("Q4 comparator");
    };

    public static Row[] fixture() {
        return new Row[] {
            new Row("Bedford", 146, 4),
            new Row("Warrendale", 30, 9),
            new Row("Somerset", 110, 4),
            new Row("Breezewood", 161, 1),
            new Row("New Stanton", 75, 9),
            new Row("Harrisburg East", 247, 3)
        };
    }

    // Q2: this sorts correctly by key but contains a stability bug.
    public static void insertionSort(Row[] a, Comparator<Row> cmp) {
        for (int i = 1; i < a.length; i++) {
            Row key = a[i];
            int j = i - 1;
            while (j >= 0 && cmp.compare(a[j], key) >= 0) {
                a[j + 1] = a[j];
                j--;
            }
            a[j + 1] = key;
        }
    }

    // Q3: inputs are already sorted by cmp; do not mutate them.
    public static Row[] merge(Row[] left, Row[] right, Comparator<Row> cmp) {
        Row[] out = new Row[left.length + right.length];
        int i = 0, j = 0, k = 0;
        // TODO Q3: choose a next element while both inputs have elements.
        // Then copy the unconsumed tail of each input. Prefer left on a tie.
        throw new UnsupportedOperationException("Q3 merge");
    }

    public static Row[] rankedCopy(Row[] source) {
        // TODO Q4: copy source, sort the copy using BY_QUEUE_THEN_MILEPOST,
        // and return the copy. Arrays.sort is allowed for this question.
        throw new UnsupportedOperationException("Q4 rankedCopy");
    }

    public static final class Node {
        public Row value;
        public Node left, right;
        public Node(Row value) { this.value = value; }
    }

    // Q8: equal mileposts must REPLACE the value in the existing node.
    // The current else branch incorrectly makes duplicates into new nodes.
    public static Node insert(Node n, Row value) {
        if (n == null) return new Node(value);
        if (value.milepost() < n.value.milepost()) {
            n.left = insert(n.left, value);
        } else {
            n.right = insert(n.right, value);
        }
        return n;
    }

    public static Row find(Node n, double milepost) {
        // TODO Q7: follow one branch at a time; return null when absent.
        throw new UnsupportedOperationException("Q7 find");
    }

    public static List<Row> inOrder(Node root) {
        List<Row> out = new ArrayList<>();
        Deque<Node> stack = new ArrayDeque<>();
        Node current = root;
        // TODO Q9: iterative inorder using this stack; no recursion/sort.
        throw new UnsupportedOperationException("Q9 inOrder");
    }

    // Q10: sorted contains distinct mileposts in increasing order.
    // lo and hi are inclusive; callers use 0 and sorted.length - 1.
    public static Node balanced(Row[] sorted, int lo, int hi) {
        if (lo > hi) return null;
        int mid = lo + (hi - lo) / 2;
        Node n = new Node(sorted[mid]);
        // TODO Q10: set left and right with recursive calls on subranges.
        return n;
    }

    // Supplied utilities, not quiz TODOs.
    public static Node treeFixture() {
        Node root = null;
        String[] names = {"Bedford", "New Stanton", "Harrisburg East",
                "Warrendale", "Somerset", "Breezewood", "Valley Forge"};
        int[] miles = {146, 75, 247, 30, 110, 161, 326};
        for (int i = 0; i < miles.length; i++) {
            root = insert(root, new Row(names[i], miles[i], 0));
        }
        return root;
    }

    public static int height(Node n) {
        return n == null ? -1 : 1 + Math.max(height(n.left), height(n.right));
    }

    public static int size(Node n) {
        return n == null ? 0 : 1 + size(n.left) + size(n.right);
    }

    public static List<Double> levelOrder(Node root) {
        List<Double> out = new ArrayList<>();
        Queue<Node> q = new ArrayDeque<>();
        if (root != null) q.offer(root);
        while (!q.isEmpty()) {
            Node n = q.poll();
            out.add(n.value.milepost());
            if (n.left != null) q.offer(n.left);
            if (n.right != null) q.offer(n.right);
        }
        return out;
    }
}

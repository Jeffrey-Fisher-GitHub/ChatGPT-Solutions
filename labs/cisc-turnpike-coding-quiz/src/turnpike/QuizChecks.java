package turnpike;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import static turnpike.TurnpikeQuiz.*;

/** Run all checks, or pass a question ID such as Q7 as a program argument. */
public final class QuizChecks {
    private static void check(boolean condition, String label) {
        if (!condition) throw new AssertionError(label);
    }

    public static void main(String[] args) {
        String[] ids = {"Q2", "Q3", "Q4", "Q7", "Q8", "Q9", "Q10"};
        int passed = 0, attempted = 0;
        for (String id : ids) {
            if (args.length > 0 && !id.equalsIgnoreCase(args[0])) continue;
            attempted++;
            try {
                run(id);
                System.out.println(id + " PASS");
                passed++;
            } catch (RuntimeException | AssertionError e) {
                System.out.println(id + " FAIL: " + e.getMessage());
            }
        }
        System.out.println(passed + "/" + attempted + " question checks passed");
    }

    private static void run(String id) {
        Row a = new Row("first", 10, 4), b = new Row("second", 20, 4);
        switch (id) {
        case "Q2": {
            Row[] equal = {a, b};
            insertionSort(equal, BY_QUEUE);
            check(equal[0] == a && equal[1] == b, "equal keys lost arrival order");
            Row[] rows = fixture();
            insertionSort(rows, BY_QUEUE);
            check(Arrays.stream(rows).map(Row::name).toList().equals(List.of(
                    "Warrendale", "New Stanton", "Bedford", "Somerset",
                    "Harrisburg East", "Breezewood")), "stable queue order");
            insertionSort(new Row[0], BY_QUEUE);
            break;
        }
        case "Q3": {
            Row low = new Row("low", 30, 1), high = new Row("high", 40, 9);
            Row[] left = {a, low}, right = {high, b};
            Row[] merged = merge(left, right, BY_QUEUE);
            check(Arrays.equals(merged, new Row[] {high, a, b, low}), "merge and tie rule");
            check(left[0] == a && right[0] == high, "input mutated");
            check(Arrays.equals(merge(left, new Row[0], BY_QUEUE), left), "empty right");
            check(Arrays.equals(merge(new Row[0], right, BY_QUEUE), right), "empty left");
            check(merge(new Row[0], new Row[0], BY_QUEUE).length == 0, "empty inputs");
            break;
        }
        case "Q4": {
            Row[] input = fixture(), saved = input.clone();
            Row[] ranked = rankedCopy(input);
            check(input != ranked && Arrays.equals(input, saved), "must return sorted copy");
            check(Arrays.stream(ranked).map(Row::name).toList().equals(List.of(
                    "Warrendale", "New Stanton", "Somerset", "Bedford",
                    "Harrisburg East", "Breezewood")), "queue then milepost order");
            check(BY_QUEUE_THEN_MILEPOST.compare(a, b) < 0, "milepost tie break");
            check(BY_QUEUE_THEN_MILEPOST.compare(a, a) == 0, "self comparison");
            check(rankedCopy(new Row[0]).length == 0, "empty ranking");
            break;
        }
        case "Q7": {
            Node root = treeFixture();
            check(find(root, 110).name().equals("Somerset"), "existing key");
            check(find(root, 100) == null, "absent key");
            check(find(null, 110) == null, "empty tree");
            check(find(root, 146) == root.value, "root key");
            break;
        }
        case "Q8": {
            Node root = treeFixture();
            Row replacement = new Row("New Stanton revised", 75, 12);
            root = insert(root, replacement);
            check(size(root) == 7, "duplicate must not add a node");
            check(root.left.value == replacement, "replace value at key 75");
            check(root.left.left.value.milepost() == 30 && root.left.right.value.milepost() == 110,
                    "replacement must keep children");
            check(size(insert(null, a)) == 1, "empty insertion");
            break;
        }
        case "Q9": {
            List<Row> rows = inOrder(treeFixture());
            check(rows.stream().map(Row::milepost).toList().equals(
                    List.of(30.0, 75.0, 110.0, 146.0, 161.0, 247.0, 326.0)), "inorder keys");
            check(inOrder(null).isEmpty(), "empty traversal");
            check(inOrder(new Node(a)).equals(List.of(a)), "single node");
            break;
        }
        case "Q10": {
            Row[] sorted = fixture();
            Arrays.sort(sorted, Comparator.comparingDouble(Row::milepost));
            Node balanced = balanced(sorted, 0, sorted.length - 1);
            check(size(balanced) == sorted.length && height(balanced) == 2, "all nodes and height");
            check(balanced(new Row[0], 0, -1) == null, "empty range");
            check(balanced(new Row[] {a}, 0, 0).value == a, "single item range");
            break;
        }
        default: throw new IllegalArgumentException(id);
        }
    }
}

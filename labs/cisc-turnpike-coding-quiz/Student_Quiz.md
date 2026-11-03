# CISC Turnpike Coding Quiz

Sorting algorithms binary search trees and p5 visualization

**15 questions | 100 points | 120 minutes | Open code and open IDE**

Name ____________________________  Section __________  Date __________

Your task is to make the Turnpike dashboard trustworthy. Predict what the code will do, repair it, and prove the repair with a small input. A correct explanation and a working edit both matter.

Use the supplied starter files and the existing PA Turnpike Visualizer. Java work uses JDK 17 or newer. Sorting and BST methods in `TurnpikeQuiz.java` are extensions for this quiz. The p5 questions refer to the existing `resources/web/sketch.js`. Setup and check commands are in `README.md`.

**Start now:** Run `QuizChecks` in Java and `QuizChecks.run()` in the browser console. The initial failures are intentional. Save this baseline. Record each prediction before rerunning the relevant code or inspecting the checks, then edit and rerun one question at a time.

**Submit:** Answers labeled Q1–Q15; edited `TurnpikeQuiz.java`, `quiz-helpers.js`, and `sketch.js`; final check output; and one screenshot showing queue badges and segment counts. For a blocked task, submit your attempted code, smallest failing input, expected result, and actual result. Answer in a separate file; this handout is not an answer sheet.

## Data for Questions 1 through 5

`Row(name, milepost, queueSize)` is an immutable snapshot. Use the following input order. Queue sizes are fixed quiz values; mileposts are the lab's approximate teaching values.

| ID | Plaza | Milepost | Queue size |
| --- | --- | --- | --- |
| B | Bedford | 146 | 4 |
| W | Warrendale | 30 | 9 |
| S | Somerset | 110 | 4 |
| R | Breezewood | 161 | 1 |
| N | New Stanton | 75 | 9 |
| H | Harrisburg East | 247 | 3 |

`BY_QUEUE` compares queue sizes in descending order and returns zero for ties. For this quiz, stability means equal keys retain their original relative order. The immutable snapshot is for analysis; do not reorder cars inside a live booth queue.

<!-- PAGE -->
## Sorting algorithms

### 1 Predict two orders

**6 points | Trace**

Write the six IDs in (a) increasing milepost order and (b) descending queue-size order using a stable sort. In your second answer, identify the two tied groups and explain the order within each group. Do this before running a sort.

### 2 Repair a sort that looks correct

**6 points | Predict and code | Java check Q2**

The supplied insertion sort contains this condition:

```java
while (j >= 0 && cmp.compare(a[j], key) >= 0)
```

Using `BY_QUEUE` and the original six rows, write the first three IDs after outer-loop iteration `i = 2`. Explain why the resulting order can pass a key-order check but fail a stability check. Make the smallest repair in `insertionSort`. Show a passing Q2 check and name the equal-key input that exposes the bug.

### 3 Finish the merge

**8 points | Code and trace | Java check Q3**

Complete `merge(left, right, cmp)` without a library sort. The inputs are already sorted by `cmp`. Return a new array, preserve every row, leave both inputs unchanged, and choose the left row when keys compare equal. Handle either input being empty.

Trace the output IDs when `left = [W, B, R]`, `right = [N, S, H]`, and `cmp = BY_QUEUE`. State the running time in terms of the two input lengths. Run Q3.

### 4 Rank a dashboard without changing its source

**6 points | Code | Java check Q4**

Implement `BY_QUEUE_THEN_MILEPOST`: larger queue first, then smaller milepost for a queue-size tie. Use comparison methods, not arithmetic subtraction. Implement `rankedCopy` so it returns a sorted copy and leaves the input array unchanged; `Arrays.sort` is allowed here.

Give the output IDs for the fixture. Explain why the order of B and S may differ from Question 1 even when the library sort is stable. Run Q4.

### 5 Choose an algorithm and a fair comparison

**4 points | Explain and repair a benchmark**

Choose between insertion sort and merge sort for (a) 20 rows already sorted under the same comparator and (b) 10,000 randomly ordered rows when predictable worst-case growth matters. Give a relevant time bound for each choice.

A teammate times insertion sort on `data` and then merge sort on that same, now sorted array. Write the two Java array-copy statements needed before either run to give both algorithms identical starting orders. State why one wall-clock run alone is weak evidence.

<!-- PAGE -->
## Binary search trees

Use an ordinary, unbalanced BST keyed only by milepost. Smaller keys go left; larger keys go right. The key is finite and does not change within a node. The node's `Row` value may be replaced when the same milepost is inserted again. Height counts edges: empty tree is -1 and a leaf is 0.

For Questions 6–9, start fresh with this insertion order, also supplied by `treeFixture()`:

```text
146 Bedford          75 New Stanton       247 Harrisburg East
30 Warrendale        110 Somerset         161 Breezewood
326 Valley Forge
```

### 6 Build the tree before you search

**6 points | Trace**

Draw the BST or list each node's left and right children. Give the keys examined, in order, when searching for milepost 110. State the tree height. Do not use the physical road's west-to-east order as the insertion order.

### 7 Make a search terminate correctly

**7 points | Code | Java check Q7**

Implement `find(Node n, double milepost)`. Follow one branch at each comparison, return the matching `Row`, and return `null` when the key is absent. Do not traverse both subtrees or copy the tree into a list.

Run checks for 110, missing key 100, the root key 146, and an empty tree. Explain which condition prevents an unsuccessful search from looping forever or dereferencing `null`.

### 8 Update a plaza without growing a duplicate branch

**6 points | Repair | Java check Q8**

Insert `new Row("New Stanton revised", 75, 12)` into a fresh tree. The current `insert` sends every equal key right. Repair it so an equal key replaces the existing node's value and retains both children.

Report the node count after the update, the stored name and queue size at milepost 75, and that node's two child keys. Explain why replacing the entire node with `new Node(value)` would lose information. Run Q8.

### 9 Use a stack to produce road order

**7 points | Code and trace | Java check Q9**

Complete iterative `inOrder` using its supplied `Deque<Node>` as a stack. Do not use recursion or a sort. It must return an empty list for an empty tree.

Predict the inorder keys and the level-order keys for the original seven-node tree. Explain why a queue is appropriate for the supplied `levelOrder` method while the inorder method uses a stack. Run Q9.

### 10 Prevent a sorted input from becoming a chain

**7 points | Analyze and code | Java check Q10**

Insert these keys, in this order, into an empty ordinary BST:

```text
30, 75, 110, 146, 161, 247, 326
```

Give its height and the number of node visits needed to find 326. Explain why a BST does not automatically guarantee logarithmic search time.

Complete the two recursive child assignments in `balanced(sorted, lo, hi)`. Its input is strictly increasing, and both bounds are inclusive. State the height of the resulting seven-node tree. Run Q10, including its even-length, empty-range, and single-item cases.

<!-- PAGE -->
## p5 visualization and state

Use `sketch.js` from the existing lab and the new `quiz-helpers.js`. The Java server owns vehicle state. The browser polls snapshots and interpolates positions between polls. JavaScript edits belong in the browser files, not in Java methods.

### 11 Locate a car from time and mileposts

**6 points | Trace actual sketch formulas**

At `accept(s)`, `Date.now()` is 105000 and `s.serverTimeMs` is 100000. Later, at drawing time, `Date.now()` is 105725. A car travels from Warrendale at MP 30 to Bedford at MP 146; `tripStartMs = 100000` and `tripDurationMs = 2900`.

Use `MARGIN = 70`, canvas `width = 1220`, and `state.mainlineMiles = 360`. Assume the given times are exact. Calculate `clockOffset`, `serverNow()`, progress `t`, current milepost, canvas x-coordinate, and direction plus lane offset. Show substitutions into the formulas from `accept`, `serverNow`, `drawTraveling`, and `mpToX`. You do not need to calculate `roadY`.

### 12 Fix a ranking that corrupts the road labels

**7 points | Repair and integrate | Browser check Q12**

A teammate adds this line before rendering the table in `updatePanels`:

```javascript
s.plazas.sort((a, b) => b.queueSize - a.queueSize);
```

Explain how it can corrupt the adjacent-plaza distances drawn by `drawSegmentDistances`, even though each plaza still has the right milepost. Implement `QuizHelpers.rankPlazas`: return a new array sorted by queue size descending, then milepost ascending. Do not change the array or its objects.

Change only the table's `.map` input in `updatePanels` from `s.plazas` to `QuizHelpers.rankPlazas(s.plazas)`. Run Q12 and show evidence that the original milepost order remains unchanged. Explain why this ranking belongs in the snapshot/table update rather than in every animation frame.

### 13 Keep one plaza drawing from moving every other plaza

**6 points | Repair and draw**

The supplied `drawQueueBadge` translates the canvas and changes drawing styles but never restores them. Repair it using the appropriate p5 drawing-state operations. Do not compensate with a guessed reverse translation.

Add `QuizHelpers.drawQueueBadge(p);` at the end of the existing `drawPlaza(p)`. A queue size of at least 9 must show a red count; smaller queues show green. Explain what would happen to later drawings without your repair. Save a screenshot with the map and badges. For a repeatable color check, temporarily test the badge with queue sizes 8 and 9, then remove that temporary override.

### 14 Reject a slowdown that exists only on the screen

**6 points | Debug and code | Browser check Q14**

The helper's progress calculation multiplies elapsed time by 0.5. A teammate says that this creates traffic congestion. The Java trip start, duration, and exit logic have not changed.

Explain where the browser would draw a car at the instant the server considers its trip complete, and why the next snapshot can make the car disappear at the wrong position. Repair `tripProgress(c, now)` to agree with server timing and clamp progress to [0, 1]. Assume a valid car has positive duration.

In `drawTraveling`, replace the existing calculation of `t` with `QuizHelpers.tripProgress(c, now)`. Run Q14. Identify which layer must model a real increase in travel time; do not implement a congestion model for this question.

<!-- PAGE -->
## Finish the dashboard

### 15 Count vehicles between exits without double counting

**12 points | Code and integrate | Browser check Q15**

Implement `countSegments(plazas, traveling, now)`. Return one record per consecutive pair of plazas, in road order, with fields `{from, to, east, west}`. The input plazas have unique names and strictly increasing mileposts. Preserve both inputs.

For each vehicle, look up its entry and exit, calculate progress using its own timing, interpolate its milepost, and determine direction from exit minus entry milepost. Include only `TRAVELING` vehicles with known endpoints, positive duration, a nonzero trip distance, and raw progress at least 0 but less than 1. Ignore future, completed, or invalid trips.

Use the geographic interval **[lower milepost, upper milepost)** for both directions. A vehicle exactly at an internal plaza belongs to the interval starting at that plaza, even when westbound. A completed trip is excluded rather than clamped back into a count.

Use these three plazas and `now = 4000`. Every vehicle below has duration 8000 and status `TRAVELING`. These are controlled fixture times, independent of the live run's time scale.

| Vehicle | Entry MP | Exit MP | Start time |
| --- | --- | --- | --- |
| A | 30 | 110 | 0 |
| B | 110 | 30 | 0 |
| C | 30 | 110 | -500 |
| D | 110 | 30 | 500 |
| E | 30 | 110 | -4000 |
| F | 30 | Unknown | 0 |

MP 30 is Warrendale, 75 is New Stanton, and 110 is Somerset. Before running Q15, list each included vehicle's segment and give both directional counts per segment. Explain why E and F are excluded.

After `drawSegmentDistances()` inside the existing `draw()`, add:

```javascript
QuizHelpers.drawSegmentCounts(
  state.plazas, state.traveling, serverNow());
```

Run Q15 and show the counts in your final screenshot. These are counts estimated from the most recent snapshot and interpolated positions. This mainline lab supports east/west travel; a north/south extension would need additional route data.

**Before you submit:** Rerun both check runners. Identify one failing case you fixed and explain the change in two sentences. If a check still fails, include the attempt and failure evidence so your reasoning can be assessed.

## Source code for this quiz

The source is [PA Turnpike Visualizer at revision 56365af1](https://github.com/Jeffrey-Fisher-GitHub/Claude-Solutions/tree/56365af1b503cdf865c7ebe15d37926ca51e64ff/labs/pa-turnpike-p5-visualizer). Read `TurnpikeData.java` for plaza facts, `SimulationEngine.java` for the JSON schema, `TurnpikeTraffic.java` for trip timing, and `resources/web/sketch.js` for the p5 functions used above. The starter files add the explicitly named sorting and BST extensions.

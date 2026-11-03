# CISC Turnpike coding quiz

15 questions, 100 points, approximately 120 minutes. Use two 60-minute meetings if needed. This is an open-code, open-IDE assessment: students predict, edit, run, and explain.

Start with [Student_Quiz.md](Student_Quiz.md). A print-ready Word copy is included as `CISC_Turnpike_Student_Quiz.docx`.

## Source and scope

This assessment builds on [PA Turnpike Visualizer](https://github.com/Jeffrey-Fisher-GitHub/Claude-Solutions/tree/56365af1b503cdf865c7ebe15d37926ca51e64ff/labs/pa-turnpike-p5-visualizer), source revision `56365af1b503cdf865c7ebe15d37926ca51e64ff`.

The existing project supplies Java threads, FIFO booth queues, a transaction stack, JSON state, and p5.js animation. The sorting and BST exercises here are new extensions. The source's plaza names and approximate mileposts are reused; quiz queue sizes and trip times are controlled teaching fixtures. This version models mainline east/west travel, not north/south routes, vehicle classes, or a density-based speed model.

## Java setup

Use JDK 17 or newer, as required by the source project. Copy `src/turnpike/TurnpikeQuiz.java` and `src/turnpike/QuizChecks.java` into the existing Eclipse project's `src/turnpike` package. Run `QuizChecks` as a Java application. Alternatively, from this folder:

```bash
javac -d out src/turnpike/*.java
java -cp out turnpike.QuizChecks
java -cp out turnpike.QuizChecks Q3
```

The starter compiles, but deliberately fails the checks for unfinished questions. Work on the named question and rerun its check. `Row` is an immutable teaching snapshot with `name`, `milepost`, and `queueSize`, matching the existing plaza facts. It is separate from the live concurrent queue. A Java adapter, when needed, is `new Row(p.getName(), p.getMilepost(), p.getQueueSize())` for each `TollPlaza p`; it samples each queue once, not atomically across all plazas.

## JavaScript and p5.js setup

Copy `web/quiz-helpers.js` and `web/quiz-checks.js` into the source project's `resources/web` directory. In its `index.html`, add these two lines immediately before its existing `sketch.js` script tag:

```html
<script src="quiz-helpers.js"></script>
<script src="quiz-checks.js"></script>
```

Run `TurnpikeVisualizerServer` from Eclipse, open the localhost page, open the browser developer console, and run `QuizChecks.run()` or `QuizChecks.run('Q15')`. The checks use fixed data; they do not change the live simulation. Follow Questions 12–15 for the small edits in `sketch.js`. Reload the page after edits. The original project already supplies p5.js; no npm install is needed.

Optional checks without the browser, if Node.js is installed:

```bash
node -e "require('./web/quiz-helpers.js'); require('./web/quiz-checks.js'); QuizChecks.run()"
```

## Submission

Submit numbered answers Q1–Q15, both edited starter implementation files, your edited `sketch.js`, the Java and JavaScript check output, and one screenshot showing your queue badges and segment counts. Record predictions before running the corresponding code. For an unfinished method, submit the attempted code and the smallest failing input.

Instructor solutions are distributed separately. Do not upload answer keys to the student repository.

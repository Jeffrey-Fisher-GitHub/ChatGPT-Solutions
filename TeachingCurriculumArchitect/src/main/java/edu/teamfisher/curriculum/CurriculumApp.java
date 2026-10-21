package edu.teamfisher.curriculum;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Instant;

public final class CurriculumApp {
    private static final ObjectMapper JSON = new ObjectMapper();
    private static final String SYSTEM = """
        You are a rigorous college instructor creating a DRAFT teaching package.
        Align objectives, lecture, lab, and assessment. Cover at least five central
        concepts; each needs a definition, how it works, a worked example,
        misconception, and comprehension check. Explain mechanisms, not slide headlines.
        Include lab acceptance criteria, a quiz, and instructor-only answer key.
        Use the provided course profile. Do not invent citations, evidence, test
        results, or claims of reading web pages. Mark unsupported assertions for review.
        Return Markdown with EXACTLY these headings: # Student Draft,
        ## Learning Objectives, ## Concepts, ## Lab, ## Quiz,
        # INSTRUCTOR ONLY, ## Answer Key.
        """;

    private CurriculumApp() {}

    public static JsonNode courseProfile(String course) {
        if (!course.equals("CISC121") && !course.equals("CISC213"))
            throw new IllegalArgumentException("Supported courses: CISC121, CISC213");
        try (InputStream stream = CurriculumApp.class.getResourceAsStream("/courses/" + course + ".json")) {
            if (stream == null) throw new IllegalStateException("Missing course profile");
            return JSON.readTree(stream);
        } catch (Exception e) { throw new IllegalStateException("Cannot read course profile", e); }
    }

    public static String sanitize(String input) {
        return input.replaceAll("[^A-Za-z0-9_-]", "_");
    }

    public static String studentDraft(String title, String objectives, String lab) {
        return "# Student Draft: " + title + "\n\n**DRAFT — NOT APPROVED**\n\n## Learning Objectives\n"
            + objectives + "\n\n## Lab\n" + lab + "\n";
    }

    public static String callModel(String system, String prompt) throws Exception {
        String key = System.getenv("OPENAI_API_KEY");
        if (key == null || key.isBlank()) throw new IllegalStateException("Set OPENAI_API_KEY");
        String model = System.getenv().getOrDefault("OPENAI_MODEL", "gpt-4.1-mini");
        ObjectNode body = JSON.createObjectNode();
        body.put("model", model);
        var messages = body.putArray("messages");
        messages.addObject().put("role", "system").put("content", system);
        messages.addObject().put("role", "user").put("content", prompt);
        HttpRequest request = HttpRequest.newBuilder(URI.create("https://api.openai.com/v1/chat/completions"))
            .header("Authorization", "Bearer " + key)
            .header("Content-Type", "application/json")
            .POST(HttpRequest.BodyPublishers.ofString(JSON.writeValueAsString(body)))
            .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() != 200)
            throw new IllegalStateException("AI API returned HTTP " + response.statusCode() + " (response withheld)");
        JsonNode message = JSON.readTree(response.body()).path("choices").path(0).path("message").path("content");
        if (!message.isTextual() || message.asText().isBlank()) throw new IllegalStateException("Empty model response");
        return message.asText();
    }

    public static void main(String[] args) throws Exception {
        boolean demo = args.length > 0 && args[0].equals("--demo");
        int offset = demo ? 1 : 0;
        String course = args.length > offset ? args[offset] : "CISC121";
        JsonNode profile = courseProfile(course);
        String topic = args.length > offset + 1
            ? String.join(" ", java.util.Arrays.copyOfRange(args, offset + 1, args.length))
            : "Computer vision: OCR compared with object detection";
        String draft;
        String review;
        if (demo) {
            draft = studentDraft("Offline lesson fixture", "- Compare classification, OCR, and detection.",
                "1. Choose an image.\n2. Test OCR or object recognition.\n3. Record a failure.\n");
            review = "# INSTRUCTOR ONLY — REVIEW\nOffline deterministic fixture, not sourced or model-generated.\n";
        } else {
            String combined = callModel(SYSTEM, "Course profile:\n" + profile.toPrettyString() + "\nTopic: " + topic);
            int split = combined.indexOf("# INSTRUCTOR ONLY");
            if (split < 0) throw new IllegalStateException("Missing answer-key split marker; no files created");
            draft = combined.substring(0, split);
            review = combined.substring(split);
            String qa = callModel("Strict curriculum QA reviewer. Report weaknesses in mechanics, relevance, objectives, lab feasibility and citation support. Do not claim correctness without evidence.",
                "Course: " + profile.toPrettyString() + "\nReview draft:\n" + combined);
            review += "\n\n## Independent QA Review\n" + qa;
        }
        Path out = Path.of("output");
        Files.createDirectories(out);
        String prefix = course + "_" + sanitize(Instant.now().toString());
        Path student = out.resolve(prefix + "_STUDENT_DRAFT.md");
        Path instructor = out.resolve(prefix + "_INSTRUCTOR_ONLY.md");
        Files.writeString(student, draft, StandardCharsets.UTF_8);
        Files.writeString(instructor, review, StandardCharsets.UTF_8);
        ObjectNode audit = JSON.createObjectNode();
        audit.put("course", course);
        audit.put("topic", topic);
        audit.put("status", "DRAFT_REVIEW_REQUIRED");
        audit.put("facultyApproved", false);
        audit.put("published", false);
        audit.put("sourceUrlsVerified", false);
        audit.put("offlineDemo", demo);
        Files.writeString(out.resolve(prefix + "_audit.json"),
            JSON.writerWithDefaultPrettyPrinter().writeValueAsString(audit), StandardCharsets.UTF_8);
        System.out.println("Created: " + student + " and " + instructor + " (NOT PUBLISHED)");
    }
}

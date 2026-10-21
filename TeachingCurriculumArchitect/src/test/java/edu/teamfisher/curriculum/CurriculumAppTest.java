package edu.teamfisher.curriculum;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
class CurriculumAppTest {
    @Test void studentTemplateNeverContainsAnswerKey() {
        String draft = CurriculumApp.studentDraft("Demo", "- Objective", "Do experiment");
        assertTrue(draft.contains("DRAFT"));
        assertFalse(draft.contains("INSTRUCTOR ONLY"));
        assertFalse(draft.contains("Answer Key"));
    }
    @Test void invalidCourseRejected() {
        assertThrows(IllegalArgumentException.class, () -> CurriculumApp.courseProfile("OTHER"));
        assertEquals("CISC 121", CurriculumApp.courseProfile("CISC121").path("course").asText());
    }
    @Test void filenameSanitized() { assertEquals("A_B_C", CurriculumApp.sanitize("A/B:C")); }
}

package seedu.address.model.tag;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class JobTitleTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new JobTitle(null));
    }

    @Test
    public void constructor_invalidJobTitle_throwsIllegalArgumentException() {
        String invalidJobTitle = "";
        assertThrows(IllegalArgumentException.class, () -> new JobTitle(invalidJobTitle));
    }

    @Test
    public void isValidJobTitle() {
        // null job title
        assertThrows(NullPointerException.class, () -> JobTitle.isValidJobTitle(null));

        // invalid job titles
        assertFalse(JobTitle.isValidJobTitle(""));
        assertFalse(JobTitle.isValidJobTitle("Teacher"));

        // valid job titles
        assertTrue(JobTitle.isValidJobTitle("HR Coordinator"));
        assertTrue(JobTitle.isValidJobTitle("Marketing Specialist"));
        assertTrue(JobTitle.isValidJobTitle("HR  Coordinator")); // extra space between words
        assertTrue(JobTitle.isValidJobTitle("HR Coordinator   ")); // extra space behind
        assertTrue(JobTitle.isValidJobTitle("   HR Coordinator")); // extra space in front
        assertTrue(JobTitle.isValidJobTitle("hr Coordinator")); // non capital
    }

    @Test
    public void equals() {
        JobTitle jobTitle = new JobTitle("HR Coordinator");

        // same values -> returns true
        assertTrue(jobTitle.equals(new JobTitle("HR Coordinator")));

        // same object -> returns true
        assertTrue(jobTitle.equals(jobTitle));

        // null -> returns false
        assertFalse(jobTitle.equals(null));

        // different types -> returns false
        assertFalse(jobTitle.equals(5.0f));

        // different values -> returns false
        assertFalse(jobTitle.equals(new JobTitle("Marketing Specialist")));
    }
}

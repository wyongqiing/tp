package seedu.address.model.tag;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class EmploymentTypeTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new EmploymentType(null));
    }

    @Test
    public void constructor_invalidEmploymentType_throwsIllegalArgumentException() {
        String invalidEmploymentType = "";
        assertThrows(IllegalArgumentException.class, () -> new EmploymentType(invalidEmploymentType));
    }

    @Test
    public void isValidEmploymentType() {
        // null employment type
        assertThrows(NullPointerException.class, () -> EmploymentType.isValidEmploymentType(null));

        // invalid employment types
        assertFalse(EmploymentType.isValidEmploymentType(""));
        assertFalse(EmploymentType.isValidEmploymentType("None"));

        // valid employment types
        assertTrue(EmploymentType.isValidEmploymentType("Full-Time"));
        assertTrue(EmploymentType.isValidEmploymentType("Part-Time"));
    }

    @Test
    public void equals() {
        EmploymentType employmentType = new EmploymentType("Full-Time");

        // same values -> returns true
        assertTrue(employmentType.equals(new EmploymentType("Full-Time")));

        // same object -> returns true
        assertTrue(employmentType.equals(employmentType));

        // null -> returns false
        assertFalse(employmentType.equals(null));

        // different types -> returns false
        assertFalse(employmentType.equals(5.0f));

        // different values -> returns false
        assertFalse(employmentType.equals(new EmploymentType("Part-Time")));
    }
}

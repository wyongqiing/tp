package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class DateOfJoiningTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DateOfJoining(null));
    }

    @Test
    public void constructor_invalidDateOfJoining_throwsIllegalArgumentException() {
        String invalidDateOfJoining = "";
        assertThrows(IllegalArgumentException.class, () -> new DateOfJoining(invalidDateOfJoining));
    }

    @Test
    public void isValidDateOfJoining() {
        assertThrows(NullPointerException.class, () -> DateOfJoining.isValidDate(null));

        // invalid dates
        assertFalse(DateOfJoining.isValidDate("")); // empty string
        assertFalse(DateOfJoining.isValidDate(" ")); // spaces only
        assertFalse(DateOfJoining.isValidDate("2025-may-01")); // wrong format
        assertFalse(DateOfJoining.isValidDate("01 may 2025")); // wrong format
        assertFalse(DateOfJoining.isValidDate("2025.01-01")); // wrong format

        // valid dates
        assertTrue(DateOfJoining.isValidDate("20-May-2025"));
        assertTrue(DateOfJoining.isValidDate("2015-01-01"));
        assertTrue(DateOfJoining.isValidDate("01-01-2025"));
        assertTrue(DateOfJoining.isValidDate("01/01/2025"));
        assertTrue(DateOfJoining.isValidDate("01.01.2025"));
    }

    @Test
    public void equals() {
        DateOfJoining date = new DateOfJoining("20-May-2023");

        // same values -> returns true
        assertTrue(date.equals(new DateOfJoining("20-May-2023")));

        // same object -> returns true
        assertTrue(date.equals(date));

        // null -> returns false
        assertFalse(date.equals(null));

        // different types -> returns false
        assertFalse(date.equals(5.0f));

        // different values -> returns false
        assertFalse(date.equals(new DateOfJoining("21-May-2023")));
    }
}

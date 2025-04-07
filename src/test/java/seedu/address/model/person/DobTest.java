package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class DobTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Dob(null));
    }

    @Test
    public void constructor_invalidDob_throwsIllegalArgumentException() {
        String invaliddob = "";
        assertThrows(IllegalArgumentException.class, () -> new Dob(invaliddob));
    }

    @Test
    public void isValidDob() {
        assertThrows(NullPointerException.class, () -> Dob.isValidDob(null));

        // invalid dates
        assertFalse(Dob.isValidDob("")); // empty string
        assertFalse(Dob.isValidDob(" ")); // spaces only
        assertFalse(Dob.isValidDob("2025-may-01")); // wrong format
        assertFalse(Dob.isValidDob("01 may 2025")); // wrong format
        assertFalse(Dob.isValidDob("2025-may-90"));
        assertFalse(Dob.isValidDate("32-Apr-2024"));

        // valid dates
        assertTrue(Dob.isValidDob("20-May-2025")); // correct format
        assertTrue(Dob.isValidDob("2015-01-01")); // correct format
        assertTrue(Dob.isValidDob("01-01-2025")); // correct format
        assertTrue(Dob.isValidDob("01/01/2025"));
        assertTrue(Dob.isValidDob("01.01.2025"));
        assertTrue(Dob.isValidDate("30-Apr-2024"));
    }

    @Test
    public void equals() {
        Dob dob = new Dob("20-May-2023");

        // same values -> returns true
        assertTrue(dob.equals(new Dob("20-May-2023")));

        // same object -> returns true
        assertTrue(dob.equals(dob));

        // null -> returns false
        assertFalse(dob.equals(null));

        // different types -> returns false
        assertFalse(dob.equals(5.0f));

        // different values -> returns false
        assertFalse(dob.equals(new Dob("21-May-2023")));
    }
}

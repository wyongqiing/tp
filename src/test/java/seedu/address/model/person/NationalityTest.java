package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class NationalityTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Nationality(null));
    }

    @Test
    public void constructor_invalidNationality_throwsIllegalArgumentException() {
        String invalidNationality = "";
        assertThrows(IllegalArgumentException.class, () -> new Nationality(invalidNationality));
    }

    @Test
    public void isValidNationality() {
        // null nationality
        assertThrows(NullPointerException.class, () -> Nationality.isValidNationality(null));

        // invalid nationality
        assertFalse(Nationality.isValidNationality("")); // empty string
        assertFalse(Nationality.isValidNationality(" ")); // spaces only
        assertFalse(Nationality.isValidNationality("^")); // only non-alphanumeric characters
        assertFalse(Nationality.isValidNationality("alice*")); // contains non-alphanumeric characters/ not in the list

        // valid nationality
        assertTrue(Nationality.isValidNationality("Bulgarian"));
        assertTrue(Nationality.isValidNationality("American"));
        assertTrue(Nationality.isValidNationality("Papua New Guinean"));
    }

    @Test
    public void equals() {
        Nationality nationality = new Nationality("Singaporean");

        // same values -> returns true
        assertTrue(nationality.equals(new Nationality("Singaporean")));

        // same object -> returns true
        assertTrue(nationality.equals(nationality));

        // null -> returns false
        assertFalse(nationality.equals(null));

        // different types -> returns false
        assertFalse(nationality.equals(5.0f));

        // different values -> returns false
        assertFalse(nationality.equals(new Nationality("Russian")));
    }
}

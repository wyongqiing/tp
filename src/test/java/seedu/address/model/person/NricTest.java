package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class NricTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Nric(null));
    }

    @Test
    public void constructor_invalidNric_throwsIllegalArgumentException() {
        String invalidNric = "";
        assertThrows(IllegalArgumentException.class, () -> new Nric(invalidNric));
    }

    @Test
    public void isValidNric() {
        // null nric
        assertThrows(NullPointerException.class, () -> Nric.isValidNric(null));

        // invalid nric
        assertFalse(Nric.isValidNric("")); // empty string
        assertFalse(Nric.isValidNric(" ")); // spaces only
        assertFalse(Nric.isValidNric("^")); // only non-alphanumeric characters
        assertFalse(Nric.isValidNric("A9385712A*")); // starts with an undefined alphabet
        assertFalse(Nric.isValidNric("S9385712a*")); // ends with non-capital letter

        // valid nric
        assertTrue(Nric.isValidNric("T0928347A")); // correct format only
        assertTrue(Nric.isValidNric("S0239481Z")); // try using S
        assertTrue(Nric.isValidNric("F9381739G")); // try using F
        assertTrue(Nric.isValidNric("G1234567P")); // try using G
    }

    @Test
    public void equals() {
        Nric nric = new Nric("T0123456A");

        // same values -> returns true
        assertTrue(nric.equals(new Nric("T0123456A")));

        // same object -> returns true
        assertTrue(nric.equals(nric));

        // null -> returns false
        assertFalse(nric.equals(null));

        // different types -> returns false
        assertFalse(nric.equals(5.0f));

        // different values -> returns false
        assertFalse(nric.equals(new Nric("T0987654A")));
    }
}

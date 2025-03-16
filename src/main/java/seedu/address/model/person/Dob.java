package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's dob in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidDob(String)}
 */
public class Dob {

    public static final String MESSAGE_CONSTRAINTS =
            "Format of DOB: dd-MMM-yyyy, and DOB should not be blank";
    public static final String VALIDATION_REGEX =
            "^(\\d{2}-[A-Za-z]{3}-\\d{4})$";

    public final String value;

    /**
     * Constructs an {@code Dob}
     *
     * @param dob A valid Dob.
     */
    public Dob(String dob) {
        requireNonNull(dob);
        checkArgument(isValidDob(dob), MESSAGE_CONSTRAINTS);
        value = dob;
    }

    /**
     * Returns if a given string is a valid dob.
     */
    public static boolean isValidDob(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Dob)) {
            return false;
        }

        Dob otherDob = (Dob) other;
        return otherDob.value.equals(value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}

package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's dateOfJoining in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidDate(String)}
 */
public class DateOfJoining {

    public static final String MESSAGE_CONSTRAINTS =
            "Format of dateOfJoining: dd-MMM-yyyy, Date of Joining should not be blank.";
    public static final String VALIDATION_REGEX =
            "^(\\d{2}-[A-Za-z]{3}-\\d{4})$";

    public final String value;

    /**
     * Constructs an {@code DateOfJoining}
     *
     * @param dateOfJoining A valid DateOfJoining.
     */
    public DateOfJoining(String dateOfJoining) {
        requireNonNull(dateOfJoining);
        checkArgument(isValidDate(dateOfJoining), MESSAGE_CONSTRAINTS);
        value = dateOfJoining;
    }

    /**
     * Returns if a given string is a valid date.
     */
    public static boolean isValidDate(String test) {
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

        if (!(other instanceof DateOfJoining)) {
            return false;
        }

        DateOfJoining otherDate = (DateOfJoining) other;
        return value.equals(otherDate.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}

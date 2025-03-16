package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's nationality in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidNationality(String)}
 */
public class Nationality {
    public static final String MESSAGE_CONSTRAINTS =
            "Nationality must contain only alphabetic characters and may include spaces for multi-word nationalities,"
                    + " but cannot be left blank";
    public static final String VALIDATION_REGEX = "^[a-zA-Z]+(?: [a-zA-Z]+)?$";

    public final String value;

    /**
     * Constructs a {@code Nationality}.
     *
     * @param nationality A valid Nationality.
     */
    public Nationality(String nationality) {
        requireNonNull(nationality);
        checkArgument(isValidNationality(nationality), MESSAGE_CONSTRAINTS);
        value = nationality;
    }

    public static boolean isValidNationality(String test) {
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

        if (!(other instanceof Nationality)) {
            return false;
        }

        Nationality otherNationality = (Nationality) other;
        return otherNationality.value.equals(value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}

package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.Set;

/**
 * Represents a Person's gender in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidGender(String)}
 */
public class Gender {
    public static final String MESSAGE_CONSTRAINTS =
            "Gender should only be either 'Male', 'Female' or 'Other', and should not be left blank";

    private static final Set<String> VALID_GENDERS = Set.of("Male", "Female", "Other");

    public final String value;

    /**
     * Constructs a {@code Gender}.
     *
     * @param gender A valid Gender.
     */
    public Gender(String gender) {
        requireNonNull(gender);
        checkArgument(isValidGender(gender), MESSAGE_CONSTRAINTS);
        value = capitalize(gender);
    }

    /**
     * Returns if Gender is valid
     */
    public static boolean isValidGender(String gender) {
        if (gender == null) {
            throw new NullPointerException(MESSAGE_CONSTRAINTS); // throw NullPointerException instead
        }
        return VALID_GENDERS.stream()
                .anyMatch(validGender -> validGender.equalsIgnoreCase(gender));
    }

    /**
     * Capitalizes the gender input (e.g., "male" -> "Male")
     */
    private static String capitalize(String gender) {
        return gender.substring(0, 1).toUpperCase() + gender.substring(1).toLowerCase();
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

        if (!(other instanceof Gender)) {
            return false;
        }

        Gender otherGender = (Gender) other;
        return value.equals(otherGender.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}

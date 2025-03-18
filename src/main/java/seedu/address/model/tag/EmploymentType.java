package seedu.address.model.tag;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.Set;

/**
 * Represents a Person's employment type in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidEmploymentType(String)}
 */
public class EmploymentType {

    public static final String MESSAGE_CONSTRAINTS = "Employment type can take any values, and it should not be blank";

    private static final Set<String> VALID_EMPLOYMENT_TYPES = Set.of("Full-Time", "Part-Time", "Contract",
            "Temporary", "Internship", "Freelance", "Apprenticeship", "Remote", "Hybrid");

    public final String value;

    /**
     * Constructs an {@code Employment Type}.
     *
     * @param employmentType A valid employment type.
     */
    public EmploymentType(String employmentType) {
        requireNonNull(employmentType);
        checkArgument(isValidEmploymentType(employmentType), MESSAGE_CONSTRAINTS);
        value = employmentType;
    }

    /**
     * Returns true if a given string is a valid employment type.
     */
    public static boolean isValidEmploymentType(String employmentType) {
        return VALID_EMPLOYMENT_TYPES.contains(employmentType);
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

        // instanceof handles nulls
        if (!(other instanceof seedu.address.model.tag.EmploymentType)) {
            return false;
        }

        seedu.address.model.tag.EmploymentType otherEmploymentType = (seedu.address.model.tag.EmploymentType) other;
        return value.equals(otherEmploymentType.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}


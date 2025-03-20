package seedu.address.model.tag;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.Set;

/**
 * Represents a Person's department in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidDepartment(String)}
 */
public class Department {

    public static final String MESSAGE_CONSTRAINTS = "Department can take any values, and it should not be blank";

    private static final Set<String> VALID_DEPARTMENTS = Set.of("Human Resources", "Finance", "Accounting",
            "Marketing", "Sales", "Customer Service", "Information Technology",
            "Research and Development", "Operations", "Legal",
            "Supply Chain & Logistics", "Procurement & Purchasing", "Engineering",
            "Quality Assurance", "Product Management", "Manufacturing",
            "Public Relations", "Corporate Communications",
            "Compliance & Risk Management", "Business Development", "Data Science",
            "Cybersecurity", "Software Development", "UX/UI Design",
            "Artificial Intelligence & Machine Learning", "Training & Development",
            "Facilities Management", "Administration", "Health & Safety",
            "Diversity, Equity & Inclusion");

    public final String value;

    /**
     * Constructs an {@code Department}.
     *
     * @param department A valid department.
     */
    public Department(String department) {
        requireNonNull(department);
        checkArgument(isValidDepartment(department), MESSAGE_CONSTRAINTS);
        value = department;
    }

    /**
     * Returns true if a given string is a valid department.
     */
    public static boolean isValidDepartment(String department) {
        return VALID_DEPARTMENTS.contains(department);
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
        if (!(other instanceof seedu.address.model.tag.Department)) {
            return false;
        }

        seedu.address.model.tag.Department otherDepartment = (seedu.address.model.tag.Department) other;
        return value.equals(otherDepartment.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}


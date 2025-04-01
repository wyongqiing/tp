package seedu.address.model.tag;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.model.tag.Department.isValidDepartment;
import static seedu.address.model.tag.EmploymentType.isValidEmploymentType;
import static seedu.address.model.tag.JobTitle.isValidJobTitle;

import java.util.Objects;

/**
 * Represents a Tag in the address book.
 * Guarantees: immutable; name is valid as declared in {@link #isValidTagName(String[])}
 */
public class Tag {

    public static final String MESSAGE_CONSTRAINTS = "Tags names should be alphanumeric";
    public static final String VALIDATION_REGEX = "\\p{Alnum}+";

    private final Department department;

    private final EmploymentType employmentType;

    private final JobTitle jobTitle;

    /**
     * Constructs a {@code Tag}.
     *
     * @param department A valid department name.
     * @param employmentType A valid employment type.
     * @param jobTitle A valid job title.
     */
    public Tag(Department department, EmploymentType employmentType, JobTitle jobTitle) {
        requireNonNull(department);
        checkArgument(isValidDepartment(department.value), Department.MESSAGE_CONSTRAINTS);
        this.department = department;

        requireNonNull(employmentType);
        checkArgument(isValidEmploymentType(employmentType.value), EmploymentType.MESSAGE_CONSTRAINTS);
        this.employmentType = employmentType;

        requireNonNull(jobTitle);
        checkArgument(isValidJobTitle(jobTitle.value), JobTitle.MESSAGE_CONSTRAINTS);
        this.jobTitle = jobTitle;
    }

    /**
     * Returns true if a given string is a valid tag name.
     */
    public static boolean isValidTagName(String[] tagList) {
        return isValidDepartment(tagList[0])
                && isValidEmploymentType(tagList[1])
                && isValidJobTitle(tagList[2]);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Tag)) {
            return false;
        }

        Tag otherTag = (Tag) other;
        return department.equals(otherTag.department)
                && employmentType.equals(otherTag.employmentType)
                && jobTitle.equals(otherTag.jobTitle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(department, employmentType, jobTitle);
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return "[Department: " + department.value
                + ", Employment Type: " + employmentType.value
                + ", Job Title: " + jobTitle.value + ']';
    }

    public String[] getValue() {
        String[] result = new String[3];
        result[0] = department.value;
        result[1] = employmentType.value;
        result[2] = jobTitle.value;
        return result;
    }

}

package seedu.address.model.tag;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.Set;

/**
 * Represents a Person's job title in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidJobTitle(String)}
 */
public class JobTitle {

    public static final String MESSAGE_CONSTRAINTS = "Job title must contain alphabetic characters, "
            + "and it should not be blank";

    private static final Set<String> VALID_JOB_TITLES = Set.of("Software Engineer", "Data Analyst", "Product Manager",
            "HR Coordinator", "Marketing Specialist", "Sales Associate",
            "Financial Analyst", "Operations Manager", "UX Designer",
            "Project Manager", "Business Consultant", "Mechanical Engineer",
            "Graphic Designer", "Customer Support Representative", "IT Technician",
            "Electrical Engineer", "Legal Advisor", "Healthcare Administrator",
            "Content Writer", "Cybersecurity Analyst", "Network Engineer",
            "Quality Assurance Tester", "Recruitment Specialist",
            "Social Media Manager", "Supply Chain Manager");

    public final String value;

    /**
     * Constructs an {@code Job Title}.
     *
     * @param jobTitle A valid job title.
     */
    public JobTitle(String jobTitle) {
        requireNonNull(jobTitle);
        checkArgument(isValidJobTitle(jobTitle), MESSAGE_CONSTRAINTS);
        value = jobTitle;
    }

    /**
     * Returns true if a given string is a valid job title.
     */
    public static boolean isValidJobTitle(String jobTitle) {
        return VALID_JOB_TITLES.contains(jobTitle);
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
        if (!(other instanceof seedu.address.model.tag.JobTitle)) {
            return false;
        }

        seedu.address.model.tag.JobTitle otherJobTitle = (seedu.address.model.tag.JobTitle) other;
        return value.equals(otherJobTitle.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}


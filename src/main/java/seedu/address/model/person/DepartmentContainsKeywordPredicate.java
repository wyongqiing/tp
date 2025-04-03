package seedu.address.model.person;

import java.util.function.Predicate;

import seedu.address.model.tag.Department;

/**
 * Tests whether a {@code Person}'s department matches a given keyword.
 */
public class DepartmentContainsKeywordPredicate implements Predicate<Person> {
    private final String keyword;

    /**
     * Constructs a predicate to test if a person's department contains the given keyword.
     *
     * @param keyword The keyword to match against the person's department.
     */
    public DepartmentContainsKeywordPredicate(String keyword) {
        this.keyword = keyword.toLowerCase();
    }

    @Override
    public boolean test(Person person) {
        String department = person.getTag().getValue()[0]; // Get department from tag

        // Check for exact match with department name (case insensitive)
        if (department.equalsIgnoreCase(keyword)) {
            return true;
        }

        // Check if the keyword is a valid short form
        try {
            // If the keyword is a valid short form, it will be mapped to a full department
            String mappedDepartment = Department.mapInput(keyword);

            // Check if the mapped department matches the person's department
            return department.equalsIgnoreCase(mappedDepartment);
        } catch (IllegalArgumentException e) {
            // If mapping fails, it means the keyword is not a valid short form
            // Check for partial match with department name (case insensitive)
            if (keyword.length() >= 3) { // Only consider partial matches for keywords of 3+ characters
                return department.toLowerCase().contains(keyword);
            }

            return false;
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof DepartmentContainsKeywordPredicate)) {
            return false;
        }

        DepartmentContainsKeywordPredicate otherPredicate = (DepartmentContainsKeywordPredicate) other;
        return keyword.equals(otherPredicate.keyword);
    }
}


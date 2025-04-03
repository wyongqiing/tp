package seedu.address.model.person;

import java.util.function.Predicate;

/**
 * Tests whether a {@code Person}'s employment type matches a given keyword.
 */
public class EmploymentTypeContainsKeywordPredicate implements Predicate<Person> {
    private static final int MIN_KEYWORD_LENGTH = 3;
    private final String keyword;

    /**
     * Constructs a predicate to test if a person's employment type matches the given keyword.
     *
     * @param keyword The keyword to match against the person's employment type.
     */
    public EmploymentTypeContainsKeywordPredicate(String keyword) {
        this.keyword = keyword.toLowerCase();
    }

    @Override
    public boolean test(Person person) {
        String employmentType = person.getTag().getValue()[1]; // Get employment type from tag

        // For multi-word searches, treat as a single phrase
        if (keyword.contains(" ")) {
            // Check if the entire phrase appears in the employment type
            return employmentType.toLowerCase().equals(keyword);
        }

        // For single-word searches
        // Exact match for the employment type (case insensitive)
        if (employmentType.toLowerCase().equals(keyword)) {
            return true;
        }

        // Check the specific common types (Full-time, Part-time, Contract, etc.)
        // Assuming these are the main employment types you want to support
        switch (keyword) {
        case "full-time":
        case "full":
        case "fulltime":
            return employmentType.toLowerCase().equals("full-time");

        case "part-time":
        case "part":
        case "parttime":
            return employmentType.toLowerCase().equals("part-time");

        case "contract":
        case "contractor":
            return employmentType.toLowerCase().equals("contract");

        case "intern":
        case "internship":
            return employmentType.toLowerCase().equals("intern");

        case "temp":
        case "temporary":
            return employmentType.toLowerCase().equals("temporary");

        default:
            // If keyword length >= 3, also check for prefix match
            return keyword.length() >= MIN_KEYWORD_LENGTH
                    && employmentType.toLowerCase().startsWith(keyword);
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof EmploymentTypeContainsKeywordPredicate)) {
            return false;
        }

        EmploymentTypeContainsKeywordPredicate otherPredicate = (EmploymentTypeContainsKeywordPredicate) other;
        return keyword.equals(otherPredicate.keyword);
    }
}


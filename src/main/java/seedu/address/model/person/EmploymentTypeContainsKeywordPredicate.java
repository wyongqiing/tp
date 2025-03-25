package seedu.address.model.person;

import java.util.function.Predicate;

/**
 * Tests whether a {@code Person}'s employment type matches a given keyword.
 */
public class EmploymentTypeContainsKeywordPredicate implements Predicate<Person> {
    private final String keyword;

    /**
     * Constructs a predicate to test if a person's employment type contains the given keyword.
     *
     * @param keyword The keyword to match against the person's employment type.
     */
    public EmploymentTypeContainsKeywordPredicate(String keyword) {
        this.keyword = keyword.toLowerCase();
    }

    @Override
    public boolean test(Person person) {
        String employmentType = person.getTag().getValue()[1]; // Get employment type from tag
        return employmentType.toLowerCase().contains(keyword);
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

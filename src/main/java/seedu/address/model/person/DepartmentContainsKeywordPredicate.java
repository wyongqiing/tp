package seedu.address.model.person;

import java.util.function.Predicate;

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
        return department.toLowerCase().contains(keyword);
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

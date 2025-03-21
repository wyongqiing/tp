package seedu.address.model.person;

import java.util.function.Predicate;

public class EmploymentTypeContainsKeywordPredicate implements Predicate<Person> {
    private final String keyword;

    public EmploymentTypeContainsKeywordPredicate(String keyword) {
        this.keyword = keyword.toLowerCase();
    }

    @Override
    public boolean test(Person person) {
        // Check if the employment type value contains the keyword
        String employmentType = person.getTag().getValue()[1];  // Get the employment type from the tag
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

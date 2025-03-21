package seedu.address.model.person;

import java.util.function.Predicate;

public class DepartmentContainsKeywordPredicate implements Predicate<Person> {
    private final String keyword;

    public DepartmentContainsKeywordPredicate(String keyword) {
        this.keyword = keyword.toLowerCase();
    }

    @Override
    public boolean test(Person person) {
        // Check if the department value contains the keyword
        String department = person.getTag().getValue()[0];  // Get the department from the tag
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

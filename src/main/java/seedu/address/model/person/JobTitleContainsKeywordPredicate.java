package seedu.address.model.person;

import java.util.function.Predicate;

public class JobTitleContainsKeywordPredicate implements Predicate<Person> {
    private final String keyword;

    public JobTitleContainsKeywordPredicate(String keyword) {
        this.keyword = keyword.toLowerCase();
    }

    @Override
    public boolean test(Person person) {
        // Check if the job title value contains the keyword
        String jobTitle = person.getTag().getValue()[2];  // Get the job title from the tag
        return jobTitle.toLowerCase().contains(keyword);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof JobTitleContainsKeywordPredicate)) {
            return false;
        }

        JobTitleContainsKeywordPredicate otherPredicate = (JobTitleContainsKeywordPredicate) other;
        return keyword.equals(otherPredicate.keyword);
    }
}

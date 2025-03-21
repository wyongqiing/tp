package seedu.address.model.person;

import java.util.function.Predicate;

/**
 * Tests whether a {@code Person}'s job title matches a given keyword.
 */
public class JobTitleContainsKeywordPredicate implements Predicate<Person> {
    private final String keyword;

    /**
     * Constructs a predicate to test if a person's job title contains the given keyword.
     *
     * @param keyword The keyword to match against the person's job title.
     */
    public JobTitleContainsKeywordPredicate(String keyword) {
        this.keyword = keyword.toLowerCase();
    }

    @Override
    public boolean test(Person person) {
        String jobTitle = person.getTag().getValue()[2]; // Get job title from tag
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

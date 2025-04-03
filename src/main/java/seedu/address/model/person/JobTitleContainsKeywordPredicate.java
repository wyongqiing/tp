package seedu.address.model.person;

import java.util.function.Predicate;

/**
 * Tests whether a {@code Person}'s job title matches a given keyword.
 */
public class JobTitleContainsKeywordPredicate implements Predicate<Person> {
    private final String keyword;

    /**
     * Constructs a predicate to test if a person's job title matches the given keyword.
     *
     * @param keyword The keyword to match against the person's job title.
     */
    public JobTitleContainsKeywordPredicate(String keyword) {
        this.keyword = keyword.toLowerCase();
    }

    @Override
    public boolean test(Person person) {
        String jobTitle = person.getTag().getValue()[2]; // Get job title from tag

        // For multi-word searches, treat as a single phrase
        if (keyword.contains(" ")) {
            // Check if the entire phrase appears in the job title
            return jobTitle.toLowerCase().contains(keyword);
        }

        // For single-word searches, check if it's a complete word
        String[] jobTitleWords = jobTitle.toLowerCase().split("\\s+");

        for (String word : jobTitleWords) {
            // Exact match for the word
            if (word.equals(keyword)) {
                return true;
            }

            // If keyword length >= 3, also check for prefix match
            if (keyword.length() >= 3 && word.startsWith(keyword)) {
                return true;
            }
        }

        return false;
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


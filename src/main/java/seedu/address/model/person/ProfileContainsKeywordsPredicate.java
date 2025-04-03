package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;

/**
 * Tests that a {@code Person}'s full name or surname matches the keywords given
 */
public class ProfileContainsKeywordsPredicate implements Predicate<Person> {
    private final List<String> keywords;

    public ProfileContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        // Get the person's full name and split into parts
        String personName = person.getName().fullName.toLowerCase();
        String[] personNameParts = personName.split("\\s+");

        // Join all search keywords into a single string
        String fullSearchName = String.join(" ", keywords).toLowerCase();

        // Case 1: Full search term matches the full name exactly
        if (personName.equals(fullSearchName)) {
            return true;
        }

        // Case 2: Single keyword matches the surname (last part of name) exactly
        if (keywords.size() == 1) {
            String surname = personNameParts[personNameParts.length - 1];
            String searchTerm = keywords.get(0).toLowerCase();

            if (surname.equals(searchTerm)) {
                return true;
            }

            // Case 3: For multi-word names, check if the single keyword matches any complete name part
            for (String namePart : personNameParts) {
                if (namePart.equals(searchTerm)) {
                    return true;
                }
            }
        }

        // Case 4: Multiple keywords - check if they form a complete subset of the full name
        // For example, "Mary Jane" would match "Mary Jane Watson"
        if (keywords.size() > 1 && personName.startsWith(fullSearchName)) {
            return true;
        }

        return false;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ProfileContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((ProfileContainsKeywordsPredicate) other).keywords)); // state check
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keywords", keywords).toString();
    }
}


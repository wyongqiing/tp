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
        //Join keywords into a single lowercase string, trimming extra spaces
        String inputName = String.join(" ", keywords).replaceAll("\\s+", " ").trim().toLowerCase();
        String personName = person.getName().fullName.replaceAll("\\s+", " ").trim().toLowerCase();

        // 1. Full name exact match
        if (inputName.equals(personName)) {
            return true;
        }

        // 2. Last word (surname) match
        String[] nameParts = personName.split(" ");
        String lastName = nameParts[nameParts.length - 1];
        return inputName.equals(lastName);

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

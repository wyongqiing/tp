package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.person.ProfileContainsKeywordsPredicate;

public class ViewCommandTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager();
    }

    @Test
    public void toStringMethod() {
        ProfileContainsKeywordsPredicate predicate = new ProfileContainsKeywordsPredicate(Arrays.asList("keyword"));
        ViewCommand findCommand = new ViewCommand(predicate);
        String expected = ViewCommand.class.getCanonicalName() + "{predicate=" + predicate + "}";
        assertEquals(expected, findCommand.toString());
    }

    @Test
    public void equals() {
        ProfileContainsKeywordsPredicate firstPredicate =
                new ProfileContainsKeywordsPredicate(Collections.singletonList("first"));
        ProfileContainsKeywordsPredicate secondPredicate =
                new ProfileContainsKeywordsPredicate(Collections.singletonList("second"));

        ViewCommand viewFirstCommand = new ViewCommand(firstPredicate);
        ViewCommand viewSecondCommand = new ViewCommand(secondPredicate);

        // same object -> returns true
        assertTrue(viewFirstCommand.equals(viewFirstCommand));

        // same values -> returns true
        ViewCommand viewFirstCommandCopy = new ViewCommand(firstPredicate);
        assertTrue(viewFirstCommand.equals(viewFirstCommandCopy));

        // different types -> returns false
        assertFalse(viewFirstCommand.equals(1));

        // null -> returns false
        assertFalse(viewFirstCommand.equals(null));

        // different predicate -> returns false
        assertFalse(viewFirstCommand.equals(viewSecondCommand));
    }

    @Test
    public void execute_personFound_success() {
        // Keyword matches the person's name "Alex"
        ProfileContainsKeywordsPredicate predicate = new ProfileContainsKeywordsPredicate(List.of("Alex"));
        ViewCommand viewCommand = new ViewCommand(predicate);

        // Update model expected state
        model.updateFilteredPersonList(predicate);
        // Execute command
        CommandResult result = viewCommand.execute(model);

        String expectedMessage = String.format(
                "%s's profile:",
                model.getFilteredPersonList().stream()
                        .map(person -> person.getName().fullName)
                        .reduce((name1, name2) -> name1 + ", " + name2)
                        .orElse("No matching profile found")
        );

        assertEquals(expectedMessage, result.getFeedbackToUser());
    }

    @Test
    public void execute_personNotFound_returnsNoMatch() {
        // Non-matching keyword
        ProfileContainsKeywordsPredicate predicate = new ProfileContainsKeywordsPredicate(List.of("Bob"));
        ViewCommand viewCommand = new ViewCommand(predicate);

        CommandResult result = viewCommand.execute(model);

        String expectedMessage = String.format(
                "%s's profile:",
                "No matching profile found"
        );

        assertEquals(expectedMessage, result.getFeedbackToUser());
    }
}

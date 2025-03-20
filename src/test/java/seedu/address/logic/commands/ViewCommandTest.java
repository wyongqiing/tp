package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;

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
}

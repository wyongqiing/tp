package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.JobTitleContainsKeywordPredicate;

public class FindByJobTitleCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        JobTitleContainsKeywordPredicate firstPredicate =
                new JobTitleContainsKeywordPredicate("Engineer");
        JobTitleContainsKeywordPredicate secondPredicate =
                new JobTitleContainsKeywordPredicate("Manager");

        FindByJobTitleCommand findFirstCommand = new FindByJobTitleCommand(firstPredicate);
        FindByJobTitleCommand findSecondCommand = new FindByJobTitleCommand(secondPredicate);

        assertTrue(findFirstCommand.equals(findFirstCommand));
        FindByJobTitleCommand findFirstCommandCopy = new FindByJobTitleCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));
        assertFalse(findFirstCommand.equals(1));
        assertFalse(findFirstCommand.equals(null));
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_noJobTitle_foundNoPersons() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        JobTitleContainsKeywordPredicate predicate = preparePredicate("CEO");
        FindByJobTitleCommand command = new FindByJobTitleCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    // @Test
    // public void execute_multipleJobTitles_multiplePersonsFound() {
    //     String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
    //     JobTitleContainsKeywordPredicate predicate = preparePredicate("Engineer");
    //     FindByJobTitleCommand command = new FindByJobTitleCommand(predicate);
    //     expectedModel.updateFilteredPersonList(predicate);
    //     assertCommandSuccess(command, model, expectedMessage, expectedModel);
    //     assertEquals(Arrays.asList(CARL, ELLE, FIONA), model.getFilteredPersonList());
    // }

    private JobTitleContainsKeywordPredicate preparePredicate(String userInput) {
        return new JobTitleContainsKeywordPredicate(userInput.trim());
    }
}

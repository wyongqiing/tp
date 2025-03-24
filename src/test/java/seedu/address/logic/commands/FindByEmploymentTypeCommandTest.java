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
import seedu.address.model.person.EmploymentTypeContainsKeywordPredicate;

public class FindByEmploymentTypeCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        EmploymentTypeContainsKeywordPredicate firstPredicate =
                new EmploymentTypeContainsKeywordPredicate("Full-time");
        EmploymentTypeContainsKeywordPredicate secondPredicate =
                new EmploymentTypeContainsKeywordPredicate("Part-time");

        FindByEmploymentTypeCommand findFirstCommand = new FindByEmploymentTypeCommand(firstPredicate);
        FindByEmploymentTypeCommand findSecondCommand = new FindByEmploymentTypeCommand(secondPredicate);

        assertTrue(findFirstCommand.equals(findFirstCommand));
        FindByEmploymentTypeCommand findFirstCommandCopy = new FindByEmploymentTypeCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));
        assertFalse(findFirstCommand.equals(1));
        assertFalse(findFirstCommand.equals(null));
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_noEmploymentType_foundNoPersons() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        EmploymentTypeContainsKeywordPredicate predicate = preparePredicate("UnknownEmploymentType");
        FindByEmploymentTypeCommand command = new FindByEmploymentTypeCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    // @Test
    // public void execute_multipleEmploymentTypes_multiplePersonsFound() {
    //     String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
    //     EmploymentTypeContainsKeywordPredicate predicate = preparePredicate("Full-time");
    //     FindByEmploymentTypeCommand command = new FindByEmploymentTypeCommand(predicate);
    //     expectedModel.updateFilteredPersonList(predicate);
    //     assertCommandSuccess(command, model, expectedMessage, expectedModel);
    //     assertEquals(Arrays.asList(CARL, ELLE, FIONA), model.getFilteredPersonList());
    // }

    private EmploymentTypeContainsKeywordPredicate preparePredicate(String userInput) {
        return new EmploymentTypeContainsKeywordPredicate(userInput.trim());
    }
}

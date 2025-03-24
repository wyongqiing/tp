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
import seedu.address.model.person.DepartmentContainsKeywordPredicate;

public class FindByDepartmentCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        DepartmentContainsKeywordPredicate firstPredicate =
                new DepartmentContainsKeywordPredicate("Human Resources");
        DepartmentContainsKeywordPredicate secondPredicate =
                new DepartmentContainsKeywordPredicate("Finance");

        FindByDepartmentCommand findFirstCommand = new FindByDepartmentCommand(firstPredicate);
        FindByDepartmentCommand findSecondCommand = new FindByDepartmentCommand(secondPredicate);

        assertTrue(findFirstCommand.equals(findFirstCommand));
        FindByDepartmentCommand findFirstCommandCopy = new FindByDepartmentCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));
        assertFalse(findFirstCommand.equals(1));
        assertFalse(findFirstCommand.equals(null));
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_noDepartment_foundNoPersons() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        DepartmentContainsKeywordPredicate predicate = preparePredicate("UnknownDept");
        FindByDepartmentCommand command = new FindByDepartmentCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    // @Test
    // public void execute_multipleDepartments_multiplePersonsFound() {
    //     String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
    //     DepartmentContainsKeywordPredicate predicate = preparePredicate("HR Finance");
    //     FindByDepartmentCommand command = new FindByDepartmentCommand(predicate);
    //     expectedModel.updateFilteredPersonList(predicate);
    //     assertCommandSuccess(command, model, expectedMessage, expectedModel);
    //     assertEquals(Arrays.asList(CARL, ELLE, FIONA), model.getFilteredPersonList());
    // }

    private DepartmentContainsKeywordPredicate preparePredicate(String userInput) {
        return new DepartmentContainsKeywordPredicate(userInput.trim());
    }
}

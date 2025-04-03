package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;

public class FindCommandTest {
    public static final String ERROR_MESSAGE = "Try using 'findByDepartment', "
                                                + "'findByEmploymentType', or 'findByJobTitle' instead.";

    private Model model = new ModelManager();

    @Test
    public void execute_invalidCommand_showsErrorMessage() {
        // Create the FindCommand and execute it
        FindCommand command = new FindCommand();
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, ERROR_MESSAGE);
        // Execute the command and check the message
        CommandResult result = command.execute(model);
        assertEquals(expectedMessage, result.getFeedbackToUser());
    }
}

package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;

public class FindCommandTest {
    private Model model = new ModelManager();

    @Test
    public void execute_invalidCommand_showsErrorMessage() {
        // Create the FindCommand and execute it
        FindCommand command = new FindCommand();
        String expectedMessage = MESSAGE_INVALID_COMMAND_FORMAT
                + "\nTry using 'findByDepartment', 'findByEmploymentType', or 'findByJobTitle' instead.";

        // Execute the command and check the message
        CommandResult result = command.execute(model);
        assertEquals(expectedMessage, result.getFeedbackToUser());
    }
}

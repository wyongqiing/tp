package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;

public class ViewCommandTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager();
    }

    @Test
    public void execute_viewCommand_success() {
        ViewCommand viewCommand = new ViewCommand();
        CommandResult result = viewCommand.execute(model);
        assertEquals("Hello from view", result.getFeedbackToUser());
    }
}

package seedu.address.logic.commands;

import seedu.address.model.Model;

/**
 * View the person's profile
 */
public class ViewCommand extends Command {

    public static final String COMMAND_WORD = "view";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult("Hello from view");
    }
}

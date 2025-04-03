package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.model.Model;

/**
 * Command to display an error message when an invalid 'find' command is used,
 * and suggest alternative search commands.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows error message that this command is invalid. "
            + "Use 'findByDepartment', 'findByEmploymentType',"
            + "or 'findByJobTitle' to search with the specified criteria.\n"
            + "Example: " + COMMAND_WORD;

    public static final String ERROR_MESSAGE = "Try using 'findByDepartment', "
                                                + "'findByEmploymentType', or 'findByJobTitle' instead.";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        // Output invalid command message and suggest alternatives
        return new CommandResult(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ERROR_MESSAGE));
    }
}

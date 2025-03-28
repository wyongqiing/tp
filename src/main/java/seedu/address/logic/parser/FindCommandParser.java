package seedu.address.logic.parser;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new FindCommand object that returns an error.
 */
public class FindCommandParser implements Parser<FindCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a Command object for execution.
     * @throws ParseException if the user input does not conform to the expected format
     */
    @Override
    public FindCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();

        // No arguments should be processed for the "find" command, it just throws an error
        if (!trimmedArgs.isEmpty()) {
            throw new ParseException("Invalid command format! Please use 'findByDepartment',"
            + "'findByEmploymentType', or 'findByJobTitle' instead.");
        }

        return new FindCommand(); // Simply return the FindCommand to show the error message
    }
}

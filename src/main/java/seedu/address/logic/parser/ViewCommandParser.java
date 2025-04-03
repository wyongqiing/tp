package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;
import java.util.regex.Pattern;

import seedu.address.logic.commands.ViewCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.ProfileContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new ViewCommand object
 */
public class ViewCommandParser implements Parser<ViewCommand> {

    //letter and spaces only
    private static final Pattern VALID_NAME_PATTERN = Pattern.compile("^[a-zA-Z\\s]+$");

    /**
     * Parses the given {@code String} of arguments in the context of the ViewCommand
     * and returns a ViewCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ViewCommand parse(String args) throws ParseException {

        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.MESSAGE_USAGE));
        }

        // Check if input contains only letters and spaces
        if (!VALID_NAME_PATTERN.matcher(trimmedArgs).matches()) {
            throw new ParseException("Names should only contain alphabetical characters and spaces.");
        }

        String[] profileKeywords = trimmedArgs.split("\\s+");

        return new ViewCommand(new ProfileContainsKeywordsPredicate(Arrays.asList(profileKeywords)));
    }

}

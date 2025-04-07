package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.FindByJobTitleCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.JobTitleContainsKeywordPredicate;

/**
 * Parses input arguments and creates a new FindByJobTitleCommand object
 */
public class FindByJobTitleCommandParser implements Parser<FindByJobTitleCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the FindByJobTitleCommand
     * and returns a FindByJobTitleCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindByJobTitleCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindByJobTitleCommand.MESSAGE_USAGE));
        }

        // Validate that job title only contains alphabetic characters and spaces
        if (!isValidJobTitleCharacters(trimmedArgs)) {
            throw new ParseException(FindByJobTitleCommand.MESSAGE_JOBTITLE_CONSTRAINTS);
        }

        return new FindByJobTitleCommand(new JobTitleContainsKeywordPredicate(trimmedArgs));
    }

    /**
     * Returns true if the job title contains only alphabetic characters and spaces.
     */
    private boolean isValidJobTitleCharacters(String jobTitle) {
        return jobTitle.chars()
                .allMatch(c -> Character.isLetter(c) || Character.isWhitespace(c));
    }
}

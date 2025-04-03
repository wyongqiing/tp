package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.FindByEmploymentTypeCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.EmploymentTypeContainsKeywordPredicate;

/**
 * Parses input arguments and creates a new FindByEmploymentTypeCommand object
 */
public class FindByEmploymentTypeCommandParser implements Parser<FindByEmploymentTypeCommand> {

    public static final String MESSAGE_EMPLOYMENTTYPE_CONSTRAINTS =
            "Employment type search terms should only contain alphabetic characters, hyphens, and spaces";

    /**
     * Parses the given {@code String} of arguments in the context of the FindByEmploymentTypeCommand
     * and returns a FindByEmploymentTypeCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindByEmploymentTypeCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindByEmploymentTypeCommand.MESSAGE_USAGE));
        }

        // Validate that employment type only contains alphabetic characters, hyphens, and spaces
        if (!isValidEmploymentTypeCharacters(trimmedArgs)) {
            throw new ParseException(MESSAGE_EMPLOYMENTTYPE_CONSTRAINTS);
        }

        // Now we only have the employment type keyword, so we can directly pass it to the predicate
        return new FindByEmploymentTypeCommand(new EmploymentTypeContainsKeywordPredicate(trimmedArgs));
    }

    /**
     * Returns true if the employment type contains only alphabetic characters, hyphens, and spaces.
     */
    private boolean isValidEmploymentTypeCharacters(String employmentType) {
        return employmentType.chars()
                .allMatch(c -> Character.isLetter(c) || c == '-' || Character.isWhitespace(c));
    }
}


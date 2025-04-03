package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.FindByDepartmentCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.DepartmentContainsKeywordPredicate;

/**
 * Parses input arguments and creates a new FindByDepartmentCommand object
 */
public class FindByDepartmentCommandParser implements Parser<FindByDepartmentCommand> {

    public static final String MESSAGE_DEPARTMENT_CONSTRAINTS =
            "Department names should only contain alphabetic characters and '&' symbols";

    /**
     * Parses the given {@code String} of arguments in the context of the FindByDepartmentCommand
     * and returns a FindByDepartmentCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindByDepartmentCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindByDepartmentCommand.MESSAGE_USAGE));
        }

        // Validate that department only contains alphabetic characters or '&'
        if (!isValidDepartment(trimmedArgs)) {
            throw new ParseException(MESSAGE_DEPARTMENT_CONSTRAINTS);
        }

        // Now we only have the department keyword, so we can directly pass it to the predicate
        return new FindByDepartmentCommand(new DepartmentContainsKeywordPredicate(trimmedArgs));
    }

    /**
     * Returns true if the department consists only of alphabetic characters and '&' symbols.
     */
    private boolean isValidDepartment(String department) {
        return department.chars()
                .allMatch(c -> Character.isLetter(c) || c == '&' || Character.isWhitespace(c));
    }
}

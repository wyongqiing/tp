package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.DepartmentContainsKeywordPredicate;

/**
 * Finds and lists all persons in address book whose department contains the specified keyword.
 */
public class FindByDepartmentCommand extends Command {

    public static final String COMMAND_WORD = "findByDepartment";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Finds all persons whose department contains the specified keyword.\n"
            + "Parameters: DEPARTMENT_KEYWORD\n"
            + "Example: " + COMMAND_WORD + " HR";

    private final DepartmentContainsKeywordPredicate predicate;

    public FindByDepartmentCommand(DepartmentContainsKeywordPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof FindByDepartmentCommand)) {
            return false;
        }

        FindByDepartmentCommand otherFindCommand = (FindByDepartmentCommand) other;
        return predicate.equals(otherFindCommand.predicate);
    }
}

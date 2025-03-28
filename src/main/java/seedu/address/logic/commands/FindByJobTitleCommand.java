package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.JobTitleContainsKeywordPredicate;

/**
 * Finds and lists all persons in address book whose department contains the specified keyword.
 */
public class FindByJobTitleCommand extends Command {

    public static final String COMMAND_WORD = "findByJobTitle";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Finds all persons whose job title contains the specified keyword.\n"
            + "Parameters:  EMPLOYMENTTYPE_KEYWORD\n"
            + "Example: " + COMMAND_WORD + " HR Coordinator";

    private final JobTitleContainsKeywordPredicate predicate;

    public FindByJobTitleCommand(JobTitleContainsKeywordPredicate predicate) {
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

        if (!(other instanceof FindByJobTitleCommand)) {
            return false;
        }

        FindByJobTitleCommand otherFindCommand = (FindByJobTitleCommand) other;
        return predicate.equals(otherFindCommand.predicate);
    }
}

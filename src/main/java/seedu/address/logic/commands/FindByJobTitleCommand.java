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
            + ": Finds all persons whose job title matches the specified keyword.\n"
            + "The search is case-insensitive and supports:\n"
            + "- Exact job title matching\n"
            + "- Partial matching with at least 3 consecutive letters\n"
            + "Parameters: JOBTITLE_KEYWORD\n"
            + "Examples:\n"
            + "- " + COMMAND_WORD + " Engineer (finds all Engineers)\n"
            + "- " + COMMAND_WORD + " Dev (finds all Developers with 'Dev' in their title)\n"
            + "- " + COMMAND_WORD + " Coord (finds all Coordinators)";

    public static final String MESSAGE_JOBTITLE_CONSTRAINTS = "Job title search terms should only contain alphabetic "
            + "characters and spaces. It should not be blank.\n"
            + "Search terms must be at least 3 characters long for partial matching.\n"
            + "Examples:\n"
            + "- Engineer\n"
            + "- Dev (matches Developer)\n"
            + "- Coord (matches Coordinator)";

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

package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.ProfileContainsKeywordsPredicate;

/**
 * View the person's profile
 */
public class ViewCommand extends Command {

    public static final String COMMAND_WORD = "view";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Views the profile "
            + "by the fullname or surname. "
            + "Existing profile will be shown.\n"
            + "Parameters: FULLNAME or PARTIALNAME\n"
            + "Example: [" + COMMAND_WORD + " Tan] / [" + COMMAND_WORD + " Alex Tan]";

    private final ProfileContainsKeywordsPredicate predicate;

    public ViewCommand(ProfileContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(predicate);
        if (model.getFilteredPersonList().isEmpty()) {
            return new CommandResult(Messages.MESSAGE_PROFILE_NOT_FOUND);
        }

        String names = model.getFilteredPersonList().stream()
                .map(person -> person.getName().fullName)
                .reduce((name1, name2) -> name1 + ", " + name2)
                .orElse("");

        return new CommandResult(String.format(Messages.MESSAGE_PROFILE_FOUND, names));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ViewCommand)) {
            return false;
        }

        ViewCommand otherViewCommand = (ViewCommand) other;
        return predicate.equals(otherViewCommand.predicate);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("predicate", predicate)
                .toString();
    }
}

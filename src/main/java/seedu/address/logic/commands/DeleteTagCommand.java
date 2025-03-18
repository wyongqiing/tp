package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;

/**
 * Deletes a specific tag from a person.
 */
public class DeleteTagCommand extends Command {

    public static final String COMMAND_WORD = "deleteTag";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes a tag from the person identified by the index number in the displayed person list.\n"
            + "Parameters: INDEX (must be a positive integer) TAG_NAME\n"
            + "Example: " + COMMAND_WORD + " 1 friend";

    public static final String MESSAGE_DELETE_TAG_SUCCESS = "Deleted tag '%2$s' from: %1$s";
    public static final String MESSAGE_TAG_NOT_FOUND = "Tag '%2$s' not found in: %1$s";
    public static final String MESSAGE_CANNOT_DELETE_LAST_TAG =
        "Cannot delete the last tag. Person must have at least one tag: %s";

    private final Index targetIndex;
    private final Tag tagToDelete;

    /**
     * Creates a DeleteTagCommand to remove a specific tag from a person.
     *
     * @param targetIndex The index of the person in the filtered person list.
     * @param tagToDelete The tag to be removed from the person.
     */
    public DeleteTagCommand(Index targetIndex, Tag tagToDelete) {
        this.targetIndex = targetIndex;
        this.tagToDelete = tagToDelete;
    }

    /**
     * Executes the DeleteTagCommand and removes the specified tag from the person.
     * Ensures the tag exists before attempting deletion.
     *
     * @param model The model containing the address book.
     * @return CommandResult with success message if deletion succeeds.
     * @throws CommandException If the person index is invalid or the tag does not exist.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToModify = lastShownList.get(targetIndex.getZeroBased());

        if (!personToModify.getTags().contains(tagToDelete)) {
            throw new CommandException(String.format(MESSAGE_TAG_NOT_FOUND,
                Messages.format(personToModify), tagToDelete));
        }

        // Create new set of tags without the specified tag
        Set<Tag> updatedTags = new HashSet<>(personToModify.getTags());
        updatedTags.remove(tagToDelete);

        // Prevent removing the last tag
        if (updatedTags.isEmpty()) {
            throw new CommandException(String.format(MESSAGE_CANNOT_DELETE_LAST_TAG, Messages.format(personToModify)));
        }

        // Create a new Person with the updated tags
        Person updatedPerson = new Person(
                personToModify.getName(),
                personToModify.getPhone(),
                personToModify.getEmail(),
                personToModify.getGender(),
                personToModify.getDob(),
                personToModify.getDateOfJoining(),
                personToModify.getNationality(),
                personToModify.getAddress(),
                updatedTags
        );

        model.setPerson(personToModify, updatedPerson);
        return new CommandResult(String.format(MESSAGE_DELETE_TAG_SUCCESS,
            Messages.format(updatedPerson), tagToDelete));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof DeleteTagCommand)) {
            return false;
        }

        DeleteTagCommand otherCommand = (DeleteTagCommand) other;
        return targetIndex.equals(otherCommand.targetIndex) && tagToDelete.equals(otherCommand.tagToDelete);
    }

    @Override
    public String toString() {
        return String.format("DeleteTagCommand: Index=%s, Tag=%s", targetIndex, tagToDelete);
    }
}

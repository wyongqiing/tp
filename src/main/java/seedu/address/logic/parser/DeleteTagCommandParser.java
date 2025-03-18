package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteTagCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tag.Tag;

/**
 * Parses user input to create a {@code DeleteTagCommand}.
 * This parser extracts the person's index and the tag to be deleted,
 * ensuring proper formatting before creating the command.
 */
public class DeleteTagCommandParser implements Parser<DeleteTagCommand> {
    /**
     * Parses the given {@code String} of arguments and creates a DeleteTagCommand object.
     *
     * @param args The user input string containing the index of the person and the tag to be deleted.
     * @return A DeleteTagCommand object for execution.
     * @throws ParseException If the input format is invalid (e.g., missing index or tag).
     */
    public DeleteTagCommand parse(String args) throws ParseException {
        String[] splitArgs = args.trim().split("\\s+", 2);

        if (splitArgs.length < 2) {
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteTagCommand.MESSAGE_USAGE));
        }

        try {
            Index index = ParserUtil.parseIndex(splitArgs[0]);
            Tag tag = new Tag(splitArgs[1]);
            return new DeleteTagCommand(index, tag);
        } catch (ParseException pe) {
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteTagCommand.MESSAGE_USAGE), pe);
        }
    }
}

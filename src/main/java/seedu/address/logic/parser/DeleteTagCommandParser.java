package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteTagCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tag.Tag;

public class DeleteTagCommandParser implements Parser<DeleteTagCommand> {
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

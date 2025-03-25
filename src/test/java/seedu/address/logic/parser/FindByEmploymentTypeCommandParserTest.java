package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindByEmploymentTypeCommand;
import seedu.address.model.person.EmploymentTypeContainsKeywordPredicate;

public class FindByEmploymentTypeCommandParserTest {

    private FindByEmploymentTypeCommandParser parser = new FindByEmploymentTypeCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            FindByEmploymentTypeCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindByEmploymentTypeCommand() {
        // no leading and trailing whitespaces
        FindByEmploymentTypeCommand expectedFindByEmploymentTypeCommand =
                new FindByEmploymentTypeCommand(new EmploymentTypeContainsKeywordPredicate("Full-time"));
        assertParseSuccess(parser, "Full-time", expectedFindByEmploymentTypeCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n Full-time  \t", expectedFindByEmploymentTypeCommand);
    }
}

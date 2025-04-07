package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindByJobTitleCommand;
import seedu.address.model.person.JobTitleContainsKeywordPredicate;

public class FindByJobTitleCommandParserTest {

    private FindByJobTitleCommandParser parser = new FindByJobTitleCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            FindByJobTitleCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindByJobTitleCommand() {
        // no leading and trailing whitespaces
        FindByJobTitleCommand expectedFindByJobTitleCommand =
                new FindByJobTitleCommand(new JobTitleContainsKeywordPredicate("Engineer"));
        assertParseSuccess(parser, "Engineer", expectedFindByJobTitleCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n Engineer  \t", expectedFindByJobTitleCommand);
    }

    @Test
    public void parse_invalidJobTitleWithNumbers_throwsParseException() {
        assertParseFailure(parser, "Engineer123",
            FindByJobTitleCommand.MESSAGE_JOBTITLE_CONSTRAINTS);
    }

    @Test
    public void parse_invalidJobTitleWithSpecialChars_throwsParseException() {
        assertParseFailure(parser, "Manager@",
            FindByJobTitleCommand.MESSAGE_JOBTITLE_CONSTRAINTS);
    }
}



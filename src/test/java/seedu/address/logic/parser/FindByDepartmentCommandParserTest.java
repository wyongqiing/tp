package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindByDepartmentCommand;
import seedu.address.model.person.DepartmentContainsKeywordPredicate;

public class FindByDepartmentCommandParserTest {

    private FindByDepartmentCommandParser parser = new FindByDepartmentCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            FindByDepartmentCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindByDepartmentCommand() {
        // no leading and trailing whitespaces
        FindByDepartmentCommand expectedFindByDepartmentCommand =
                new FindByDepartmentCommand(new DepartmentContainsKeywordPredicate("Human Resources"));
        assertParseSuccess(parser, "Human Resources", expectedFindByDepartmentCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n Human Resources \t", expectedFindByDepartmentCommand);
    }
}

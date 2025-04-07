package seedu.address.logic.parser;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindByDepartmentCommand;
import seedu.address.model.person.DepartmentContainsKeywordPredicate;
import seedu.address.model.tag.Department;

public class FindByDepartmentCommandParserTest {

    private FindByDepartmentCommandParser parser = new FindByDepartmentCommandParser();

    @Test
    public void parse_invalidDepartmentWithNumbers_throwsParseException() {
        assertParseFailure(parser, "HR123", "Department does not exist. "
            + Department.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_invalidDepartmentWithSpecialChars_throwsParseException() {
        assertParseFailure(parser, "Finance#", "Department does not exist. "
            + Department.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_validDepartmentWithAmpersand_returnsFindByDepartmentCommand() {
        FindByDepartmentCommand expectedCommand =
                new FindByDepartmentCommand(new DepartmentContainsKeywordPredicate("R&D"));
        assertParseSuccess(parser, "R&D", expectedCommand);
    }
}

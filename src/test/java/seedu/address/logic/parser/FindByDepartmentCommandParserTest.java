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
        assertParseFailure(parser, "HR123",
            Department.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_invalidDepartmentWithSpecialChars_throwsParseException() {
        assertParseFailure(parser, "Finance#",
            Department.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_validDepartmentWithAmpersand_returnsFindByDepartmentCommand() {
        FindByDepartmentCommand expectedCommand =
                new FindByDepartmentCommand(new DepartmentContainsKeywordPredicate("Finance & Accounting"));
        assertParseSuccess(parser, "Finance & Accounting", expectedCommand);
    }
}

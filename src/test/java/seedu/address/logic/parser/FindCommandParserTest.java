package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;

public class FindCommandParserTest {

    private final FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        // Expect a ParseException when no arguments are provided
        assertThrows(ParseException.class, () -> parser.parse("find"));
    }

    @Test
    public void parse_validArgs_throwsParseException() {
        // Expect a ParseException when arguments are passed with the "find" command
        assertThrows(ParseException.class, () -> parser.parse("find foo bar"));
    }

    @Test
    public void parse_noArguments_throwsParseException() {
        assertThrows(ParseException.class, () -> parser.parse("find"));
    }
};

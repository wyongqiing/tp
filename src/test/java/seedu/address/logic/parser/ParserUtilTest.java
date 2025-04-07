package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Department;
import seedu.address.model.tag.EmploymentType;
import seedu.address.model.tag.JobTitle;
import seedu.address.model.tag.Tag;

public class ParserUtilTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TAG = "#Human Resource/Full-Time/HR Coordinator";

    private static final String VALID_NAME = "Rachel Walker";
    private static final String VALID_PHONE = "83075829";
    private static final String VALID_ADDRESS = "123 Main Street #0505/233948";
    private static final String VALID_EMAIL = "rachel@example.com";
    private static final String VALID_DEPARTMENT_1 = "Human Resources";
    private static final String VALID_EMPLOYMENT_TYPE_1 = "Full-Time";
    private static final String VALID_JOB_TITLE_1 = "HR Coordinator";
    private static final String WHITESPACE = " \t\r\n";

    private final AddressBookParser parser = new AddressBookParser();

    @Test
    public void parseFindCommand_invalidCommand_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            FindCommand.MESSAGE_USAGE), () ->
            parser.parseCommand("find foo bar"));
    }

    @Test
    public void parseIndex_invalidInput_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseIndex("10 a"));
    }

    @Test
    public void parseIndex_outOfRangeInput_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_INVALID_PERSON_DISPLAYED_INDEX, ()
            -> ParserUtil.parseIndex(Long.toString(Integer.MAX_VALUE + 1)));
    }

    @Test
    public void parseIndex_validInput_success() throws Exception {
        // No whitespaces
        assertEquals(INDEX_FIRST_PERSON, ParserUtil.parseIndex("1"));

        // Leading and trailing whitespaces
        assertEquals(INDEX_FIRST_PERSON, ParserUtil.parseIndex("  1  "));
    }

    @Test
    public void parseName_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseName((String) null));
    }

    @Test
    public void parseName_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseName(INVALID_NAME));
    }

    @Test
    public void parseName_validValueWithoutWhitespace_returnsName() throws Exception {
        Name expectedName = new Name(VALID_NAME);
        assertEquals(expectedName, ParserUtil.parseName(VALID_NAME));
    }

    @Test
    public void parseName_validValueWithWhitespace_returnsTrimmedName() throws Exception {
        String nameWithWhitespace = WHITESPACE + VALID_NAME + WHITESPACE;
        Name expectedName = new Name(VALID_NAME);
        assertEquals(expectedName, ParserUtil.parseName(nameWithWhitespace));
    }

    @Test
    public void parsePhone_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parsePhone((String) null));
    }

    @Test
    public void parsePhone_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parsePhone(INVALID_PHONE));
    }

    @Test
    public void parsePhone_validValueWithoutWhitespace_returnsPhone() throws Exception {
        Phone expectedPhone = new Phone(VALID_PHONE);
        assertEquals(expectedPhone, ParserUtil.parsePhone(VALID_PHONE));
    }

    @Test
    public void parsePhone_validValueWithWhitespace_returnsTrimmedPhone() throws Exception {
        String phoneWithWhitespace = WHITESPACE + VALID_PHONE + WHITESPACE;
        Phone expectedPhone = new Phone(VALID_PHONE);
        assertEquals(expectedPhone, ParserUtil.parsePhone(phoneWithWhitespace));
    }

    @Test
    public void parseAddress_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseAddress((String) null));
    }

    @Test
    public void parseAddress_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseAddress(INVALID_ADDRESS));
    }

    @Test
    public void parseAddress_validValueWithoutWhitespace_returnsAddress() throws Exception {
        Address expectedAddress = new Address(VALID_ADDRESS);
        assertEquals(expectedAddress, ParserUtil.parseAddress(VALID_ADDRESS));
    }

    @Test
    public void parseAddress_validValueWithWhitespace_returnsTrimmedAddress() throws Exception {
        String addressWithWhitespace = WHITESPACE + VALID_ADDRESS + WHITESPACE;
        Address expectedAddress = new Address(VALID_ADDRESS);
        assertEquals(expectedAddress, ParserUtil.parseAddress(addressWithWhitespace));
    }

    @Test
    public void parseEmail_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseEmail((String) null));
    }

    @Test
    public void parseEmail_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseEmail(INVALID_EMAIL));
    }

    @Test
    public void parseEmail_validValueWithoutWhitespace_returnsEmail() throws Exception {
        Email expectedEmail = new Email(VALID_EMAIL);
        assertEquals(expectedEmail, ParserUtil.parseEmail(VALID_EMAIL));
    }

    @Test
    public void parseEmail_validValueWithWhitespace_returnsTrimmedEmail() throws Exception {
        String emailWithWhitespace = WHITESPACE + VALID_EMAIL + WHITESPACE;
        Email expectedEmail = new Email(VALID_EMAIL);
        assertEquals(expectedEmail, ParserUtil.parseEmail(emailWithWhitespace));
    }

    @Test
    public void parseTag_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTag(null));
    }

    @Test
    public void parseTag_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTag(INVALID_TAG));
    }

    @Test
    public void parseTag_validValueWithoutWhitespace_returnsTag() throws Exception {
        Department department = new Department(VALID_DEPARTMENT_1);
        EmploymentType employmentType = new EmploymentType(VALID_EMPLOYMENT_TYPE_1);
        JobTitle jobTitle = new JobTitle(VALID_JOB_TITLE_1);
        Tag expectedTag = new Tag(department, employmentType, jobTitle);
        assertEquals(expectedTag, ParserUtil.parseTag("Human Resources/Full-Time/HR Coordinator"));
    }

    @Test
    public void parseDepartment_validValueWithWhitespace_returnsTrimmedTag() throws Exception {
        String departmentWithWhitespace = WHITESPACE + VALID_DEPARTMENT_1 + WHITESPACE;
        Department department = new Department(departmentWithWhitespace);
        EmploymentType employmentType = new EmploymentType(VALID_EMPLOYMENT_TYPE_1);
        JobTitle jobTitle = new JobTitle(VALID_JOB_TITLE_1);
        Tag expectedTag = new Tag(department, employmentType, jobTitle);
        assertEquals(expectedTag, ParserUtil.parseTag("Human Resources/Full-Time/HR Coordinator"));
    }

    @Test
    public void parseEmploymentType_validValueWithWhitespace_returnsTrimmedTag() throws Exception {
        String employmentWithWhiteSpace = WHITESPACE + VALID_EMPLOYMENT_TYPE_1 + WHITESPACE;
        Department department = new Department(VALID_DEPARTMENT_1);
        EmploymentType employmentType = new EmploymentType(employmentWithWhiteSpace);
        JobTitle jobTitle = new JobTitle(VALID_JOB_TITLE_1);
        Tag expectedTag = new Tag(department, employmentType, jobTitle);
        assertEquals(expectedTag, ParserUtil.parseTag("Human Resources/Full-Time/HR Coordinator"));
    }

    @Test
    public void parseJobTitle_validValueWithWhitespace_returnsTrimmedTag() throws Exception {
        String jobWithWhiteSpace = WHITESPACE + VALID_JOB_TITLE_1 + WHITESPACE;
        Department department = new Department(VALID_DEPARTMENT_1);
        EmploymentType employmentType = new EmploymentType(VALID_EMPLOYMENT_TYPE_1);
        JobTitle jobTitle = new JobTitle(jobWithWhiteSpace);
        Tag expectedTag = new Tag(department, employmentType, jobTitle);
        assertEquals(expectedTag, ParserUtil.parseTag("Human Resources/Full-Time/HR Coordinator"));
    }

    @Test
    public void parseTags_collectionWithInvalidTags_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTag(INVALID_TAG));
    }

    @Test
    public void parseTags_emptyCollection_returnsEmptySet() throws Exception {
        Department department = new Department(VALID_DEPARTMENT_1);
        EmploymentType employmentType = new EmploymentType(VALID_EMPLOYMENT_TYPE_1);
        JobTitle jobTitle = new JobTitle(VALID_JOB_TITLE_1);
        Tag expectedTag = new Tag(department, employmentType, jobTitle);
        assertEquals(expectedTag, ParserUtil.parseTag("Human Resources/Full-Time/HR Coordinator"));
    }

    @Test
    public void parseTags_collectionWithValidTags_returnsTagSet() throws Exception {
        Tag actualTag = ParserUtil.parseTag("Human Resources/Full-Time/HR Coordinator");

        Department department = new Department(VALID_DEPARTMENT_1);
        EmploymentType employmentType = new EmploymentType(VALID_EMPLOYMENT_TYPE_1);
        JobTitle jobTitle = new JobTitle(VALID_JOB_TITLE_1);
        Tag expectedTag = new Tag(department, employmentType, jobTitle);

        assertEquals(expectedTag, actualTag);
    }
}

package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DOB_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DOB_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GENDER_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GENDER_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NATIONALITY_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NATIONALITY_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NRIC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NRIC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUMAN_RESOURCE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_MARKETING;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.person.Person;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalPersons {

    public static final Person ALICE = new PersonBuilder()
            .withName("Alice Pauline")
            .withPhone("94351253")
            .withEmail("alice@example.com")
            .withNric("S9134567A")
            .withGender("Female")
            .withDob("01-Oct-1991")
            .withDateOfJoining("15-Apr-2024")
            .withNationality("Singaporean")
            .withAddress("123, Jurong West Ave 6, #08-111")
            .withTags("Human Resources/Full-Time/HR Coordinator")
            .build();

    public static final Person BENSON = new PersonBuilder()
            .withName("Benson Meier")
            .withPhone("98765432")
            .withEmail("johnd@example.com")
            .withNric("T0134567B")
            .withGender("Male")
            .withDob("10-Mar-2001")
            .withDateOfJoining("15-Apr-2024")
            .withNationality("Singaporean")
            .withAddress("311, Clementi Ave 2, #02-25")
            .withTags("Marketing/Part-Time/Marketing Specialist")
            .build();

    public static final Person CARL = new PersonBuilder()
            .withName("Carl Kurz")
            .withPhone("95352563")
            .withEmail("heinz@example.com")
            .withNric("T0234568C")
            .withGender("Male")
            .withDob("20-Aug-2002")
            .withDateOfJoining("15-Apr-2024")
            .withNationality("American")
            .withAddress("wall street")
            .withTags("Information Technology/Full-Time/Software Engineer")
            .build();

    public static final Person DANIEL = new PersonBuilder()
            .withName("Daniel Meier")
            .withPhone("87652533")
            .withEmail("cornelia@example.com")
            .withNric("T0134569D")
            .withGender("Male")
            .withDob("19-Aug-2001")
            .withDateOfJoining("15-Apr-2024")
            .withNationality("American")
            .withAddress("10th street")
            .withTags("Finance/Internship/Financial Analyst")
            .build();

    public static final Person ELLE = new PersonBuilder()
            .withName("Elle Meyer")
            .withPhone("9482224")
            .withEmail("werner@example.com")
            .withNric("T0234560E")
            .withGender("Female")
            .withDob("28-Dec-2002")
            .withDateOfJoining("15-Apr-2024")
            .withNationality("Indonesian")
            .withAddress("michegan ave")
            .withTags("Information Technology/Full-Time/Data Analyst")
            .build();

    public static final Person FIONA = new PersonBuilder()
            .withName("Fiona Kunz")
            .withPhone("9482427")
            .withEmail("lydia@example.com")
            .withNric("T0435560F")
            .withGender("Female")
            .withDob("30-Feb-2004")
            .withDateOfJoining("01-Jan-2024")
            .withNationality("Singaporean")
            .withAddress("little tokyo")
            .withTags("Information Technology/Part-Time/Data Analyst")
            .build();

    public static final Person GEORGE = new PersonBuilder()
            .withName("George Best")
            .withPhone("9482442")
            .withEmail("anna@example.com")
            .withNric("T0435569G")
            .withGender("Male")
            .withDob("27-Sep-2004")
            .withDateOfJoining("01-Jan-2024")
            .withNationality("American")
            .withAddress("4th street")
            .withTags("Engineering/Part-Time/Electrical Engineer")
            .build();

    // Manually added
    public static final Person HOON = new PersonBuilder()
            .withName("Hoon Meier")
            .withPhone("8482424")
            .withEmail("stefan@example.com")
            .withNric("T0135567H")
            .withGender("Male")
            .withDob("10-Dec-2001")
            .withDateOfJoining("15-Dec-2024")
            .withNationality("Singaporean")
            .withAddress("little india")
            .withTags("Finance/Internship/Financial Analyst")
            .build();

    public static final Person IDA = new PersonBuilder()
            .withName("Ida Mueller")
            .withPhone("8482131")
            .withEmail("hans@example.com")
            .withNric("T0835561J")
            .withGender("Female")
            .withDob("01-Jan-2008")
            .withDateOfJoining("15-Dec-2024")
            .withNationality("Singaporean")
            .withAddress("chicago ave")
            .withTags("Finance/Internship/Financial Analyst")
            .build();


    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Person AMY = new PersonBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY).withNric(VALID_NRIC_AMY)
            .withGender(VALID_GENDER_AMY).withDob(VALID_DOB_AMY)
            .withDateOfJoining(VALID_DATE_AMY).withNationality(VALID_NATIONALITY_AMY)
            .withAddress(VALID_ADDRESS_AMY).withTags(VALID_TAG_HUMAN_RESOURCE).build();
    public static final Person BOB = new PersonBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB).withNric(VALID_NRIC_BOB)
            .withGender(VALID_GENDER_BOB).withDob(VALID_DOB_BOB)
            .withDateOfJoining(VALID_DATE_BOB).withNationality(VALID_NATIONALITY_BOB)
            .withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_MARKETING)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalPersons() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical persons.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Person person : getTypicalPersons()) {
            ab.addPerson(person);
        }
        return ab;
    }

    public static List<Person> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}

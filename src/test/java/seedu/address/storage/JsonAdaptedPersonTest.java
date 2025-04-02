package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedPerson.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.BENSON;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Address;
import seedu.address.model.person.DateOfJoining;
import seedu.address.model.person.Dob;
import seedu.address.model.person.Email;
import seedu.address.model.person.Gender;
import seedu.address.model.person.Name;
import seedu.address.model.person.Nationality;
import seedu.address.model.person.Nric;
import seedu.address.model.person.Phone;

public class JsonAdaptedPersonTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_NRIC = "T03171800";
    private static final String INVALID_GENDER = "test";
    private static final String INVALID_DOB = "2020 01 01";
    private static final String INVALID_DATE = "2020/01-01";
    private static final String INVALID_NATIONALITY = "singapore";
    private static final String[] INVALID_TAG = new String[] {"Teaching", "Full-Time", "HR Coordinator"};

    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_PHONE = BENSON.getPhone().toString();
    private static final String VALID_EMAIL = BENSON.getEmail().toString();
    private static final String VALID_NRIC = BENSON.getNric().toString();
    private static final String VALID_GENDER = BENSON.getGender().toString();
    private static final String VALID_DOB = BENSON.getDob().toString();
    private static final String VALID_DATE = BENSON.getDateOfJoining().toString();
    private static final String VALID_NATIONALITY = BENSON.getNationality().toString();
    private static final String VALID_ADDRESS = BENSON.getAddress().toString();
    private static final String VALID_NOTE = BENSON.getNote().toString();
    private static final String[] VALID_TAGS = BENSON.getTag().getValue();

    @Test
    public void toModelType_validPersonDetails_returnsPerson() throws Exception {
        JsonAdaptedPerson person = new JsonAdaptedPerson(BENSON);
        assertEquals(BENSON, person.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(INVALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_NRIC, VALID_GENDER, VALID_DOB,
                        VALID_DATE, VALID_NATIONALITY, VALID_ADDRESS, VALID_NOTE, VALID_TAGS);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(null, VALID_PHONE, VALID_EMAIL, VALID_NRIC, VALID_GENDER, VALID_DOB,
                        VALID_DATE, VALID_NATIONALITY, VALID_ADDRESS, VALID_NOTE, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, INVALID_PHONE, VALID_EMAIL, VALID_NRIC, VALID_GENDER, VALID_DOB,
                        VALID_DATE, VALID_NATIONALITY, VALID_ADDRESS, VALID_NOTE, VALID_TAGS);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, null, VALID_EMAIL, VALID_NRIC, VALID_GENDER, VALID_DOB,
                        VALID_DATE, VALID_NATIONALITY, VALID_ADDRESS, VALID_NOTE, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, INVALID_EMAIL, VALID_NRIC, VALID_GENDER, VALID_DOB,
                        VALID_DATE, VALID_NATIONALITY, VALID_ADDRESS, VALID_NOTE, VALID_TAGS);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, null, VALID_NRIC, VALID_GENDER, VALID_DOB,
                        VALID_DATE, VALID_NATIONALITY, VALID_ADDRESS, VALID_NOTE, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidNric_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, INVALID_NRIC, VALID_GENDER, VALID_DOB,
                        VALID_DATE, VALID_NATIONALITY, VALID_ADDRESS, VALID_NOTE, VALID_TAGS);
        String expectedMessage = Nric.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullNric_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, null, VALID_GENDER, VALID_DOB,
                        VALID_DATE, VALID_NATIONALITY, VALID_ADDRESS, VALID_NOTE, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Nric.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidGender_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_NRIC, INVALID_GENDER, VALID_DOB,
                        VALID_DATE, VALID_NATIONALITY, VALID_ADDRESS, VALID_NOTE, VALID_TAGS);
        String expectedMessage = Gender.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullGender_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_NRIC, null, VALID_DOB,
                        VALID_DATE, VALID_NATIONALITY, VALID_ADDRESS, VALID_NOTE, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Gender.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidDob_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_NRIC, VALID_GENDER, INVALID_DOB,
                        VALID_DATE, VALID_NATIONALITY, VALID_ADDRESS, VALID_NOTE, VALID_TAGS);
        String expectedMessage = Dob.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullDob_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_NRIC, VALID_GENDER, null,
                        VALID_DATE, VALID_NATIONALITY, VALID_ADDRESS, VALID_NOTE, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Dob.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidDate_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_NRIC, VALID_GENDER, VALID_DOB,
                        INVALID_DATE, VALID_NATIONALITY, VALID_ADDRESS, VALID_NOTE, VALID_TAGS);
        String expectedMessage = DateOfJoining.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullDate_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_NRIC, VALID_GENDER, VALID_DOB,
                        null, VALID_NATIONALITY, VALID_ADDRESS, VALID_NOTE, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, DateOfJoining.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidNationality_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_NRIC, VALID_GENDER, VALID_DOB,
                        VALID_DATE, INVALID_NATIONALITY, VALID_ADDRESS, VALID_NOTE, VALID_TAGS);
        String expectedMessage = Nationality.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullNationality_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_NRIC, VALID_GENDER, VALID_DOB,
                        VALID_DATE, null, VALID_ADDRESS, VALID_NOTE, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Nationality.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidAddress_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_NRIC, VALID_GENDER, VALID_DOB,
                        VALID_DATE, VALID_NATIONALITY, INVALID_ADDRESS, VALID_NOTE, VALID_TAGS);
        String expectedMessage = Address.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullAddress_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_NRIC, VALID_GENDER, VALID_DOB,
                        VALID_DATE, VALID_NATIONALITY, null, VALID_NOTE, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_NRIC, VALID_GENDER, VALID_DOB,
                        VALID_DATE, VALID_NATIONALITY, VALID_ADDRESS, VALID_NOTE, INVALID_TAG);
        assertThrows(IllegalValueException.class, person::toModelType);
    }

}

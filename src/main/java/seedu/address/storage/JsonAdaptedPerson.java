package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Address;
import seedu.address.model.person.DateOfJoining;
import seedu.address.model.person.Dob;
import seedu.address.model.person.Email;
import seedu.address.model.person.Gender;
import seedu.address.model.person.Name;
import seedu.address.model.person.Nationality;
import seedu.address.model.person.Note;
import seedu.address.model.person.Nric;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Department;
import seedu.address.model.tag.EmploymentType;
import seedu.address.model.tag.JobTitle;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Person}.
 */
class JsonAdaptedPerson {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";

    private final String name;
    private final String phone;
    private final String email;
    private final String nric;
    private final String gender;
    private final String dob;
    private final String dateOfJoining;
    private final String nationality;
    private final String address;
    private final String note;
    private final String[] tag;

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedPerson(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
            @JsonProperty("email") String email, @JsonProperty("nric") String nric,
            @JsonProperty("gender") String gender,
            @JsonProperty("dob") String dob, @JsonProperty("dateOfJoining") String dateOfJoining,
            @JsonProperty("nationality") String nationality,
            @JsonProperty("address") String address, @JsonProperty("note") String note,
                             @JsonProperty("tag") String[] tag) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.nric = nric;
        this.gender = gender;
        this.dob = dob;
        this.dateOfJoining = dateOfJoining;
        this.nationality = nationality;
        this.address = address;
        this.note = note;
        this.tag = tag;
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedPerson(Person source) {
        name = source.getName().fullName;
        phone = source.getPhone().value;
        email = source.getEmail().value;
        nric = source.getNric().value;
        gender = source.getGender().value;
        dob = source.getDob().value;
        dateOfJoining = source.getDateOfJoining().value;
        nationality = source.getNationality().value;
        address = source.getAddress().value;
        note = source.getNote().value;
        tag = source.getTag().getValue();
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Person toModelType() throws IllegalValueException {
        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (phone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName()));
        }
        if (!Phone.isValidPhone(phone)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }
        final Phone modelPhone = new Phone(phone);

        if (email == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName()));
        }
        if (!Email.isValidEmail(email)) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        }
        final Email modelEmail = new Email(email);

        if (nric == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Nric.class.getSimpleName()));
        }
        if (!Nric.isValidNric(nric)) {
            throw new IllegalValueException(Nric.MESSAGE_CONSTRAINTS);
        }
        final Nric modelNric = new Nric(nric);

        if (gender == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Gender.class.getSimpleName()));
        }
        if (!Gender.isValidGender(gender)) {
            throw new IllegalValueException(Gender.MESSAGE_CONSTRAINTS);
        }
        final Gender modelGender = new Gender(gender);

        if (dob == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Dob.class.getSimpleName()));
        }
        if (!Dob.isValidDob(dob)) {
            throw new IllegalValueException(Dob.MESSAGE_CONSTRAINTS);
        }
        final Dob modelDob = new Dob(dob);

        if (dateOfJoining == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    DateOfJoining.class.getSimpleName()));
        }
        if (!DateOfJoining.isValidDate(dateOfJoining)) {
            throw new IllegalValueException(DateOfJoining.MESSAGE_CONSTRAINTS);
        }
        final DateOfJoining modelDateOfJoining = new DateOfJoining(dateOfJoining);

        if (nationality == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Nationality.class.getSimpleName()));
        }
        if (!Nationality.isValidNationality(nationality)) {
            throw new IllegalValueException(Nationality.MESSAGE_CONSTRAINTS);
        }
        final Nationality modelNationality = new Nationality(nationality);

        if (address == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Address.class.getSimpleName()));
        }
        if (!Address.isValidAddress(address)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }
        final Address modelAddress = new Address(address);

        final Note modelNote = new Note(note);
        /* if (note == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Note.class.getSimpleName()));
        } */

        if (tag == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Tag.class.getSimpleName()));
        }
        if (!Tag.isValidTagName(tag)) {
            throw new IllegalValueException(Tag.MESSAGE_CONSTRAINTS);
        }
        final Department department = new Department(tag[0]);
        final EmploymentType employmentType = new EmploymentType(tag[1]);
        final JobTitle jobTitle = new JobTitle(tag[2]);
        final Tag modelTag = new Tag(department, employmentType, jobTitle);

        return new Person(modelName, modelPhone, modelEmail, modelNric, modelGender, modelDob, modelDateOfJoining,
                modelNationality, modelAddress, modelNote, modelTag);
    }

}

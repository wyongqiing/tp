package seedu.address.testutil;

import seedu.address.model.person.Address;
import seedu.address.model.person.DateOfJoining;
import seedu.address.model.person.Dob;
import seedu.address.model.person.Email;
import seedu.address.model.person.Gender;
import seedu.address.model.person.Name;
import seedu.address.model.person.Nationality;
import seedu.address.model.person.Nric;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Note;
import seedu.address.model.tag.Department;
import seedu.address.model.tag.EmploymentType;
import seedu.address.model.tag.JobTitle;
import seedu.address.model.tag.Tag;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_NRIC = "T0412345A";
    public static final String DEFAULT_GENDER = "Female";
    public static final String DEFAULT_DOB = "01-Jan-2020";
    public static final String DEFAULT_DATE = "17-Mar-2025";
    public static final String DEFAULT_NATIONALITY = "Singaporean";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    public static final String DEFAULT_DEPARTMENT = "Finance";
    public static final String DEFAULT_EMPLOYMENT_TYPE = "Full-Time";
    public static final String DEFAULT_JOB_TITLE = "Financial Analyst";
    public static final String DEFAULT_NOTE = "She likes aardvarks.";

    private Name name;
    private Phone phone;
    private Email email;
    private Nric nric;
    private Gender gender;
    private Dob dob;
    private DateOfJoining dateOfJoining;
    private Nationality nationality;
    private Address address;
    private Note note;
    private Tag tag;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        nric = new Nric(DEFAULT_NRIC);
        gender = new Gender(DEFAULT_GENDER);
        dob = new Dob(DEFAULT_DOB);
        dateOfJoining = new DateOfJoining(DEFAULT_DATE);
        nationality = new Nationality(DEFAULT_NATIONALITY);
        address = new Address(DEFAULT_ADDRESS);
        Department department = new Department(DEFAULT_DEPARTMENT);
        EmploymentType employmentType = new EmploymentType(DEFAULT_EMPLOYMENT_TYPE);
        JobTitle jobTitle = new JobTitle(DEFAULT_JOB_TITLE);
        tag = new Tag(department, employmentType, jobTitle);
        note = new Note(DEFAULT_NOTE);
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        name = personToCopy.getName();
        phone = personToCopy.getPhone();
        email = personToCopy.getEmail();
        nric = personToCopy.getNric();
        gender = personToCopy.getGender();
        dob = personToCopy.getDob();
        dateOfJoining = personToCopy.getDateOfJoining();
        nationality = personToCopy.getNationality();
        address = personToCopy.getAddress();
        note = personToCopy.getNote();
        tag = personToCopy.getTag();
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public PersonBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Person} that we are building.
     */
    public PersonBuilder withTags(String tags) {
        String[] tagList = tags.split("/");
        Department department = new Department(tagList[0]);
        EmploymentType employmentType = new EmploymentType(tagList[1]);
        JobTitle jobTitle = new JobTitle(tagList[2]);
        this.tag = new Tag(department, employmentType, jobTitle);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Person} that we are building.
     */
    public PersonBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Person} that we are building.
     */
    public PersonBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Person} that we are building.
     */
    public PersonBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code Nric} of the {@code Person} that we are building.
     */
    public PersonBuilder withNric(String nric) {
        this.nric = new Nric(nric);
        return this;
    }

    /**
     * Sets the {@code Gender} of the {@code Person} that we are building.
     */
    public PersonBuilder withGender(String gender) {
        this.gender = new Gender(gender);
        return this;
    }

    /**
     * Sets the {@code Dob} of the {@code Person} that we are building.
     */
    public PersonBuilder withDob(String dob) {
        this.dob = new Dob(dob);
        return this;
    }

    /**
     * Sets the {@code DateOfJoining} of the {@code Person} that we are building.
     */
    public PersonBuilder withDateOfJoining(String dateOfJoining) {
        this.dateOfJoining = new DateOfJoining(dateOfJoining);
        return this;
    }

    /**
     * Sets the {@code Nationality} of the {@code Person} that we are building.
     */
    public PersonBuilder withNationality(String nationality) {
        this.nationality = new Nationality(nationality);
        return this;
    }

    /**
     * Sets the {@code Note} of the {@code Person} that we are building.
     */
    public PersonBuilder withNote(String note) {
        this.note = new Note(note);
        return this;
    }


    public Person build() {
        return new Person(name, phone, email, nric, gender, dob, dateOfJoining, nationality, address, note ,tag);
    }
}

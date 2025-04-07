package seedu.address.model.util;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
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
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static final Note EMPTY_NOTE = new Note("");
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new Nric("S9019283Z"),
                new Gender("Male"), new Dob("01-Oct-1990"), new DateOfJoining("12-Feb-2025"),
                new Nationality("Singaporean"), new Address("Blk 30 Geylang Street 29, #06-40/101010"), EMPTY_NOTE,
                getTag("Human Resources", "Full-Time", "HR Coordinator")),
            new Person(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new Nric("T0119283Z"),
                new Gender("Female"), new Dob("12-May-2001"), new DateOfJoining("12-Feb-2025"),
                new Nationality("Singaporean"), new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18/192393"),
                EMPTY_NOTE, getTag("Marketing", "Part-Time", "Marketing Specialist")),
            new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                new Nric("T1919183H"),
                new Gender("Female"), new Dob("10-Dec-2019"), new DateOfJoining("12-Feb-2025"),
                new Nationality("American"), new Address("Blk 11 Ang Mo Kio Street 74, #11-04/023456"), EMPTY_NOTE,
                getTag("Information Technology", "Full-Time", "Software Engineer")),
            new Person(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                new Nric("S9819013B"),
                new Gender("Male"), new Dob("31-Oct-1998"), new DateOfJoining("12-Feb-2025"),
                new Nationality("Chinese"), new Address("Blk 436 Serangoon Gardens Street 26, #16-43/203909"),
                EMPTY_NOTE, getTag("Finance", "Internship", "Financial Analyst")),
            new Person(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                new Nric("T0219283W"),
                new Gender("Male"), new Dob("10-Oct-2002"), new DateOfJoining("20-Aug-2024"),
                new Nationality("Singaporean"), new Address("Blk 47 Tampines Street 20, #17-35/189002"), EMPTY_NOTE,
                getTag("Information Technology", "Full-Time", "Data Analyst")),
            new Person(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                new Nric("S9919285A"),
                new Gender("Male"), new Dob("30-Apr-1999"), new DateOfJoining("20-Aug-2024"),
                new Nationality("Indonesian"), new Address("Blk 45 Aljunied Street 85, #11-31/067567"), EMPTY_NOTE,
                getTag("Engineering", "Part-Time", "Electrical Engineer"))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        return sampleAb;
    }

    /**
     * Returns a tag containing the list of strings given.
     */
    public static Tag getTag(String department, String employmentType, String jobTitle) {
        Department modelDepartment = new Department(department);
        EmploymentType modelEmploymentType = new EmploymentType(employmentType);
        JobTitle modelJobTitle = new JobTitle(jobTitle);
        return new Tag(modelDepartment, modelEmploymentType, modelJobTitle);
    }

}

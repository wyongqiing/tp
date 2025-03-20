package seedu.address.model.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Department;
import seedu.address.model.tag.EmploymentType;
import seedu.address.model.tag.JobTitle;
import seedu.address.model.tag.Tag;

public class SampleDataUtilTest {

    @Test
    public void getSamplePersons() {
        Person[] samplePersons = SampleDataUtil.getSamplePersons();

        assertTrue(samplePersons.length == 6);

        Person person = samplePersons[0];
        assertEquals("Alex Yeoh", person.getName().fullName);
        assertEquals("87438807", person.getPhone().value);
        assertEquals("alexyeoh@example.com", person.getEmail().value);
        assertEquals("S9019283Z", person.getNric().value);
        assertEquals("Male", person.getGender().value);
        assertEquals("01-Oct-1990", person.getDob().value);
        assertEquals("12-Feb-2025", person.getDateOfJoining().value);
        assertEquals("Singaporean", person.getNationality().value);
        assertEquals("Blk 30 Geylang Street 29, #06-40", person.getAddress().value);
        assertEquals("Human Resources", person.getTag().getValue()[0]);
        assertEquals("Full-Time", person.getTag().getValue()[1]);
        assertEquals("HR Coordinator", person.getTag().getValue()[2]);
    }

    @Test
    public void getSampleAddressBook() {
        ReadOnlyAddressBook sampleAddressBook = SampleDataUtil.getSampleAddressBook();

        assertEquals(SampleDataUtil.getSamplePersons().length, sampleAddressBook.getPersonList().size());

        Person personInBook = sampleAddressBook.getPersonList().get(0);
        Person samplePerson = SampleDataUtil.getSamplePersons()[0];
        assertEquals(samplePerson, personInBook);
    }

    @Test
    public void getTag() {
        Department department = new Department("Human Resources");
        EmploymentType employmentType = new EmploymentType("Full-Time");
        JobTitle jobTitle = new JobTitle("HR Coordinator");
        Tag expectedTag = new Tag(department, employmentType, jobTitle);
        assertEquals(expectedTag,
                SampleDataUtil.getTag("Human Resources", "Full-Time", "HR Coordinator"));
    }
}

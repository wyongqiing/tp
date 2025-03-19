package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.tag.Department;
import seedu.address.model.tag.EmploymentType;
import seedu.address.model.tag.JobTitle;
import seedu.address.model.tag.Tag;

public class JsonAdaptedTagTest {
    private static final String VALID_DEPARTMENT = "Human Resources";
    private static final String VALID_EMPLOYMENT_TYPE = "Full-Time";
    private static final String VALID_JOB_TITLE = "HR Coordinator";
    private static final String INVALID_DEPARTMENT = "#Human Resources";

    @Test
    public void toModelType_validTag() throws Exception {
        String[] tag = new String[] {VALID_DEPARTMENT, VALID_EMPLOYMENT_TYPE, VALID_JOB_TITLE};
        JsonAdaptedTag jsonAdaptedTag = new JsonAdaptedTag(tag);
        Tag expectedTag = new Tag(
                new Department(VALID_DEPARTMENT),
                new EmploymentType(VALID_EMPLOYMENT_TYPE),
                new JobTitle(VALID_JOB_TITLE)
        );
        assertEquals(expectedTag, jsonAdaptedTag.toModelType());
    }

    @Test
    public void toModelType_invalidTag_throwsIllegalValueException() {
        String[] tag = new String[] {INVALID_DEPARTMENT, VALID_EMPLOYMENT_TYPE, VALID_JOB_TITLE};
        JsonAdaptedTag jsonAdaptedTag = new JsonAdaptedTag(tag);
        assertThrows(IllegalValueException.class, jsonAdaptedTag::toModelType);
    }

    @Test
    public void toModelType_nullTag_throwsIllegalValueException() {
        JsonAdaptedTag jsonAdaptedTag = new JsonAdaptedTag((String[]) null);
        assertThrows(NullPointerException.class, jsonAdaptedTag::toModelType);
    }

    @Test
    public void constructor_validTag() {
        Department department = new Department(VALID_DEPARTMENT);
        EmploymentType employmentType = new EmploymentType(VALID_EMPLOYMENT_TYPE);
        JobTitle jobTitle = new JobTitle(VALID_JOB_TITLE);
        Tag tag = new Tag(department, employmentType, jobTitle);
        JsonAdaptedTag jsonAdaptedTag = new JsonAdaptedTag(tag);
        String[] expectedTag = new String[] {VALID_DEPARTMENT, VALID_EMPLOYMENT_TYPE, VALID_JOB_TITLE};
        assertEquals(expectedTag[0], jsonAdaptedTag.getTagName()[0]);
        assertEquals(expectedTag[1], jsonAdaptedTag.getTagName()[1]);
        assertEquals(expectedTag[2], jsonAdaptedTag.getTagName()[2]);
    }
}

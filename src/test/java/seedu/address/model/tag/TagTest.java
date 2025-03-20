package seedu.address.model.tag;

import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TagTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Tag(null, null, null));
    }

    @Test
    public void constructor_invalidTagName_throwsIllegalArgumentException() {
        EmploymentType employmentType = new EmploymentType("Full-Time");
        JobTitle jobTitle = new JobTitle("HR Coordinator");
        assertThrows(NullPointerException.class, () -> new Tag(null, employmentType, jobTitle));
    }

    @Test
    public void isValidTagName() {
        // null tag name
        assertThrows(NullPointerException.class, () -> Tag.isValidTagName(null));
    }

}

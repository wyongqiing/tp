package seedu.address.model.tag;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class DepartmentTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Department(null));
    }

    @Test
    public void constructor_invalidDepartment_throwsIllegalArgumentException() {
        String invalidDepartment = "";
        assertThrows(IllegalArgumentException.class, () -> new Department(invalidDepartment));
    }

    @Test
    public void isValidDepartment() {
        // null department
        assertThrows(NullPointerException.class, () -> Department.isValidDepartment(null));

        // invalid departments
        assertFalse(Department.isValidDepartment(""));
        assertFalse(Department.isValidDepartment("#Human Resources"));

        // valid departments
        assertTrue(Department.isValidDepartment("hr"));
        assertTrue(Department.isValidDepartment("Human Resources"));
        assertTrue(Department.isValidDepartment("Human  Resources")); // extra space between words
        assertTrue(Department.isValidDepartment("Marketing"));
        assertTrue(Department.isValidDepartment("Marketing    ")); // extra spaces after word
        assertTrue(Department.isValidDepartment("    Marketing")); // extra spaces before word
        assertTrue(Department.isValidDepartment("marketing")); // non capital letter
        assertTrue(Department.isValidDepartment("marKEting")); // different capitalizations
    }

    @Test
    public void isValidDepartmentInput() {
        // null department
        assertThrows(NullPointerException.class, () -> Department.isValidDepartmentInput(null));

        // invalid departments
        assertFalse(Department.isValidDepartmentInput(""));
        assertFalse(Department.isValidDepartmentInput("#Human Resources"));

        // valid departments
        assertTrue(Department.isValidDepartmentInput("hr"));
        assertTrue(Department.isValidDepartmentInput("Human Resources"));
        assertTrue(Department.isValidDepartmentInput("Human  Resources")); // extra space between words
        assertTrue(Department.isValidDepartmentInput("Marketing"));
        assertTrue(Department.isValidDepartmentInput("Marketing    ")); // extra spaces after word
        assertTrue(Department.isValidDepartmentInput("    Marketing")); // extra spaces before word
        assertTrue(Department.isValidDepartmentInput("marketing")); // non capital letter
        assertTrue(Department.isValidDepartmentInput("marKEting")); // different capitalizations
    }

    @Test
    public void equals() {
        Department department = new Department("Human Resources");

        // same values -> returns true
        assertTrue(department.equals(new Department("Human Resources")));

        // same object -> returns true
        assertTrue(department.equals(department));

        // null -> returns false
        assertFalse(department.equals(null));

        // different types -> returns false
        assertFalse(department.equals(5.0f));

        // different values -> returns false
        assertFalse(department.equals(new Department("Marketing")));
    }
}

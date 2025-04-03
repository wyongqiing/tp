package seedu.address.model.tag;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.Map;
import java.util.Set;

/**
 * Represents a Person's department in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidDepartment(String)}
 */
public class Department {
    private static final String departments = "Human Resources, Finance, Accounting, Marketing, Sales, "
            + "Customer Service, Information Technology, \n"
            + "Research and Development, Operations, Legal, Supply Chain & Logistics, Procurement & Purchasing, n"
            + "Engineering, Quality Assurance, Product Management, Manufacturing, Public Relations, \n"
            + "Corporate Communications, Compliance & Risk Management, Business Development, Data Science, \n"
            + "Cybersecurity, Software Development, UX/UI Design, Artificial Intelligence & Machine Learning, \n"
            + "Training & Development, Facilities Management, Administration, Health & Safety, \n"
            + "Diversity, Equity & Inclusion.";

    public static final String MESSAGE_CONSTRAINTS = "Department must only contain alphabetic characters \n"
            + "and the '&' symbol. \n"
            + "Flexibility in department inputs is allowed where the validation is not case sensitive \n"
            + "and short forms are allowed. \n"
            + "Here is the list of Departments: \n"
            + departments;

    private static final Set<String> VALID_DEPARTMENTS = Set.of("Human Resources", "Finance", "Accounting",
            "Marketing", "Sales", "Customer Service", "Information Technology",
            "Research and Development", "Operations", "Legal",
            "Supply Chain & Logistics", "Procurement & Purchasing", "Engineering",
            "Quality Assurance", "Product Management", "Manufacturing",
            "Public Relations", "Corporate Communications",
            "Compliance & Risk Management", "Business Development", "Data Science",
            "Cybersecurity", "Software Development", "UX/UI Design",
            "Artificial Intelligence & Machine Learning", "Training & Development",
            "Facilities Management", "Administration", "Health & Safety",
            "Diversity, Equity & Inclusion");

    private static final Map<String, String> DEPARTMENTS_SHORT_FORM = Map.ofEntries(
            Map.entry("Human Resources", "HR"),
            Map.entry("Customer Service", "CS"),
            Map.entry("Information Technology", "IT"),
            Map.entry("Research and Development", "R&D"),
            Map.entry("Supply Chain & Logistics", "SCM"),
            Map.entry("Procurement & Purchasing", "Procurement"),
            Map.entry("Quality Assurance", "QA"),
            Map.entry("Public Relations", "PR"),
            Map.entry("Corporate Communications", "CorpComm"),
            Map.entry("Compliance & Risk Management", "Risk & Compliance"),
            Map.entry("Business Development", "BizDev"),
            Map.entry("Data Science", "DS"),
            Map.entry("Cybersecurity", "CyberSec"),
            Map.entry("Software Development", "SD"),
            Map.entry("UX/UI Design", "UX/UI"),
            Map.entry("Artificial Intelligence & Machine Learning", "AI/ML"),
            Map.entry("Training & Development", "T&D"),
            Map.entry("Facilities Management", "FM"),
            Map.entry("Health & Safety", "H&S"),
            Map.entry("Diversity, Equity & Inclusion", "DEI")
    );

    public final String value;

    /**
     * Constructs an {@code Department}.
     *
     * @param department A valid department.
     */
    public Department(String department) {
        requireNonNull(department);
        checkArgument(isValidDepartment(department), MESSAGE_CONSTRAINTS);
        value = mapInput(department);
    }

    private static String normalizeWhitespace(String input) {
        return input.trim().replaceAll("\\s+", " ");
    }

    /**
     * Returns true if a given string is a valid department.
     */
    public static boolean isValidDepartment(String department) {
        String normalizedInput = normalizeWhitespace(department.toLowerCase());

        boolean isDepartment = VALID_DEPARTMENTS.stream()
                .map(validDepartment -> normalizeWhitespace(validDepartment.toLowerCase()))
                .toList()
                .contains(normalizedInput);

        boolean isShortForm = DEPARTMENTS_SHORT_FORM.values().stream()
                .map(shortForm -> normalizeWhitespace(shortForm.toLowerCase()))
                .toList()
                .contains(normalizedInput);

        return isDepartment || isShortForm;
    }

    /**
     * Maps given string to a string in VALID_DEPARTMENTS
     */
    public static String mapInput(String department) {
        String normalizedInput = normalizeWhitespace(department.toLowerCase());

        for (String validDepartment : VALID_DEPARTMENTS) {
            if (normalizeWhitespace(validDepartment.toLowerCase()).equals(normalizedInput)) {
                return validDepartment;
            }
        }

        for (Map.Entry<String, String> departmentShortForm : DEPARTMENTS_SHORT_FORM.entrySet()) {
            if (normalizeWhitespace(departmentShortForm.getValue().toLowerCase()).equals(normalizedInput)) {
                return departmentShortForm.getKey();
            }
        }

        throw new IllegalArgumentException("Invalid department: " + department);
    }

    /**
     * Returns a map of all departments and their short forms.
     */
    public static Map<String, String> getDepartmentsShortForm() {
        return DEPARTMENTS_SHORT_FORM;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof seedu.address.model.tag.Department)) {
            return false;
        }

        seedu.address.model.tag.Department otherDepartment = (seedu.address.model.tag.Department) other;
        return value.equals(otherDepartment.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}


package seedu.address.model.tag;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.Set;

/**
 * Represents a Person's job title in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidJobTitle(String)}
 */
public class JobTitle {
    private static final String jobTitles = "Software Engineer, DevOps Engineer, Front-End Developer, "
            + "Back-End Developer, Full-Stack Developer, Machine Learning Engineer, Data Engineer,  \n"
            + "Electrical Engineer, Mechanical Engineer, Civil Engineer, Robotics Engineer, Systems Engineer, "
            + "Embedded Systems Developer, Test Automation Engineer, Aerospace Engineer, Chemical Engineer, \n"
            + "Hardware Engineer, Biomedical Engineer, Data Analyst, Business Intelligence Analyst, "
            + "Data Scientist, Financial Analyst, Quantitative Analyst, Risk Analyst, \n"
            + "Market Research Analyst, Statistician, Data Architect, Database Administrator, "
            + "Pricing Analyst, Operations Research Analyst, Product Manager, Associate Product Manager, \n"
            + "Technical Product Manager, UX Designer, UI Designer, UX Researcher, Product Designer, "
            + "Interaction Designer, Creative Director, Industrial Designer, Marketing Specialist, \n"
            + "Digital Marketing Manager, SEO Specialist, Content Marketer, Growth Marketing Manager, "
            + "Brand Manager, Sales Associate, Account Executive, Account Manager, \n"
            + "Business Development Representative, Sales Manager, Partnership Manager, Social Media Manager, "
            + "Customer Success Manager, Operations Manager, Chief Executive Officer, Chief Operating Officer, \n"
            + "Chief Marketing Officer, Chief Technology Officer, Project Manager, Program Manager, "
            + "Strategy Consultant, Business Consultant, General Manager, HR Coordinator, \n"
            + "Recruitment Specialist, Office Manager, Executive Assistant, Administrative Assistant, "
            + "Talent Acquisition Specialist, HR Generalist, Payroll Specialist, Customer Support Representative, \n"
            + "Call Center Agent, IT Technician, Network Administrator, Systems Administrator, "
            + "Cybersecurity Analyst, Information Security Analyst, IT Support Specialist, IT Project Manager, \n"
            + "Cloud Engineer, Network Engineer, DevSecOps Engineer, Healthcare Administrator, "
            + "Registered Nurse, Medical Assistant, Physical Therapist, Pharmacist, \n"
            + "Clinical Research Coordinator, Mental Health Counselor, Medical Billing Specialist, Legal Advisor, "
            + "Paralegal, Corporate Lawyer, Compliance Officer, Auditor, \n"
            + "Accountant, Bookkeeper, Tax Analyst, Financial Planner, "
            + "Investment Analyst, Electrician, Plumber, Welder, \n"
            + "Construction Worker, Warehouse Manager, Logistics Coordinator, Supply Chain Manager, "
            + "Procurement Specialist, Quality Assurance Tester, Field Service Technician, Graphic Designer, \n"
            + "Content Writer, Copywriter, Technical Writer, Video Editor, "
            + "Motion Graphics Designer, Photographer, Art Director, Animator, \n"
            + "Editor, Journalist, Podcast Producer.";



    public static final String MESSAGE_CONSTRAINTS = "Job title must contain alphabetic characters. \n"
            + "It should not be blank. \n"
            + "\n"
            + "Here is the list of Job Titles: \n"
            + jobTitles;

    private static final Set<String> VALID_JOB_TITLES = Set.of(
            "Software Engineer", "DevOps Engineer", "Front-End Developer", "Back-End Developer",
            "Full-Stack Developer", "Machine Learning Engineer", "Data Engineer", "Electrical Engineer",
            "Mechanical Engineer", "Civil Engineer", "Robotics Engineer", "Systems Engineer",
            "Embedded Systems Developer", "Test Automation Engineer", "Aerospace Engineer", "Chemical Engineer",
            "Hardware Engineer", "Biomedical Engineer", "Data Analyst", "Business Intelligence Analyst",
            "Data Scientist", "Financial Analyst", "Quantitative Analyst", "Risk Analyst",
            "Market Research Analyst", "Statistician", "Data Architect", "Database Administrator",
            "Pricing Analyst", "Operations Research Analyst", "Product Manager", "Associate Product Manager",
            "Technical Product Manager", "UX Designer", "UI Designer", "UX Researcher", "Product Designer",
            "Interaction Designer", "Creative Director", "Industrial Designer", "Marketing Specialist",
            "Digital Marketing Manager", "SEO Specialist", "Content Marketer", "Growth Marketing Manager",
            "Brand Manager", "Sales Associate", "Account Executive", "Account Manager",
            "Business Development Representative", "Sales Manager", "Partnership Manager", "Social Media Manager",
            "Customer Success Manager", "Operations Manager", "Chief Executive Officer", "Chief Operating Officer",
            "Chief Marketing Officer", "Chief Technology Officer", "Project Manager", "Program Manager",
            "Strategy Consultant", "Business Consultant", "General Manager", "HR Coordinator", "Recruitment Specialist",
            "Office Manager", "Executive Assistant", "Administrative Assistant", "Talent Acquisition Specialist",
            "HR Generalist", "Payroll Specialist", "Customer Support Representative", "Call Center Agent",
            "IT Technician", "Network Administrator", "Systems Administrator", "Cybersecurity Analyst",
            "Information Security Analyst", "IT Support Specialist", "IT Project Manager", "Cloud Engineer",
            "Network Engineer", "DevSecOps Engineer", "Healthcare Administrator", "Registered Nurse",
            "Medical Assistant", "Physical Therapist", "Pharmacist", "Clinical Research Coordinator",
            "Mental Health Counselor", "Medical Billing Specialist", "Legal Advisor", "Paralegal",
            "Corporate Lawyer", "Compliance Officer", "Auditor", "Accountant", "Bookkeeper",
            "Tax Analyst", "Financial Planner", "Investment Analyst", "Electrician", "Plumber",
            "Welder", "Construction Worker", "Warehouse Manager", "Logistics Coordinator",
            "Supply Chain Manager", "Procurement Specialist", "Quality Assurance Tester",
            "Field Service Technician", "Graphic Designer", "Content Writer", "Copywriter",
            "Technical Writer", "Video Editor", "Motion Graphics Designer", "Photographer",
            "Art Director", "Animator", "Editor", "Journalist", "Podcast Producer"
    );


    public final String value;

    /**
     * Constructs an {@code Job Title}.
     *
     * @param jobTitle A valid job title.
     */
    public JobTitle(String jobTitle) {
        requireNonNull(jobTitle);
        checkArgument(isValidJobTitle(jobTitle), MESSAGE_CONSTRAINTS);
        value = mapInput(jobTitle);
    }

    private static String normalizeWhitespace(String input) {
        return input.trim().replaceAll("\\s+", " ");
    }

    /**
     * Returns true if a given string is a valid job title.
     */
    public static boolean isValidJobTitle(String jobTitle) {
        String normalizedInput = normalizeWhitespace(jobTitle.toLowerCase());

        return VALID_JOB_TITLES.stream()
                .map(validJobTitle -> normalizeWhitespace(validJobTitle.toLowerCase()))
                .toList()
                .contains(normalizedInput);
    }

    /**
     * Maps given string to a string in VALID_JOB_TITLES
     */
    public static String mapInput(String jobTitle) {
        String normalizedInput = normalizeWhitespace(jobTitle.toLowerCase());

        for (String validJobTitle : VALID_JOB_TITLES) {
            if (normalizeWhitespace(validJobTitle.toLowerCase()).equals(normalizedInput)) {
                return validJobTitle;
            }
        }

        throw new IllegalArgumentException("Invalid job title: " + jobTitle);
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
        if (!(other instanceof seedu.address.model.tag.JobTitle)) {
            return false;
        }

        seedu.address.model.tag.JobTitle otherJobTitle = (seedu.address.model.tag.JobTitle) other;
        return value.equals(otherJobTitle.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}


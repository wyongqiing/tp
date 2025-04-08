package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.Messages;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Address;
import seedu.address.model.person.DateOfJoining;
import seedu.address.model.person.Dob;
import seedu.address.model.person.Email;
import seedu.address.model.person.Gender;
import seedu.address.model.person.Name;
import seedu.address.model.person.Nationality;
import seedu.address.model.person.Nric;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Department;
import seedu.address.model.tag.EmploymentType;
import seedu.address.model.tag.JobTitle;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex, String commandUsage) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();

        try {
            long parsedIndex = Long.parseLong(trimmedIndex);

            if (parsedIndex > Integer.MAX_VALUE || parsedIndex < Integer.MIN_VALUE) {
                throw new ParseException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
            }

        } catch (NumberFormatException e) {
            if (!trimmedIndex.matches("\\d+")) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, commandUsage), e);
            } else {
                throw new ParseException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX, e);
            }
        }

        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static Name parseName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(trimmedName);
    }

    /**
     * Parses a {@code String phone} into a {@code Phone}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code phone} is invalid.
     */
    public static Phone parsePhone(String phone) throws ParseException {
        requireNonNull(phone);
        String trimmedPhone = phone.trim();
        if (!Phone.isValidPhone(trimmedPhone)) {
            throw new ParseException(Phone.MESSAGE_CONSTRAINTS);
        }
        return new Phone(trimmedPhone);
    }

    /**
     * Parses a {@code String address} into an {@code Address}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code address} is invalid.
     */
    public static Address parseAddress(String address) throws ParseException {
        requireNonNull(address);
        String trimmedAddress = address.trim();
        if (!Address.isValidAddress(trimmedAddress)) {
            throw new ParseException(Address.MESSAGE_CONSTRAINTS);
        }
        return new Address(trimmedAddress);
    }

    /**
     * Parses a {@code String email} into an {@code Email}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code email} is invalid.
     */
    public static Email parseEmail(String email) throws ParseException {
        requireNonNull(email);
        String trimmedEmail = email.trim();
        if (!Email.isValidEmail(trimmedEmail)) {
            throw new ParseException(Email.MESSAGE_CONSTRAINTS);
        }
        return new Email(trimmedEmail);
    }

    /**
     * Parses a {@code String nric} into an {@code Nric}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code nric} is invalid.
     */
    public static Nric parseNric(String nric) throws ParseException {
        requireNonNull(nric);
        String trimmedNric = nric.trim();
        if (!Nric.isValidNric(trimmedNric)) {
            throw new ParseException(Nric.MESSAGE_CONSTRAINTS);
        }
        return new Nric(trimmedNric);
    }

    /**
     * Parses a {@code String gender} into an {@code Gender}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code gender} is invalid.
     */
    public static Gender parseGender(String gender) throws ParseException {
        requireNonNull(gender);
        String trimmedGender = gender.trim();
        if (!Gender.isValidGender(trimmedGender)) {
            throw new ParseException(Gender.MESSAGE_CONSTRAINTS);
        }
        return new Gender(trimmedGender);
    }

    /**
     * Parses a {@code String dob} into an {@code Dob}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code dob} is invalid.
     */
    public static Dob parseDob(String dob) throws ParseException {
        requireNonNull(dob);
        String trimmedDob = dob.trim();

        if (!Dob.isValidDob(trimmedDob)) {
            throw new ParseException(Dob.MESSAGE_CONSTRAINTS);
        }
        if (!Dob.isValidDate(trimmedDob)) {
            throw new ParseException(Dob.DATE_INVALID_MESSAGE);
        }

        List<String> formats = List.of(
                "dd-MMM-yyyy",
                "dd/MM/yyyy",
                "dd.MM.yyyy",
                "yyyy-MM-dd",
                "dd-MM-yyyy"
        );


        Date parsedDate = null;
        java.text.ParseException lastException = null;

        for (String format : formats) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.ENGLISH);
                sdf.setLenient(false); // Strict parsing
                parsedDate = sdf.parse(trimmedDob);
                break; // Stop once a valid format is found
            } catch (java.text.ParseException e) {
                lastException = e;
            }
        }

        if (parsedDate == null) {
            throw new ParseException(Dob.DATE_INVALID_MESSAGE);
        }

        // Check if date is in the future
        Date today = new Date();
        if (parsedDate.after(today)) {
            throw new ParseException(Dob.FUTURE_DATE_INVALID);
        }

        // Always return the date in "dd-MMM-yyyy" format
        SimpleDateFormat outputFormat = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
        return new Dob(outputFormat.format(parsedDate));
    }

    /**
     * Parses a {@code String dateOfJoining} into an {@code DateOfJoining}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code dateOfJoining} is invalid.
     */
    public static DateOfJoining parseDate(String dateOfJoining) throws ParseException {
        requireNonNull(dateOfJoining);
        String trimmedDate = dateOfJoining.trim();

        if (!DateOfJoining.isValidDate(trimmedDate)) {
            throw new ParseException(DateOfJoining.MESSAGE_CONSTRAINTS);
        }

        if (!DateOfJoining.isAValidDate(trimmedDate)) {
            throw new ParseException(DateOfJoining.DATE_INVALID_MESSAGE);
        }

        List<String> formats = List.of(
                "dd-MMM-yyyy",
                "dd/MM/yyyy",
                "dd.MM.yyyy",
                "yyyy-MM-dd",
                "dd-MM-yyyy"
        );

        Date parsedDate = null;
        java.text.ParseException lastException = null;

        for (String format : formats) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.ENGLISH);
                sdf.setLenient(false); // Strict parsing
                parsedDate = sdf.parse(trimmedDate);
                break; // Stop once a valid format is found
            } catch (java.text.ParseException e) {
                lastException = e;
            }
        }

        if (parsedDate == null) {
            throw new ParseException(DateOfJoining.DATE_INVALID_MESSAGE);
        }

        // Always return the date in "dd-MMM-yyyy" format
        SimpleDateFormat outputFormat = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
        return new DateOfJoining(outputFormat.format(parsedDate));
    }

    /**
     * Parses a {@code String nationality} into an {@code Nationality}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code nationality} is invalid.
     */
    public static Nationality parseNationality(String nationality) throws ParseException {
        requireNonNull(nationality);
        String trimmedNationality = nationality.trim();
        if (!Nationality.isValidNationality(trimmedNationality)) {
            throw new ParseException(Nationality.MESSAGE_CONSTRAINTS);
        }
        return new Nationality(trimmedNationality);
    }

    /**
     * Parses a {@code String tag} into a {@code Tag}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code tag} is invalid.
     */
    public static Tag parseTag(String tags) throws ParseException {
        try {
            requireNonNull(tags);
            String trimmedTags = tags.trim();

            // Check if the input is empty after trimming
            if (trimmedTags.isEmpty()) {
                throw new ParseException(Tag.MESSAGE_CONSTRAINTS);
            }

            String[] tagList = trimmedTags.split("/");

            // Check for incorrect number of components or ends with /
            if (tagList.length != 3 || trimmedTags.endsWith("/")) {
                throw new ParseException(Tag.MESSAGE_CONSTRAINTS);
            }

            // Check if any component is empty
            for (String tagComponent : tagList) {
                if (tagComponent.trim().isEmpty()) {
                    throw new ParseException(Tag.MESSAGE_CONSTRAINTS);
                }
            }

            // Validate and parse each component
            if (!Department.isValidDepartmentInput(tagList[0].trim())) {
                throw new ParseException(Department.MESSAGE_CONSTRAINTS);
            }

            if (!EmploymentType.isValidEmploymentType(tagList[1].trim())) {
                throw new ParseException(EmploymentType.MESSAGE_CONSTRAINTS);
            }

            if (!JobTitle.isValidJobTitle(tagList[2].trim())) {
                throw new ParseException(JobTitle.MESSAGE_CONSTRAINTS);
            }

            Department department = new Department(tagList[0].trim());
            EmploymentType employmentType = new EmploymentType(tagList[1].trim());
            JobTitle jobTitle = new JobTitle(tagList[2].trim());

            return new Tag(department, employmentType, jobTitle);
        } catch (NullPointerException e) {
            throw e;
        } catch (ParseException pe) {
            throw pe;
        } catch (Exception e) {
            throw new ParseException(e.getMessage());
        }
    }
}



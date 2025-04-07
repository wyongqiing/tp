package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

/**
 * Represents a Person's dateOfJoining in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidDate(String)}
 */
public class DateOfJoining {

    public static final String MESSAGE_CONSTRAINTS =
            "Format of DateOfJoining should be one of the following: dd-MMM-yyyy, dd/MM/yyyy, dd.MM.yyyy, yyyy-MM-dd, "
                    + "dd-MM-yyyy. DateOfJoining should not be blank.";

    public static final String VALIDATION_REGEX =
            "^(\\d{2}-[A-Za-z]{3}-\\d{4}"
                    + "|\\d{2}/\\d{2}/\\d{4}"
                    + "|\\d{2}\\.\\d{2}\\.\\d{4}"
                    + "|\\d{4}-\\d{2}-\\d{2}"
                    + "|\\d{2}-\\d{2}-\\d{4})$";


    public static final String DATE_INVALID_MESSAGE = "The date given for DateOfJoining is invalid.";

    private static final DateTimeFormatter[] DATE_FORMATTERS = new DateTimeFormatter[] {
            DateTimeFormatter.ofPattern("dd-MMM-yyyy").withLocale(Locale.ENGLISH),
            DateTimeFormatter.ofPattern("dd/MM/yyyy"),
            DateTimeFormatter.ofPattern("dd.MM.yyyy"),
            DateTimeFormatter.ofPattern("dd-MM-yyyy"),
            DateTimeFormatter.ofPattern("yyyy-MM-dd")
    };

    public final String value;

    /**
     * Constructs an {@code DateOfJoining}
     *
     * @param dateOfJoining A valid DateOfJoining.
     */
    public DateOfJoining(String dateOfJoining) {
        requireNonNull(dateOfJoining);
        checkArgument(isValidDate(dateOfJoining), MESSAGE_CONSTRAINTS);
        checkArgument(isAValidDate(dateOfJoining), DATE_INVALID_MESSAGE);
        value = dateOfJoining;
    }

    /**
     * Returns if a given string is a valid date.
     */
    public static boolean isValidDate(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns if a given string is a valid date.
     */
    public static boolean isAValidDate(String test) {
        for (DateTimeFormatter formatter : DATE_FORMATTERS) {
            try {
                LocalDate.parse(test, formatter);
                return true;
            } catch (DateTimeParseException e) {
                //
            }
        }
        return false;
    }

    /**
     * For converting to local date to compare with DOB
     */
    public LocalDate toLocalDate() {
        for (DateTimeFormatter formatter : DATE_FORMATTERS) {
            try {
                return LocalDate.parse(value, formatter);
            } catch (DateTimeParseException e) {
                // try next formatter
            }
        }
        throw new IllegalStateException("Invalid date format stored in value.");
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

        if (!(other instanceof DateOfJoining)) {
            return false;
        }

        DateOfJoining otherDate = (DateOfJoining) other;
        return value.equals(otherDate.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}

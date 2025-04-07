package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

/**
 * Represents a Person's dob in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidDob(String)}
 */
public class Dob {

    public static final String MESSAGE_CONSTRAINTS =
            "Format of DOB should be one of the following: dd-MMM-yyyy, dd/MM/yyyy, dd.MM.yyyy, yyyy-MM-dd, "
                   + "dd-MM-yyyy. DOB should not be blank.";

    public static final String VALIDATION_REGEX =
            "^(\\d{2}-[A-Za-z]{3}-\\d{4}"
                  + "|\\d{2}/\\d{2}/\\d{4}"
                  + "|\\d{2}\\.\\d{2}\\.\\d{4}"
                  + "|\\d{4}-\\d{2}-\\d{2}"
                  + "|\\d{2}-\\d{2}-\\d{4})$";


    public static final String DATE_INVALID_MESSAGE = "The date given for DOB is invalid.";
    public static final String FUTURE_DATE_INVALID = "DOB cannot be in the future.";

    private static final DateTimeFormatter[] DATE_FORMATTERS = new DateTimeFormatter[] {
            DateTimeFormatter.ofPattern("dd-MMM-yyyy").withLocale(Locale.ENGLISH),
            DateTimeFormatter.ofPattern("dd/MM/yyyy"),
            DateTimeFormatter.ofPattern("dd.MM.yyyy"),
            DateTimeFormatter.ofPattern("dd-MM-yyyy"),
            DateTimeFormatter.ofPattern("yyyy-MM-dd")
    };

    public final String value;

    /**
     * Constructs an {@code Dob}
     *
     * @param dob A valid Dob.
     */
    public Dob(String dob) {
        requireNonNull(dob);
        checkArgument(isValidDob(dob), MESSAGE_CONSTRAINTS);
        checkArgument(isValidDate(dob), DATE_INVALID_MESSAGE);
        value = dob;
    }

    /**
     * Returns if a given string is a valid dob.
     */
    public static boolean isValidDob(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns if a given string is a valid date.
     */
    public static boolean isValidDate(String test) {
        for (DateTimeFormatter formatter : DATE_FORMATTERS) {
            try {
                LocalDate date = LocalDate.parse(test, formatter);
                if (date != null) {
                    return true; // Valid date
                }
                return true;
            } catch (DateTimeParseException e) {
                // Try next formatter
            }
        }
        return false;
    }

    /**
     * For converting to local date to compare with DateOfJoining
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

        if (!(other instanceof Dob)) {
            return false;
        }

        Dob otherDob = (Dob) other;
        return otherDob.value.equals(value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}

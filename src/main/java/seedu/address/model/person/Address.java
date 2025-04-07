package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's address in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidAddress(String)}
 */
public class Address {
    public static final String MESSAGE_CONSTRAINTS = "Addresses should follow the format: [address]/[postal code]\n"
            + "where [address] is the street address and [postal code] is a valid 6-digit Singapore postal code.\n"
            + "The first two digits of the postal code must be a valid Singapore postal district (01-28).\n"
            + "Example: Blk 123 Clementi Avenue 3/123456";

    // Regex to validate the full address format: any text, followed by slash (with optional spaces), then 6 digits
    private static final String ADDRESS_VALIDATION_REGEX = ".+\\s*/\\s*\\d{6}$";

    // Valid first two digits of Singapore postal codes
    private static final String[] VALID_POSTAL_PREFIXES = {
        "01", "02", "03", "04", "05", "06", "07", "08", "09", "10",
        "11", "12", "13", "14", "15", "16", "17", "18", "19", "20",
        "21", "22", "23", "24", "25", "26", "27", "28"
    };

    public final String value;

    /**
     * Constructs an {@code Address}.
     *
     * @param address A valid address.
     */
    public Address(String address) {
        requireNonNull(address);
        checkArgument(isValidAddress(address), MESSAGE_CONSTRAINTS);
        // Normalize the address by removing excess whitespace around the slash
        value = normalizeAddress(address);
    }

    /**
     * Normalizes the address by removing excess whitespace around the slash separator.
     */
    private String normalizeAddress(String address) {
        // Split by slash, trim whitespace from each part, then rejoin
        String[] parts = address.split("/");
        if (parts.length == 2) {
            return parts[0].trim() + "/" + parts[1].trim();
        }
        return address.trim();
    }

    /**
     * Returns true if a given string is a valid address with valid Singapore postal code.
     */
    public static boolean isValidAddress(String test) {
        // Check if the address matches the basic format (allowing whitespace around the slash)
        if (!test.matches(ADDRESS_VALIDATION_REGEX)) {
            return false;
        }

        // Extract the postal code (last 6 digits)
        String[] parts = test.split("/");
        String postalCodePart = parts[1].trim();
        String postalCode = postalCodePart.replaceAll("\\s+", "");

        // Check if postal code is 6 digits
        if (!postalCode.matches("\\d{6}")) {
            return false;
        }

        String postalPrefix = postalCode.substring(0, 2);

        // Check if the first two digits are a valid postal district
        for (String prefix : VALID_POSTAL_PREFIXES) {
            if (prefix.equals(postalPrefix)) {
                return true;
            }
        }

        return false;
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

        if (!(other instanceof Address)) {
            return false;
        }

        Address otherAddress = (Address) other;
        return value.equals(otherAddress.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}


package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.tag.Tag;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;
    private final Nric nric;

    // Data fields
    private final Gender gender;
    private final Dob dob;
    private final DateOfJoining dateOfJoining;
    private final Nationality nationality;
    private final Address address;
    private final Tag tag;

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Email email, Nric nric, Gender gender, Dob dob, DateOfJoining dateOfJoining,
                  Nationality nationality, Address address, Tag tag) {
        requireAllNonNull(name, phone, email, gender, dob, dateOfJoining, nationality, address, tag);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.nric = nric;
        this.gender = gender;
        this.dob = dob;
        this.dateOfJoining = dateOfJoining;
        this.nationality = nationality;
        this.address = address;
        this.tag = tag;
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Nric getNric() {
        return nric;
    }

    public Gender getGender() {
        return gender;
    }

    public Dob getDob() {
        return dob;
    }

    public DateOfJoining getDateOfJoining() {
        return dateOfJoining;
    }

    public Nationality getNationality() {
        return nationality;
    }

    public Address getAddress() {
        return address;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Tag getTag() {
        return tag;
    }

    /**
     * Returns true if both persons have the same name.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
                && otherPerson.getName().equals(getName())
                && otherPerson.getNric().equals(getNric());
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Person)) {
            return false;
        }

        Person otherPerson = (Person) other;
        return name.equals(otherPerson.name)
                && phone.equals(otherPerson.phone)
                && email.equals(otherPerson.email)
                && nric.equals(otherPerson.nric)
                && gender.equals(otherPerson.gender)
                && dob.equals(otherPerson.dob)
                && dateOfJoining.equals(otherPerson.dateOfJoining)
                && nationality.equals(otherPerson.nationality)
                && address.equals(otherPerson.address)
                && tag.equals(otherPerson.tag);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, nric, gender, dob, dateOfJoining, nationality, address, tag);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("phone", phone)
                .add("email", email)
                .add("nric", nric)
                .add("gender", gender)
                .add("dob", dob)
                .add("dateOfJoining", dateOfJoining)
                .add("nationality", nationality)
                .add("address", address)
                .add("tags", tag)
                .toString();
    }

}

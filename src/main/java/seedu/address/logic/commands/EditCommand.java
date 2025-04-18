package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DOB;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GENDER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NATIONALITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Address;
import seedu.address.model.person.DateOfJoining;
import seedu.address.model.person.Dob;
import seedu.address.model.person.Email;
import seedu.address.model.person.Gender;
import seedu.address.model.person.Name;
import seedu.address.model.person.Nationality;
import seedu.address.model.person.Note;
import seedu.address.model.person.Nric;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.ProfileContainsKeywordsPredicate;
import seedu.address.model.tag.Tag;

/**
 * Edits the details of an existing person in the address book.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the person identified "
            + "by the index number used in the displayed person list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_GENDER + "GENDER] "
            + "[" + PREFIX_DOB + "DOB] "
            + "[" + PREFIX_DATE + "DATE OF JOINING] "
            + "[" + PREFIX_NATIONALITY + "NATIONALITY] "
            + "[" + PREFIX_ADDRESS + "ADDRESS] "
            + "[" + PREFIX_TAG + "DEPARTMENT/EMPLOYMENT TYPE/JOB TITLE]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_PHONE + "91234567 "
            + PREFIX_EMAIL + "johndoe@example.com";

    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Edited Person: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the address book.";
    public static final String MESSAGE_NOT_CHANGED = "The field(s) provided to edit is already present.";

    private ProfileContainsKeywordsPredicate predicate;

    private final Index index;
    private final EditPersonDescriptor editPersonDescriptor;

    /**
     * @param index of the person in the filtered person list to edit
     * @param editPersonDescriptor details to edit the person with
     */
    public EditCommand(Index index, EditPersonDescriptor editPersonDescriptor) {
        requireNonNull(index);
        requireNonNull(editPersonDescriptor);

        this.index = index;
        this.editPersonDescriptor = new EditPersonDescriptor(editPersonDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        if (!editPersonDescriptor.isAnyFieldEdited()) {
            throw new CommandException(EditCommand.MESSAGE_NOT_EDITED);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());
        Person editedPerson = createEditedPerson(personToEdit, editPersonDescriptor);
        this.predicate = new ProfileContainsKeywordsPredicate(Arrays.asList(editedPerson.getName().toString()));

        if (!personToEdit.isSamePerson(editedPerson) && model.hasPerson(editedPerson)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        if (personToEdit.equals(editedPerson)) {
            throw new CommandException(MESSAGE_NOT_CHANGED);
        }

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(predicate);
        return new CommandResult(String.format(MESSAGE_EDIT_PERSON_SUCCESS, Messages.format(editedPerson)));
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    private static Person createEditedPerson(Person personToEdit, EditPersonDescriptor editPersonDescriptor)
            throws CommandException {
        assert personToEdit != null;

        Name updatedName = editPersonDescriptor.getName().orElse(personToEdit.getName());
        Phone updatedPhone = editPersonDescriptor.getPhone().orElse(personToEdit.getPhone());
        Email updatedEmail = editPersonDescriptor.getEmail().orElse(personToEdit.getEmail());
        Nric updatedNric = personToEdit.getNric();
        Gender updatedGender = editPersonDescriptor.getGender().orElse(personToEdit.getGender());
        Dob updatedDob = editPersonDescriptor.getDob().orElse(personToEdit.getDob());
        DateOfJoining updatedDate = editPersonDescriptor.getDateOfJoining().orElse(personToEdit.getDateOfJoining());
        Nationality updatedNationality = editPersonDescriptor.getNationality().orElse(personToEdit.getNationality());
        Address updatedAddress = editPersonDescriptor.getAddress().orElse(personToEdit.getAddress());
        Tag updatedTag = editPersonDescriptor.getTag().orElse(personToEdit.getTag());
        Note updatedNote = editPersonDescriptor.getNote().orElse(personToEdit.getNote());

        if (!updatedDate.toLocalDate().isAfter(updatedDob.toLocalDate())) {
            throw new CommandException("Date of Joining must be after DOB.");
        }

        return new Person(updatedName, updatedPhone, updatedEmail, updatedNric, updatedGender, updatedDob, updatedDate,
                updatedNationality, updatedAddress, updatedNote, updatedTag);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditCommand)) {
            return false;
        }

        EditCommand otherEditCommand = (EditCommand) other;
        return index.equals(otherEditCommand.index)
                && editPersonDescriptor.equals(otherEditCommand.editPersonDescriptor);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("index", index)
                .add("editPersonDescriptor", editPersonDescriptor)
                .toString();
    }

    /**
     * Stores the details to edit the person with. Each non-empty field value will replace the
     * corresponding field value of the person.
     */
    public static class EditPersonDescriptor {
        private Name name;
        private Phone phone;
        private Email email;
        private Gender gender;
        private Dob dob;
        private DateOfJoining dateOfJoining;
        private Nationality nationality;
        private Address address;
        private Note note;
        private Tag tag;

        public EditPersonDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditPersonDescriptor(EditPersonDescriptor toCopy) {
            setName(toCopy.name);
            setPhone(toCopy.phone);
            setEmail(toCopy.email);
            setGender(toCopy.gender);
            setDob(toCopy.dob);
            setDateOfJoining(toCopy.dateOfJoining);
            setNationality(toCopy.nationality);
            setAddress(toCopy.address);
            setNote(toCopy.note);
            setTag(toCopy.tag);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, phone, email, gender, dob, dateOfJoining,
                    nationality, address, tag);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setPhone(Phone phone) {
            this.phone = phone;
        }

        public Optional<Phone> getPhone() {
            return Optional.ofNullable(phone);
        }

        public void setEmail(Email email) {
            this.email = email;
        }

        public Optional<Email> getEmail() {
            return Optional.ofNullable(email);
        }

        public void setGender(Gender gender) {
            this.gender = gender;
        }

        public Optional<Gender> getGender() {
            return Optional.ofNullable(gender);
        }

        public void setDob(Dob dob) {
            this.dob = dob;
        }

        public Optional<Dob> getDob() {
            return Optional.ofNullable(dob);
        }

        public void setDateOfJoining(DateOfJoining dateOfJoining) {
            this.dateOfJoining = dateOfJoining;
        }

        public Optional<DateOfJoining> getDateOfJoining() {
            return Optional.ofNullable(dateOfJoining);
        }

        public void setNationality(Nationality nationality) {
            this.nationality = nationality;
        }

        public Optional<Nationality> getNationality() {
            return Optional.ofNullable(nationality);
        }

        public void setAddress(Address address) {
            this.address = address;
        }

        public Optional<Address> getAddress() {
            return Optional.ofNullable(address);
        }

        public void setNote(Note note) {
            this.note = note;
        }

        public Optional<Note> getNote() {
            return Optional.ofNullable(note);
        }

        public void setTag(Tag tag) {
            this.tag = tag;
        }

        public Optional<Tag> getTag() {
            return Optional.ofNullable(tag);
        }


        @Override
        public boolean equals(Object other) {

            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditPersonDescriptor)) {
                return false;
            }

            EditPersonDescriptor otherEditPersonDescriptor = (EditPersonDescriptor) other;
            return Objects.equals(name, otherEditPersonDescriptor.name)
                    && Objects.equals(phone, otherEditPersonDescriptor.phone)
                    && Objects.equals(email, otherEditPersonDescriptor.email)
                    && Objects.equals(gender, otherEditPersonDescriptor.gender)
                    && Objects.equals(dob, otherEditPersonDescriptor.dob)
                    && Objects.equals(dateOfJoining, otherEditPersonDescriptor.dateOfJoining)
                    && Objects.equals(nationality, otherEditPersonDescriptor.nationality)
                    && Objects.equals(address, otherEditPersonDescriptor.address)
                    && Objects.equals(note, otherEditPersonDescriptor.note)
                    && Objects.equals(tag, otherEditPersonDescriptor.tag);
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .add("name", name)
                    .add("phone", phone)
                    .add("email", email)
                    .add("gender", gender)
                    .add("dob", dob)
                    .add("dateOfJoining", dateOfJoining)
                    .add("nationality", nationality)
                    .add("address", address)
                    .add("note", note)
                    .add("tag", tag)
                    .toString();
        }
    }
}

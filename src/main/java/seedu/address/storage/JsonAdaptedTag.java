package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.tag.Department;
import seedu.address.model.tag.EmploymentType;
import seedu.address.model.tag.JobTitle;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Tag}.
 */
class JsonAdaptedTag {

    private final String[] tag;

    /**
     * Constructs a {@code JsonAdaptedTag} with the given {@code tagName}.
     */
    @JsonCreator
    public JsonAdaptedTag(String[] tag) {
        this.tag = tag;
    }

    /**
     * Converts a given {@code Tag} into this class for Jackson use.
     */
    public JsonAdaptedTag(Tag source) {
        this.tag = source.getValue();
    }

    @JsonValue
    public String[] getTagName() {
        return this.tag;
    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code Tag} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted tag.
     */
    public Tag toModelType() throws IllegalValueException {
        if (!Tag.isValidTagName(this.tag)) {
            throw new IllegalValueException(Tag.MESSAGE_CONSTRAINTS);
        }
        Department department = new Department(tag[0]);
        EmploymentType employmentType = new EmploymentType(tag[1]);
        JobTitle jobTitle = new JobTitle(tag[2]);
        return new Tag(department, employmentType, jobTitle);
    }

}

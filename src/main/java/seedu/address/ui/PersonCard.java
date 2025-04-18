package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class PersonCard extends UiPart<Region> {

    private static final String FXML = "PersonListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Person person;
    @FXML
    private Label note;
    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label phone;
    @FXML
    private Label address;
    @FXML
    private Label email;
    @FXML
    private Label nric;
    @FXML
    private Label gender;
    @FXML
    private Label dob;
    @FXML
    private Label dateOfJoining;
    @FXML
    private Label nationality;
    @FXML
    private HBox tagBox;
    @FXML
    private FlowPane department;
    @FXML
    private FlowPane employmentType;
    @FXML
    private FlowPane jobTitle;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public PersonCard(Person person, int displayedIndex) {
        super(FXML);
        this.person = person;
        id.setText(displayedIndex + ". ");
        name.setText(person.getName().fullName);
        phone.setText(person.getPhone().value);
        address.setText(person.getAddress().value);
        note.setText(person.getNote().value);
        nric.setText(person.getNric().value);
        gender.setText(person.getGender().value);
        dob.setText(person.getDob().value);
        dateOfJoining.setText(person.getDateOfJoining().value);
        nationality.setText(person.getNationality().value);
        email.setText(person.getEmail().value);

        Tag tag = person.getTag();

        if (tag != null) {
            String[] tags = tag.getValue();
            tagBox.getChildren().clear();
            department.getChildren().clear();
            department.getChildren().add(new Label("Department: " + tags[0]));
            employmentType.getChildren().clear();
            employmentType.getChildren().add(new Label("Employment Type: " + tags[1]));
            jobTitle.getChildren().clear();
            jobTitle.getChildren().add(new Label("Job Title: " + tags[2]));
            tagBox.getChildren().addAll(department, employmentType, jobTitle);
        }
    }

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public PersonCard(Person person, int displayedIndex, boolean placeholder) {
        super(FXML);
        this.person = person;
        id.setText(displayedIndex + ". ");
        name.setText(person.getName().fullName);
        note.setText(person.getNote().value);
        phone.setVisible(false);
        phone.setManaged(false);
        phone.setPrefHeight(0);
        phone.setMinHeight(0);
        phone.setMaxHeight(0);
        address.setVisible(false);
        address.setManaged(false);
        address.setPrefHeight(0);
        address.setMinHeight(0);
        address.setMaxHeight(0);
        nric.setVisible(false);
        nric.setManaged(false);
        nric.setPrefHeight(0);
        nric.setMinHeight(0);
        nric.setMaxHeight(0);
        gender.setVisible(false);
        gender.setManaged(false);
        gender.setPrefHeight(0);
        gender.setMinHeight(0);
        gender.setMaxHeight(0);
        dob.setVisible(false);
        dob.setManaged(false);
        dob.setPrefHeight(0);
        dob.setMinHeight(0);
        dob.setMaxHeight(0);
        dateOfJoining.setVisible(false);
        dateOfJoining.setManaged(false);
        dateOfJoining.setPrefHeight(0);
        dateOfJoining.setMinHeight(0);
        dateOfJoining.setMaxHeight(0);
        nationality.setVisible(false);
        nationality.setManaged(false);
        nationality.setPrefHeight(0);
        nationality.setMinHeight(0);
        nationality.setMaxHeight(0);
        email.setVisible(false);
        email.setManaged(false);
        email.setPrefHeight(0);
        email.setMinHeight(0);
        email.setMaxHeight(0);

        Tag tag = person.getTag();

        if (tag != null) {
            String[] tags = tag.getValue();
            tagBox.getChildren().clear();
            department.getChildren().clear();
            department.getChildren().add(new Label("Department: " + tags[0]));
            employmentType.getChildren().clear();
            employmentType.getChildren().add(new Label("Employment Type: " + tags[1]));
            jobTitle.getChildren().clear();
            jobTitle.getChildren().add(new Label("Job Title: " + tags[2]));
            tagBox.getChildren().addAll(department, employmentType, jobTitle);
        }
    }

    public FlowPane getDepartment() {
        return department;
    }

    public FlowPane getEmploymentType() {
        return employmentType;
    }

    public FlowPane getJobTitle() {
        return jobTitle;
    }
}

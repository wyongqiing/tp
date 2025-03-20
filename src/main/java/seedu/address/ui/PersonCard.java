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
}

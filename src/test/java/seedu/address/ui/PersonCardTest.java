package seedu.address.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import seedu.address.model.person.Person;
import seedu.address.model.util.SampleDataUtil;

public class PersonCardTest {

    @Test
    public void constructor() {
        Person person = SampleDataUtil.getSamplePersons()[0];
        PersonCard personCard = new PersonCard(person, 1);

        FlowPane department = personCard.getDepartment();
        Label departmentText = (Label) department.getChildren().get(0);
        assertEquals("Department: Human Resources", departmentText.getText());

        FlowPane employmentType = personCard.getEmploymentType();
        Label employmentTypeText = (Label) employmentType.getChildren().get(0);
        assertEquals("Employment Type: Full-Time", employmentTypeText.getText());

        FlowPane jobTitle = personCard.getJobTitle();
        Label jobTitleText = (Label) jobTitle.getChildren().get(0);
        assertEquals("Job Title: HR Coordinator", jobTitleText.getText());
    }
}
